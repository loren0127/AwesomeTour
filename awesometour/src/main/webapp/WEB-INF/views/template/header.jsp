<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top bg-light" id="mainNav">
	<div class="container">
		<a class="navbar-brand js-scroll-trigger" href="${pageContext.request.contextPath}/main/main.do"><i class="fa fa-font"></i><span>wesome Tour</span></a>
		<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" 
				aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <i class="fa fa-bars"></i>
        </button>
        <div class="navbar-collapse collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link js-scroll-trigger" id="opener1" style="cursor:pointer;" data-tooltip-text="지도에서 직접 검색하세요.">지도</a></li>
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="${pageContext.request.contextPath}/group/groupMain.do">모임</a></li>
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="#">추천</a></li>
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