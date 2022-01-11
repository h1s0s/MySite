package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.javaex.vo.UserVo;

public class UserDao {

	// 필드
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@172.30.1.54:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	// 메소드 g/s

	// 메소드 일반
	private void getConnection() {
		try {
			Class.forName(driver);

			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	public void close() {
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// 저장 메소드(회원가입)
	public int insert(UserVo userVo) {
		int count = 0;
		getConnection();
		
		try {
			// SQL문 준비
			// 문자열
			String query = "";
			query += " insert into users ";
			query += " values (seq_users_no.nextval, ?, ?, ?, ?) ";
			
			// 문자열을 쿼리문으로
			pstmt = conn.prepareStatement(query);
			
			// 바인딩
			pstmt.setString(1, userVo.getId());
			pstmt.setString(2, userVo.getPassword());
			pstmt.setString(3, userVo.getName());
			pstmt.setString(4, userVo.getGender());
			
			// 실행
			count = pstmt.executeUpdate();
			
			// 결과처리
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
		close();
		
		return count;
	}
}
