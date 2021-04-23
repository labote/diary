package gdu.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import gdu.diary.util.DBUtil;

public class TodoDao {
	
	private DBUtil dbUtil;
	
	public int deleteTodoByMember(Connection conn, int memberNo) throws SQLException {
		// 객체 생성 및 초기화
		this.dbUtil = new DBUtil();
		PreparedStatement stmt = null;
		int rowCnt = 0;
		
		try {
			// SQL문 실행
			stmt = conn.prepareStatement(TodoQuery.DELETE_TODO_BY_MEMBER);
			stmt.setInt(1, memberNo);
			rowCnt = stmt.executeUpdate();
		} finally { // 할당 해제
			this.dbUtil.close(null, stmt, null);
		}
		
		return rowCnt;
	}
}
