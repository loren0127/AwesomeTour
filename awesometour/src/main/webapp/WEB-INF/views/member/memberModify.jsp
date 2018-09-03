<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/confirmPasswd.js"></script>
<div class="container section-sepa1" style="margin-top: 100px;">
	<h1>회원 정보 수정</h1>
	<br> <br>
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
				<label for="member_current_passwd">현재 비밀번호</label> <input
					type="password" name="member_current_passwd"
					id="member_current_passwd" class="form-control">
					<input type="button" id="passwd_check" value="비밀번호 확인">
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
			</div>
			<div class="form-group">
				<label for="member_nickname">닉네임</label>
				<form:input path="member_nickname" class="form-control"
					value="${member.member_nickname}" readonly="true" />
				<form:errors path="member_nickname" cssClass="error-color" />
			</div>
			
			<div class="form-group">
				<label for="upload">프로필</label>
				<input type="file" name="upload" id="upload"/>
			</div>
			
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

<%-- <div class="page-main-style">
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




