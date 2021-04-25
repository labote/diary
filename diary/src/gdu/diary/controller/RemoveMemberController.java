package gdu.diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.MemberService;
import gdu.diary.vo.Member;

@WebServlet("/auth/removeMember")
public class RemoveMemberController extends HttpServlet {

	private MemberService memberService;
	
	// 비밀번호 입력 폼	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet");
		request.getRequestDispatcher("/WEB-INF/view/auth/removeMember.jsp").forward(request, response);
	}
	
	// 삭제 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		System.out.println("doPost");
		
		// request 호출
		String memberPw = request.getParameter("memberPw");
		Member member = (Member)request.getSession().getAttribute("sessionMember");
		member.setMemberPw(memberPw);
		
		// 디버깅
		System.out.println("memberId : " + member.getMemberId());
		System.out.println("memberPw : " + memberPw);
		
		// 의존 객체 생성
		this.memberService = new MemberService();
		
		// redirect 
		boolean result = this.memberService.removeMemberByKey(member);
		if(!result) {
			System.out.println("회원탈퇴 실패");
			response.sendRedirect(request.getContextPath()+"/auth/removeMember");
			return;
		}
		response.sendRedirect(request.getContextPath()+"/auth/logout");
	}
}
