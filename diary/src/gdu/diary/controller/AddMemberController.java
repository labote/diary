package gdu.diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.MemberService;
import gdu.diary.vo.Member;

@WebServlet("/addMember")
public class AddMemberController extends HttpServlet {

	private MemberService memberService;
	
	// 회원 가입 폼
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response);
	}

	// 가입 액션
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		this.memberService = new MemberService();
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		System.out.println("member : " + member);
		
		boolean result = this.memberService.addMember(member);
		if(!result) {
			System.out.println("회원가입 실패(같은 아이디가 존재합니다)");
			response.sendRedirect(request.getContextPath()+"/addMember");
			return;
		}
		response.sendRedirect(request.getContextPath()+"/login");
	}

}
