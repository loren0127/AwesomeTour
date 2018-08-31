<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="page-main-style">
	<h2>숙소 목록</h2>
	<nav class="navbar sticky-top navbar-light bg-light">
	<div class="col-md-10 col-lg-8 col-xl-7">
	<!-- 검색 시작 -->
		<form id="search_form" action="../accomList/accomList.do">
			<div class="form-row" style="width: 1200px; margin-left: 100px;">
				<div>
					<input type="text" id="date_in" name="check_in" value=""
						style="height: 50px; width: 150px;"> <input type="hidden"
						name="check_in" id="date_in" value="">
				</div>
				<div>

					<input type="text" id="date_out" name="check_out" value=""
						style="height: 50px; width: 150px;"> <input type="hidden"
						name="check_out" id="date_out" value="">
				</div>
				<div class="people_pop">
					<input type="hidden" name="people_count" id="people_count"
						value="1">

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
						name="search" placeholder="서울">
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
	<!-- 숙소 리스트 시작ㄴ-->
	<div>
	<c:if test="${count == 0}">
	<div class="align-center">등록된 게시물이 없습니다.</div>
	</c:if>
	<c:if test="${count > 0}">
	<table>
		<tr>
			<th>번호</th>
			<th width="400">제목</th>
			<th>ID</th>
			<th>등록날짜</th>
			<th>조회수</th>
		</tr>
		<c:forEach var="accom" items="${list}">
		<tr>
			<td>${accom.acc_num}</td>
			<td>${accom.acc_name}</td>
			<td>${accom.acc_address1}</td>
		</tr>
		</c:forEach>
	</table>
	<div class="align-center">${pagingHtml}</div>	
	</c:if>
	</div>
</div>







