<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>비밀번호 체크</title>
</head>
<body>
	<div class="container" align="center">
		<h4>Passwd Check</h4>
		<form action="${pageContext.request.contextPath}/member/detailChecking.do" method="post">
			<div align="center">
				<input type="text" name="member_email" size="120" style="width: 30%" value="${user_email }" readonly="true" >
				<br>
				<input type="text" name="member_passwd" size="120" style="width: 30%" placeholder="비밀번호">
			</div>
			<div align="center">
				<input type="submit" value="확인" class="btn btn-warning">
			</div>
		</form>
	</div>
</body>
</html>