<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="container section-sepa1">
	<h1>회원로그인</h1>
	<form:form commandName="command" action="login.do" id="login_form">
		<form:errors element="div" cssClass="error-color" />
		<ul>
			<li><label for="member_email">이메일</label> 
				<form:input path="member_email" /> 
				<form:errors path="member_email" cssClass="error-color" /></li>
			<li><label for="member_passwd">비밀번호</label> <form:password
					path="member_passwd" /> <form:errors path="member_passwd"
					cssClass="error-color" /></li>
		</ul>
		<div class="align-center">
			<input type="submit" value="전송"> <input type="button"
				value="홈으로"
				onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
</div>
 --%>
 
 <%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/member.js"></script>
<style>
	  @media screen and (max-width: 767px){
	  	#image{
	  		display:none;
	  	}
	  
	  }
</style>
	<div class="container section-sepa1"  style="height: 800px;">
	<section class="about_history_area section_gap" style="margin-top:100px;margin-bottom:200px;">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 d_flex align-items-center">
                        <div class="about_content" style="margin-left:100px;">
							<form:form commandName="command" action="login.do" id="login_form">
							
								<div align="center" style="margin-top:45px;">
									<h4>로그인</h4>
									<form:input path="member_email" class="form-control" placeholder="이메일" style="max-width:500px;"/> 
									<form:errors path="member_email" cssClass="error-color" />
								</div>
								<br>
								<div>
									<form:password path="member_passwd" class="form-control" placeholder="비밀번호" style="display:inline;max-width:500px;"/>
									<form:errors path="member_passwd" cssClass="error-color" />
								</div>
								<form:errors element="div" cssClass="error-color" style=""/>
								<br> <br>
								<div align="center">
									<input type="submit" value="로그인" class="btn btn-warning">
								</div>
							</form:form>
						</div>
                    </div>
                    <div class="col-md-6">
                        <img class="img-fluid" src="${pageContext.request.contextPath}/resources/images/rome.jpg" alt="img" id="image">
                    </div>
                </div>
            </div>
        </section>
        </div>
 