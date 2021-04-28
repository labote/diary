<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>todoOne</title>
</head>
<body>
	<h1>todoOne</h1>
	<table border="1">
		<thead>
			<tr>
				<td>todoNo</td>
				<td>memberNo</td>
				<td>todoDate</td>
				<td>todoTitle</td>
				<td>todoContent</td>
				<td>todoFontColor</td>
				<td>todoAddDate</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${todo.todoNo}</td>
				<td>${todo.memberNo}</td>
				<td>${todo.todoDate}</td>
				<td>${todo.todoTitle}</td>
				<td>${todo.todoContent}</td>
				<td>${todo.todoFontColor}</td>
				<td>${todo.todoAddDate}</td>
			</tr>
		</tbody>
	</table>
	<a href="${pageContext.request.contextPath}/auth/modifyTodo?todoNo=${todo.todoNo}&targetYear=${todoDate.year}&targetMonth=${todoDate.month}"><button type="button">수정</button></a>
	<a href="${pageContext.request.contextPath}/auth/removeTodo?todoNo=${todo.todoNo}&targetYear=${todoDate.year}&targetMonth=${todoDate.month}"><button type="button">삭제</button></a>
	<a href="${pageContext.request.contextPath}/auth/diary?targetYear=${todoDate.year}&targetMonth=${todoDate.month}"><button>뒤로가기</button></a>
	<a href="${pageContext.request.contextPath}/login"><button>홈</button></a>
</body>
</html>