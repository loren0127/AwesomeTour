<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<c:set var="now" value="<%=new java.util.Date()%>"/>
<c:set var="sysMonth"><fmt:formatDate value="${now}" pattern="MM"/></c:set>
<div>
	<p>- 나의 예약 정보 -</p>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>숙소명</th>
				<th>금액 / 수수료</th>
				<th>인원</th>
				<th>날짜</th>
			</tr>
		</thead>
		<c:if test="${count > 0}">
			<c:forEach var="reservationList" items="${reservationList}">
				<tbody>
					<tr>
						<fmt:parseNumber var="principal" integerOnly="true" value="${reservationList.rv_money/11*10}"/>
						<fmt:parseNumber var="commission" integerOnly="true" value="${reservationList.rv_money/11}"/>
						<td><a href="mypageReservationDetail.do?&ro_num=${reservationList.ro_num}&rv_num=${reservationList.rv_num}&acc_num=${reservationList.acc_num}" data-toggle="tooltip" title="예약 정보보기">${reservationList.acc_name}</a></td>
						<td><fmt:formatNumber value="${reservationList.rv_money}" pattern="#,###"/>원 / <fmt:formatNumber value="${commission}" pattern="#,###"/>원</td>
						<td>${reservationList.rv_people}명</td>
						<td>${reservationList.rv_start_date} ~ ${reservationList.rv_end_date}</td>
					</tr>
				</tbody>
			</c:forEach>
		</c:if>
		<c:if test="${count <= 0}">
		<tbody>
			<tr>예약하신 숙소가 없습니다!</tr>
		</tbody>
		</c:if>
	</table>
	
	<hr style="margin-top:50px; margin-bottom:50px;">
	
	<p>- 나의 이전 예약 기록 -</p>
	<table class="table table-hover">
		<thead>
			<tr>
				<th>숙소명</th>
				<th>금액 / 수수료</th>
				<th>인원</th>
				<th>날짜</th>
			</tr>
		</thead>
		<c:if test="${count > 0}">
			<c:forEach var="reservationListOld" items="${reservationListOld}">
				<tbody>
					<tr>
						<fmt:parseNumber var="principal" integerOnly="true" value="${reservationListOld.rv_money/11*10}"/>
						<fmt:parseNumber var="commission" integerOnly="true" value="${reservationListOld.rv_money/11}"/>
						<td><a href="mypageReservationDetail.do?ro_num=${reservationListOld.ro_num}&rv_num=${reservationListOld.rv_num}&acc_num=${reservationListOld.acc_num}" data-toggle="tooltip" title="예약 정보보기">${reservationListOld.acc_name}</a></td>
						<td><fmt:formatNumber value="${reservationListOld.rv_money}" pattern="#,###"/>원 / <fmt:formatNumber value="${commission}" pattern="#,###"/>원</td>
						<td>${reservationListOld.rv_people}명</td>
						<td>${reservationListOld.rv_start_date} ~ ${reservationListOld.rv_end_date}</td>
					</tr>
				</tbody>
			</c:forEach>
		</c:if>
		<c:if test="${countOld <= 0}">
			<tbody>
			<tr>이전 예약 기록이 없네요!</tr>
		</tbody>
		</c:if>
	</table>
	<div style="text-align:center;">${pagingHtml}</div>
</div>

<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();   
});
</script>