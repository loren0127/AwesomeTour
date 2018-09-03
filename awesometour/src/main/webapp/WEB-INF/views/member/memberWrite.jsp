<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>--%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/member.js"></script>
	<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/confirmNickname.js"></script>
<div class="container section-sepa1" style="margin-top:180px;">
	<section class="about_history_area section_gap">
		<div class="row">
			<div class="col-md-6 d_flex align-items-center">
				<div class="about_content ">
					<div align="center">
						<h4>회원가입</h4>
						<form:form action="write.do" id="register_form"
							name="register_form" commandName="command"
							enctype="multipart/form-data">
							<form:errors element="div" cssClass="error-color" />
							<div class="mt-10">
								<form:input path="member_email" value="${code_email}"
									readonly="true" style="width:200px;" class="form-control"
									size="20" />
								<form:errors path="member_email" cssClass="error-color" />
							</div>
							<br>
							<div class="mt-10">
								<form:password path="member_passwd" class="form-control"
									placeholder="비밀번호" style="width:200px;" />
								<form:errors path="member_passwd" cssClass="error-color" />
							</div>
							<br>
							<div class="mt-10">
								<input type="password" class="form-control"
									name="member_passwd2" id="member_passwd2" placeholder="비밀번호 확인"
									style="width: 200px;"> <span id="message_check"></span>
								<span id="message_unique"></span><br>
							</div>

							<div class="mt-10">
								<form:input path="member_nickname" class="form-control"
									placeholder="닉네임" style="width:200px;" />
								<form:errors path="member_nickname" cssClass="error-color"/><br>
								<span id="message_nickname"></span><br>
								<input type="button" id="confirm_nickname" value="닉네임 중복체크"  class="btn btn-warning">
							</div>
							<br>	
							<div class="mt-10" >
								<label for="upload">프로필</label><br>
								 <input type="file" name="upload" id="upload" style="margin-left:100px;"/>
							</div>
							<br>
							<div class="mt-10">
								<form:input path="member_content" class="form-control"
									placeholder="자기소개" style="width:200px;" />
								<form:errors path="member_content" cssClass="error-color" />
							</div>
							<br>
							<div class="align-center">
								<input type="submit" value="다음" class="btn btn-warning">
								<input type="button" class="btn btn-warning" value="홈으로"
									onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
							</div>
						</form:form>
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<img class="img-fluid"
					src="${pageContext.request.contextPath}/resources/img/rome2.jpg"
					alt="img">
			</div>
		</div>
	</section>
</div>
<%-- <h1>회원가입</h1>
	<div style="align:center;">
		<form:form action="write.do" id="register_form" commandName="command" enctype="multipart/form-data">
			<form:errors element="div" cssClass="error-color" />	
			<ul>
				<li><!--    -->
					<form:input path="member_email" value="${code_email}" readonly="true" style="width:200px;"  class="form-control" size="20"/>
					<form:errors path="member_email" cssClass="error-color"/>
				</li>	
				<li>
					<form:password path="member_passwd" class="form-control" placeholder="비밀번호" style="width:200px;"/>
					<form:errors path="member_passwd" cssClass="error-color"/>
				</li>
				<li>
					<form:input path="member_nickname" class="form-control"  placeholder="닉네임" style="width:200px;"/>
					<form:errors path="member_nickname" cssClass="error-color"/>
				</li>
				<li>
					<label for="upload">프로필</label>
					<input type="file" name="upload" id="upload"/>
				</li>
				<li>
					<form:input path="member_content" class="form-control"  placeholder="자기소개" style="width:200px;"/>
					<form:errors path="member_content" cssClass="error-color"/>
				</li>
			</ul>
			
			<br>
			<br>
			<div class="align-center">
				<input type="submit" value="가입">
				<input type="button" value="홈으로"onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
			</div>
		</form:form>
	</div> --%>
<%-- <div class="section-top-border">
		<div class="row">
			<div class="col-lg-8 col-md-8">
				<h3 class="mb-30 title_color">Form Element</h3>
				<form:form action="write.do" id="register_form"
					commandName="command" enctype="multipart/form-data">
					<form:errors element="div" cssClass="error-color" />
					<div class="mt-10">
						<form:input path="member_email" value="${code_email}"
							readonly="true" style="width:200px;" class="form-control"
							size="20" />
						<form:errors path="member_email" cssClass="error-color" />
					</div>
					<div class="mt-10">
						<form:password path="member_passwd" class="form-control"
							placeholder="비밀번호" style="width:200px;" />
						<form:errors path="member_passwd" cssClass="error-color" />
					</div>
					<div class="mt-10">
						<form:input path="member_nickname" class="form-control"
							placeholder="닉네임" style="width:200px;" />
						<form:errors path="member_nickname" cssClass="error-color" />
					</div>
					<div class="mt-10">
						<label for="upload">프로필</label> <input type="file" name="upload"
							id="upload" />
					</div>
					<div class="mt-10">
						<form:input path="member_content" class="form-control"
							placeholder="자기소개" style="width:200px;" />
						<form:errors path="member_content" cssClass="error-color" />
					</div>
					<div class="align-center">
						<input type="submit" value="가입"> <input type="button"
							value="홈으로"
							onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
					</div>
				</form:form>
			</div>

		</div>
	</div> --%>



