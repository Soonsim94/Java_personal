package com.yedam.app.commo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//데이터에 접근하는 클래스  
public class DAO {
	// DataBase 연결정보
	private String jdbcDriver = "org.sqlite.JDBC";
	private String jdbcUrl = "jdbc:sqlite:/D:/database/yedam-1.0.6.db";

	// 각 메소드에서 공통적으로 사용하는 변수 -> 필드
	protected Connection conn;
	protected Statement stmt;
	protected PreparedStatement pstmt;
	protected ResultSet rs;

	// 연결
	// 1. JDBC DRIVE LOADING
	// 2. CONNECTION
	protected void connect() {
		try {
			Class.forName(jdbcDriver);
			
			conn = DriverManager.getConnection(jdbcUrl);
			
		}catch(ClassNotFoundException e) {
			System.out.println("JDBC DRIVAER LOADING FAIL");
		}catch(SQLException e) {
			System.out.println("DATABASE CONNECTION FAIL");
		}
	}
	// 연결해제
	// 6. 자원해제
	public void disconnect() {
		try {
			if(rs != null) rs.close();
			if(stmt != null) stmt.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
			
		}catch(SQLException e) {
			System.out.println("정상적으로 자원이 해제되지 않았습니다.");
		}
	}
}
