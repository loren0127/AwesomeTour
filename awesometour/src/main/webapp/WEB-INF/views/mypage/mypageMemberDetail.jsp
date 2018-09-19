<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<div class="container section-sepa1" style="padding-top:10px;">
	<h2>회원 정보</h2>
	<br>
	<div class="col-md-6 col-md-offset-3">

		<form role="form">
			<div class="form-group">
				<label for="member_email">이메일 주소</label><br> <input
					type="hidden" class="form-control" id="member_email"
					value="${member.member_email}" readonly="true"> <span>${member.member_email}</span>
			</div>
			<hr>
			<div class="form-group">
				<label for="member_nickname">닉네임</label><br> <input
					type="hidden" class="form-control" id="member_nickname"
					value="${member.member_nickname}" readonly="true"> <span>${member.member_nickname}</span>
			</div>
			<hr>
			<div class="form-group">
				<label for="member_nickname">성향</label><br> <input
					type="hidden" class="form-control" id="member_tendency"
					value="${member.member_tendency}" readonly="true"> <span>${member.member_tendency}</span>
			</div>
			<hr>
			<div class="form-group">
				<label for="member_hobby">취미</label><br> <input type="hidden"
					class="form-control" id="member_hobby"
					value="${member.member_hobby}" readonly="true"> <span>${member.member_hobby}</span>
			</div>
			<hr>
			<div class="form-group">
				<label for="member_filename">프로필</label><br>
				<c:if test="${!empty member.member_filename}">
					<input type="hidden" class="form-control" id="member_filename"
						value="${member.member_filename}" readonly="true">
					<span>${member.member_filename}</span>
					<hr>
				</c:if>
				<c:if test="${empty member.member_filename}">
					<input type="hidden" class="form-control" id="member_filename"
						readonly="true">
					<span>${member.member_filename}</span>
					<hr>
				</c:if>
			</div>
			<div class="form-group">
				<label for="member_content">자기소개</label><br> <input
					type="hidden" class="form-control" id="member_content"
					value="${member.member_content}" readonly="true"> <span>${member.member_content}</span>
			</div>
			<div class="form-group text-center">
				<input type="button" value="수정 " class="btn btn-default"
					onclick="location.href='${pageContext.request.contextPath}/member/update.do'"> <input type="button"
					value="탈퇴" class="btn btn-warning"
					onclick="location.href='${pageContext.request.contextPath}/member/memberdeleteCheck.do'">
			</div>
		</form>
	</div>
</div>