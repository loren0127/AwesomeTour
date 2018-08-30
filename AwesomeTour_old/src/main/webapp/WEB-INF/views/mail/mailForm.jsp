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
		<h4>회원가입</h4>
		<form action="${pageContext.request.contextPath}/mail/mailSending.do" method="post">
			<div align="center">
				<!-- 받는 사람 이메일 -->
				<input type="text" name="email" size="120" style="width: 30%" placeholder="이메일" class="form-control">
				<br>
			</div>
			<div align="center">
				<input type="submit" value="인증하기" class="btn btn-warning">
				<input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</div>
		</form>
	</div>
</body>
</html>
