<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>diary</title>
</head>
<body>
	<h1>DDAY LIST</h1>
	<div>
		<table border="1">
			<tr>
				<th>todoDate</th>
				<th>todoTitle</th>
				<th>dday</th>
			</tr>
			<c:forEach var="m" items="${diaryMap.ddayList}">
				<tr>
					<td>${m.todoDate}</td>
					<td><a href="${pageContext.request.contextPath}/auth/todoOne?targetYear=${diaryMap.targetYear}&targetMonth=${diaryMap.targetMonth+1}&todoNo=${m.todoNo}">${m.todoTitle}</a></td>
					<td>-${m.dday}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
	<a href="${pageContext.request.contextPath}/auth/diary?targetYear=${diaryMap.targetYear}&targetMonth=${diaryMap.targetMonth-1}">이전달</a>
	${diaryMap.targetYear}년 ${diaryMap.targetMonth+1}월
	<a href="${pageContext.request.contextPath}/auth/diary?targetYear=${diaryMap.targetYear}&targetMonth=${diaryMap.targetMonth+1}">다음달</a>
	<table border="1" width="90%">
		<tr>
			<td>일</td>
			<td>월</td>
			<td>화</td>
			<td>수</td>
			<td>목</td>
			<td>금</td>
			<td>토</td>
		</tr>
		<tr>
			<c:forEach begin="1" end="${diaryMap.totalCell}" var="i" step="1">
				<c:set var="num" value="${i-diaryMap.startBlank}"></c:set>
				<td>
					<c:if test="${num>0 && num<=diaryMap.endDay}">
						<a href="${pageContext.request.contextPath}/auth/addTodo?targetYear=${diaryMap.targetYear}&targetMonth=${diaryMap.targetMonth+1}&targetDate=${num}">
							<div>${num}</div>
							<div>
								<c:forEach var="todo" items="${diaryMap.todoList}">
									<c:if test="${todo.todoDate == num}">
										<div style="background-color: ${todo.todoFontColor}"><a href="${pageContext.request.contextPath}/auth/todoOne?targetYear=${diaryMap.targetYear}&targetMonth=${diaryMap.targetMonth+1}&targetDate=${num}&todoNo=${todo.todoNo}">${todo.todoTitle}...</a></div>
										<!-- todoOne 상세정보 - 수정 - 삭제 -->
									</c:if>
								</c:forEach>
							</div>
						</a>
					</c:if>
					<c:if test="${num<=0 || num>diaryMap.endDay}">
						&nbsp;
					</c:if>
				</td>
				<c:if test="${i%7 == 0}">
					</tr><tr>
				</c:if>
			</c:forEach>
		</tr>
	</table>
	<a href="${pageContext.request.contextPath}/login"><button>홈</button></a>
</body>
</html>