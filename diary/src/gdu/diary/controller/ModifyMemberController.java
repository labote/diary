package gdu.diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.MemberService;
import gdu.diary.vo.Member;

@WebServlet("/auth/modifyMember")
public class ModifyMemberController extends HttpServlet {

	private MemberService memberService;
	
	// 비밀번호 변경 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/auth/modifyMember.jsp").forward(request, response);
	}

	// 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// request 호출
		String memberPw = request.getParameter("memberPw");
		Member member = (Member)request.getSession().getAttribute("sessionMember");
		member.setMemberPw(memberPw);
		
		// 디버깅
		System.out.println("memberId : " + member.getMemberId());
		System.out.println("memberPw : " + memberPw);
		
		
		//의존 객체 생성 
		this.memberService = new MemberService();
		 
		// 서비스 호출
		boolean result = this.memberService.modifyMember(member);
	
		// redirect
		if(!result) { 
			System.out.println("비밀번호 수정 실패");
			response.sendRedirect(request.getContextPath() + "/auth/addMember"); return;
		}
		request.getSession().invalidate();
		response.sendRedirect(request.getContextPath() + "/login");
		 
	}

}
