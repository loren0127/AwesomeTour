<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top bg-light" id="mainNav">
	<div class="container">
		<a class="navbar-brand js-scroll-trigger" href="${pageContext.request.contextPath}/main/main.do"><i class="fa fa-font" style="font-size:51px;color:#d900ed;"></i><span style="font-size:32px;color:#d900ed;weight:bold;">wesome Tour</span></a>
		<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" 
				aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation" style="color:rgb(33, 37, 41, .7);">
          Menu&nbsp;<i class="fa fa-bars"></i>
        </button>
        <div class="navbar-collapse collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link js-scroll-trigger" id="opener1" style="cursor:pointer;">지도</a></li>
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="${pageContext.request.contextPath}/awesomeMenu/group.do">지도보기</a></li>
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="${pageContext.request.contextPath}/group/groupMain.do">모임</a></li>
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="${pageContext.request.contextPath}/awesomeMenu/recommend.do">추천</a></li>
				<c:if test="${empty user_email}">
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="${pageContext.request.contextPath}/member/login.do">로그인</a></li><!-- 로그아웃 -->
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="${pageContext.request.contextPath}/mail/mailForm.do">회원가입</a></li><!-- 마이페이지 -->
				</c:if>
				<c:if test="${!empty user_email}">
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="${pageContext.request.contextPath}/member/detailCheck.do">회원정보 수정</a></li><!-- 테스트 -->
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li><!-- 테스트 -->
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="${pageContext.request.contextPath}/mypage/mypageForm.do">마이페이지</a></li><!-- 테스트 -->
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="${pageContext.request.contextPath}/chat/selectChatMemberList.do?selected=mainChat" onclick="window.open(this.href, 'Chat_page_popup', 'width=1000, height=620'); return false;">채팅</a></li><!-- Test -->
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
			<form class="form-inline" style="float:left;">
				<div class="form-group mb-2"><i class="fa fa-search" style="font-size:20px"></i></div>
				<div class="form-group mx-sm-3 mb-2">
					<label for="searchyouwant" class="sr-only">search</label>
					<input type="search" class="form-control" name="searchyouwant" id="searchyouwant" placeholder="모임 이름 혹은 주소로 검색하기">
				</div>
				<input type="submit" value="검색" class="btn btn-warning mb-2" style="color:white;">
			</form>
			<span class="closer">&times;</span>
		</div>
		<div id="mapSearch" style="width: 100%; height: 700px;"></div>
	</div>
</div>
