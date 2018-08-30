<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<div class="page-main-style">
	<h1>회원가입</h1>
	<form:form action="write.do" id="register_form" commandName="command" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color" />	
		<ul>
			<li><!--    -->
				<label for="member_email">이메일</label>
				<form:input path="member_email" value="${code_email}" readonly="true" />
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
		
		<br>
		<br>
		<div class="align-center">
			<input type="submit" value="가입">
			<input type="button" value="홈으로"onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>







