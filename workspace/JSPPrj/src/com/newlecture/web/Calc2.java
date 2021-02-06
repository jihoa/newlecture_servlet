package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calc2")
public class Calc2 extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application =request.getServletContext();						//이 값을 저장해야하는데 어디다 저장해야하는가? request에보면 ServletContext()가있다. 이것이 어플리케이션 저장소이다 application이라는 변수에다가 getservletCOntext()를저장하자
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String v_ = request.getParameter("v");											//사용자가 전달하는 값은 하나만 전달한다.
		String op = request.getParameter("operator"); 									//그리고 오퍼레이터를 같이 전달한다.
		
		int v= 0;																		// 기본값이 필요할까도 생각을 해봐야한다. 전달하는 내용이 값이없을때 기본값으로 받아야하나? 값입력x시 0으로 처리하기위해 v=0 기본값세팅
		if(!v_.equals("")) v= Integer.parseInt(v_);
		
		//계산
		if(op.equals("=")) {															//왜 if문에 =(equal)로했냐면 덧셈과 뺄셈은 하는일이 같다. 하지만 =는 계산하는것이다. 
																						//아래에서 값을 읽어와야한다. 하나는 앞에서 저장했던내용이고 하나는 지금가져온내용으로 두가지를 읽어야한다.
			
			int x = (Integer)application.getAttribute("value");							//앞에서 저장한값을 어플리케이션저장소에서 꺼내와야하는데 application.getAttribute("담을떄사용했던 값")메소드를 사용한다. 그리고 (Integer)을 안했을 떄 빨간줄이 뜨는것은 Object로 값을 반환하기 때문이다.
			int y = v;																	//지금사용자가 저장한값
			String operator= (String)application.getAttribute("op");					//그래서 그 밸류값과 앞에서 저장했던값을 꺼내서 덧셈을 할지 뺄셈을 할지는 사용자가 저장했던 operator값을 꺼내서 봐야한다. 어플리케이션 저장소에서 읽어오자
			int result=0;
			
			if(operator.equals("+"))			
				result = x+y;
			else
				result = x-y;
			response.getWriter().printf("result is %d", result);
		
		
		}
		// 값을 저장
		else {
			application.setAttribute("value", v);										//application에다가 두가지를 저장하자 v(값), op(연산자) setAttribute에다가 저장한다 어떻게 저장하냐면 map Collection처럼 key 와 value를 넣어준다.
			application.setAttribute("op",op);  						 				//오퍼레이터도 같은 방식으로 저장해주기위해서 key와 value를 저장해주자 이것들을 두고두고 쓸수있다.
																						//전달된내용이 op이 뭐냐인가에 따라서 저장해야할수도있고 계산이 될수도있다.
			
		}
		
		
	}

}
