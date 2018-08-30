<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">



<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<!-- Include all compiled plugins (below), or include individual files as needed -->

<title>회원가입</title>
</head>
<body>
	<div class="container" align="center">
		<h4>회원가입</h4>
		<input type="button" value="인증하기"onclick="location.href='${pageContext.request.contextPath}/mail/mailCheck.do'">
		<input type="button" value="홈으로"onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
	</div>
</body>
</html>
