package gdu.diary.service;

import java.sql.Connection;
import java.sql.SQLException;

import gdu.diary.dao.ADao;
import gdu.diary.dao.BDao;
import gdu.diary.util.DBUtil;

public class ABService {
	
	private DBUtil dbUtil;
	private ADao aDao;
	private BDao bDao;
	
	public void insert() {
		// 의존 객체 생성
		this.dbUtil = new DBUtil();
		this.aDao = new ADao();
		this.bDao = new BDao();
		
		Connection conn = null;
		try {
			conn = this.dbUtil.getConnection();
			conn.setAutoCommit(false); // false -> 둘다 성공할때까지 갖고 있음
			aDao.insert(conn); // insert 메서드 자체에서 예외처리를 했기 때문에 실패하지 않음(try ~ catch) -> 따라서 예외 처리 없앤다(throws SQLException)
			bDao.insert(conn); // insert 메서드 자체에서 예외처리를 했기 때문에 실패하지 않음 -> 따라서 예외 처리 없앤다.
			conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			dbUtil.close(null, null, conn);
		}
	}
}
