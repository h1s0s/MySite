package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.GuestbookVo;


@WebServlet("/guest")
public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/guest");
		String act = request.getParameter("action");
		
		if("addList".equals(act)) {
			GuestbookDao guestbookDao = new GuestbookDao();
			List<GuestbookVo> guestbookList = guestbookDao.getList();
			
			request.setAttribute("gList", guestbookList);//리퀘스트에 추가로 더 담는다.
			
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/addList.jsp");
		} else if("add".equals(act)) {
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			
			GuestbookVo guestbookVo = new GuestbookVo(name, password, content);
			
			GuestbookDao guestbookDao = new GuestbookDao();
			guestbookDao.guestbookInsert(guestbookVo);
			
			WebUtil.redirect(request, response, "/mysite/guest?action=addList");
		} else if("deleteForm".equals(act)) {
			WebUtil.forward(request, response, "/WEB-INF/views/guestbook/deleteForm.jsp");
			
		} else if("delete".equals(act)) {
			int id = Integer.parseInt(request.getParameter("id"));
			String password = request.getParameter("password");
			
			GuestbookDao guestbookDao = new GuestbookDao();
			guestbookDao.guestbookDelete(id, password);
			
			//WebUtil.redirect(request, response, "/mysite/guest?action=joinForm");
		} else {
			System.out.println("파라미터 없음");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
