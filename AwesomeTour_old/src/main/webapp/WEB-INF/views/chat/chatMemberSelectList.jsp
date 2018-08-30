<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/board.js"></script>
<h2>게시판 목록</h2>
	<form action="list.do" id="search_form" method="get">
		<ul class="search">
			<li>
				<select name="keyfield">
					<option value="title">제목</option>
					<option value="id">ID</option>
					<option value="content">내용</option>
					<option value="all">전체</option>
				</select>
			</li>
			<li>
				<input type="text" name="keyword" id="keyword">
			</li>
			<li>
				<input type="submit" value="찾기">
			</li>
		</ul>
	</form>
	<div class="align-right">
		<c:if test="${!empty user_email}">
		<input type="button" value="채팅방 방만들기" onclick="location.href='chatAllInsert.do'">
		</c:if>
	</div>
	<c:if test="${count == 0}">
		<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table>
		<tr>
			<th>번호</th>
			<th width="400">방 이름</th>
			<th>분류</th>
			<th>최신</th>
		</tr>
		<c:forEach var="chatMemberCommandList" items="${chatMemberCommand}">
		<tr>
			<td>${chatMemberCommandList.chat_member_num}</td>
			<td><a href="chatJoin.do?num=${chatMemberCommandList.chat_member_num}">${chatMemberCommandList.chat_member_title}(접속자 수 미구현)</a></td>
			
			<td>${chatMemberCommandList.chat_member_mod_date}</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${pagingHtml}</div>
	</c:if>