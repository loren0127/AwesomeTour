<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	  
	  
	  #submit_delete:hover{
		color: #212529;
		background-color: #fff;
	    border-color: #ffc107;
	  }
	  #submit_check:hover{
		color: #212529;
		background-color: #fff;
	    border-color: #ffc107;
	  }
</style>
 <body>
 
	<div class="container section-sepa1" align="center" style="margin-top:250px;margin-bottom:250px;">
		<h4>정말 탈퇴하시겠습니까?</h4>
		<h4>확인 버튼을 누르시면 이메일 인증 페이지로 이동합니다.</h4>
		<form action="${pageContext.request.contextPath}/member/delete.do" method="post">
			<div align="center">
				<input type="submit" value="확인" class="btn btn-warning mb-2" id="submit_delete">
				<input type="button" class="btn btn-warning mb-2" value="홈으로" id="submit_check"
									onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</div>
		</form>
	</div>
</body>
