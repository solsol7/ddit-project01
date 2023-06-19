package util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOConnection {
	/* 방화벽 전까지는 localhost로 수정해서 사용해야함 확인요망 */
	private final String URL = "jdbc:oracle:thin:@192.168.35.55:1521:xe";  //
	private final String USER = "pc13_p2"; // 
	private final String PASSWD = "java";

	private static DAOConnection instance = null;

	private DAOConnection() {}

	public static DAOConnection getInstance() {
		if (instance == null) instance = new DAOConnection();
		return instance;
	}
			/* 0615 10:23 김석호 수정사항 
			 * conn에서 static 삭제
			 */
	public Connection conn = null;
	public Statement stmt = null;
	public PreparedStatement pstmt = null;
	public ResultSet rs = null;
	public CallableStatement cstmt = null;
	
	public void connectConn() {
		/*
		 * 여기서 clearConsole 호출하거나 connetConn 불러오기 전에 clearConsole 호출할것
		 */

		try {
			conn = DriverManager.getConnection(URL, USER, PASSWD);
			stmt = conn.createStatement();
			stmt.executeUpdate("UPDATE STUDENT SET STD_STDATE = NULL WHERE STD_STDATE < SYSDATE");
			stmt.execute(" select ascii_seq.nextval from dual ");
			stmt.execute(" alter sequence ascii_seq increment by -1 ");
			stmt.execute(" select ascii_seq.nextval from dual ");
			stmt.execute(" alter sequence ascii_seq increment by 1 ");
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			ScanUtil.errSleep();
			System.err.println("　　　　　　　　　　　　　　♡♡♡♡♡♡♡♡♡♡아주 심각한 오류가 발생했어용♡♡♡♡♡♡♡♡♡♡");
		}
	}

	public void disconectConn() {
		if (rs != null)	try {rs.close();} catch (Exception e) {}
		if (stmt != null)try {stmt.close();} catch (Exception e) {}
		if (pstmt != null)try {pstmt.close();} catch (Exception e) {}
		if (conn != null)try {conn.close();} catch (Exception e) {}
	}
}
