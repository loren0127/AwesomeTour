<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container" align="center">
		<h4>정말 삭제하시겠습니까?</h4>
		<h4>확인 버튼을 누르시면 인증 페이지로 넘어갑니다.</h4>
		<form action="${pageContext.request.contextPath}/member/delete.do" method="post">
			<div align="center">
				<input type="submit" value="확인" class="btn btn-warning">
			</div>
		</form>
	</div>
</body>
</html>