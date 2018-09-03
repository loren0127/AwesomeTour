<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <body>
	<div class="container section-sepa1" align="center" style="margin-top:250px;margin-bottom:250px;">
		<h4>정말 탈퇴하시겠습니까?</h4>
		<h4>확인 버튼을 누르시면 이메일 인증 페이지로 이동합니다.</h4>
		<form action="${pageContext.request.contextPath}/member/delete.do" method="post">
			<div align="center">
				<input type="submit" value="확인" class="btn btn-white">
				<input type="button" class="btn btn-warning" value="홈으로"
									onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</div>
		</form>
	</div>
</body>
