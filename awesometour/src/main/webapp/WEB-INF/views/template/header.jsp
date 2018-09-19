<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>
#list_nav li{
	padding:10px 3px; 
}
</style>

<script>
$(function(){
	$("#rv").hide();
	$("#rv_btn").click(function(){
		$("#rv").animate({
			    left: "+=50",
			    height: "toggle"
			});
	});
});
</script>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top bg-white" id="mainNav">
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
<nav class="sticky-top navbar-dark navbar-expand-sm justify-content-center">
	<ul class="nav navbar-nav navbar-dark bg-dark justify-content-center"
		id="list_nav">
		<li class="nav-item"><input type="text"
			style="margin-top: 15px; width: 100%; height: 40px;" id="je_search"
			name="search" placeholder="구 또는 이름을 검색하세요" value="${map.search}">
		</li>
		<li class="nav-item"><input type="hidden" name="searchtype"
			value="${map.searchtype}"> <input type="text" name="check_in"
			class="date_in" value="${map.check_in}"
			style="height: 40px; width: 150px; margin-top: 15px; padding-left: 5px;"
			autocomplete="off"></li>
		<li class="nav-item"><input type="text" name="check_out"
			class="date_out" value="${map.check_out}"
			style="height: 40px; width: 150px; margin-top: 15px;"
			autocomplete="off"></li>
		<li class="nav-item" id="plus_minus"><input type="hidden"
			name="people_count" id="people_count" value="1">
			<button id="people" name="people"
				style="height: 40px; width: 150px; position: absolute; background-color: white; border: 1px solid #A9A9A9; position: absolute; z-index: 0; margin-top: 15px;">
				<span id="peo_sum_btn">${map.people_count}</span>명
			</button> <input type="button" name="minus" id="people_minus" value="-"
			class="form-control"
			style="border: 0;; font-size: 20px; width: 40px; position: relative; margin-top: 16px; marging-left: 1px">
			<input type="button" name="people_plus" class="form-control"
			id="people_plus" value="+"
			style="border: 0; font-size: 20px; position: relative; z-index: 1; width: 40px; margin-left: 68px; margin-top: 16px;">
		</li>
		<li class="nav-item" id="search_btn">
			<button type="submit" class="hotelLink_main"
				style="background-color: white; border: 1px; height: 40px; width: 100px; margin-top: 15px;">검색</button>
		</li>
	</ul>
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