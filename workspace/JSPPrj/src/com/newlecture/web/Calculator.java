package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/calculator")											//����ڰ� url�� ���ؼ� calculaotr�� ��û�ϰԵǸ� Get ��û���ص� service�� ����ǰ� POST�� ��û�ص� service�� ����ƴ�. �׷���û�� GET POST��û�� �����ؾ��Ұ�쿡�� �������ΰ� 
																	//2���� ������� �˾ƺ����̴�. 1. �����Լ����� �������� ��� 2.GET��û�� POST��û�� Ưȭ�� �޼��带 Ȱ���غ��� ���
public class Calculator extends HttpServlet {						//�������� ����� ���� ���� Ŭ���� ��ӹޱ� 

	@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doGET �޼ҵ尡 ȣ�� �Ǿ����ϴ�.");
		}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPOST �޼ҵ尡 ȣ�� �Ǿ����ϴ�.");
		}
}
