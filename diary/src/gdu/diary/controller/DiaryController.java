package gdu.diary.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gdu.diary.service.DiaryService;
import gdu.diary.vo.Member;

@WebServlet("/auth/diary")
public class DiaryController extends HttpServlet {

	private DiaryService diaryService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		//의존 객체 생성
		this.diaryService = new DiaryService();
		
		// request 호출
		String targetYear = request.getParameter("targetYear"); // "2021",... null
		String targetMonth = request.getParameter("targetMonth"); // "4",... null
		int memberNo = ((Member)session.getAttribute("sessionMember")).getMemberNo();
		
		// 디버깅
		System.out.println("currentYear : " + targetYear);
		System.out.println("currentMonth : " + targetMonth);
		System.out.println("memberNo : " + memberNo);
		
		Map<String,Object> diaryMap = this.diaryService.getDiary(targetYear, targetMonth, memberNo);
		
		request.setAttribute("diaryMap", diaryMap);
		request.getRequestDispatcher("/WEB-INF/view/auth/diary.jsp").forward(request, response);
	}
}
