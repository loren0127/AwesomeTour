<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<h3>ChatAllInsert.jsp</h3>
<form:form commandName="chatAllCommand" action="insertChatAll.do">
	<ul>
		<li>
			<label for="chat_all_title">방 제목</label>
			<form:input path="chat_all_title" />
			<%-- <form:errors path="title" cssClass="error-color" /></li> --%>
		<li>
			<label for="chat_all_sort">구분</label>
			<form:select path="chat_all_sort">
				<form:option value="개인">개인 채팅</form:option>
				<form:option value="그룹">그룹 채팅</form:option>
				<form:option value="숙소">숙소 채팅</form:option>
			</form:select>
			<%-- <form:errors path="content" cssClass="error-color" /> --%>
		</li>
		<li>
			<label for="chat_all_member_max">최대 인원</label>
			<form:input path="chat_all_member_max"/>
		</li>
	</ul>
	<div class="align-center">
		<input type="submit" value="전송"> <input type="button" value="목록" onclick="location.href='list.do'">
	</div>
</form:form>