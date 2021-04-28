<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>modifyTodo</title>
</head>
<body>
	<h1>modifyTodo</h1>
	<form action="${pageContext.request.contextPath}/auth/modifyTodo" method="post">
		<input type="hidden" name="todoNo" value="${todo.todoNo}">
		<input type="hidden" name="targetYear" value="${todoDate.year}">
		<input type="hidden" name="targetMonth" value="${todoDate.month}">
		<table border="1">
			<tr>
				<td>todoNo</td>
				<td>memberNo</td>
				<td>todoDate</td>
				<td>todoTitle</td>
				<td>todoContent</td>
				<td>todoFontColor</td>
				<td>todoAddDate</td>
			</tr>
			<tr>
				<td>${todo.todoNo}</td>
				<td>${todo.memberNo}</td>
				<td>${todo.todoDate}</td>
				<td><input type="text" name="todoTitle" placeholder="${todo.todoTitle}"></td>
				<td><input type="text" name="todoContent" placeholder="${todo.todoContent}"></td>
				<td><input type="text" name="todoFontColor" placeholder="${todo.todoFontColor}"></td>
				<td>${todo.todoAddDate}</td>
			</tr>
		</table>
		<button type="submit">수정</button>
		<button type="button" onclick="history.back()">취소</button>
	</form>
</body>
</html>