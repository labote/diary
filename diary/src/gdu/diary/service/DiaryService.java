package gdu.diary.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class DiaryService {
	
	// 달력 메서드
	public Map<String, Object> getDiary(String targetYear, String targetMonth){
		
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
		
		return map;
	}
}
