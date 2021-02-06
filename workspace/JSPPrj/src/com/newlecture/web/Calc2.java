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
		ServletContext application =request.getServletContext();						//�� ���� �����ؾ��ϴµ� ���� �����ؾ��ϴ°�? request������ ServletContext()���ִ�. �̰��� ���ø����̼� ������̴� application�̶�� �������ٰ� getservletCOntext()����������
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String v_ = request.getParameter("v");											//����ڰ� �����ϴ� ���� �ϳ��� �����Ѵ�.
		String op = request.getParameter("operator"); 									//�׸��� ���۷����͸� ���� �����Ѵ�.
		
		int v= 0;																		// �⺻���� �ʿ��ұ ������ �غ����Ѵ�. �����ϴ� ������ ���̾����� �⺻������ �޾ƾ��ϳ�? ���Է�x�� 0���� ó���ϱ����� v=0 �⺻������
		if(!v_.equals("")) v= Integer.parseInt(v_);
		
		//���
		if(op.equals("=")) {															//�� if���� =(equal)���߳ĸ� ������ ������ �ϴ����� ����. ������ =�� ����ϴ°��̴�. 
																						//�Ʒ����� ���� �о�;��Ѵ�. �ϳ��� �տ��� �����ߴ������̰� �ϳ��� ���ݰ����³������� �ΰ����� �о���Ѵ�.
			
			int x = (Integer)application.getAttribute("value");							//�տ��� �����Ѱ��� ���ø����̼�����ҿ��� �����;��ϴµ� application.getAttribute("����������ߴ� ��")�޼ҵ带 ����Ѵ�. �׸��� (Integer)�� ������ �� �������� �ߴ°��� Object�� ���� ��ȯ�ϱ� �����̴�.
			int y = v;																	//���ݻ���ڰ� �����Ѱ�
			String operator= (String)application.getAttribute("op");					//�׷��� �� ������� �տ��� �����ߴ����� ������ ������ ���� ������ ������ ����ڰ� �����ߴ� operator���� ������ �����Ѵ�. ���ø����̼� ����ҿ��� �о����
			int result=0;
			
			if(operator.equals("+"))			
				result = x+y;
			else
				result = x-y;
			response.getWriter().printf("result is %d", result);
		
		
		}
		// ���� ����
		else {
			application.setAttribute("value", v);										//application���ٰ� �ΰ����� �������� v(��), op(������) setAttribute���ٰ� �����Ѵ� ��� �����ϳĸ� map Collectionó�� key �� value�� �־��ش�.
			application.setAttribute("op",op);  						 				//���۷����͵� ���� ������� �������ֱ����ؼ� key�� value�� ���������� �̰͵��� �ΰ�ΰ� �����ִ�.
																						//���޵ȳ����� op�� �����ΰ��� ���� �����ؾ��Ҽ����ְ� ����� �ɼ����ִ�.
			
		}
		
		
	}

}
