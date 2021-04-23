package gdu.diary.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gdu.diary.service.DiaryService;

@WebServlet("/auth/diary")
public class DiaryController extends HttpServlet {

	private DiaryService diaryService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//의존 객체 생성
		this.diaryService = new DiaryService();
		
		// request 호출
		String targetYear = request.getParameter("targetYear"); // "2021",... null
		String targetMonth = request.getParameter("targetMonth"); // "4",... null
		
		// 디버깅
		System.out.println("currentYear : " + targetYear);
		System.out.println("currentMonth : " + targetMonth);
		
		Map<String,Object> diaryMap = this.diaryService.getDiary(targetYear, targetMonth);
		
		request.setAttribute("diaryMap", diaryMap);
		request.getRequestDispatcher("/WEB-INF/view/auth/diary.jsp").forward(request, response);
	}
}
