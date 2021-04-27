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

@WebServlet("/auth/modify")
public class ModifyTodoController extends HttpServlet {
	
	private TodoService todoService;
	
	// 변경 폼
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// request 호출
		HttpSession session = request.getSession();
		int memberNo = ((Member)session.getAttribute("sessionMember")).getMemberNo();
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
	
		request.getRequestDispatcher("/WEB-INF/view/auth/modifyTodo.jsp").forward(request, response);
	}
	
	// 변경 액션
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
