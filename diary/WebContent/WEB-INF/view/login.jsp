<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>login</title>
</head>
<body>
	<!-- 로그인 전 -->

	<c:if test="${sessionMember == null}">
		<h1>login</h1>
		<form action="${pageContext.request.contextPath}/login" method="post">
			<div>
				<div>Id : </div>
				<div><input type="text" name="memberId" value="goodee@gdu.co.kr"></div>
				<div>Pw : </div>
				<div><input type="password" name="memberPw" value="1234"></div>
				<button type="submit">로그인</button>
			</div>
		</form>
	</c:if>
	
	<!-- 로그인 후 -->
	<c:if test="${sessionMember != null}">
		${sessionMember.memberId} 님 반갑습니다.
		<div><a href="/auth/">다이어리</a></div>
		<div><a href="${pageContext.request.contextPath}/auth/logout">로그아웃</a></div>
	</c:if>
</body>
</html>