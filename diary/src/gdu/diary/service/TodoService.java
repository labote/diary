package gdu.diary.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import gdu.diary.dao.TodoDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Todo;

public class TodoService {
	
	private DBUtil dbUtil;
	private TodoDao todoDao;
	
	// modify 메서드
	public int modifyTodo(Todo todo) {
		// 객체 생성 및 초기화
		this.todoDao = new TodoDao();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		int rowCnt = 0;
		
		try {
			// DB 연결 및 Dao 호출
			conn = this.dbUtil.getConnection();
			rowCnt = this.todoDao.updateTodo(conn, todo);
			conn.commit();
		} catch(Exception e) { // 예외 처리
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowCnt;
	}
	
	// remove 메서드
	public int removeTodo(int memberNo, int todoNo) {
		// 객체 생성 및 초기화
		this.todoDao = new TodoDao();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		int rowCnt = 0;
		
		try {
			// DB 연결 및 Dao 호출
			conn = this.dbUtil.getConnection();
			rowCnt = this.todoDao.deleteTodo(conn, memberNo, todoNo);
			conn.commit();
		} catch(Exception e) { // 예외 처리
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowCnt;
	}
	
	// 해당 Todo를 가져오는 메서드
	public Todo getTodo(int todoNo, int memberNo) {
		// 객체 생성 및 초기화
		this.todoDao = new TodoDao();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		Todo returntodo = null;
		
		try {
			// DB 연결 및 Dao 호출
			conn = this.dbUtil.getConnection();
			returntodo = this.todoDao.selectTodoByDate(conn, todoNo, memberNo);
			conn.commit();
		} catch(Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return returntodo;
	}
	
	// add 메서드
	public int addTodo(Todo todo) {
		
		// 객체 생성 및 초기화
		this.todoDao = new TodoDao();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		int rowCnt = 0;
		try {
			// DB 연결 및 Dao 호출
			conn = this.dbUtil.getConnection();
			rowCnt = this.todoDao.insertTodo(conn, todo);
			conn.commit();
		} catch(Exception e) { // 예외 처리
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return rowCnt;
	}
}
