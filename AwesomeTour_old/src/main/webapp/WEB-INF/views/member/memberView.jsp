<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-main-style">
	<h2>회원상세정보</h2>
	<ul>
		<li>이메일 : ${member.member_email}</li>
		<li>닉네임 : ${member.member_nickname}</li>
		<li>자기소개 : ${member.member_content}</li>
		<li>성향 : ${member.member_tendency}</li>
		<li>취미 : ${member.member_hobby}</li>
		<c:if test="${!empty member.member_filename}">
			<li>프로필 : ${member.member_filename}</li>
		</c:if>
			<div class="align-center">
				<img src="imageView.do?member_email=${member.member_email}" style="max-width: 500px">
			</div>
		
	</ul>
	<hr size="1" width="100%">
	<p class="align-right">
		<input type="button" value="수정" onclick="location.href='update.do'">
		<input type="button" value="탈퇴" onclick="location.href='memberdeleteCheck.do'">
	</p>
</div>








