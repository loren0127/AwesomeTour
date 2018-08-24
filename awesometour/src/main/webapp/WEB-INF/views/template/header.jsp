<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top bg-light" id="mainNav">
	<div class="container">
		<a class="navbar-brand js-scroll-trigger" href="${pageContext.request.contextPath}/main/main.do"><i class="fa fa-adn" style="font-size:48px;color:#d900ed"></i>wesome Tour</a>
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="${pageContext.request.contextPath}/awesomeMenu/map.do">지도</a></li>
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="${pageContext.request.contextPath}/awesomeMenu/group.do">모임</a></li>
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="${pageContext.request.contextPath}/awesomeMenu/recommend.do">추천</a></li>
			</ul>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto" style="float:right;">
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="#features">로그인</a></li><!-- 로그아웃 -->
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="#contact">회원가입</a></li><!-- 마이페이지 -->
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="#contact">호스팅</a></li>
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="#contact">QnA</a></li>
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="#contact">관리자</a></li><!-- 로그인 전 숨김 -->
			</ul>
		</div>
	</div>
</nav>