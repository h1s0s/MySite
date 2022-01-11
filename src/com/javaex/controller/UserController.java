package com.javaex.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.UserDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.UserVo;
import com.javaex.vo.UserVo;


@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/user");
		String act = request.getParameter("action");
		
		if("joinForm".equals(act)) {
			WebUtil.forward(request, response, "/WEB-INF/views/user/joinForm.jsp");
		
		} else if("join".equals(act)) {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			
			UserVo userVo = new UserVo(id, password, name, gender);
			UserDao userDao = new UserDao();
			
			System.out.println("[" + userDao.insert(userVo) + " 건이 저장되었습니다.]"); ;
			
			//WebUtil.redirect(request, response, "/user?action=joinOk");
		} else if("joinOk".equals(act)) {
			
			
		} else {
			System.out.println("파라미터 없음");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
