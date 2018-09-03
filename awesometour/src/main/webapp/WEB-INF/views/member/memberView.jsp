<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="container section-sepa1" style="margin-top:100px;">
	<%-- <h2>회원상세정보</h2>
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
	</p> --%>
          <h1>회원 정보</h1>
       <br>
       <br>
        <div class="col-md-6 col-md-offset-3">
          <form role="form">
            <div class="form-group">
              <label for="member_email">이메일 주소</label>
              <input type="email" class="form-control" id="member_email" value="${member.member_email}" readonly="true">
            </div>
            <div class="form-group">
              <label for="member_nickname">닉네임</label>
              <input type="text" class="form-control" id="member_nickname" value="${member.member_nickname}" readonly="true">
            </div>
            <div class="form-group">
              <label for="member_nickname">성향</label>
              <input type="text" class="form-control" id="member_tendency" value="${member.member_tendency}" readonly="true">
            </div>
            <div class="form-group">
              <label for="member_hobby">취미</label>
              <input type="text" class="form-control" id="member_hobby" value="${member.member_hobby}" readonly="true">
            </div>
            <div class="form-group">
              <label for="member_filename">프로필</label>
              <c:if test="${!empty member.member_filename}">
              	<input type="text" class="form-control" id="member_filename" value="${member.member_filename}" readonly="true">
              </c:if>
              <c:if test="${empty member.member_filename}">
              	<input type="text" class="form-control" id="member_filename" readonly="true">
              </c:if>
            </div>
            <div class="form-group">
              <label for="member_content">자기소개</label>
              <textarea type="text" class="form-control" id="member_content" value="" readonly="true">${member.member_content}</textarea>
            </div>
            <div class="form-group text-center">
              <input type="button" value="수정 " class="btn btn-default"  onclick="location.href='update.do'">
			<input type="button" value="탈퇴" class="btn btn-warning" onclick="location.href='memberdeleteCheck.do'">
            </div>
          </form>
</div>
 </div>







