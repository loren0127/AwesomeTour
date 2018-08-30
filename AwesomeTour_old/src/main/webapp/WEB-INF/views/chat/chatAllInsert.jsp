<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<h3>ChatAllInsert.jsp</h3>
<form:form commandName="chatAllCommand" action="chatAllInsert.do">
	<ul>
		<li>
			<label for="status_i">개인채팅</label>
			<form:radiobutton path="status" value="1"/>
			
			<label for="status_g">그룹채팅</label>
			<form:radiobutton path="status" value="2"/>
		</li>
		<li>
			<label for="chat_all_title">방 주제</label>
			<form:input path="chat_all_title"/>
			<input type="button" id="confirmChatAllTitle" value="중복확인">
			<span id="chat_all_title_check"></span>
		</li>
		<li>
			<label for="chat_all_member_max">최대 인원</label>
			<form:input path="chat_all_member_max"/>
		</li>
		<li>
			<button type="submit">방만들기</button>
		</li>
	</ul>
</form:form>