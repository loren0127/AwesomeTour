<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<html class="fontawesome-i2svg-active fontawesome-i2svg-complete">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" 
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/landing-page.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/header.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/body.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/map.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/footer.css"
	rel="stylesheet">

<!-- JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/header.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/map.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/map2.js"></script>

<!-- Mypage layout css -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mypage.css">

<body>
	<tiles:insertAttribute name="header"/>
	<div style="padding-top: 100px;"></div>
	
	<!-- Chat button start -->
	<div style="position: fixed; z-index: 20000; bottom: 10px; right: 5px;">
		<a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/chat/chatFunctionResult.do?selected=mainChat"
			onclick="window.open(this.href, 'Chat_page_popup', 'width=1100, height=620'); return false;">
			<img src="${pageContext.request.contextPath}/resources/img/chat_icon.png" style="width: 70px;"></a>
	</div>
	<!-- Chat button end -->

	<div class="wrapper">
		<!-- Sidebar  -->
		<nav id="sidebar">
			<div class="sidebar-header">
				<h6 style="text-align:center;">${user_email}</h6>
			</div>
			
			<ul class="list-unstyled components">
				<p style="text-align: center;">
					<img src="${pageContext.request.contextPath}/member/imageView.do?member_email=${user_email}" style="width: 60%;">
				</p>
					
				<ul class="nav nav-tabs" role="tablist">
					<li class="nav-item" style="width:50%"><a class="nav-link active" data-toggle="tab" href="#member" style="text-align:">&nbsp;&nbsp;&nbsp;회원</a></li>
					<c:if test="${user_auth == 3}">
						<li class="nav-item" style="width:50%"><a class="nav-link" data-toggle="tab" href="#host" style="test-align: center">&nbsp;&nbsp;&nbsp;호스트</a></li>
					</c:if>
				</ul>
				<div class="tab-content">
					<div id="member" class="container tab-pane active">
						<hr>
						<li><a href="${pageContext.request.contextPath}/mypage/mypageMemberDetail.do">내 정보관리</a><hr></li>
						<li><a href="${pageContext.request.contextPath}/mypage/mypageReservationList.do">내 예약관리</a><hr></li>
						<li><a href="#">내 그룹관리</a><hr></li>
					</div>
					<div id="host" class="container tab-pane">
						<hr>
						<li class="active"><a href="${pageContext.request.contextPath}/mypage/mypageComplainList.do">컴플레인 관리</a><hr></li>
						<li><a href="#">호스트 관리</a><hr></li>
					</div>
				</div>
			</ul>

			<ul class="list-unstyled CTAs">
				<li>
					<a href="#" class="download">Download source</a></li>
				<li>
			</ul>
		</nav>

		<!-- Page Content  -->
		<div id="content">

			<nav class="navbar navbar-expand-lg navbar-light bg-light">
				<div class="container-fluid">

					<button type="button" id="sidebarCollapse" class="btn btn-primary">
						<svg class="svg-inline--fa fa-align-left fa-w-14"
							aria-hidden="true" data-prefix="fas" data-icon="align-left"
							role="img" xmlns="http://www.w3.org/2000/svg"
							viewBox="0 0 448 512" data-fa-i2svg="" style="width:15px;">
							<path fill="currentColor"
								d="M288 44v40c0 8.837-7.163 16-16 16H16c-8.837 0-16-7.163-16-16V44c0-8.837 7.163-16 16-16h256c8.837 0 16 7.163 16 16zM0 172v40c0 8.837 7.163 16 16 16h416c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16H16c-8.837 0-16 7.163-16 16zm16 312h416c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16H16c-8.837 0-16 7.163-16 16v40c0 8.837 7.163 16 16 16zm256-200H16c-8.837 0-16 7.163-16 16v40c0 8.837 7.163 16 16 16h256c8.837 0 16-7.163 16-16v-40c0-8.837-7.163-16-16-16z"></path></svg>
						<!-- <i class="fas fa-align-left"></i> -->
						<span>메뉴</span>
					</button>
				</div>
			</nav>

			<!-- Tiles body area start -->
			<div class="container" id="mypageBody">
				<tiles:insertAttribute name="body" />
			</div>
			<!-- Tiles body area end -->
	</div>
	
	<!-- Popper.JS -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"
			integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
			crossorigin="anonymous"></script>

	<script type="text/javascript">
        $(document).ready(function () {
            $('#sidebarCollapse').on('click', function () {
                $('#sidebar').toggleClass('active');
            });
            
            $('#sidebarCollapse').on('click', function() {
            	if($('#sidebar').is('.active') === true) {
            		$('#mypageBody').hide();
            	} else {
            		$('#mypageBody').show();
            	}
            });
            
        });
    </script>
   </div>
   </body>
</html>