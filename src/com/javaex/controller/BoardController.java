package com.javaex.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/board");
		String act = request.getParameter("action");
		if("list".equals(act)) {
			
		} else if ("writeForm".equals(act)) {
			
		} else if ("write".equals(act)) {
			
		} else if ("read".equals(act)) {
			
		} else if ("modifyForm".equals(act)) {
			
		} else if ("modify".equals(act)) {
			
		} else {
			System.out.println("파라미터 없음");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
