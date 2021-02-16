package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")											//사용자가 url을 통해서 calculaotr를 요청하게되면 Get 요청을해도 service가 실행되고 POST를 요청해도 service가 실행됐다. 그런요청을 GET POST요청을 구분해야할경우에는 나눌것인가 
																	//2가지 방식으로 알아볼것이다. 1. 서비스함수에서 구분짓는 방법 2.GET요청과 POST요청에 특화된 메서드를 활용해보는 방법
public class Calculator extends HttpServlet {						//서블릿으로 만들기 위해 서블릿 클래스 상속받기 

	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGET 메소드가 호출 되었습니다.");
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPOST 메소드가 호출 되었습니다.");
		}
}
