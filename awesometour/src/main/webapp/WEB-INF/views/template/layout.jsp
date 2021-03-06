<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title><tiles:getAsString name="title"/></title>
<!-- CSS -->

<link rel="shortcut icon" href="../resources/images/favicon.ico">
<link href="https://fonts.googleapis.com/css?family=Noto+Sans+KR" rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/jquery-ui.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/landing-page.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/header.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/body.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/map.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/footer.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/main.css" rel="stylesheet">
<!-- JavaScript -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/header.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/map.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/map2.js"></script>
<!-- services 라이브러리 불러오기 -->
<script type="text/javascript"
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=463df352b2234b5bac553021b6d8bd14&libraries=services"></script>
<script>
    $(function() {
        $(window).scroll(function() {
            if ($(this).scrollTop() > 300) {
                $('#MOVE_TOP_BTN').fadeIn();
            } else {
                $('#MOVE_TOP_BTN').fadeOut();
            }
        });
        
        $("#MOVE_TOP_BTN").click(function() {
            $('html, body').animate({
                scrollTop : 0
            }, 400);
            return false;
        });
    });
</script>


</head>

<body>
<div id="main">
	<div id="main_header">
		<tiles:insertAttribute name="header"/>
	</div>
	<div style="position:fixed;z-index:20000;bottom: 80px;right: 45px;">
	<a href="#"><i class="fa fa-angle-up" style="font-size:32pt; color:#C80DD8 ; z-index: 999;" id="MOVE_TOP_BTN"></i></a>
	
	</div>
	<c:if test="${not empty user_email}">
		<div style="position:fixed;z-index:20000;bottom: 10px;right: 5px;">
			<a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/chat/chatFunctionResult.do?selected=mainChat" onclick="window.open(this.href, 'Chat_page_popup', 'width=1100, height=620'); return false;"><img alt="icon" src="${pageContext.request.contextPath}/resources/img/chat_icon.png" style="width:70px; z-index: 999;"></a>
		</div>
	</c:if>
	<div id="main_body">
		<tiles:insertAttribute name="body"/>
	</div>
	<div id="main_footer">
		<tiles:insertAttribute name="footer"/>
	</div>
</div>
</body>
</html>
