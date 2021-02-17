package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")											//사용자가 url을 통해서 calculaotr를 요청하게되면 Get 요청을해도 service가 실행되고 POST를 요청해도 service가 실행됐다. 그런요청을 GET POST요청을 구분해야할경우에는 나눌것인가 
																	//2가지 방식으로 알아볼것이다. 1. 서비스함수에서 구분짓는 방법 2.GET요청과 POST요청에 특화된 메서드를 활용해보는 방법
public class Calculator extends HttpServlet {						//서블릿으로 만들기 위해 서블릿 클래스 상속받기 

	@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies =request.getCookies();	
		
		String exp ="0";														
		if(cookies != null)		//문제는 쿠키가 Null일경우도 있기때문에 브라우저에 쿠키가 아예없으면 Null로반환
			for(Cookie c : cookies)					
				if(c.getName().equals("exp")){			
					exp= c.getValue();				
					break;									
				}
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out =response.getWriter();
		
		out.write("<!DOCTYPE html>");
		out.write("<html>");
		out.write("<head>");
		out.write("<meta charset=\"UTF-8\">");
		out.write("<title>Insert title here</title>");
		out.write("<style>");
		out.write("input{");
		out.write("width:50px;");
		out.write("height:50px;");
		out.write("}");
		out.write(".output{");
		out.write("height:50px;");
		out.write("background: #e9e9e9;");
		out.write("font-size:24px;");
		out.write("font-weight:bold;");
		out.write("text-align:right;");
		out.write("padding:0px 5px;");
		out.write("}");
		out.write("</style>");
		out.write("</head>");
		out.write("<body>");
		out.write("<form method=\"post\">");
		out.write("<table>");
		out.write("<tr>");
		out.printf("<td class=\"output\" colspan=\"4\">%s</td>",exp);
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"CE\"/></td>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"C\"/></td>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"BS\"/></td>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"/\"/></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"7\"/></td>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"8\"/></td>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"9\"/></td>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"*\"/></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"4\"/></td>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"5\"/></td>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"6\"/></td>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"-\"/></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"1\"/></td>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"2\"/></td>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"3\"/></td>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"+\"/></td>");
		out.write("</tr>");
		out.write("<tr>");
		out.write("<td></td>");
		out.write("<td><input type=\"submit\" name=\"value\" value=\"0\"/></td>");
		out.write("<td><input type=\"submit\" name=\"dot\" value=\".\"/></td>");
		out.write("<td><input type=\"submit\" name=\"operator\" value=\"=\"/></td>");
		out.write("</tr>");
		out.write("</table>");			
		out.write("</form>");
		out.write("</body>");
		out.write("</html>");
		}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies =request.getCookies();											//3.사용자가 쿠키를 보냈으니까 다음요청이 들어왔을떄 보내줄것이다. 그러기위해서 쿠키를 읽어야한다. 쿠키를 읽기위해 request.getCookie()메소드를 사용하고 쿠키가 Cookies라는 배열로 읽히기 때문에 Cookie[]라는 배열로 선언해준다.
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String value = request.getParameter("value");											
		String operator = request.getParameter("operator"); 									
		String dot = request.getParameter("dot");  
		
		String exp ="";
		if(cookies != null)		//문제는 쿠키가 Null일경우도 있기때문에 브라우저에 쿠키가 아예없으면 Null로반환
			for(Cookie c : cookies)					
				if(c.getName().equals("exp")){			
					exp= c.getValue();				
					break;									
				}
		
		if(operator != null &&operator.equals("=")) {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("graal.js");
			try {
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(operator != null &&operator.equals("C")) {
			exp="";
		}
		else {
			exp += (value == null)?"":value;
			exp += (operator == null)?"":operator;
			exp += (dot == null)?"":dot;
		}
		Cookie expCookie =new Cookie("exp",exp);
		if(operator != null &&operator.equals("C")) {
			expCookie.setMaxAge(0);
		}
		expCookie.setPath("/calculator");
		response.addCookie(expCookie);
		response.sendRedirect("calculator");
		}
}
