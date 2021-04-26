package gdu.diary.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gdu.diary.dao.TodoDao;
import gdu.diary.util.DBUtil;
import gdu.diary.vo.Todo;

public class DiaryService {
	
	private TodoDao todoDao;
	private DBUtil dbUtil;
	
	// 달력 메서드
	public Map<String, Object> getDiary(String targetYear, String targetMonth, int memberNo){
		
		// 타겟 년, 월, 일(날짜)
		// 타겟 달의 1일(날짜)
		// 타겟 달의 마지막 일 -> endDay
		
		// startBlank + endDay + endBlank
		// 전체 셀의 개수(마지막 일의 숫자보다는 크고 7로 나누어 떨어져야 한다
		// 앞의 빈 셀 개수 -> startBlank
		// 타겟 달 숫자가 나와야 할 셀의 개수(1~마지막 날짜)
		// 뒤의 빈 셀의 개수 -> endBlank -> 7-(startBlank + endDay)%7
		
		Map<String, Object> map = new HashMap<String, Object>();
		Calendar target = Calendar.getInstance(); // new 안되는 이유?
		
		if(targetYear != null) {
			target.set(Calendar.YEAR, Integer.parseInt(targetYear));
		}
		
		if(targetMonth != null) {
			target.set(Calendar.MONTH, Integer.parseInt(targetMonth));
		}
		
		/*
		 * int numTargetMonth = 0; int numTargetYear = 0;
		 * 
		 * if(targetMonth != null && targetYear != null) {
		 * 
		 * numTargetYear = Integer.parseInt(targetYear); numTargetMonth =
		 * Integer.parseInt(targetMonth);
		 * 
		 * if(numTargetMonth == 0) { numTargetYear--; numTargetMonth = 12; }
		 * if(numTargetMonth == 13) { numTargetYear++; numTargetMonth = 1; }
		 * 
		 * target.set(Calendar.YEAR, numTargetYear); target.set(Calendar.MONTH,
		 * numTargetMonth-1); }
		 */
		
		target.set(Calendar.DATE, 1);
		
		// target월의 1숫자 앞에 와야할 빈 셀의 개수
		int startBlank = target.get(Calendar.DAY_OF_WEEK) - 1;
		// 마지막 날짜
		int endDay = target.getActualMaximum(Calendar.DATE);
		int endBlank = 0;
		
		if((startBlank + endDay)%7 != 0) {
			endBlank = 7-(startBlank + endDay)%7;
		}
		int totalCell = startBlank + endDay + endBlank;
		
		map.put("targetYear", target.get(Calendar.YEAR));
		map.put("targetMonth", target.get(Calendar.MONTH));
		map.put("startBlank", startBlank);
		map.put("endDay", endDay);
		map.put("totalCell", totalCell);
		
		// 2. targetYear, targetMonth(0이면 1월, 1이면 2월)에 해당하는 todo목록 가져와서 추가
		
		// 객체 생성 및 초기화
		this.todoDao = new TodoDao();
		this.dbUtil = new DBUtil();
		Connection conn = null;
		List<Todo> todoList = null;
		
		try {
			// DB 연결 및 Dao 호출
			conn = this.dbUtil.getConnection();
			todoList = this.todoDao.selectTodoListByDate(conn, target.get(Calendar.YEAR), target.get(Calendar.MONTH)+1, memberNo);
			conn.commit();
		} catch (SQLException e) {
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
		
		map.put("todoList", todoList);
		
		return map;
	}
}
