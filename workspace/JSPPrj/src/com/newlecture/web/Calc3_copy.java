package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calc3Copy")
public class Calc3_copy extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application =request.getServletContext();						//이 값을 저장해야하는데 어디다 저장해야하는가? request에보면 ServletContext()가있다. 이것이 어플리케이션 저장소이다 application이라는 변수에다가 getservletCOntext()를저장하자
		HttpSession session =request.getSession();
		Cookie[] cookies =request.getCookies();											//3.사용자가 쿠키를 보냈으니까 다음요청이 들어왔을떄 보내줄것이다. 그러기위해서 쿠키를 읽어야한다. 쿠키를 읽기위해 request.getCookie()메소드를 사용하고 쿠키가 Cookies라는 배열로 읽히기 때문에 Cookie[]라는 배열로 선언해준다.
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String v_ = request.getParameter("v");											//사용자가 전달하는 값은 하나만 전달한다.
		String op = request.getParameter("operator"); 									//그리고 오퍼레이터를 같이 전달한다.
		
		int v= 0;																		// 기본값이 필요할까도 생각을 해봐야한다. 전달하는 내용이 값이없을때 기본값으로 받아야하나? 값입력x시 0으로 처리하기위해 v=0 기본값세팅
		if(!v_.equals("")) v= Integer.parseInt(v_);
		
		//계산
		if(op.equals("=")) {															//왜 if문에 =(equal)로했냐면 덧셈과 뺄셈은 하는일이 같다. 하지만 =는 계산하는것이다. 
																						//아래에서 값을 읽어와야한다. 하나는 앞에서 저장했던내용이고 하나는 지금가져온내용으로 두가지를 읽어야한다.
																						//4. 쿠키가왔으니 읽어야하는데 읽는곳은 이곳이다.  
//			int x = (Integer)application.getAttribute("value");							//앞에서 저장한값을 어플리케이션저장소에서 꺼내와야하는데 application.getAttribute("담을떄사용했던 값")메소드를 사용한다. 그리고 (Integer)을 안했을 떄 빨간줄이 뜨는것은 Object로 값을 반환하기 때문이다.
//			int x = (Integer)session.getAttribute("value");	
			
			int x=0;																	//6. x가 value가 담기는 값인데 일단 초기화 하도록하자
			for(Cookie c : cookies)														//8. 찾는작업이 여러번이니 for문 Cookies라는 배열에서 꺼내서 하나씩 Cookie c에 넣겠다.
			if(c.getName().equals("value"))	{											//5. 첫번째 쿠키를 꺼내면 getName()이름이있다. 이 이름이 "value"가 있는지? if문을 쓰자.
				x= Integer.parseInt(c.getValue());										//7.그 value가 찾아졌다하면 c.getValue()그 값을 가져온다 근데 빨간글씨뜬다 반환값이 String이라서그렇다 정수형으로바꾸자
				break;																	//9. 조건문을 두개 문장이 들어갈건데 찾았으면 그만검색 break;
				}
			
			int y = v;																	//지금사용자가 저장한값
//			String operator= (String)application.getAttribute("op");					//그래서 그 밸류값과 앞에서 저장했던값을 꺼내서 덧셈을 할지 뺄셈을 할지는 사용자가 저장했던 operator값을 꺼내서 봐야한다. 어플리케이션 저장소에서 읽어오자
//			String operator= (String)session.getAttribute("op");
			
			String operator ="";														//10.op값을 쿠키에서 찾는과정 value와동일
			for(Cookie c : cookies)					
				if(c.getName().equals("op")){			
					operator= c.getValue();				
					break;									
					}
				
			
			int result=0;
			
			if(operator.equals("+"))			
				result = x+y;
			else
				result = x-y;
			response.getWriter().printf("result is %d", result);
		
		
		}
		// 값을 저장
		else {
//			application.setAttribute("value", v);										//application에다가 두가지를 저장하자 v(값), op(연산자) setAttribute에다가 저장한다 어떻게 저장하냐면 map Collection처럼 key 와 value를 넣어준다.
//			application.setAttribute("op",op);  						 				//오퍼레이터도 같은 방식으로 저장해주기위해서 key와 value를 저장해주자 이것들을 두고두고 쓸수있다.																						//전달된내용이 op이 뭐냐인가에 따라서 저장해야할수도있고 계산이 될수도있다.	
//			session.setAttribute("value", v);
//			session.setAttribute("op",op);
			Cookie valueCookie= new Cookie("value", String.valueOf(v));					//1. 심는것이니까 여기서 부터 시작 Cookie를 키와 밸류값으로 각각 value와 op를 주자 빨간줄이 뜨는데 그이유는 op는 문자열 v는 정수형이어서다 그래서 문자열로 변환해주는 메소드인 String.valueOf(v)를 사용해주자 쿠키로 보낼수있는값은 문자열만 가능하기때문 문자열중에서도 UTF로 보내야한다. 쿠키라는 녀석은 문자열로만 저장하는 한계가있지만 JSON XML을 이용하면 다양한형태로 저장가능
			Cookie opCookie= new Cookie("op",op);
			valueCookie.setPath("/calc2");
			valueCookie.setMaxAge(24*60*60);
			opCookie.setPath("/calc2");
			response.addCookie(valueCookie);											//2-1. 쿠키 두개를 만들었을 뿐이고클라이언트에게 보내기위해서 addCookie()메소드를 사용하자
			response.addCookie(opCookie);												//2-2. 어떻게 전달이되냐면 response header에 심어지는 형태로 전달이된다. 이것이 클라이언트로 갔으니까 브라우저가 '쿠키가 왔네!' 하면서 브라우저가 잘저장하고있을것이다.
	
			response.sendRedirect("calc2.html");										//백지를 받지않기위해서 사용자가 돌려줄때 response.라는 출력도구중에는 sendRedirect()라는게 있다 여기서 다른페이지로 전환할수있는것이다. 
		}																				//2-3. 쿠키가 전달되면 크롬 ▶ 설정 ▶ 사이트설정에 보면 쿠키라는 항목이 있다. ▶쿠키 에서 사이트에서 쿠키 데이터를 저장하고 읽도록 허용(권장) 이라는 항목이있는데 요즘은 대부분 허용하는데 안될수도있다는 것을 알아둬야한다. 그리고 쿠키데이터보기라는 항목이있는데 클릭해보면 ▶ 사이트 주소가 있다 사이트주소들이 나한테 보낸 쿠키들이보인다. 클릭해보면 쿠키값도 확인할수있다. 언제 생성 만료도 확인가능하다. 
	}
}
