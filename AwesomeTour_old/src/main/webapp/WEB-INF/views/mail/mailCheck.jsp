<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
</head>
<body>
	<div class="container" align="center">
		<h4>mailCheck</h4>
		<form action="${pageContext.request.contextPath}/mail/mailChecking.do" method="post">
			<div align="center">
				<c:if test="${email != null }">
					<input type="text" name="code_email" size="120" style="width: 30%" value="${email }" readonly="true" >
					<br>
					<input type="text" name="code_code" size="120" style="width: 30%" placeholder="코드">
				</c:if>
				<c:if test="${email == null }">
					<input type="text" name="code_email" size="120" style="width: 30%" placeholder="이메일" >
					<br>
					<input type="text" name="code_code" size="120" style="width: 30%" placeholder="코드">
				</c:if>
			</div>
			<div align="center">
				<input type="submit" value="인증하기" class="btn btn-warning">
			</div>
		</form>
	</div>
</body>
</html>
