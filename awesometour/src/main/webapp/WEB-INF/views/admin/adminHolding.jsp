<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style2.css">
<div class="container">
	<div class="section-top-border" style="margin-left:15%">
		<h3 class="mb-30 title_color">홀딩리스트</h3>
		<div class="progress-table-wrap" style="font-size:20px;">
			<div class="progress-table">
				<div class="table-head">
					<div class="serial" style="width:15%">순서</div>
					<div class="country" style="width:15%">입금</div>
					<div class="visit">계좌</div>
					<div class="percentage">금액</div>
				</div>
				<c:forEach var="i" items="${ list}">
					<div class="table-row">
						<div class="serial" style="width:15%">${i.rv_num }</div>
						<c:if test="${i.hd_deposit == 1}">
							<div class="country" style="width:15%">O</div>
						</c:if>
						<c:if test="${i.hd_deposit != 1}">
							<div class="country" style="width:15%">X</div>
						</c:if>
						<div class="visit">${i.hd_account }</div>
						<div class="percentage">${i.hd_money }</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<%-- <c:forEach var="i" items="${ list3}">
	${i.at_pin }<br>
	</c:forEach> --%>
</div>