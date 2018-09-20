<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style2.css">
<style type="text/css">
.starR1{
    background: url('../resources/images/star.png') no-repeat -52px 0;
    background-size: auto 100%;
    width: 15px;
    height: 30px;
    float:left;
    text-indent: -9999px;
    cursor: pointer;
}
/* http://miuu227.godohosting.com/images/icon/ico_review.png */
.starR2{
    background: url('../resources/images/star.png') no-repeat right 0;
    background-size: auto 100%;
    width: 15px;
    height: 30px;
    float:left;
    text-indent: -9999px;
    cursor: pointer;
}
.starR1.plus{background-position:0 0;}
.starR2.plus{background-position:-15px 0;}

.star-color{
	font-size:20px;
	color:#d900ed;
}

</style>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/accomDetail/accomDetail_private.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>

<div class="container" style="min-height: 3500px;">
	<div class="comments-area" style="min-height: 4000px;">
	<div id="carouselExampleControls" class="carousel slide"
			data-ride="carousel">
			<div class="carousel-inner">
							<div class="carousel-item active">
								<img
									src="${pageContext.request.contextPath}/accomDetail/imageViewPrivate.do?im_ac_num=${privateDetail.acc_num}&kind=im_cover"
									onerror="this.src='${pageContext.request.contextPath}/resources/images/NoImage.gif'">
							</div>
							<div class="carousel-item">
								<img
									src="${pageContext.request.contextPath}/accomDetail/imageViewPrivate.do?im_ac_num=${privateDetail.acc_num}&kind=im_image2"
									onerror="this.src='${pageContext.request.contextPath}/resources/images/NoImage.gif'">
							</div>
							<div class="carousel-item">
								<img
									src="${pageContext.request.contextPath}/accomDetail/imageViewPrivate.do?im_ac_num=${privateDetail.acc_num}&kind=im_image3"
									onerror="this.src='${pageContext.request.contextPath}/resources/images/NoImage.gif'">
							</div>
			</div>

			<a class="carousel-control-prev" href="#carouselExampleControls"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">이전</span>
			</a> <a class="carousel-control-next" href="#carouselExampleControls"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">다음</span>
			</a>
		</div>
		<br> <br>
		<div class="container">
		<h3 style="float: left;width:90%;">${privateDetail.acc_name}</h3>
		<div style="float: right; width: 10%;font-size:20px;"><span style="padding-right:10%;"><b><i class="fa fa-heart"></i></b>&nbsp;${privateDetail.ag_grade}점</span></div>
		<div style="float: left; width: 90%;">${privateDetail.acc_address1}<a href="#detailMap" style="font-size:12px;"><b>자세한 주소</b></a></div>
		<div style="float: right; width: 10%;" id="theme_id">
			<b style="color: #d900ed;">${privateDetail.acc_theme}</b>테마
		</div>
		<div style="float:left;width:100%;"><b style="font-size:20px;color: #d900ed;">₩<fmt:formatNumber>${privateDetail.ro_price}</fmt:formatNumber></b></div>
		<br> <br><br><br>
		<hr size="1" noshade>
	 	<div style="padding-left: 30%;">
			<span>【 숙소 100배 즐기기 】</span> <span style="padding-left:10%;">
		<c:forEach var="service" items="${serviceSet2}">
			${service}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</c:forEach>
			</span>
		</div> 
		<hr size="1" noshade>
		</div>
		<br> <br> <br>
		<div class="container" style="min-height:300px;">
			<h3>호스트 소개</h3>
				<div class="comments-area" style="background-color:#f8f9fa;">
						<div class="row">
							<div class="col-md-3">
								<img src="${pageContext.request.contextPath}/member/imageView.do?member_email=${privateDetail.member_email}" alt="" style="width:100px;" onerror="this.src='${pageContext.request.contextPath}/resources/images/NoImage.gif'">
							</div>
							<br><br>
								
							<div class="col-md-9 mt-sm-20 left-align-p">
								<p style="font-weight:bold;"><b>${privateDetail.member_nickname}</b>님의 프라이빗 하우스입니다!</p>
								<p>${privateDetail.member_content}</p>
								<p>가입일 : ${privateDetail.member_reg_date}</p>
							</div>
							<div style="padding-left:26%;">
							<input type="button" class="btn-reply button_hover" style="width:100px;" id="host_message_button" value="연락하기">
							</div>
							<div id="accomDetail_dialog" title="메시지 보내기">
							<br>
							<p style="text-align:center;"><b>${privateDetail.member_nickname}</b>님에게 문의하기</p>
							<form>
								<input type="hidden" name="message_receiver" id="message_receiver" value="${privateDetail.acc_host}">
								<input type="text" name="message_title" placeholder="제목 입력" size="37" id="message_title" maxlength="90"><br><br>
								<textarea rows="6" cols="40" placeholder="내용 입력" name="message_content" id="message_content"></textarea>
							</form>
							</div>
						</div>
					</div>
				</div>
				<br><br>
			<div class="container" style="background-color: white;">
				<br> <br>
				<h4>
					한 눈에 보기&nbsp;<b>∨</b>
				</h4>
				<div class="row">
					<div class="col-md-6 d_flex align-items-center" style="text-align:left;">
						<div class="about_content" style="padding-left: 10%;">
							<h5 class="title title_color">편의시설 및 서비스</h5>
							<c:forEach var="service" items="${serviceNameArray2}">
								<ul>
									<li>${service}</li>
								</ul>
							</c:forEach>
							<br>
						</div>
						<div>
							<h5>주의사항</h5>
							<ul>
								<li>체크인 : ${privateDetail.acc_in}시</li>
								<li>체크아웃 : ${privateDetail.acc_out}시</li>
							</ul>
						</div>
					</div>
					<div class="col-md-6">
						<img class="img-fluid"
							src="${pageContext.request.contextPath}/resources/images/about_bg.jpg"
						    alt="img"> <br> <br>
					</div>
				</div>
			</div>
		<br><br>
		<div class="container col-sm-12" style="min-height:300px;">
			<input type="hidden" name="check_in" value="${check_in}" id="check_in">
			<input type="hidden" name="check_out" value="${check_out}" id="check_out">
			<input type="hidden" name="people_count" value="${people_count}" id="people_count">
			<input type="hidden" name="start_date" value="${privateDetail2.start_date}" id="start_date">
			<input type="hidden" name="end_date" value="${privateDetail2.end_date}" id="end_date">
			<input type="hidden" name="acc_name" value="${privateDetail.acc_name}" id="acc_name">
			<h3>예약 가능 여부</h3>
			<p>*예약 가능 날짜만 참고하세요!</p>
			<br>
			<!--  <input type="text" value="" id="datepicker">-->
			<div id="datepicker_accomDetail" style="float:left;width:30%;"></div>
			<div id="datepicker2_accomDetail"></div>
		</div>
		<br><br><br>
		<div class="reservation_link">
		<a href="${pageContext.request.contextPath}/reservation/confirm.do?im_ac_num=${privateDetail.acc_num}&check_in=${check_in}&check_out=${check_out}&people_count=${people_count}&ro_room_num=0" class="btn-reply button_hover reservation_button" style="width:100px;clear:both;<c:if test="${disableCount == 0}">display:none;</c:if>">예약하기</a>
		</div>
		<br>
		<!-- 후기 -->
		<div class="container" id="reply_div">
			<div class="comment-list">
				<div class="comment-form">
					<h4>Review&nbsp;&nbsp;<span id="reviewCount"></span></h4>
					<div class="starRev">
						<span class="starR1 plus">별1_왼쪽</span> <span class="starR2">별1_오른쪽</span>
						<span class="starR1">별2_왼쪽</span> <span class="starR2">별2_오른쪽</span>
						<span class="starR1">별3_왼쪽</span> <span class="starR2">별3_오른쪽</span>
						<span class="starR1">별4_왼쪽</span> <span class="starR2">별4_오른쪽</span>
						<span class="starR1">별5_왼쪽</span> <span class="starR2">별5_오른쪽</span>
					</div>
					<span class="starCount" style="float: right; padding-right: 44%;"></span> <br>
					<br> <br>
					<form id="re_form">
						<input type="hidden" name="im_ac_num" value="${privateDetail.acc_num}" id="im_ac_num">
					    <input type="hidden" name="re_acc_num" value="${privateDetail.acc_num}" id="re_acc_num">
					    <input type="hidden" name="ag_acc_num" value="${privateDetail.acc_num}" id="ag_acc_num">
					    <input type="hidden" name="al_acc_num" value="${privateDetail.acc_num}" id="al_acc_num">
					    <input type="hidden" name="user_email" value="${user_email}" id="user_email">
						<input type="hidden" name="re_email" value="${user_email}" id="re_email">
						<input type="hidden" name="ag_email" value="${user_email}" id="ag_email">
						<input type="hidden" name="al_email" value="${user_email}" id="al_email">
						<div class="form-group form-inline">
							<textarea class="form-control mb-10 rep-content" rows="5"
								name="re_content" id="re_content" placeholder="후기를 등록해보세요!"
								<c:if test="${empty user_email}">disabled="disabled"</c:if>><c:if
									test="${empty user_email}">로그인해야 작성할 수 있습니다.</c:if></textarea>
						</div>
						<c:if test="${!empty user_email}">
							<div id="re_first">
								<span class="letter-count">300/300</span>
							</div>
							<br>
							<div id="re_second">
								<input type="submit" class="btn-reply text-uppercase" value="click" id="re_submit">
							</div>
						</c:if>
					</form>
				</div>
				<br> <br>
				<div class="comment-list">
				<div class="block_cate" id="blockcate">
				<div class="con_align" style="text-align:center;">
					<hr size="1" noshade>
					<span style="padding-left:10px;"><input type="radio" class="confirm-radio" name="reviewList" id="review_like"
						value="first" checked> <label for="review_like" >좋아요순</label></span>
					<span style="padding-left:10px;"><input type="radio" class="confirm-radio" name="reviewList" id="review_recency"
						value="second"> <label for="review_recency">최신순</label></span>
					<hr size="1" noshade>
				</div>
				</div>
					<div id="output"></div>
					<div class="paging-link4"
						style="text-align:center;<c:if test="${more5 == 0}">display:none;</c:if>">
						<a href="#" style="color: #222; font-weight: bold;"><span
							id="remainCount" style="color: #d900ed;"></span>개 더보기&nbsp;<b>∨</b></a>
					</div>
					<div class="paging-link5"
						style="text-align:center;<c:if test="${more5 == 0}">display:none;</c:if>">
						<a href="#" style="color: #222; font-weight: bold;"><span
							id="remainCount2" style="color: #d900ed;"></span>개 더보기&nbsp;<b>∨</b></a>
					</div>
					<div id="loading" style="display: none;">
						<img
							src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif">
					</div>
				</div>
			</div>
		</div>
		<!-- 평점이 높은 다른 숙소 -->
		<div class="container" style="min-height:300px;clear:left;padding-top:50px;">
			<h3>평점이 높은 프라이빗 하우스</h3>
			<br>
            <div class="container">
                <div class="row">
                <c:if test="${!empty privateGrade}">
                <c:forEach var="list" items="${privateGrade}">
                    <div class="col-lg-3 col-sm-6">
                        <div class="accomodation_item text-center">
                            <div class="hotel_img">
                                <img
									src="${pageContext.request.contextPath}/accomDetail/imageViewPrivate.do?im_ac_num=${list.acc_num}&kind=im_cover"
									style="width:200px;"
									onerror="this.src='${pageContext.request.contextPath}/resources/images/NoImage.gif'">
                            </div>
                            <h5 style="color:#d900ed;">${list.acc_name}</h5>
                            <div><fmt:formatNumber>${list.ro_price}</fmt:formatNumber>원<small>/1박</small></div><span><b><i class="fa fa-heart"></i></b>${list.ag_grade}점</span>
                            <div style="width:200px;padding-left:30%;">
                            <a href="${pageContext.request.contextPath}/accomDetail/accomDetail_private.do?im_ac_num=${list.acc_num}&check_in=${check_in}&check_out=${check_out}&people_count=${people_count}&search=${search}" class="btn-reply button_hover" style="width:90px;">예약하기</a>
                            </div>
                        </div>
                    </div>
                </c:forEach> 
                </c:if>
                 <c:if test="${empty privateGrade}">
                	<p>현재 예약가능한 평점이 높은 프라이빗 하우스가 없습니다.</p>
                </c:if>
                </div>
            </div>
		</div>
		<br>
		<h3>지도</h3>
		<div class="container" style="width:100%; height:300px;max-height:300px;" id="detailMap">
			<span id="address">${privateDetail.acc_address1}</span>
		</div>
</div>
</div>