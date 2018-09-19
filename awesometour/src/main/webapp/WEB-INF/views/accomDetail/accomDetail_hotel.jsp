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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/accomDetail/accomDetail_hotel.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-ui.min.js"></script>

<div class="container" style="min-height: 3500px;">
	<div class="comments-area" style="min-height: 4600px;">
		<div id="carouselExampleControls" class="carousel slide"
			data-ride="carousel">
			<div class="carousel-inner">
				<c:forEach var="hotel" items="${totalHotelImage}" varStatus="status">
					<c:forEach var="i" begin="0" end="2">
						<c:if test="${i == 0 && status.first}">
							<div class="carousel-item active">
								<img
									src="${pageContext.request.contextPath}/accomDetail/imageView.do?im_ac_num=${hotelDetail.acc_num}&ro_room_num=${hotel.ro_room_num}&kind=im_cover"
									onerror="this.src='${pageContext.request.contextPath}/resources/images/NoImage.gif'">
							</div>
						</c:if>
						<c:if test="${i == 1}">
							<div class="carousel-item">
								<img
									src="${pageContext.request.contextPath}/accomDetail/imageView.do?im_ac_num=${hotelDetail.acc_num}&ro_room_num=${hotel.ro_room_num}&kind=im_image2"
									onerror="this.src='${pageContext.request.contextPath}/resources/images/NoImage.gif'">
							</div>
						</c:if>
						<c:if test="${i == 2}">
							<div class="carousel-item">
								<img
									src="${pageContext.request.contextPath}/accomDetail/imageView.do?im_ac_num=${hotelDetail.acc_num}&ro_room_num=${hotel.ro_room_num}&kind=im_image3"
									onerror="this.src='${pageContext.request.contextPath}/resources/images/NoImage.gif'">
							</div>
						</c:if>
					</c:forEach>
				</c:forEach>
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
		<h3 style="float: left; width: 90%;">${hotelDetail.acc_name}</h3>
		<div style="float: right; width: 10%;font-size:20px;"><span style="padding-right:10%;"><b><i class="fa fa-heart"></i></b>&nbsp;${hotelDetail.ag_grade}점</span></div>
		<div style="float: left; width: 100%;">
			<c:if test="${hotelDetail.acc_grade == 1}">
				<i class="fa fa-star star-color"></i>
			</c:if>
			<c:if test="${hotelDetail.acc_grade == 2}">
				<i class="fa fa-star star-color"></i>
				<i class="fa fa-star star-color"></i>
			</c:if>
			<c:if test="${hotelDetail.acc_grade == 3}">
				<i class="fa fa-star star-color"></i>
				<i class="fa fa-star star-color"></i>
				<i class="fa fa-star star-color"></i>
			</c:if>
			<c:if test="${hotelDetail.acc_grade == 4}">
				<i class="fa fa-star star-color"></i>
				<i class="fa fa-star star-color"></i>
				<i class="fa fa-star star-color"></i>
				<i class="fa fa-star star-color"></i>
			</c:if>
			<c:if test="${hotelDetail.acc_grade == 5}">
				<i class="fa fa-star star-color"></i>
				<i class="fa fa-star star-color"></i>
				<i class="fa fa-star star-color"></i>
				<i class="fa fa-star star-color"></i>
				<i class="fa fa-star star-color"></i>
			</c:if>
		</div>
		<div style="float: left; width: 90%;">${hotelDetail.acc_address1}<a href="#detailMap" style="font-size:12px;"><b>자세한 주소</b></a></div>
		<div style="float: right; width: 10%;" id="theme_id">
			<b style="color: #d900ed;">${hotelDetail.acc_theme}</b>테마
		</div>
		<br> <br><br><br>
		<div><input type="button" class="btn-reply button_hover" style="font-size:12px;" id="hotel_host_message" value="문의하기"></div>
		<div id="accomDetail_dialog" title="메시지 보내기">
			<br>
			<p style="text-align: center;">
				<b>${hotelDetail.acc_name}</b>에 문의하기
			</p>
			<form>
				<input type="hidden" name="message_receiver" id="message_receiver"
					value="${hotelDetail.acc_host}"> <input type="text"
					name="message_title" placeholder="제목 입력" size="37"
					id="message_title" maxlength="90"><br>
				<br>
				<textarea rows="6" cols="40" placeholder="내용 입력"
					name="message_content" id="message_content"></textarea>
			</form>
		</div>
		<hr size="1" noshade>
		<div style="padding-left: 30%;">
			<span>【 숙소 100배 즐기기 】</span> <span style="padding-left: 10%;">
				<c:forEach var="service" items="${serviceSet}">
	${service}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</c:forEach>
			</span>
		</div>
		<hr size="1" noshade>
		<br> <br> <br>
		<input type="hidden" id="check_in" value="${check_in}">
		<input type="hidden" id="check_out" value="${check_out}">
		<input type="hidden" id="people_count" value="${people_count}">
		<!-- 룸디테일 -->
		<input id="breakfast" type="hidden" value="${breakfast}"> <input
			id="WIFI" type="hidden" value="${WIFI}">
		<div class="room_standard">
			<div class="section-top-border" style="text-align: center;">
				<h4 class="mb-30 title_color">스탠다드</h4>
				<div class="progress-table-wrap">
					<div class="progress-table tableStandard">
						<div class="table-head">
							<div class="country">객실/투숙공간</div>
							<div class="country">서비스/시설</div>
							<div class="visit">1박요금</div>
							<div class="visit">예약하기</div>
						</div>
					</div>
				</div>
				<div class="paging-link"
					style="text-align:center;<c:if test="${more == 0}">display:none;</c:if>">
					<a href="#" style="color: #222; font-weight: bold;">더보기&nbsp;<b>∨</b></a>
				</div>
			</div>
		</div>
		<br> <br>
		<div class="room_delux">
			<div class="section-top-border" style="text-align: center;">
				<h4 class="mb-30 title_color">디럭스</h4>
				<div class="progress-table-wrap">
					<div class="progress-table tableDelux">
						<div class="table-head">
							<div class="country">객실/투숙공간</div>
							<div class="country">서비스/시설</div>
							<div class="visit">1박요금</div>
							<div class="visit">예약하기</div>
						</div>
					</div>
				</div>
				<div class="paging-link2"
					style="text-align:center;<c:if test="${more2 == 0}">display:none;</c:if>">
					<a href="#" style="color: #222; font-weight: bold;">더보기&nbsp;<b>∨</b></a>
				</div>
			</div>
		</div>
		<br> <br>
		<div class="room_suite">
			<div class="section-top-border" style="text-align: center;">
				<h4 class="mb-30 title_color">스위트</h4>
				<div class="progress-table-wrap">
					<div class="progress-table tableSuite">
						<div class="table-head">
							<div class="country">객실/투숙공간</div>
							<div class="country">서비스/시설</div>
							<div class="visit">1박요금</div>
							<div class="visit">예약하기</div>
						</div>
					</div>
				</div>
				<div class="paging-link3"
					style="text-align:center;<c:if test="${more3 == 0}">display:none;</c:if>">
					<a href="#" style="color: #222; font-weight: bold;">더보기&nbsp;<b>∨</b></a>
				</div>
			</div>
		</div>
		<br> <br> <br>
		<div>
			<div class="container" style="background-color: white;">
				<br> <br>
				<h4>
					한 눈에 보기&nbsp;<b>∨</b>
				</h4>
				<div class="row">
					<div class="col-sm-12 col-md-6 d_flex align-items-center" style="text-align:left;">
						<div class="about_content">
							<h5 class="title title_color">편의시설 및 서비스</h5>
							<c:forEach var="service" items="${serviceNameArray}">
								<ul>
									<li>${service}</li>
								</ul>
							</c:forEach>
							<br>
						</div>
						<div>
							<h5>주의사항</h5>
							<ul>
								<li>체크인 : ${hotelDetail.acc_in}시</li>
								<li>체크아웃 : ${hotelDetail.acc_out}시</li>
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
		</div>
		<br> <br>
		<div id="reply_div">
			<div class="comment-list">
				<div class="comment-form">
					<h4>Review&nbsp;&nbsp;<span id="reviewCount"></span></h4>
					<div class="starRev" style="float: left;">
						<span class="starR1 plus">별1_왼쪽</span> <span class="starR2">별1_오른쪽</span>
						<span class="starR1">별2_왼쪽</span> <span class="starR2">별2_오른쪽</span>
						<span class="starR1">별3_왼쪽</span> <span class="starR2">별3_오른쪽</span>
						<span class="starR1">별4_왼쪽</span> <span class="starR2">별4_오른쪽</span>
						<span class="starR1">별5_왼쪽</span> <span class="starR2">별5_오른쪽</span>
					</div>
					<span class="starCount" style="float: right;padding-right: 38%;"></span> <br>
					<br> <br>
					<form id="re_form">
						<input type="hidden" name="im_ac_num" value="${hotelDetail.acc_num}" id="im_ac_num">
						<input type="hidden" name="ag_acc_num" value="${hotelDetail.acc_num}" id="ag_acc_num">
						<input type="hidden" name="al_acc_num" value="${hotelDetail.acc_num}" id="al_acc_num">
						<input type="hidden" name="user_email" value="${user_email}" id="user_email">
						<input type="hidden" name="ag_email" value="${user_email}" id="ag_email">
						<input type="hidden" name="al_email" value="${user_email}" id="al_email">
					    <input type="hidden" name="re_acc_num" value="${hotelDetail.acc_num}" id="re_acc_num">
						<input type="hidden" name="re_email" value="${user_email}" id="re_email">
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
							<div id="re_second" style="float: left;">
								<input type="submit" class="btn-reply text-uppercase"
									value="click">
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
						style="text-align:center;<c:if test="${more4 == 0}">display:none;</c:if>">
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
		<div class="section-top-border" style="clear: left;">
			<h3>평점이 높은 호텔</h3>
			<br>
            <div class="container">
                <div class="row">
                <c:if test="${!empty hotelGrade}">
                <c:forEach var="list" items="${hotelGrade}">
                    <div class="col-lg-3 col-sm-6">
                        <div class="accomodation_item text-center">
                            <div class="hotel_img">
                                <img
									src="${pageContext.request.contextPath}/accomDetail/imageView.do?im_ac_num=${list.acc_num}&ro_room_num=${list.ro_room_num}&kind=im_cover"
									style="width:200px;"
									onerror="this.src='${pageContext.request.contextPath}/resources/images/NoImage.gif'">
                            </div>
                            <h5 style="color:#d900ed;">${list.acc_name}</h5>
                            <div><fmt:formatNumber>${list.ro_price}</fmt:formatNumber>원<small>/1박</small></div><span><b><i class="fa fa-heart"></i></b>${list.ag_grade}점</span>
                            <div style="width:200px;padding-left:30%;">
                            <a href="${pageContext.request.contextPath}/accomDetail/accomDetail_hotel.do?im_ac_num=${list.acc_num}&check_in=${check_in}&check_out=${check_out}&people_count=${people_count}&search=${search}" class="btn-reply button_hover" style="width:90px;">예약하기</a>
                            </div>
                        </div>
                    </div>
                </c:forEach> 
                </c:if>
                <c:if test="${empty hotelGrade}">
                	<p>현재 예약가능한 평점이 높은 호텔이 없습니다.</p>
                </c:if>
                </div>
            </div>
		</div>
		<h3>지도</h3>
		<input type="hidden" value="${hotelDetail.acc_name}" name="acc_name" id="acc_name">
		<div id="detailMap" class="section-top-border" style="width:100%; height:300px;max-height:300px;">
			<span id="address">${hotelDetail.acc_address1}</span>
		</div>
	</div>
</div>



