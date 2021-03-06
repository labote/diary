package gdu.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import gdu.diary.util.DBUtil;
import gdu.diary.vo.Member;

public class MemberDao {
	
	private DBUtil dbUtil;
	
	// 비밀번호 수정 메서드
	public int updateMember(Connection conn, Member member) throws SQLException {
		
		// 객체 생성 및 초기화
		// this.dbUtil = new DBUtil();
		PreparedStatement stmt = null;
		int rowCnt = 0;
		
		try {
			// SQL 실행
			stmt = conn.prepareStatement(MemberQuery.UPDATE_MEMBER_BY_KEY);
			stmt.setString(1, member.getMemberPw());
			stmt.setString(2, member.getMemberId());
			System.out.println("updateMember : " + stmt); // 디버깅
			stmt.executeUpdate();
		} finally {
			stmt.close();
			//this.dbUtil.close(null, stmt, null);
		}
		
		return rowCnt;
	}
	
	// 아이디 중복 체크 메서드
	public String checkMember(Connection conn, String memberId) throws SQLException {
		
		// 객체 생성 및 초기화
		// this.dbUtil = new DBUtil();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String returnMemberId = null;
		
		try {
			// SQL문 실행
			stmt = conn.prepareStatement(MemberQuery.CHECK_MEMBER_BY_KEY);
			stmt.setString(1, memberId);
			System.out.println("checkMember : " + stmt); // 디버깅
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				returnMemberId = rs.getString("memberId");
			}
		} finally {
			rs.close();
			stmt.close();
			//this.dbUtil.close(rs, stmt, null);
		}
		return returnMemberId;
	}
	
	// 회원 가입 메서드
	public int insertMember(Connection conn, Member member) throws SQLException {
		
		// 객체 생성 및 초기화
		// this.dbUtil = new DBUtil();
		PreparedStatement stmt = null;
		int rowCnt = 0;
		
		try {
			// SQL문 실행
			stmt = conn.prepareStatement(MemberQuery.INSERT_MEMBER_BY_KEY);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			System.out.println("insertMember : " + stmt); // 디버깅
			rowCnt = stmt.executeUpdate();
			
		} finally {
			stmt.close();
			// this.dbUtil.close(null, stmt, null);
		}
		return rowCnt;
	}
	
	// 회원 탈퇴 메서드
	public int deleteMemberByKey(Connection conn, Member member) throws SQLException {
		
		// 객체 생성 및 초기화
		// this.dbUtil = new DBUtil();
		PreparedStatement stmt = null;
		int rowCnt = 0;

		try {
			// SQL문 실행
			stmt = conn.prepareStatement(MemberQuery.DELETE_MEMBER_BY_KEY);
			stmt.setInt(1, member.getMemberNo());
			stmt.setString(2, member.getMemberPw());
			System.out.println("deleteMemberByKey : " + stmt); // 디버깅
			rowCnt = stmt.executeUpdate();
		} finally { // 할당 해제
			stmt.close();
			// this.dbUtil.close(null, stmt, null);
		}
		
		return rowCnt;
	}
	
	// throws SQLException, 호출한 친구에게 예외 처리
	// select 메서드
	public Member selectMemberByKey(Connection conn, Member member) throws SQLException {
		
		// 객체 생성 및 초기화
		// this.dbUtil = new DBUtil();
		Member returnMember = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// SQL문 실행
			stmt = conn.prepareStatement(MemberQuery.SELECT_MEMBER_BY_KEY);
			stmt.setString(1, member.getMemberId());
			stmt.setString(2, member.getMemberPw());
			System.out.println("selectMemberByKey : " + stmt); // 디버깅
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				returnMember = new Member();
				returnMember.setMemberNo(rs.getInt("memberNo"));
				returnMember.setMemberId(rs.getString("memberId"));
			}
			
		} finally { // 할당 해제
			rs.close();
			stmt.close();
			// this.dbUtil.close(rs, stmt, null);
		}
		
		return returnMember;
	}
}
