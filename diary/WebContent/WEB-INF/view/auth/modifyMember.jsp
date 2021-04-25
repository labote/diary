<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/auth/modifyMember" method="post">
		<div>
			memberPw : 
		</div>
		<div>
			<input type="password" name="memberPw">
		</div>
		<div>
			<button type="submit">수정</button>
			<button type="button" onclick="history.back()">취소</button>
		</div>
	</form>
</body>
</html>