<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<div class="page-main-style">
	<h1>회원가입2</h1>
	<form:form action="success.do" id="register_form" commandName="command" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color" />	
		<div class="align-center">
			<br>
			<input type="hidden" name ="member_email" value="${command.member_email}">
			<input type="checkbox" name="ch" value="야외활등">야외활동
			<input type="checkbox" name="ch" value="기술">기술
			<input type="checkbox" name="ch" value="스포츠">스포츠
			<input type="checkbox" name="ch" value="학습">학습
			<input type="checkbox" name="ch" value="사진촬영">사진촬영
			<input type="checkbox" name="ch" value="음식">음식
			<input type="checkbox" name="ch" value="건강과 웰빙">건강과 웰빙
			<input type="checkbox" name="ch" value="글쓰기">글쓰기
			<input type="checkbox" name="ch" value="게임">게임
		</div>
		<div class="align-center">
			<input type="radio" name="ra" value="외향">외향
			<input type="radio" name="ra" value="내향">내향
		</div>
		<div class="align-center">
			<input type="submit" value="가입">
			<input type="button" value="홈으로"onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>







