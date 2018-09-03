<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/confirmDeletemember.js"></script>
<div class="container section-sepa1" style="height: 800px;">
	<section class="about_history_area section_gap"
		style="margin-top: 80px;margin-bottom:220px;">
		<div class="container">
			<div class="row">
				<div class="col-md-6 d_flex align-items-center">
					<div class="about_content ">
						<form action="${pageContext.request.contextPath}/member/deleteCheck.do" method="post" id="memberDelete">
							<div align="center">
							<h4>인증코드가 전송되었습니다.</h4>
							<h4>코드를 입력하시면 정상적으로 회원탈퇴가 진행됩니다.</h4>
								<input type="text" name="code_email" id="code_email" style="width: 50%" placeholder="이메일" class="form-control" value="${user_email }" readonly="true">
									<br>
								<input type="text" name="code_code" id="code_code" style="width: 50%" placeholder="코드입력">
								<input type="button" class="btn btn-warning" id="delete_check" value="코드인증"><br>
								<span id="message_check"></span>
							</div>
							<br>
							<div align="center">
								<input type="submit" value="탈퇴" class="btn btn-warning">
							</div>
						</form>
					</div>
				</div>
				<div class="col-md-6">
					<img class="img-fluid"
						src="${pageContext.request.contextPath}/resources/img/rome.jpg"
						alt="img">
				</div>
			</div>
		</div>
	</section>
</div>
<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<div class="container" align="center">
		<h4>회원탈퇴</h4>
		<form action="${pageContext.request.contextPath}/member/deleteCheck.do" method="post">
			<div align="center">
				<input type="text" name="code_email" size="120" style="width: 30%" placeholder="이메일" class="form-control" value="${user_email }" readonly="true">
				<br>
				<input type="text" name="code_code" size="120" style="width: 30%" placeholder="코드">
			</div>
			<div align="center">
				<input type="submit" value="인증하기" class="btn btn-warning">
			</div>
		</form>
	</div>
 --%>
