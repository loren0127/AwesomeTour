<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top bg-white" id="mainNav" >
	<div class="container">
		<a class="navbar-brand js-scroll-trigger" href="${pageContext.request.contextPath}/main/main.do"><i class="fa fa-font"></i><span style="font-weight:bold;">wesome Tour</span></a>
		<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" 
				aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <i class="fa fa-bars"></i>
        </button>
        <div class="navbar-collapse collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="#" id="rv_btn">예약</a></li>
				<li class="nav-item"><a class="nav-link js-scroll-trigger" id="opener1" style="cursor:pointer;" data-tooltip-text="지도에서 직접 검색하세요.">지도</a></li>
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="${pageContext.request.contextPath}/group/groupMain.do">모임</a></li>
				<c:if test="${empty user_email}">
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="${pageContext.request.contextPath}/member/login.do">로그인</a></li><!-- 로그아웃 -->
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="${pageContext.request.contextPath}/mail/mailForm.do">회원가입</a></li><!-- 마이페이지 -->
				</c:if>
				<c:if test="${!empty user_email}">
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li><!-- 테스트 -->
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="${pageContext.request.contextPath}/mypage/mypageMemberDetail.do">마이페이지</a></li><!-- 테스트 -->
				</c:if>
				<c:if test="${(!empty user_email) && (user_auth == 4) }">
					<li class="nav-item"><a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/admin/adminMainForm.do">관리자 페이지</a></li><!-- 테스트 -->
				</c:if>
			</ul>
		</div>
	</div>
</nav>

<!-- 성원이가 만든 예약 바 시작 -->
 <nav class="navbar-dark navbar-expand-sm fixed-top" id="rv_h" style="margin-top:82px;position:absolute">
 	<form id="search_form_h" action="../accomList/accomList.do">
 		<ul class="je_form_row nav navbar-nav navbar-dark justify-content-center" id="list_nav_h" style="background-color: #0F1721;">
		<li class="nav-item" style="margin:15px 5px;">
			<select name="searchtype" style="height: 38px; width: 150px;">
				<option value="h">호텔</option>
				<option value="p">프라이빗 하우스</option>
			</select>
		</li><!-- =======================================================절대로 li태그 내부 태그 정렬 바꾸지 마시오!!! -->
		<li class="nav-item"><input type="text" name="check_in"	class="date_in_h" value="${map.check_in}"
					style="height: 38px;width: 150px;margin-top: 15px;" autocomplete="off"><input type="text" name="check_out"	class="date_out_h" value="${map.check_out}"
					style="height: 38px;width: 150px;margin-top:15px;" autocomplete="off"></li>
		<li class="nav-item" id="plus_minus_h" style="margin:0 5px;margin-top:15px;height:100%;"><input type="hidden" name="people_count" id="people_count_h" value="1">
					<button id="people_minus_h" name="minus" style="outline:none;">-</button>
					<button id="people_h" name="people" style="outline:none;">
						<span id="peo_sum_btn_h">1</span>명
					</button>
					<button id="people_plus_h" name="people_plus" style="outline:none;">+</button></li>
		<li class="nav-item"><input type="text" style="margin-top: 15px;width: 75%;height: 38px;"
					id="je_search_h" name="search"
					placeholder="숙소명/지역" value="${map.search}">
		</li>
		<li class="nav-item" id="search_btn_h" style="margin-top:15px;margin-left:-50px;">
			<button type="submit" class="btn hotelLink_main" style="height:38px;background-color:#fff;">검색</button>
		</li>
	</ul>
	</form>
</nav>
<!-- 성원이가 만든 예약 바 끝 -->

<!-- Map -->
<div class="container">
	<div id="mapSearch_dialog">
		<div>
			<form action="main.do" method="get">
				<div style="display:inline-flex;margin-bottom:10px;">
					<div><i class="fa fa-search" style="margin-top:9px"></i></div>
					<input type="text" class="form-control" id="searchyouwant" placeholder="숙소명/지역/종류로 검색" style="width:100%;padding-left:40px;">
					<div><i class="fa fa-close" style="margin-top:10px"></i></div>
					<input type="submit" value="검색" id="map_search" class="btn btn-warning" style="margin-left:10px;">
				</div>
			<span class="closer">&times;</span>
			</form>
		</div>
		<div id="mapSearch" style="width: 100%; height: 700px;"></div>
	</div>
</div>