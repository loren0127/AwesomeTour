<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/member.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/mypageList.js"></script>
<div class="container section-sepa1">
	<h3 class="mb-30 title_color">호스트 마이페이지</h3>
	<div class="progress-table-wrap">
		<div class="progress-table" align="center">
			<div class="table-head">
				<div class="serial" align="center">호텔이름</div>
				<div class="serial">방번호</div>
				<div class="serial" style="margin-left:2%">사유</div>
				<div class="serial">고객이메일</div>
				<div class="serial"></div>
				<div class="serial">보낸날짜</div>
				<div class="serial">숙박 종료날짜</div>
			</div>
			
			<input type="hidden" value="${user_email }" id="user_email" name="user_email">
				<div id="output">
				</div>
				<div class="paging-link" style="text-align:center;<c:if test="${more == 0}">display:none;</c:if>">
					<a href="#" style="color: #222; font-weight: bold;">더보기&nbsp;<b>∨</b></a>
			</div>
		</div>
	</div>

	</div>