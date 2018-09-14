<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/confirmPasswd.js"></script>
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
	/* .filebox input[type="file"] { 
	/* 파일 필드 숨기기 */ 
	position: absolute; 
	width: 1px; 
	height: 1px; 
	padding: 0; 
	margin: -1px; 
	overflow: hidden; 
	clip:rect(0,0,0,0); 
	border: 0; 
	} */
	
	 @media screen and (max-width: 1020px){
	  	#div1{
	  		display:none;
	  	}
	  
	  }
</style>
<div class="container section-sepa1" style="margin-top: 100px;">
	<h1>회원 정보 수정</h1>
	<br> <br>
	<div style="float:right;padding:250px 150px;border-width:10px;border-color:#ffc004;border-style:solid;" id="div1">
        		회원수정 페이지입니다.<br>
        		현재 비밀번호와 변경하실<br>
        		비밀번호를 입력하신 후<br>
        		수정 버튼을 클릭해주세요.
        </div>
	<div class="col-md-6 col-md-offset-3">
		<form:form commandName="command" action="update.do" id="modify_form"
			enctype="multipart/form-data">
			<form:errors element="div" cssClass="error-color" />
			<div class="form-group">
				<label for="member_email">이메일</label>
				<form:input path="member_email" value="${member.member_email}"
					readonly="true" class="form-control" />
				<form:errors path="member_email" cssClass="error-color" />
			</div>
			<div class="form-group">
				<label for="member_current_passwd">현재 비밀번호</label><br>
				<input type="password" name="member_current_passwd"  id="member_current_passwd" class="form-control" style="max-width:200px; display:inline;">
					<input type="button" id="passwd_check" value="비밀번호 확인" class="btn btn-warning" >
					<span id="message_current_passwd"></span>
			</div>
			<div class="form-group">
				<label for="member_passwd">변경 비밀번호</label>
				<form:password path="member_passwd" class="form-control" />
				<form:errors path="member_passwd" cssClass="error-color" />
			</div>
			<div class="form-group">
				<label for="member_passwd2">비밀번호 확인</label> <input type="password"
					name="member_passwd2" id="member_passwd2" class="form-control">
				<span id="message_member_passwd"></span>
				<span id="message_member_passwd2"></span>
				<span id="message_member_passwd3"></span>
				<span id="message_member_passwd4"></span>
				<span id="message_member_passwd5"></span>
			</div>
			<div class="form-group">
				<label for="member_nickname">닉네임</label>
				<form:input path="member_nickname" class="form-control"
					value="${member.member_nickname}" readonly="true" />
				<form:errors path="member_nickname" cssClass="error-color" />
			</div>
			
			<div class="form-group">
				<div class="filebox">
					<label for="upload">프로필</label>
					<input type="file" name="upload" id="upload"/>
				</div>
			</div>
			<div class="align-center">
				<img src="imageView.do?member_email=${member.member_email}" style="max-width:500px" id="data-type">
			</div>
			<br>
			<div class="form-group">
				<label for="member_content">자기소개</label>
				<form:textarea path="member_content"  class="form-control" value="${member.member_content}" />
				<form:errors path="member_content" cssClass="error-color"/>
				
			</div>
			<div class="form-group text-center">
				<input type="submit" value="다음" class="btn btn-default"> <input
					type="button" value="홈으로" class="btn btn-warning"
					onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</div>
		</form:form>
	</div>
</div> 
<%-- 
<div class="page-main-style">
	<h1>회원정보수정</h1>
	<form:form commandName="command" action="update.do" id="modify_form" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color"/>	
		<ul>
			<li><!--    -->
				<label for="member_email">이메일</label>
				<form:input path="member_email" value="${member.member_email}" readonly="true"/>
				<form:errors path="member_email" cssClass="error-color"/>
			</li>
			<li>
				<label for="member_passwd">비밀번호</label>
				<form:password path="member_passwd"/>
				<form:errors path="member_passwd" cssClass="error-color"/>
			</li>
			<li>
				<label for="member_nickname">닉네임</label>
				<form:input path="member_nickname"/>
				<form:errors path="member_nickname" cssClass="error-color"/>
			</li>
			<li>
				<label for="upload">프로필</label>
				<input type="file" name="upload" id="upload"/>
			</li>
			<li>
				<label for="member_content">자기소개</label>
				<form:input path="member_content"/>
				<form:errors path="member_content" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="홈으로"
			      onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>

 --%>

