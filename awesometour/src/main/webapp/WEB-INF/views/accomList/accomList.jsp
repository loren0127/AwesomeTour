<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/search.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.form.min.js"></script>

<script>
//----------------------------------------------ajax 편의시설 클릭시----------------
$(function() {
	
	var pageNum = 1;

	function numberWithCommas(x) {
		return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	//서비스 및 편의시설 체크박스 이벤트
	$('input[type="checkbox"]').on('click', function() {

		search();
	});

	//호텔성급 이벤트
	$('input[type="radio"]').on('click', function() {
		search();
	});

	// 가격 정렬 이벤트
	$('select[name="price"]').on('click change', function() {
		search();
	});

	// 추천숙소 정렬 이벤트
	$('select[name="test"]').on('click change', function() {
		search();
	});

	//평점 정렬 이벤트
	$('select[name="avg"]').on('click change', function() {
		search();
	});

	$('#se_form').on("submit", function(event) {
		//alert(form)
		event.preventDefault();
		search();
	});
	$('#more').on('click', function(event) {
		search($('#more').attr('id'));
		event.preventDefault();
		event.stopPropagation();
	});

	function search(btn) {
		var data = $('#se_form').serialize();

		if (btn == 'more') {
			pageNum += 1;
		} else {
			pageNum = 1;
			$('#col-6').empty();
		}

		data += '&pageNum=' + pageNum;
		//alert(data);
		$
				.ajax({
					type : 'post',
					url : 'accomlistajax.do',
					data : data,
					dataType : 'json',
					cache : false,
					timeout : 30000,
					success : function(data) {

						if (data.result == 'success') {
							var list = data.list;
							var count = data.count;
							var pagingAppend = '';

							$(list).each(function(index, item) {
								//호텔성급 숫자->문자 -> 특수문자 로 
								var grade = item.acc_grade;
								grade_str = grade.toString();
								grade_str = grade_str.replace('1', '★');
								grade_str = grade_str.replace('2', '★★');
								grade_str = grade_str.replace('3', '★★★');
								grade_str = grade_str.replace('4','★★★★');
								grade_str = grade_str.replace('5','★★★★★');

							pagingAppend += '<div class="row" style="margin-bottom: 20px;">';
							pagingAppend += '<div class="col-10" style="height: 150px; border: 1px solid #e5e5e5; border-right: none;">';
							pagingAppend += '<span>';
							pagingAppend += '<img class="rounded" src="${pageContext.request.contextPath}/accomList/ListimageView.do?im_ac_num='+ item.acc_num+ '&kind=im_cover" style="width:150px;height:140px;float:left;">';
							pagingAppend += '<b><span>'+ item.acc_name+ '</span></b><br>';

							if (item.ro_sub == 'h') {
								pagingAppend += '<span>호텔성급:'+ grade_str+ '</span>';
								pagingAppend += '<input type="hidden" value="'+item.acc_num+'" name="im_ac_num"> ';
								pagingAppend += '<br>';
							}
								pagingAppend += '</span>';
								pagingAppend += '<span>'+ item.acc_address1+ '</span><br>';
								pagingAppend += '<input type="hidden" value="'+item.se_name+' id="service_name"">';
								pagingAppend += '<div class="row service_arr">';
								pagingAppend += '<span class="hobby_small rounded" style="height:30px; width: max-content;text-align:center;border: 2px solid #D900ED; margin: auto;">'+ item.acc_theme+ '</span><br>';

							var array = item.se_name.split(',');

							for (var i = 0; i < 3; i++) {
								pagingAppend += '<div class="col-3"><div class="hobby_small rounded" style="height:30px;width: max-content;text-align:center;border: 2px solid #D900ED; margin: auto;"><h6>'+ array[i]+ '</h6></div></div>';
							
							}

								pagingAppend += '</div>';

							if (item.ro_sub == 'h') {
								pagingAppend += '<a style="text-decoration: none;"  href="../accomDetail/accomDetail_hotel.do?im_ac_num='+ item.acc_num+ '&check_in=${map.check_in}&check_out=${map.check_out}&people_count=${map.people_count}&search=${map.search}">자세히보기</a>';
								pagingAppend += '<br>';
							}

							if (item.ro_sub == 'p') {
								pagingAppend += '<a style="text-decoration: none;"  href="../accomDetail/accomDetail_private.do?im_ac_num='+ item.acc_num+ '&check_in=${map.check_in}&check_out=${map.check_out}&people_count=${map.people_count}&search=${map.search}">자세히보기</a>';
								pagingAppend += '<br>';
							}
												
								pagingAppend += '</div>';
								pagingAppend += '<div class="col-2" style="height: 150px; border: 1px solid #e5e5e5;"id="col-3">';
								pagingAppend += '<span>1박 요금</span><br>';
								pagingAppend += '<span>'+ numberWithCommas(item.ro_price)+ '</span><br>';
								pagingAppend += '</div>';
								pagingAppend += '</div>';

							var page = data.pageHtml.split('&nbsp;&nbsp;');

							if (page.length - 3 == pageNum) {
								$('#more').hide();
								//$('#col-6').empty();
							} else {
								$('#more').show();
								//$('.service_arr').empty();
							}
						});

							$('#col-6').append($(pagingAppend).hide().fadeIn(200));
							//$('.service_arr').empty();

						} else {
							alert('오류');
						}

					},
					error : function() {
						alert('해당조건에 부합하는 숙소가 없습니다');
					}
				});
	}
});


</script>
<!-- ====================정은이 폼 시작==================== -->
<form id="se_form">
<div>
	<nav class="navbar sticky-top navbar-light bg-light"></nav>
	<!-- 검색창 끝-->

	<div class="sticky-top" id="search_top"
		style="z-index: 9999; background-color: white;margin-top: 65px;">
		<!-- 검색 시작 -->
		<div class="form-row" style="width: 100%;margin-left: 300px;">
			<div>
				<input type="hidden" name="searchtype" value="h"> <input
					type="text" id="date_in1" name="check_in"
					class="form-control hasDatepicker" value="2018/09/27"
					style="height: 50px; width: 150px;" autocomplete="off">

			</div>
			<div>

				<input type="text" id="date_out" name="check_out"
					class="form-control hasDatepicker" value="2018/09/28"
					style="height: 50px; width: 150px;" autocomplete="off">
			</div>
			<div class="people_pop">
				<input type="hidden" name="people_count" id="people_count"
					value="1">

				<button id="people" name="people" class="form-control"
					style="height: 50px; width: 150px;">
					인원 <span id="peo_sum_btn">1</span>명
				</button>

				<div class="check" id="pe_pop"
					style="position: absolute; margin-left: 50px; display: none;">
					<input type="button" name="people_plus" class="form-control"
						id="people_plus" value="+"> <span id="people_sum">1</span>
					<input type="button" name="minus" id="people_minus" value="-"
						class="form-control">

				</div>
			</div>
			<div>
				<input type="text" style="width: 300px; height: 50px;"
					class="form-control" id="je_search" name="search"
					placeholder="구 또는 이름을 검색하세요" value="서울">
			</div>

			<div>
				<button type="submit" class="btn btn-block btn-lg btn-primary">검색</button>
			</div>
		</div>
		<div></div>

	</div>

	<!-- ====================숙소 리스트 시작==================== -->
	<div class="container">
		<h2>숙소 목록</h2>
		<!-- Map Dialog Button Start -->

		<!-- Map Dialog Button End -->

		<select name="price" style="height: 30px; color: #fff;background-color: #5392f9;">
			<option value="ASC">가격 낮은순</option>
			<option value="DESC">가격 높은순</option>
			<option value="avg_DESC">평점</option>
			<option value="test_DESC">추천숙소</option>
		</select>

		<div class="row" style="margin-top: 30px;">
			<div id="map" class="col-3">
				<button id="opener2" style="position: absolute;border: 0;margin-left: 25px;margin-top: 5px;background-color: inherit;">숙소 위치 확인!<br>
				<i class="fa fa-map-marker" style="font-size: 30px;color:red;"></i></button>
				<img src="../resources/images/지도.jpg"><br>
				<div>
				<span style="text-align: center;"><b>편의시설</b></span>
				<ul style="list-style: none;">
					<li><input type="checkbox" value="WIFI" name="se_name">WIFI</li>
					<li><input type="checkbox" value="개인금고" name="se_name">개인금고</li>
					<li><input type="checkbox" value="주차장" name="se_name">주차장</li>
					<li><input type="checkbox" value="장애인용 시설" name="se_name">장애인용
						시설</li>
					<li><input type="checkbox" value="스파(호텔전용)" name="se_name">스파(호텔전용)</li>
					<li><input type="checkbox" value="피트니스 센터" name="se_name">피트니스
						센터</li>
					<li><input type="checkbox" value="프로젝트 룸" name="se_name">프로젝트
						룸</li>
					<li><input type="checkbox" value="주방" name="se_name">주방</li>
				</ul>
				<span style="text-align: center;"><b>서비스 및 이용규칙</b></span>
				<ul style="list-style: none;">
					<li><input type="checkbox" value="픽업" name="se_name">픽업</li>
					<li><input type="checkbox" value="24시간 프론트(호텔전용)"
						name="se_name">24시간 프론트(호텔전용)</li>
					<li><input type="checkbox" value="룸 서비스" name="se_name">룸
						서비스</li>
					<li><input type="checkbox" value="세탁" name="se_name">세탁</li>
					<li><input type="checkbox" value="어메니티" name="se_name">어메니티</li>
					<li><input type="checkbox" value="조식" name="se_name">조식</li>
					<li><input type="checkbox" value="반려동물 허용" name="se_name">반려동물
						허용</li>
					<li><input type="checkbox" value="파티 행사 허용" name="se_name">파티
						행사 허용</li>
				</ul>
				<span style="text-align: center;"><b>호텔성급</b></span>
				<ul style="list-style: none;">
					<li><input type="radio" value="1" name="hotel_grade">★</li>
					<li><input type="radio" value="2" name="hotel_grade">★★</li>
					<li><input type="radio" value="3" name="hotel_grade">★★★</li>
					<li><input type="radio" value="4" name="hotel_grade">★★★★</li>
					<li><input type="radio" value="5" name="hotel_grade">★★★★★</li>
				</ul>
				</div>
			</div>



			<div id="col-6" class="col-9">


				<c:if test="${count == 0}">
					<div class="align-center">등록된 게시물이 없습니다.</div>
				</c:if>
				<c:forEach var="accom" items="${list}">
					<div class="row" style="margin-bottom: 20px;">
						<div class="col-10"
							style="height: 150px; border: 1px solid #e5e5e5; border-right: none;">
							<span> <img class="rounded"
								src="${pageContext.request.contextPath}/accomList/ListimageView.do?im_ac_num=${accom.acc_num}&kind=im_cover"
								style="width: 150px; height: 140px; float: left;"> <b><span>${accom.acc_name}</span></b><br>
								<c:if test="${accom.ro_sub=='h'}">
									<fmt:formatNumber value="${accom.acc_grade}" var="accom_grade"></fmt:formatNumber>
									<c:set var="grade1"
										value="${fn:replace(accom.acc_grade,'1','★')}" />
									<c:set var="grade2"
										value="${fn:replace(accom.acc_grade,'2','★★')}" />
									<c:set var="grade3"
										value="${fn:replace(accom.acc_grade,'3','★★★')}" />
									<c:set var="grade4"
										value="${fn:replace(accom.acc_grade,'4','★★★★')}" />
									<c:set var="grade5"
										value="${fn:replace(accom.acc_grade,'5','★★★★★')}" />
									<c:if test="${accom_grade == '1'}">
									호텔성급 : ${grade1}
								</c:if>
									<c:if test="${accom_grade == '2'}">
									호텔성급 : ${grade2}
								</c:if>
									<c:if test="${accom_grade == '3'}">
									호텔성급 : ${grade3}
								</c:if>
									<c:if test="${accom_grade == '4'}">
									호텔성급 : ${grade4}
								</c:if>
									<c:if test="${accom_grade == '5'}">
									호텔성급 : ${grade5}
								</c:if>
								</c:if>
							</span><br> <span>${accom.acc_address1}</span><br>
							<div class="service">
								<div class="row">
									<input type="hidden" value="${accom.se_name}"> 
									<span class="hobby_small rounded"
												style="height: 30px; width: max-width; text-align: center; border: 2px solid #D900ED; margin: auto;">${accom.acc_theme}</span>
									<c:set var="array" value="${fn:split(accom.se_name,',')}" />
									<!-- 서비스를 배열형태로 반환하여 실행함 -->
									<c:forEach var="hobby" items="${array}" begin="0" end="2">
										<div class="col-3">
											<div class="hobby_small rounded"
												style="height: 30px; width: max-content; text-align: center; border: 2px solid #D900ED; margin: auto;">
												<h6>${hobby}</h6>
											</div>
										</div>
									</c:forEach>
								</div>
							</div>
							<input type="hidden" value="${accom.acc_num}" name="im_ac_num">
							<c:if test="${accom.ro_sub=='h'}">
								<br>
								<a style="text-decoration: none;" 
								href="../accomDetail/accomDetail_hotel.do?im_ac_num=${accom.acc_num}&check_in=${map.check_in}&check_out=${map.check_out}&people_count=${map.people_count}&search=${map.search}">자세히보기</a>
							</c:if>
							<c:if test="${accom.ro_sub=='p'}">
								<br>
								<a style="text-decoration: none;"  
								href="../accomDetail/accomDetail_private.do?im_ac_num=${accom.acc_num}&check_in=${map.check_in}&check_out=${map.check_out}&people_count=${map.people_count}&search=${map.search}">자세히보기</a>
							</c:if>
						</div>
						<div class="col-2"
							style="height: 150px; border: 1px solid #e5e5e5;padding-top: 50px;" id="col-3">
							<span style="font-size:12px;">1박요금</span><br>
							￦<fmt:formatNumber>${accom.ro_price}</fmt:formatNumber>
							<br>
						</div>
					</div>
				</c:forEach>
			</div>
			<button id="more" style="margin-left: 500px;">더보기</button>
			<!-- ====================숙소 리스트 끝==================== -->
		</div>
	</div>

</div>
</form>
<!-- ====================정은이 폼 끝==================== -->

<!-- Map -->
<div class="section-sepa1" style="max-height: 1000px;">
	<div class="container">
		<div id="mapList_dialog">
			<div>
				<span class="closer">&times;</span>
			</div>
			<div class="map-header">
				<div class="mapFilter" style="padding-bottom: 10px">
					<form class="form-inline" action="" method="get">
						<div class="col-3 col-xs-3 priceFilter"
							style="padding-right: 10px;">
							<span style="font-size: 13px;">1박당 요금</span>
							<div id="slider-range" style="width: 260px; font-size: .7em;"></div>
							<input type="text" id="perNightPriceAbove" readonly> <label
								for="perNightPriceAbove"
								style="display: inline-block; font-size: 13px;">이상&nbsp;&nbsp;&nbsp;</label>
							<input type="text" id="perNightPriceBelow" readonly> <label
								for="perNightPriceBelow"
								style="display: inline-block; font-size: 13px;">이하</label>
						</div>
						<div class="col-auto dateFilter" style="padding-right: 10px;">
							<label for="datepicker1" style="display: inline-block;"><i
								class="fa fa-calendar"></i></label> <input type="text"
								name="datepicker1" id="datepicker1" style="width: 100px;">
							&nbsp;&nbsp;&nbsp; <label for="datepicker2"
								style="display: inline-block;"><i class="fa fa-calendar"></i></label>
							<input type="text" name="datepicker2" id="datepicker2"
								style="width: 100px;">
						</div>
						<div class="col-auto headcountFilter">
							<label for="headcount" style="display: inline-block;">인원수&nbsp;</label>
							<input type="button" value="-" id="count_m"> <input
								type="text" value="1" id="headcount"
								style="text-align: center; width: 50px;"> <input
								type="button" value="+" id="count_p">
						</div>
						<div class="col-auto">
							<input type="submit" value="재검색" id="research"
								class="btn btn-warning"
								style="font-size: .8em; font-weight: bold;">
						</div>
					</form>
				</div>
			</div>
			<div class="map-body" style="height: 765px;">
				<div class="map-mapArea" id="mapList"
					style="width: calc(100% - 450px); height: 765px; float: right;"></div>
				<div class="map-sidePanel" style="width: 450px;">
					<div>
						예약 가능 숙소&nbsp;<span>0</span>개
					</div>
					<div>
						<i class="fa fa-calendar-check-o"></i>&nbsp;<span id="perNight3"></span>박
						(<input type="text" id="perNight1"
							style="width: 100px; border: none;">-<input type="text"
							id="perNight2" style="width: 100px; border: none;">)
					</div>
					<!-- <div id="selectedInMap">선택 조건 표시박스</div> -->
					<div id="checkedInMap" style="margin-top: 5px;">
						<form action="#">
							<fieldset style="border: none;">
								<label for="detailInMap" style="display: inline-block;">정렬
									기준&nbsp;</label> <select name="detailInMap" id="detailInMap">
									<option disabled selected>상세 조건을 직접 선택하세요.</option>
									<option value="recommend">오썸투어 추천으로 볼래요!</option>
									<option value="rating">투숙객 평점이 좋은 곳은?</option>
									<option value="minimum">최저가부터 확인하실래요?</option>
									<option value="hotelGrade">호텔 4성급 이상만 볼래요!</option>
									<optgroup label="숙소 테마별로 확인!" id="accomTheme">
										<option value="relax">조용하고 편안</option>
										<option value="luxury">럭셔리</option>
										<option value="business">비지니스</option>
										<option value="party">파티</option>
									</optgroup>
								</select>
							</fieldset>
						</form>
					</div>
					<div id="output"
						style="padding-bottom: 15px; height: 700px; overflow-y: scroll;"></div>
				</div>
			</div>
		</div>
	</div>
</div>