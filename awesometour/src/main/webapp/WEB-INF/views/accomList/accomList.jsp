<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/search.js"></script>
<div class="page-main-style">
	<h2>숙소 목록</h2>
	<nav class="navbar sticky-top navbar-light bg-light">
	<div class="col-md-10 col-lg-8 col-xl-7">
	<!-- 검색 시작 -->
		<form id="search_form">
			<div class="form-row" style="width: 1200px; margin-left: 100px;margin-top:150px;">
				<div >
					<input type="text" id="date_in" name="check_in" value="${map.check_in}"
						style="height: 50px; width: 150px;" autocomplete="off"> 
						<input type="hidden"
						name="check_in" id="date_in" value="">
						
				</div>
				<div>

					<input type="text" id="date_out" name="check_out" value="${map.check_out}"
						style="height: 50px; width: 150px;"autocomplete="off"> 
						<input type="hidden"
						name="check_out" id="date_out" >
				</div>
				<div class="people_pop">
					<input type="hidden" name="people_count" id="people_count"
						value="${map.people_count}">

					<button id="people" name="people"
						style="height: 50px; width: 150px;">
						인원 <span id="peo_sum_btn">1</span>명
					</button>

					<div class="check" id="pe_pop">
						<span>인원<input type="button" name="people_plus"
							id="people_plus" value="+"> <span id="people_sum">1</span>
							<input type="button" name="minus" id="people_minus" value="-"></span>

					</div>
				</div>
				<div>
					<input type="text" style="width: 300px; height: 50px;"
						name="search" placeholder="서울" value="${map.search}">
				</div>
				<div>
					<button type="submit" class="btn btn-block btn-lg btn-primary">검색</button>
				</div>
			</div>
			<div>
			</div>
		</form>
	</div>
</nav>
		<!-- 검색창 끝-->
	<!-- 숙소 리스트 시작(호텔)-->
	<div>
	<c:if test="${count == 0}">
	<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:forEach var="accom" items="${list}">	
	<c:if test="${accom.ro_sub=='h'}">
	<div style="width:200px; height:150px; border:1px solid; float:right;">
		<span>1박요금</span><br>
		<span>${accom.ro_price}</span><br>
	</div>
	<form action="../accomDetail/accomDetail_hotel.do">
	<div style="width:500px; height:150px; border:1px solid; float:right;">
			
			<span>${accom.acc_num}</span><br>
			<span>${accom.acc_name}</span><br>
			<span>${accom.acc_address1}</span><br>
		
		<div>
			<input type="submit" value="확인">
		</div>
	</div>
	</form>
	</c:if>
	</c:forEach>
	</div>
	
	<c:forEach var="accom" items="${list}">
	<c:if test="${accom.ro_sub=='h'}">
	</c:if>
	</c:forEach>
	
<!-- 숙소 리스트 시작(프라이빗하우스)-->
	<div>
	<c:if test="${count == 0}">
	<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	
	<c:forEach var="accom" items="${list}">	
	<c:if test="${accom.ro_sub=='p'}">
	<div style="width:200px; height:150px; border:1px solid; float:right;">
		<span>1박요금</span><br>
		<span>${accom.ro_price}</span><br>
	</div>
	</c:if>
	</c:forEach>
	
	<c:forEach var="accom" items="${list}">
	<c:if test="${accom.ro_sub =='p'}">
	<form action="../accomDetail/accomDetail_private.do">
	<div style="width:500px; height:150px; border:1px solid; float:right;">
			<span>${accom.acc_num}</span><br>
			<span>${accom.acc_name}</span><br>
			<span>${accom.acc_address1}</span><br>
		
		<div>
			<input type="submit" value="확인">
		</div>
	</div>
	</form>
	</c:if>
		</c:forEach>
	</div>
	<!-- 숙소 리스트 끝(프라이빗하우스)-->
</div>







