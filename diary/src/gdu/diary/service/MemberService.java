package gdu.diary.service;

import java.sql.Connection;
import java.sql.SQLException;

import gdu.diary.dao.MemberDao;
import gdu.diary.dao.TodoDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberService {
	private MemberDao memberDao;
	private DBUtil dbUtil;
	private TodoDao todoDao;
	// select -> get
	// insert -> add
	// update -> modify 
	// delete -> remove
	
	//
	public boolean addMember(Member member) {
		// 객체 생성 및 초기화
		this.dbUtil = new DBUtil();
		this.memberDao = new MemberDao();
		Connection conn = null;
		
		try {
			// DB 연결 및 SQL 실행
			conn = this.dbUtil.getConnection();
			// 중복 체크
			if(this.memberDao.checkMember(conn, member.getMemberId()) != null) {
				return false;
			}
			this.memberDao.insertMember(conn, member);
			conn.commit();
		} catch (SQLException e) {
			try {
				// 둘 중 하나라도 에러가 나면 롤백 시킨다(그 전 실행은 취소)
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		} finally {
			this.dbUtil.close(null, null, conn);
		}
		
		return true;
	}
	
	// 삭제 성공하면 true, 실패하면 false
	public boolean removeMemberByKey(Member member) {
		// 객체 생성 및 초기화
		this.dbUtil = new DBUtil();
		this.memberDao = new MemberDao();
		this.todoDao = new TodoDao();
		Connection conn = null;

		try {
			// DB 연결 및 SQL 실행
			conn = this.dbUtil.getConnection();
			this.todoDao.deleteTodoByMember(conn, member.getMemberNo());
			this.memberDao.deleteMemberByKey(conn, member);
			conn.commit();
		} catch (SQLException e) {
			try {
				// 둘 중 하나라도 에러가 나면 롤백 시킨다(그 전 실행은 취소)
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		} finally {
			this.dbUtil.close(null, null, conn);
		}
		
		return true;
	}
	
	// 멤버를 가져오는 selectMemberByKey를 불러온다.
	public Member getMemberKey(Member member) {
		
		// 객체 생성 및 초기화
		this.dbUtil = new DBUtil();
		this.memberDao = new MemberDao();
		Member returnMember = null;
		Connection conn = null;
		
		try {
			// DB 연결 및 SQL문 실행
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
		} finally { // 할당 해제
			this.dbUtil.close(null, null, conn);
		}
		
		return returnMember;
	}
}
