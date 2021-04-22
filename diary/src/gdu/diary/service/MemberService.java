package gdu.diary.service;

import java.sql.Connection;
import java.sql.SQLException;

import gdu.diary.dao.MemberDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberService {
	private MemberDao memberDao;
	private DBUtil dbUtil;
	
	// select -> get
	// insert -> add
	// update -> modify 
	// delete -> remove
	public Member getMemberKey(Member member) {
		this.dbUtil = new DBUtil();
		this.memberDao = new MemberDao();
		Member returnMember = null;
		Connection conn = null;
		
		try {
			conn = dbUtil.getConnection();
			//conn.setAutoCommit(false);
			//conn.commit();
			//returnMember = new Member();
			returnMember = this.memberDao.selectMemberByKey(conn, member);
			conn.commit();
		} catch(SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			this.dbUtil.close(null, null, conn);
		}
		
		return returnMember;
	}
}
