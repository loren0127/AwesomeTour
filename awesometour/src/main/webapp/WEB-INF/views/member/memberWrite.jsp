<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/member.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/confirmNickname.js"></script>

	<style>
	.filebox label { 
	display: inline-block; 
	padding: .5em .75em; 
	color: #000000; 
	font-size: inherit; 
	line-height: normal; 
	vertical-align: middle; 
	background-color: #f7c820; 
	cursor: pointer; 
	border: 1px solid #ebebeb; 
	border-bottom-color: #e2e2e2; 
	border-radius: .25em; 
	}
	.filebox input[type="file"] { 
	/* 파일 필드 숨기기 */ 
	position: absolute; 
	width: 1px; 
	height: 1px; 
	padding: 0; 
	margin: -1px; 
	overflow: hidden; 
	clip:rect(0,0,0,0); 
	border: 0; 
	}

</style>
<div class="container section-sepa1">
	<section class="about_history_area section_gap">
		<div class="row">
			<div class="col-md-6 d_flex align-items-center">
				<div class="about_content ">
					<div align="center">
						<h4>회원가입</h4>
						<form:form action="write.do" id="register_form" name="register_form" commandName="command" enctype="multipart/form-data">
							<form:errors element="div" cssClass="error-color" />
							<div class="mt-10">
								<form:input path="member_email" value="${code_email}" readonly="true" style="width:200px;" class="form-control" size="20" />
								<form:errors path="member_email" cssClass="error-color" />
							</div>
							<br>
							<div class="mt-10">
								<form:password path="member_passwd" class="form-control"
									placeholder="비밀번호" style="width:200px;" />
								<form:errors path="member_passwd" cssClass="error-color" />
							</div>
							<br>
							<div class="mt-10">
								<input type="password" class="form-control"
									name="member_passwd2" id="member_passwd2" placeholder="비밀번호 확인"
									style="width: 200px;">
									 <span id="message_check"> </span><br>
								<span id="message_unique"></span><br>
								<span id="message_unique2"></span><br>
								<span id="message_unique3"></span><br>
								<span id="message_unique4"></span><br>
							</div>

							<div class="mt-10">
								<form:input path="member_nickname" class="form-control"
									placeholder="닉네임" style="width:200px;" />
								<form:errors path="member_nickname" cssClass="error-color"/><br>
								<span id="message_nickname"></span><br>
								<input type="button" id="confirm_nickname" value="닉네임 중복체크"  class="btn btn-warning">
							</div>
							<br>	
							<div class="mt-10" >
								<div class="filebox">
									<label for="upload">프로필</label><br>
								 	<input type="file" name="upload" id="upload" style="margin-left:100px;"/>	
								</div>
							</div>
							<br>
							<div class="mt-10">
								<form:input path="member_content" class="form-control"
									placeholder="자기소개" style="width:200px;" />
								<form:errors path="member_content" cssClass="error-color" />
							</div>
							<br>
							<div class="align-center">
								<input type="submit" value="다음" class="btn btn-warning">
								<input type="button" class="btn btn-warning" value="홈으로"
									onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
							</div>
						</form:form>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<img class="img-fluid"
					src="${pageContext.request.contextPath}/resources/images/rome2.jpg"
					alt="img">
			</div>
		</div>
	</section>
</div>
