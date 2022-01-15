package com.javaex.dao;

import com.javaex.vo.BoardVo;
import com.javaex.vo.GuestbookVo;
import com.javaex.vo.UserVo;

public class TestDao {

	public static void main(String[] args) {
		
		//UserDao 테스트
		UserVo userVo = new UserVo("123","123","한상선","male");
		
		new UserDao().insert(userVo);
		
		//GuestbookDao 테스트
		GuestbookVo guestbookVo = new GuestbookVo();
		guestbookVo.setName("한상선");
		guestbookVo.setPassword("1234");
		guestbookVo.setContent("방명록 테스트 글");
		
		new GuestbookDao().guestbookInsert(guestbookVo);
		
		//BoardDao 테스트
		BoardVo boardVo = new BoardVo();
		
		boardVo.setTitle("게시글 테스트");
		boardVo.setContent("게시판 테스트 글");
		boardVo.setUserNo(1);
		
		new BoardDao().boardInsert(boardVo);
	}

}
