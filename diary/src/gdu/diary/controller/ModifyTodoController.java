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

@WebServlet("/auth/modifyTodo")
public class ModifyTodoController extends HttpServlet {
	
	private TodoService todoService;
	private TodoDate todoDate;
	
	// 변경 폼
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request 호출
		HttpSession session = request.getSession();
		int memberNo = ((Member)session.getAttribute("sessionMember")).getMemberNo();
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		int targetYear = Integer.parseInt(request.getParameter("targetYear"));
		int targetMonth = Integer.parseInt(request.getParameter("targetMonth"));
		
		// 디버깅
		System.out.println("memberNo : " + memberNo);
		System.out.println("todoNo : " + todoNo);
		System.out.println("targetYear : " + targetYear);
		System.out.println("targetMonth : " + targetMonth);
		
		// 의존 객체 생성 및 서비스 호출
		this.todoService = new TodoService();
		this.todoDate = new TodoDate();
		Todo todo = this.todoService.getTodo(todoNo, memberNo);
		todoDate.setYear(targetYear);
		todoDate.setMonth(targetMonth);
		
		
		// View forward
		request.setAttribute("todoDate", todoDate);
		request.setAttribute("todo", todo);
		request.getRequestDispatcher("/WEB-INF/view/auth/modifyTodo.jsp").forward(request, response);
	}
	
	// 변경 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// request 호출
		String todoTitle = request.getParameter("todoTitle");
		String todoContent = request.getParameter("todoContent");
		String todoFontColor = request.getParameter("todoFontColor");
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		String targetYear = request.getParameter("targetYear");
		String targetMonth = request.getParameter("targetMonth");
		
		// 디버깅
		System.out.println("todoTitle : " + todoTitle);
		System.out.println("todoContent : " + todoContent);
		System.out.println("todoFontColor : " + todoFontColor);
		System.out.println("todoNo : " + todoNo);
		System.out.println("targetYear : " + targetYear);
		System.out.println("targetMonth : " + targetMonth);
		
		// 전처리
		Todo todo = new Todo();
		todo.setTodoNo(todoNo);
		todo.setTodoTitle(todoTitle);
		todo.setTodoContent(todoContent);
		todo.setTodoFontColor(todoFontColor);
		
		System.out.println("todo : " + todo);
		
		// 의존 객체 생성 및 서비스 호출
		this.todoService = new TodoService();
		this.todoService.modifyTodo(todo);
		
		// redirect
		response.sendRedirect(request.getContextPath() + "/auth/todoOne?todoNo=" + todoNo + "&targetYear="+targetYear+"&targetMonth="+(Integer.parseInt(targetMonth)-1));
	}

}
