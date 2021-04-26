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

@WebServlet("/auth/addTodo")
public class AddTodoController extends HttpServlet {
	
	public TodoService todoService;
	
	// todo 입력폼
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// request 호출
		int year = Integer.parseInt(request.getParameter("targetYear"));
		int month = Integer.parseInt(request.getParameter("targetMonth"));
		int day = Integer.parseInt(request.getParameter("targetDate"));
		
		// 디버깅
		System.out.println("year : " + year);
		System.out.println("month : " + month);
		System.out.println("day : " + day);
		
		// 전처리
		TodoDate todoDate = new TodoDate();
		todoDate.setYear(year);
		todoDate.setMonth(month);
		todoDate.setDay(day);
		
		// 디버깅
		System.out.println("todoDate : " + todoDate);
		
		// View forward
		request.setAttribute("todoDate",todoDate);
		request.getRequestDispatcher("/WEB-INF/view/auth/addTodo.jsp").forward(request, response);
	}
	
	// todo 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// request 호출
		HttpSession session = request.getSession();
		int memberNo = ((Member)session.getAttribute("sessionMember")).getMemberNo();
		String todoDate = request.getParameter("todoDate");
		String todoTitle = request.getParameter("todoTitle");
		String todoContent = request.getParameter("todoContent");
		String todoFontColor = request.getParameter("todoFontColor");
		
		// 디버깅
		System.out.println("memberNo : " + memberNo);
		System.out.println("todoDate : " + todoDate);
		System.out.println("todoTitle : " + todoTitle);
		System.out.println("todoContent : " + todoContent);
		System.out.println("todoFontColor : " + todoFontColor);
		
		// 전처리
		Todo todo = new Todo();
		todo.setMemberNo(memberNo);
		todo.setTodoDate(todoDate);
		todo.setTodoTitle(todoTitle);
		todo.setTodoContent(todoContent);
		todo.setTodoFontColor(todoFontColor);
		
		// 디버깅
		System.out.println("todo : " + todo);
		
		// 서비스 호출
		this.todoService = new TodoService();
		this.todoService.addTodo(todo);
		
		// sendRedirect
		String[] arr = todoDate.split("-"); // arr[0] = "2021"
		response.sendRedirect(request.getContextPath()+"/auth/diary?targetYear="+arr[0]+"&targetMonth="+(Integer.parseInt(arr[1])-1));
	}
}
