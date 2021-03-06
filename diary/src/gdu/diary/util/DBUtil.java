package gdu.diary.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBUtil {
	// 1. DB 연결
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/diary","root","java1004");
			conn.setAutoCommit(false);  // false -> 성공할때까지 갖고 있음
		} catch(Exception e) {
			e.printStackTrace();
		}
		return conn;
	}
	/*
	 * // 2. DB자원 (conn, stmt, rs) 해제 public void close(ResultSet rs,
	 * PreparedStatement stmt, Connection conn) { // rs, stmt, conn 이 null이 아닐 경우에만
	 * close시켜 에러가 안뜨도록 한다. (유효성 검사) if(rs != null) { try { rs.close(); } catch
	 * (Exception e) { e.printStackTrace(); } } if(stmt != null) { try {
	 * stmt.close(); } catch (Exception e) { e.printStackTrace(); } } if(conn !=
	 * null) { try { conn.close(); } catch (Exception e) { e.printStackTrace(); } }
	 * }
	 */
}
