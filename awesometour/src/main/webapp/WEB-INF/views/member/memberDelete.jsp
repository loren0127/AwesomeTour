<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="container" align="center">
		<h4>회원탈퇴</h4>
		<form action="${pageContext.request.contextPath}/member/deleteCheck.do" method="post">
			<div align="center">
				<input type="text" name="code_email" size="120" style="width: 30%" placeholder="이메일" class="form-control" value="${user_email }" readonly="true">
				<br>
				<input type="text" name="code_code" size="120" style="width: 30%" placeholder="코드">
			</div>
			<div align="center">
				<input type="submit" value="인증하기" class="btn btn-warning">
			</div>
		</form>
	</div>







