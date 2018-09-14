<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/member.js"></script>
<style>
	  @media screen and (max-width: 995px){
	  	#image{
	  		display:none;
	  	}
	  }
</style>
	<div class="container section-sepa1"  style="height: 50%;">
	<section class="about_history_area section_gap">
            <div class="container">
                <div class="row">
                    <div class="col-md-6 d_flex align-items-center">
                        <div class="about_content ">
							<form action="${pageContext.request.contextPath}/mail/mailChecking.do" method="post" id="mailCheck" name="mailCheck">
								<div align="center">
									<h4 style="margin-left:50%">코드 확인</h4>
									<c:if test="${email != null }">
										<input type="email" name="code_email" class="form-control"value="${email }" readonly="true" style="margin-left:50%">
										<br>
										<input type="text" name="code_code" id="code_code" class="form-control"placeholder="코드" style="margin-left:50%">
									</c:if>
									<c:if test="${email == null }">
										<input type="email" name="code_email" class="form-control"placeholder="이메일">
										<br>
										<input type="text" name="code_code"  id="code_code"  class="form-control"placeholder="코드">
									</c:if>
								</div>
								<br> <br>
								<div align="center" style="margin-left:80%;">
									<input type="submit" value="인증하기" class="btn btn-warning">
								</div>
							</form>
						</div>
                    </div>
                    <div class="col-md-6">
                        <img class="img-fluid" src="${pageContext.request.contextPath}/resources/images/rome.jpg" alt="img" id="image">
                    </div>
                </div>
            </div>
        </section>
        </div>
