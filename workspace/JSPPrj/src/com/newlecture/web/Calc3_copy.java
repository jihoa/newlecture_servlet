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
		ServletContext application =request.getServletContext();						//�� ���� �����ؾ��ϴµ� ���� �����ؾ��ϴ°�? request������ ServletContext()���ִ�. �̰��� ���ø����̼� ������̴� application�̶�� �������ٰ� getservletCOntext()����������
		HttpSession session =request.getSession();
		Cookie[] cookies =request.getCookies();											//3.����ڰ� ��Ű�� �������ϱ� ������û�� �������� �����ٰ��̴�. �׷������ؼ� ��Ű�� �о���Ѵ�. ��Ű�� �б����� request.getCookie()�޼ҵ带 ����ϰ� ��Ű�� Cookies��� �迭�� ������ ������ Cookie[]��� �迭�� �������ش�.
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String v_ = request.getParameter("v");											//����ڰ� �����ϴ� ���� �ϳ��� �����Ѵ�.
		String op = request.getParameter("operator"); 									//�׸��� ���۷����͸� ���� �����Ѵ�.
		
		int v= 0;																		// �⺻���� �ʿ��ұ ������ �غ����Ѵ�. �����ϴ� ������ ���̾����� �⺻������ �޾ƾ��ϳ�? ���Է�x�� 0���� ó���ϱ����� v=0 �⺻������
		if(!v_.equals("")) v= Integer.parseInt(v_);
		
		//���
		if(op.equals("=")) {															//�� if���� =(equal)���߳ĸ� ������ ������ �ϴ����� ����. ������ =�� ����ϴ°��̴�. 
																						//�Ʒ����� ���� �о�;��Ѵ�. �ϳ��� �տ��� �����ߴ������̰� �ϳ��� ���ݰ����³������� �ΰ����� �о���Ѵ�.
																						//4. ��Ű�������� �о���ϴµ� �д°��� �̰��̴�.  
//			int x = (Integer)application.getAttribute("value");							//�տ��� �����Ѱ��� ���ø����̼�����ҿ��� �����;��ϴµ� application.getAttribute("����������ߴ� ��")�޼ҵ带 ����Ѵ�. �׸��� (Integer)�� ������ �� �������� �ߴ°��� Object�� ���� ��ȯ�ϱ� �����̴�.
//			int x = (Integer)session.getAttribute("value");	
			
			int x=0;																	//6. x�� value�� ���� ���ε� �ϴ� �ʱ�ȭ �ϵ�������
			for(Cookie c : cookies)														//8. ã���۾��� �������̴� for�� Cookies��� �迭���� ������ �ϳ��� Cookie c�� �ְڴ�.
			if(c.getName().equals("value"))	{											//5. ù��° ��Ű�� ������ getName()�̸����ִ�. �� �̸��� "value"�� �ִ���? if���� ����.
				x= Integer.parseInt(c.getValue());										//7.�� value�� ã�������ϸ� c.getValue()�� ���� �����´� �ٵ� �����۾���� ��ȯ���� String�̶󼭱׷��� ���������ιٲ���
				break;																	//9. ���ǹ��� �ΰ� ������ ���ǵ� ã������ �׸��˻� break;
				}
			
			int y = v;																	//���ݻ���ڰ� �����Ѱ�
//			String operator= (String)application.getAttribute("op");					//�׷��� �� ������� �տ��� �����ߴ����� ������ ������ ���� ������ ������ ����ڰ� �����ߴ� operator���� ������ �����Ѵ�. ���ø����̼� ����ҿ��� �о����
//			String operator= (String)session.getAttribute("op");
			
			String operator ="";														//10.op���� ��Ű���� ã�°��� value�͵���
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
		// ���� ����
		else {
//			application.setAttribute("value", v);										//application���ٰ� �ΰ����� �������� v(��), op(������) setAttribute���ٰ� �����Ѵ� ��� �����ϳĸ� map Collectionó�� key �� value�� �־��ش�.
//			application.setAttribute("op",op);  						 				//���۷����͵� ���� ������� �������ֱ����ؼ� key�� value�� ���������� �̰͵��� �ΰ�ΰ� �����ִ�.																						//���޵ȳ����� op�� �����ΰ��� ���� �����ؾ��Ҽ����ְ� ����� �ɼ����ִ�.	
//			session.setAttribute("value", v);
//			session.setAttribute("op",op);
			Cookie valueCookie= new Cookie("value", String.valueOf(v));					//1. �ɴ°��̴ϱ� ���⼭ ���� ���� Cookie�� Ű�� ��������� ���� value�� op�� ���� �������� �ߴµ� �������� op�� ���ڿ� v�� �������̾�� �׷��� ���ڿ��� ��ȯ���ִ� �޼ҵ��� String.valueOf(v)�� ��������� ��Ű�� �������ִ°��� ���ڿ��� �����ϱ⶧�� ���ڿ��߿����� UTF�� �������Ѵ�. ��Ű��� �༮�� ���ڿ��θ� �����ϴ� �Ѱ谡������ JSON XML�� �̿��ϸ� �پ������·� ���尡��
			Cookie opCookie= new Cookie("op",op);
			valueCookie.setPath("/calc2");
			valueCookie.setMaxAge(24*60*60);
			opCookie.setPath("/calc2");
			response.addCookie(valueCookie);											//2-1. ��Ű �ΰ��� ������� ���̰�Ŭ���̾�Ʈ���� ���������ؼ� addCookie()�޼ҵ带 �������
			response.addCookie(opCookie);												//2-2. ��� �����̵ǳĸ� response header�� �ɾ����� ���·� �����̵ȴ�. �̰��� Ŭ���̾�Ʈ�� �����ϱ� �������� '��Ű�� �Գ�!' �ϸ鼭 �������� �������ϰ��������̴�.
	
			response.sendRedirect("calc2.html");										//������ �����ʱ����ؼ� ����ڰ� �����ٶ� response.��� ��µ����߿��� sendRedirect()��°� �ִ� ���⼭ �ٸ��������� ��ȯ�Ҽ��ִ°��̴�. 
		}																				//2-3. ��Ű�� ���޵Ǹ� ũ�� �� ���� �� ����Ʈ������ ���� ��Ű��� �׸��� �ִ�. ����Ű ���� ����Ʈ���� ��Ű �����͸� �����ϰ� �е��� ���(����) �̶�� �׸����ִµ� ������ ��κ� ����ϴµ� �ȵɼ����ִٴ� ���� �˾Ƶ־��Ѵ�. �׸��� ��Ű�����ͺ����� �׸����ִµ� Ŭ���غ��� �� ����Ʈ �ּҰ� �ִ� ����Ʈ�ּҵ��� ������ ���� ��Ű���̺��δ�. Ŭ���غ��� ��Ű���� Ȯ���Ҽ��ִ�. ���� ���� ���ᵵ Ȯ�ΰ����ϴ�. 
	}
}
