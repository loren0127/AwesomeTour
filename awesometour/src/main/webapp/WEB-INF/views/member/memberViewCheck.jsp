<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/member.js"></script>
<style>
	  @media screen and (max-width: 767px){
	  	#image{
	  		display:none;
	  	}
	  
	  }
	  
	  #check_view:hover{
		color: #212529;
		background-color: #fff;
	    border-color: #ffc107;
	  }
</style>
<div class="container section-sepa1" style="height: 800px;">
	<section class="about_history_area section_gap"
		style="margin-top: 150px;">
		<div class="container">
			<div class="row">
				<div class="col-md-6 d_flex align-items-center">
					<div class="about_content " style="margin-top: 85px;">
						<form
							action="${pageContext.request.contextPath}/member/detailChecking.do"
							method="post" id="view_Check" name="view_Check">
							<div align="center">
								<input type="text" name="member_email" style="max-width:500px;" size="180" value="${user_email }" readonly="true" class="form-control"> <br>
								<input type="password" name="member_passwd" id="member_passwd" style="max-width:500px;" size="180" placeholder="비밀번호" class="form-control">
							</div>
							<br>
							<div align="center">
								<input type="submit" value="확인" class="btn btn-warning mb-2" id="check_view">
							</div>
						</form>
					</div>
				</div>
				<div class="col-md-6">
					<img class="img-fluid"
						src="${pageContext.request.contextPath}/resources/images/rome.jpg"
						alt="img" id="image">
				</div>
			</div>
		</div>
	</section>
</div>

<%-- <title>비밀번호 체크</title>
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
</html> --%>