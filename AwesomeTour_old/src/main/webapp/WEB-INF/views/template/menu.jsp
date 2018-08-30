<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<ul class="menu">
	<c:if test="${empty user_email}">
		<li><a href="${pageContext.request.contextPath}/member/login.do">로그인</a></li>
		<li><a href="${pageContext.request.contextPath}/mail/mailForm.do">회원가입하기</a></li>
	</c:if>
	<c:if test="${!empty user_email}">
		<li><a href="${pageContext.request.contextPath}/chat/chatMemberSelectList.do">채팅방 목록</a></li>
		<li><a href="${pageContext.request.contextPath}/member/detailCheck.do">회원정보</a></li>
		<li>${user_email}님로그인 중</li>
		<li><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
	</c:if>
</ul>


