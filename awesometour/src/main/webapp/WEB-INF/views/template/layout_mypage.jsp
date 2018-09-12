<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/landing-page.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/body.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/map.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/footer.css" rel="stylesheet">
<!-- JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/header.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/map.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/map2.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/mypage.css">

<!-- Font Awesome JS -->
<script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js"
		integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ"
		crossorigin="anonymous"></script>
<script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js"
		integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY"
		crossorigin="anonymous"></script>
</head>
		<div style="position:fixed;z-index:20000;bottom: 10px;right: 5px;">
			<a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/chat/selectChatMain.do?selected=mainChat" onclick="window.open(this.href, 'Chat_page_popup', 'width=1100, height=620'); return false;"><img alt="icon" src="${pageContext.request.contextPath}/resources/img/chat_icon.png" style="width:70px;"></a>
		</div>
		<tiles:insertAttribute name="header"/>
<div style="padding-top: 100px;"></div>
<div class="wrapper">
	<!-- Sidebar  -->
	<nav id="sidebar">
		<div class="sidebar-header">
			<h6 style="margin: 0; text-align: center">${user_email}</h6>
		</div>

		<ul class="list-unstyled components">
			<p style="text-align: center;"><img src="${pageContext.request.contextPath}/member/imageView.do?member_email=${user_email}" style="width: 70%;"></p>
			
				<ul class="nav nav-tabs" role="tablist">
					<li class="nav-item" style="width:50%"><a class="nav-link active"
						data-toggle="tab" href="#member" style="text-align:">&nbsp;&nbsp;&nbsp;회원</a></li>
					<c:if test="${user_auth == 3}">
					<li class="nav-item" style="width:50%"><a class="nav-link" data-toggle="tab"
						href="#host" style="test-align: center">&nbsp;&nbsp;&nbsp;호스트</a></li>
					</c:if>
				</ul>
			

			<div class="tab-content">
				<div id="member" class="container tab-pane active">
					<ul>
					<li class="active"><a href="#">내 정보관리</a><hr></li>
					
					<li><a href="#">내 예약관리</a><hr></li>
					
					<li><a href="#">내 그룹관리</a><hr></li>
					
					</ul>
					<a href="#pageSubmenu" data-toggle="collapse"
						aria-expanded="false" class="dropdown-toggle">쪽지</a>
						<ul class="collapse list-unstyled" id="pageSubmenu">
							<li><a href="#">쪽지 전송</a></li>
							<li><a href="#">보낸 쪽지 목록</a></li>
							<li><a href="#">받은 쪽지 목록</a></li>
						</ul>
				</div>
				<c:if test="${user_auth == 3}">
					<div id="host" class="container tab-pane fade">
						<ul>
							<li class="active"><a href="#">호스트 관리</a><hr></li>
							<li><a href="#">호스트 관리</a><hr></li>
						</ul>
					</div>
				</c:if>
			</div>
		</ul>

		<ul class="list-unstyled CTAs">
			<li><a href="#" class="download">메인 페이지</a></li>
		</ul>
	</nav>

	<!-- Page Content  -->
	<div id="content">

		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">

				<button type="button" id="sidebarCollapse" class="btn btn-info">
					<i class="fas fa-align-left"></i> <span></span>
				</button>
				<button class="btn btn-dark d-inline-block d-lg-none ml-auto"
					type="button" data-toggle="collapse"
					data-target="#navbarSupportedContent"
					aria-controls="navbarSupportedContent" aria-expanded="false"
					aria-label="Toggle navigation">
					<i class="fas fa-align-justify"></i>
				</button>
			</div>
		</nav>

		<div class="container">
			<%-- <tiles:insertAttribute name="body" /> --%>
		</div>
	</div>

	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" 
			integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ"
			crossorigin="anonymous"></script>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#sidebarCollapse').on('click', function() {
				$('#sidebar').toggleClass('active');
			});
		});
	</script>
</div>

<div id="main_footer">
		<tiles:insertAttribute name="footer"/>
	</div>
</html>