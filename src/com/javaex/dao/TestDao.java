package com.javaex.dao;

import com.javaex.vo.UserVo;

public class TestDao {

	public static void main(String[] args) {
		
		UserDao userDao = new UserDao();
		
		UserVo userVo = new UserVo("123","123","한상선","male");
		
		System.out.println("[" + userDao.insert(userVo) + " 건이 저장되었습니다.]");
		
	}

}
