package gdu.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

// 트랜잭션(select 제외, 취소하는것(rollback))
public class ADao {
	
	/*
	 * public void insert() {
	 * 
	 * // finally 쓰기 위해 이렇게 작업 Connection conn = null; PreparedStatement stmt1 =
	 * null; PreparedStatement stmt2 = null; try { conn = dbUtil.getConnection();
	 * conn.setAutoCommit(false); // false -> 둘다 성공할때까지 갖고 있음 stmt1 =
	 * conn.prepareStatement("A insert 쿼리"); stmt1.execute(); stmt2 =
	 * conn.prepareStatement("B insert 쿼리"); stmt2.execute();
	 * 
	 * conn.commit(); } catch (SQLException e) { try { conn.rollback(); // 에러 발생시
	 * 롤백시켜준다. } catch (SQLException e1) { e1.printStackTrace(); }
	 * e.printStackTrace(); } finally { dbUtil.close(null, stmt1, conn); } }
	 */
	
	
	// insert 메서드 자체에서 예외처리를 했기 때문에 실패하지 않음(try ~ catch)
	// -> 따라서 예외 처리 없앤다(throws SQLException)
	// -> close 해줘야하기 때문에 try 추가
	public void insert(Connection conn) throws SQLException {
		
		/*
		 * // finally 쓰기 위해 이렇게 작업 PreparedStatement stmt1 = null; try { stmt1 =
		 * conn.prepareStatement("A insert 쿼리"); stmt1.execute(); } catch (SQLException
		 * e) { e.printStackTrace(); }
		 */
		
		/*
		 * PreparedStatement stmt1 = null; stmt1 = conn.prepareStatement("A insert 쿼리");
		 * stmt1.execute();
		 */
		PreparedStatement stmt = null; 
		
		try {
			stmt = conn.prepareStatement("A insert 쿼리");
			stmt.execute();
		} finally {
			stmt.close();
		}
	}
}
