package gdu.diary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import gdu.diary.service.TodoService;
import gdu.diary.vo.Member;
import gdu.diary.vo.Todo;
import gdu.diary.vo.TodoDate;

@WebServlet("/auth/todoOne")
public class TodoOneController extends HttpServlet {

	private TodoService todoService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// request 호출
		int year = Integer.parseInt(request.getParameter("targetYear"));
		int month = Integer.parseInt(request.getParameter("targetMonth"));
		int day = Integer.parseInt(request.getParameter("targetDate"));
		int memberNo = ((Member)session.getAttribute("sessionMember")).getMemberNo();
		
		// 디버깅
		System.out.println("year : " + year);
		System.out.println("month : " + month);
		System.out.println("day : " + day);
		System.out.println("memberNo : " + memberNo);
		
		// 전처리
		TodoDate todoDate = new TodoDate();
		todoDate.setYear(year);
		todoDate.setMonth(month);
		todoDate.setDay(day);
		
		// 디버깅
		System.out.println("todoDate : " + todoDate);
		
		// 의존 객체 생성 및 서비스 호출
		this.todoService = new TodoService();
		Todo todo = this.todoService.getTodo(todoDate.toString(), memberNo);
		
		// View forward
		request.setAttribute("todo", todo);
		request.setAttribute("todoDate", todoDate);
		request.getRequestDispatcher("/WEB-INF/view/auth/todoOne.jsp").forward(request, response);
	}
}
