<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
	  @media screen and (max-width: 900px){
	  	#div1{
	  		display:none;
	  	}
	  
	  }
	  
	  #delete_view:hover{
		color: #212529;
		background-color: #fff;
	    border-color: #ffc107;
	  }
	   #modify_view:hover{
		color: #212529;
		background-color: #fff;
	    border-color: #ffc107;
	  }
</style>
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
       <div style="float:right;padding:200px 30px;border-width:10px;border-color:#ffc004;border-style:solid;" id="div1">
        		회원정보 페이지입니다.<br>
        		회원정보 수정을 원하시면 수정버튼을<br>
        		탈퇴를 원하시면 탈퇴버튼을<br>
        		클릭 후 진행해주시면 됩니다.
        </div>
        <div class="col-md-6 col-md-offset-3">
        	
          <form role="form">
            <div class="form-group">
              <label for="member_email">이메일 주소</label><br>
              <input type="hidden" class="form-control" id="member_email" value="${member.member_email}" readonly="true">
              <span>${member.member_email}</span>
            </div>
            <hr>
            <div class="form-group">
              <label for="member_nickname">닉네임</label><br>
              <input type="hidden" class="form-control" id="member_nickname" value="${member.member_nickname}" readonly="true">
              <span>${member.member_nickname}</span>
            </div>
            <hr>
            <div class="form-group">
              <label for="member_nickname">성향</label><br>
              <input type="hidden" class="form-control" id="member_tendency" value="${member.member_tendency}" readonly="true">
               <span>${member.member_tendency}</span>
            </div>
            <hr>
            <div class="form-group">
              <label for="member_hobby">취미</label><br>
              <input type="hidden" class="form-control" id="member_hobby" value="${member.member_hobby}" readonly="true">
              <span>${member.member_hobby}</span>
            </div>
            <hr>
            <div class="form-group">
              <label for="member_filename">프로필</label><br>
              <c:if test="${!empty member.member_filename}">
              	<input type="hidden" class="form-control" id="member_filename" value="${member.member_filename}" readonly="true">
              	 <span>${member.member_filename}</span>
              	 <hr>
              </c:if>
              <c:if test="${empty member.member_filename}">
              	<input type="hidden" class="form-control" id="member_filename" readonly="true">
              	 <span>${member.member_filename}</span>
              	 <hr>
              </c:if>
            </div>
            <div class="form-group">
              <label for="member_content">자기소개</label><br>
              <input type="hidden" class="form-control" id="member_content" value="${member.member_content}" readonly="true">
              <span>${member.member_content}</span>
            </div>
            <div class="form-group text-center">
              <input type="button" value="수정 " class="btn btn-warning mb-2" id="modify_view" onclick="location.href='update.do'">
			<input type="button" value="탈퇴" class="btn btn-warning mb-2" id="delete_view" onclick="location.href='memberdeleteCheck.do'">
            </div>
          </form>
</div>
 </div>







