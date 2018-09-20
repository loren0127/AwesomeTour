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
	
	 @media screen and (max-width: 1020px){
	  	#div1{
	  		display:none;
	  	}
	  	.col-md-6 d_flex align-items-center{
	  		padding-left: 20%;
	  	}
	  }
	  @media screen and (max-width: 420px){
	  	.col-md-6 d_flex align-items-center{
	  		padding-left: 0%;
	  	}
	  }
	  #confirm_nickname:hover{
		color: #212529;
		background-color: #fff;
	    border-color: #ffc107;
	  }#profile:hover{
		color: #212529;
		background-color: #fff;
	    border-color: #ffc107;
	  }
	  #next_write:hover{
		color: #212529;
		background-color: #fff;
	    border-color: #ffc107;
	  }
	  #home_write:hover{
		color: #212529;
		background-color: #fff;
	    border-color: #ffc107;
	  }
	  /* body{
	  	background: url(../resources/images/rome2.jpg) no-repeat center center fixed; 
   		 -webkit-background-size: cover;
    		-moz-background-size: cover;
   			 -o-background-size: cover;
   			 background-size: cover;
		  } */
</style>
<script type="text/javascript">
	function getThumbnailPrivew(input, targetId) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function (e) {
	            var element = window.document.getElementById(targetId);
	            element.setAttribute("src", e.target.result);
	        }
	        reader.readAsDataURL(input.files[0]);
	    }
	}
</script>

<div align="center">
	<img class="img-fluid" src="${pageContext.request.contextPath}/resources/images/member_write.png" alt="img" id="div1" style="max-width:100%;margin-top:5%;">
</div>
<div class="container section-sepa1">
	
	<div class="section-top-border">
		<h3 class="mb-30 title_color">회원가입</h3>
			<form:form action="write.do" id="register_form" name="register_form" commandName="command" enctype="multipart/form-data">
			<form:errors element="div" cssClass="error-color" />
				<div class="row">
					<div class="col-md-4">
						<div class="single-defination">
							<div>
								<h4 class="mb-20">순서 1</h4>
								<form:input path="member_email" value="${code_email}" readonly="true" style="width:200px;" class="form-control" size="20" />
								<form:errors path="member_email" cssClass="error-color" />
							</div>
							<br>
							<div>
								<form:password path="member_passwd" class="form-control" placeholder="비밀번호" style="width:200px;" />
								<form:errors path="member_passwd" cssClass="error-color" />
							</div>
							<br>
							<div>
								<input type="password" class="form-control"
									name="member_passwd2" id="member_passwd2" placeholder="비밀번호 확인"
									style="width: 200px;">
									 <span id="message_check"> </span><br>
								<span id="message_unique"></span><br>
								<span id="message_unique2"></span><br>
								<span id="message_unique3"></span><br>
								<span id="message_unique4"></span><br>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="single-defination">
							<h4 class="mb-20">순서 2</h4>
							<div >
								<form:input path="member_nickname" class="form-control" placeholder="닉네임" style="width:250px;margin:10px 0" />
								<form:errors path="member_nickname" cssClass="error-color"/>
								<input type="button" id="confirm_nickname" value="닉네임 중복체크"  class="btn btn-warning mb-2">
								<span id="message_nickname" style="margin-left:5px"></span><br>
							</div>
							<br>
							<div class="filebox">
								<img id="data-type"  src="../resources/images/noImg.jpg" class="img-circle" width="40%" height="40%" style="border:1px solid silver">
								<input type="file" name="upload" id="upload" accept=".bmp, .gif, .jpg, .png" onchange="getThumbnailPrivew(this, 'data-type');">	<br>
								<label for="upload" class="btn btn-warning mb-2" id="profile" style=";margin-top: 10px;">프로필</label>
							</div>
						</div>
					</div>
					<div class="col-md-4">
						<div class="single-defination">
							<h4 class="mb-20">순서 3</h4>
							<div>
								<textarea name="member_content" id="member_content" class="form-control" placeholder="자기소개" style="width:200px; height:200px" ></textarea>
								<form:errors path="member_content" cssClass="error-color" />
							</div>
							<br><br>
							<div>
								<input type="submit" value="다음" class="btn btn-warning mb-2" id="next_write">
								<input type="button" class="btn btn-warning mb-2" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'" id="home_write">
							</div>
						</div>
					</div>
				</div>
			</form:form>
	</div>
</div>
<%-- <div class="container section-sepa1">
	<section class="about_history_area section_gap">
		<div class="row">
			<div class="col-md-6 d_flex align-items-center" style="padding-left: 40%;">
				<div class="about_content">
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
									<label for="upload">프로필</label>
									<input type="file" name="upload" id="upload" accept=".bmp, .gif, .jpg, .png" onchange="getThumbnailPrivew(this, 'data-type');">	
								</div>
							</div>
							<div class="mt-10">
								<img id="data-type" class="img-circle" width="70%" height="70%">
								<img src="imageView.do?member_email=${member.member_email}" style="max-width:500px" id="data-type" class="data-type" name="data-type">
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
					alt="img" id="div1">
			</div>
		</div>
	</section>
</div> --%>

