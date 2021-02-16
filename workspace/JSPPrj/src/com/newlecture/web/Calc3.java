package com.newlecture.web;

import java.io.IOException;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc3")
public class Calc3 extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		response.addCookie(expCookie);
		response.sendRedirect("calcpage");
	}
}
