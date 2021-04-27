package gdu.diary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gdu.diary.vo.Todo;

public class TodoDao {

	// dday select 메서드
	public List<Map<String,Object>> selectTodoDdayList(Connection conn, int memberNo) throws SQLException {
		// 객체 생성 및 초기화
		PreparedStatement stmt = null;
		List<Map<String,Object>> list = new ArrayList<>();
		ResultSet rs = null;
		
		try {
			// SQL문 실행
			stmt = conn.prepareStatement(TodoQuery.SELECT_TODO_DDAY_LIST);
			stmt.setInt(1, memberNo);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Map<String,Object> map = new HashMap<>();
				map.put("todoNo", rs.getInt("todoNo"));
				map.put("todoDate", rs.getString("todoDate"));
				map.put("todoTitle", rs.getString("todoTitle"));
				map.put("dday", rs.getInt("dday"));
				list.add(map);
			}
		} finally {
			stmt.close();
			if(rs!=null) rs.close();
		}
		
		return list;
	}
	
	// todo update 메서드
	public int updateTodo(Connection conn, Todo todo) throws SQLException {
		// 객체 생성 및 초기화
		int rowCnt = 0;
		PreparedStatement stmt = null;
		
		try {
			// SQL문 실행
			stmt = conn.prepareStatement(TodoQuery.UPDATE_TODO_BY_TODONO);
			stmt.setString(1, todo.getTodoTitle());
			stmt.setString(2, todo.getTodoContent());
			stmt.setString(3, todo.getTodoFontColor());
			stmt.setInt(4, todo.getTodoNo());
			System.out.println("selectTodoByDate : " + stmt);
			stmt.executeUpdate();
		} finally { // 할당 해제
			stmt.close();
		}
		
		return rowCnt;
	}
	
	// oo년 oo월 oo일에 있는 todo를 가져오는 메서드
	public Todo selectTodoByDate(Connection conn, int todoNo, int memberNo)  throws SQLException {
		// 객체 생성 및 초기화
		Todo todo = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// SQL문 실행
			stmt = conn.prepareStatement(TodoQuery.SELECT_TODO_BY_DATE);
			stmt.setInt(1, todoNo);
			stmt.setInt(2, memberNo);
			System.out.println("selectTodoByDate : " + stmt);
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				todo = new Todo();
				todo.setTodoNo(rs.getInt("todoNo"));
				todo.setMemberNo(rs.getInt("memberNo"));
				todo.setTodoTitle(rs.getString("todoTitle"));
				todo.setTodoContent(rs.getString("todoContent"));
				todo.setTodoDate(rs.getString("todoDate"));
				todo.setTodoFontColor(rs.getString("todoFontColor"));
				todo.setTodoAddDate(rs.getString("todoAddDate"));
			}
		} finally { // 할당 해제
			stmt.close();
			if(rs!=null) rs.close();
		}
		
		return todo;
	}
	
	// oo년 oo월에 있는 todo list를 가져오는 메서드
	public List<Todo> selectTodoListByDate(Connection conn, int targetYear, int targetMonth, int memberNo) throws SQLException {
		// 객체 생성 및 초기화
		List<Todo> list = new ArrayList<>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// SQL문 실행
			stmt = conn.prepareStatement(TodoQuery.SELECT_TODO_LIST_BY_DATE);
			stmt.setInt(1, targetYear);
			stmt.setInt(2, targetMonth);
			stmt.setInt(3, memberNo);
			System.out.println("selectTodoListByDate : " + stmt); // 디버깅
			
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				Todo todo = new Todo();
				todo.setTodoNo(rs.getInt("todoNo"));
				todo.setTodoTitle(rs.getString("todoTitle"));
				todo.setTodoDate(rs.getString("todoDate"));
				todo.setTodoFontColor(rs.getString("todoFontColor"));
				list.add(todo);
			}
		} finally { // 할당 해제
			stmt.close();
			rs.close();
		}
		
		return list;
	}
	
	// todo insert 메서드
	public int insertTodo(Connection conn, Todo todo) throws SQLException {
		// 객체 생성 및 초기화
		PreparedStatement stmt = null;
		int rowCnt = 0;
		
		try {
			// SQL문 실행
			stmt = conn.prepareStatement(TodoQuery.INSERT_TODO);
			stmt.setInt(1, todo.getMemberNo());
			stmt.setString(2, todo.getTodoDate());
			stmt.setString(3, todo.getTodoTitle());
			stmt.setString(4, todo.getTodoContent());
			stmt.setString(5, todo.getTodoFontColor());
			System.out.println("insertTodo : " + stmt); // 디버깅
			stmt.executeUpdate();
		} finally { // 할당 해제
			stmt.close();
		}
		
		return rowCnt;
	}
	
	// 00년 00월 00일 todo 삭제 메서드
	public int deleteTodo(Connection conn, int memberNo, int todoNo) throws SQLException {
		// 객체 생성 및 초기화
		PreparedStatement stmt = null;
		int rowCnt = 0;
		
		try {
			// SQL문 실행
			stmt = conn.prepareStatement(TodoQuery.DELETE_TODO_BY_DATE);
			stmt.setInt(1, memberNo);
			stmt.setInt(2, todoNo);
			System.out.println("deleteTodoByMember : " + stmt); // 디버깅
			rowCnt = stmt.executeUpdate();
		} finally { // 할당 해제
			stmt.close();
		}
		
		return rowCnt;
	}
	
	// 회원 탈퇴 트랜잭션
	public int deleteTodoListByMember(Connection conn, int memberNo) throws SQLException {
		// 객체 생성 및 초기화
		PreparedStatement stmt = null;
		int rowCnt = 0;
		
		try {
			// SQL문 실행
			stmt = conn.prepareStatement(TodoQuery.DELETE_TODO_LIST_BY_MEMBER);
			stmt.setInt(1, memberNo);
			System.out.println("deleteTodoByMember : " + stmt); // 디버깅
			rowCnt = stmt.executeUpdate();
		} finally { // 할당 해제
			stmt.close();
		}
		
		return rowCnt;
	}
}
