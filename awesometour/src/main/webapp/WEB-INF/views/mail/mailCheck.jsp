<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/member.js"></script>
	<div class="container section-sepa1"  style="height: 800px;">
	<section class="about_history_area section_gap" style="margin-top:150px;">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 d_flex align-items-center">
                        <div class="about_content ">
							<form action="${pageContext.request.contextPath}/mail/mailChecking.do" method="post" id="mailCheck" name="mailCheck">
								<div align="center" style="margin-top:45px;">
									<h4>이메일 코드 확인</h4>
									<c:if test="${email != null }">
										<input type="email" name="code_email" class="form-control"
											size="180" value="${email }"
											readonly="true">
										<br>
										<input type="text" name="code_code" id="code_code" class="form-control"
											size="180" placeholder="코드">
									</c:if>
									<c:if test="${email == null }">
										<input type="email" name="code_email" class="form-control"
											size="180" placeholder="이메일">
										<br>
										<input type="text" name="code_code"  id="code_code"  class="form-control"
											size="180" placeholder="코드">
									</c:if>
								</div>
								<br> <br>
								<div align="center">
									<input type="submit" value="인증하기" class="btn btn-warning">
								</div>
							</form>
						</div>
                    </div>
                    <div class="col-md-6">
                        <img class="img-fluid" src="${pageContext.request.contextPath}/resources/img/rome.jpg" alt="img">
                    </div>
                </div>
            </div>
        </section>
        </div>
