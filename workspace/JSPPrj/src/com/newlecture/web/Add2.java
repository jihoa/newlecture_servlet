package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/add2")
public class Add2 extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");
		
//		int x = Integer.parseInt(req.getParameter("x"));
//		int y = Integer.parseInt(req.getParameter("y"));
		
		int x=0;
		int y=0;
		
		
		PrintWriter out = resp.getWriter();
		
		String temp1 = req.getParameter("x");
		String temp2 = req.getParameter("y");
		
		if(temp1 != null && !temp1.equals(""))
			x= Integer.parseInt(temp1);

		if(temp2 != null && !temp2.equals(""))
			y= Integer.parseInt(temp2);
		int hap=x+y;

		out.println(hap);
	}
}
