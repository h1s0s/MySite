package com.javaex.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.javaex.dao.BoardDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.BoardVo;
import com.javaex.vo.UserVo;

@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/board");
		String act = request.getParameter("action");
		if("list".equals(act)) {
			System.out.println("action > list");
			
			BoardDao boardDao = new BoardDao();
			List<BoardVo> boardList = boardDao.getList();
			
			request.setAttribute("boardList", boardList);
			
			WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");
		} else if ("writeForm".equals(act)) {
			System.out.println("action > writeForm");
			
			WebUtil.forward(request, response, "/WEB-INF/views/board/writeForm.jsp");
		} else if ("write".equals(act)) {
			System.out.println("action > write");
			
			HttpSession session = request.getSession();
			
			UserVo authUser = (UserVo)session.getAttribute("authUser");
			
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			int userNo = authUser.getNo();
			
			BoardVo boardVo = new BoardVo(title, content, userNo);
			BoardDao boardDao = new BoardDao();
			boardDao.boardInsert(boardVo);
			
			WebUtil.redirect(request, response, "/mysite/board?action=list");
		} else if ("read".equals(act)) {
			System.out.println("action > read");
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			BoardDao boardDao = new BoardDao();
			
			BoardVo boardVo = boardDao.getBoard(no);
			
			request.setAttribute("boardVo", boardVo);
			
			WebUtil.forward(request, response, "/WEB-INF/views/board/read.jsp");
		} else if ("delete".equals(act)) {
			System.out.println("action > delete");
			
			int num = Integer.parseInt(request.getParameter("no"));
			BoardDao boardDao = new BoardDao();
			
			boardDao.boardDelete(num);
			
			WebUtil.redirect(request, response, "/mysite/board?action=list");
		} else if ("modifyForm".equals(act)) {
			System.out.println("action > modifyForm");
			
			int no = Integer.parseInt(request.getParameter("no"));
			BoardDao boardDao = new BoardDao();
			
			BoardVo boardVo = boardDao.getBoard(no);
			request.setAttribute("boardVo", boardVo);
			
			WebUtil.forward(request, response, "/WEB-INF/views/board/modifyForm.jsp");
		} else if ("modify".equals(act)) {
			System.out.println("action > modify");
			
			int no = Integer.parseInt(request.getParameter("no"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			
			BoardDao boardDao = new BoardDao();
			BoardVo boardVo = new BoardVo(no, title, content);
			boardDao.boardUpdate(boardVo);
			
			WebUtil.redirect(request, response, "/mysite/board?action=list");
		} else {
			System.out.println("파라미터 없음");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
