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
		}else {
			exp += (value == null)?"":value;
			exp += (operator == null)?"":operator;
			exp += (dot == null)?"":dot;
		}
		Cookie expCookie =new Cookie("exp",exp);
		
		response.addCookie(expCookie);
		response.sendRedirect("calcpage");
	}
}
