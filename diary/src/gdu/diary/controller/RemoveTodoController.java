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

/**
 * Servlet implementation class RemoveTodoController
 */
@WebServlet("/auth/removeTodo")
public class RemoveTodoController extends HttpServlet {

	private TodoService todoService;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// request 호출
		HttpSession session = request.getSession();
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		int memberNo = ((Member)session.getAttribute("sessionMember")).getMemberNo();
		String targetYear = request.getParameter("targetYear");
		String targetMonth = request.getParameter("targetMonth");
		
		// 디버깅
		System.out.println("todoNo : " + todoNo);
		System.out.println("memberNo : " + memberNo);
		System.out.println("targetYear : " + targetYear);
		System.out.println("targetMonth : " + targetMonth);
		
		// 의존객체 생성 및 서비스 호출
		this.todoService = new TodoService();
		this.todoService.removeTodo(memberNo, todoNo);
		
		// redirect
		response.sendRedirect(request.getContextPath()+"/auth/diary?targetYear="+targetYear+"&targetMonth="+(Integer.parseInt(targetMonth)-1));
	}

}
