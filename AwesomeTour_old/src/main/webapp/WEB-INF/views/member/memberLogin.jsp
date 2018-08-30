<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="page-main-style">
	<h1>회원로그인</h1>
	<form:form commandName="command" action="login.do" id="login_form">
		<form:errors element="div" cssClass="error-color"/>	
		<ul>
			<li>
				<label for="member_email">이메일</label>
				<form:input path="member_email"/>
				<form:errors path="member_email" cssClass="error-color"/>
			</li>
			<li>
				<label for="member_passwd">비밀번호</label>
				<form:password path="member_passwd"/>
				<form:errors path="member_passwd" cssClass="error-color"/>
			</li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송">
			<input type="button" value="홈으로"
			      onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>







