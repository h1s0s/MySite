package com.javaex.dao;

import com.javaex.vo.UserVo;

public class TestDao {

	public static void main(String[] args) {
		
		UserDao userDao = new UserDao();
		
		UserVo userVo = new UserVo("ccc","123","한상선","male");
		
		userDao.insert(userVo);
		
	}

}
