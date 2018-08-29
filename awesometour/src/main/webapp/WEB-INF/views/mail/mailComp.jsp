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
	<div class="container section-sepa1" align="center">
		<h4>회원가입</h4>
		<input type="button" value="인증하기" class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/mail/mailCheck.do'">
		<input type="button" value="홈으로" class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
	</div>
</body>
</html>
