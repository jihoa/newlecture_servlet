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

@WebServlet("/calculator")											//����ڰ� url�� ���ؼ� calculaotr�� ��û�ϰԵǸ� Get ��û���ص� service�� ����ǰ� POST�� ��û�ص� service�� ����ƴ�. �׷���û�� GET POST��û�� �����ؾ��Ұ�쿡�� �������ΰ� 
																	//2���� ������� �˾ƺ����̴�. 1. �����Լ����� �������� ��� 2.GET��û�� POST��û�� Ưȭ�� �޼��带 Ȱ���غ��� ���
public class Calculator extends HttpServlet {						//�������� ����� ���� ���� Ŭ���� ��ӹޱ� 

	@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies =request.getCookies();	
		
		String exp ="0";														
		if(cookies != null)		//������ ��Ű�� Null�ϰ�쵵 �ֱ⶧���� �������� ��Ű�� �ƿ������� Null�ι�ȯ
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
		Cookie[] cookies =request.getCookies();											//3.����ڰ� ��Ű�� �������ϱ� ������û�� �������� �����ٰ��̴�. �׷������ؼ� ��Ű�� �о���Ѵ�. ��Ű�� �б����� request.getCookie()�޼ҵ带 ����ϰ� ��Ű�� Cookies��� �迭�� ������ ������ Cookie[]��� �迭�� �������ش�.
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String value = request.getParameter("value");											
		String operator = request.getParameter("operator"); 									
		String dot = request.getParameter("dot");  
		
		String exp ="";
		if(cookies != null)		//������ ��Ű�� Null�ϰ�쵵 �ֱ⶧���� �������� ��Ű�� �ƿ������� Null�ι�ȯ
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
