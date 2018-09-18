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
    var count;
    var rowCount;
    var data;
    var check_search=0;
	
	function numberWithCommas(x) {
		return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	//서비스 및 편의시설 체크박스 이벤트
	$('input[type="checkbox"]').on('click', function() {
		
		
		
		if($('input[type="checkbox"]:checked').length == 0 && $('input[type="radio"]:checked').length == 0 && $('select[name="price"] > option:selected').val() == ''){
			check_search = 0;
		}else{
			check_search = 1;
		}
		console.log($('input[type="checkbox"]:checked').length);
		console.log($('input[type="radio"]:checked').length);
		console.log($('select[name="price"] > option:selected').val());
		console.log('check_search: ' + check_search);
		
		search();
	});

	//호텔성급 이벤트
	$('input[type="radio"]').on('click', function() {
		check_search = 1;
		search();
	});

	// 가격 정렬 이벤트
	$('select[name="price"]').on('click change', function() {
		check_search = 1;
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
		check_search = 1;
		event.preventDefault();
		search();
	});
	$('#more').on('click', function(event) {
		search($('#more').attr('id'));
		event.preventDefault();
		event.stopPropagation();
	});

	function search(btn) {
		if(check_search==0){
			data = 'searchtype=${map.searchtype}&check_in=${map.check_in}&check_out=${map.check_out}&people_count=${map.people_count}&search=${map.search}';
		}else{			
			data = $('#se_form').serialize();
		}

		if (btn == 'more') {
			pageNum += 1;
		} else {
			pageNum = 1;
			$('#col-6').empty();
		}

		data += '&pageNum=' + pageNum;
		//alert(data);
		$.ajax({
					type : 'post',
					url : 'accomlistajax.do',
					data : data,
					dataType : 'json',
					cache : false,
					timeout : 30000,
					success : function(data) {

						if (data.result == 'success') {
							var list = data.list;
							count = data.count;
							rowCount = data.rowCount;
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
							pagingAppend += '<div class="col-10" style="height: 200px; border: 1px solid #e5e5e5; border-right: none;padding-left: inherit;box-shadow: 0 2px 2px rgba(0,0,0,.12);">';
							pagingAppend += '<span>';
							
							if (item.ro_sub == 'h') {
								pagingAppend += '<a style="text-decoration: none;"href="../accomDetail/accomDetail_hotel.do?im_ac_num='+ item.acc_num+ '&check_in=${map.check_in}&check_out=${map.check_out}&people_count=${map.people_count}&search=${map.search}">';								
							pagingAppend += '<img class="rounded" src="${pageContext.request.contextPath}/accomList/ListimageView.do?im_ac_num='+ item.acc_num+ '&kind=im_cover" style="width: 200px; height: 170px; float: left; margin: 13px 15px 10px 10px;"></a>';
							}
							
							if (item.ro_sub == 'p') {
								pagingAppend += '<a style="text-decoration: none;" href="../accomDetail/accomDetail_private.do?im_ac_num='+ item.acc_num+ '&check_in=${map.check_in}&check_out=${map.check_out}&people_count=${map.people_count}&search=${map.search}">';								
							pagingAppend += '<img class="rounded" src="${pageContext.request.contextPath}/accomList/ListimageView.do?im_ac_num='+ item.acc_num+ '&kind=im_cover" style="width: 200px; height: 170px; float: left; margin: 13px 15px 10px 10px;"></a>';
							}
							
							pagingAppend += '<div style="margin-top: 12px;">';
							pagingAppend += '<b><span>'+ item.acc_name+ '</span></b>';
							pagingAppend += '</div>';

							if (item.ro_sub == 'h') {
								pagingAppend += '<span>호텔성급:'+ grade_str+ '</span><br>';
								pagingAppend += '<input type="hidden" value="'+item.acc_num+'" name="im_ac_num">';
							}
								pagingAppend += '</span>';
								pagingAppend += '<span>'+ item.acc_address1+ '</span><br>';
								pagingAppend += '<input type="hidden" value="'+item.se_name+' id="service_name"">';
								pagingAppend += '<div>';
								pagingAppend += '<span class="hobby_small rounded" style="height:30px; text-align:center;color: green;font-weight: 700;">'+ item.acc_theme+ '</span><br>';
								pagingAppend += '</div>';
								pagingAppend += '<div class="service">';
								pagingAppend += '<div class="row">';
								
							var array = item.se_name.split(',');

							for (var i = 0; i < 3; i++) {
								pagingAppend += '<div class="col-3" style="margin: auto;display:contents;"><div class="hobby_small rounded" style="height: 30px; width: max-content; text-align: center;color: #D900ED;margin-right: 10px;"><h6>'+array[i]+ '</h6></div></div>';
							}

								pagingAppend += '</div>';
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
								pagingAppend += '<div class="col-2" style="height: 200px; border: 1px solid #e5e5e5;padding-top: 65px;text-align:center;box-shadow: 0 2px 2px rgba(0,0,0,.12);"id="col-3">';
								pagingAppend += '<span style="font-size:12px;">1박 요금</span><br>';
								pagingAppend += '￦<span>'+ numberWithCommas(item.ro_price)+ '</span><br>';
								pagingAppend += '</div>';
								pagingAppend += '</div>';

							
							console.log('pageNum : ' + pageNum+','+'count : '+count+','+'rowCount : '+rowCount);
							if(pageNum>=Math.ceil(count/rowCount)){
								console.log('check_search: ' + check_search);
								//다음 페이지가 없음
								$('#more').hide();
							}else{
								console.log('check_search: ' + check_search);
								//다음 페이지가 존재
								$('#more').show();
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
		style="z-index: 9998; background-color: white;margin-top: 65px;">
		<!-- 검색 시작 -->
		
		<div class="form-row" style="width: 100%; background-color: #d5e1ed;height: 70px;">
						<div style="margin-left: 150px;">
				<input type="text" style="margin-top: 10px;margin-right: 30px;width: 60%;height: 70%;margin-left: 140px;"
					id="je_search" name="search"
					placeholder="구 또는 이름을 검색하세요" value="${map.search}">
			</div>
			<div>
				<input type="hidden" name="searchtype" value="${map.searchtype}">
			 <input
					type="text" name="check_in"
					class="date_in" value="${map.check_in}"
					style="height: 50px;width: 150px;margin-top: 10px;" autocomplete="off">

			</div>
			<div style="margin-right: 8px;">

				<input type="text" name="check_out"
					class="date_out" value="${map.check_out}"
					style="height: 50px;width: 150px;margin-top: 10px;" autocomplete="off">
			</div>
			<div class="people_pop" style=" margin-right: 8px; margin-top: 10px;">
				<input type="hidden" name="people_count" id="people_count"
					value="1">

				<button id="people" name="people"
					style="height: 50px;width: 150px;position: absolute;background-color: white;">
					<span id="peo_sum_btn">${map.people_count}</span>명
				</button>

				<div class="check" id="pe_pop" 
					style="position: absolute; margin-left: 50px; display: inline-flex;margin-top: 5px;margin-left: 3px;">
					<input type="button" name="minus" id="people_minus" value="-"
						class="form-control" style="border:0;padding-top: 3px;font-size:20px;margin-right: 75px;">
					<input type="button" name="people_plus" class="form-control"
						id="people_plus" value="+" style="border:0;font-size:20px;">

				</div>
			</div>

			<div style="margin-left: 150px;margin-top: 10px;">
				<button type="submit" class="btn btn-block btn-lg btn-primary">검색</button>
			</div>
		</div>
		<div></div>

	</div>

	<!-- ====================숙소 리스트 시작==================== -->
	<div class="container" style="min-height: 3500px;">
		<h2>숙소 목록</h2>
		<div class="row" style="margin-top: 30px;">
			<div id="map" class="col-3">
				<button id="opener2" style="position: absolute;border: 0;margin-left: 25px;margin-top: 5px;background-color: inherit;">숙소 위치 확인!<br>
				<i class="fa fa-map-marker" style="font-size: 30px;color:red;"></i></button>
				<img src="../resources/images/지도.jpg" style=" margin-bottom: 40px;"><br>
					<select name="price" style="width: 100%;height: 40px; color: #fff;background-color: #5392f9;margin-bottom: 20px;">
			<option value="">선택하세요</option>
			<option value="ASC">가격 낮은순</option>
			<option value="DESC">가격 높은순</option>
			<option value="avg_DESC">평점</option>
			<option value="test_DESC">추천숙소</option>
			</select>
				<div class="sticky-top" style="border: 1px solid #e5e5e5;box-shadow: 0 2px 2px rgba(0,0,0,.12);">
				<span style="padding-left: 10px;"><b>편의시설</b></span>
				<ul style="list-style: none;padding-left: 10px;">
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
				<span style="padding-left: 10px;"><b>서비스 및 이용규칙</b></span>
				<ul style="list-style: none;padding-left: 10px;">
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
				<span style="padding-left: 10px;"><b>호텔성급</b></span>
				<ul style="list-style: none;padding-left: 10px;">
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
							style="height: 200px; border: 1px solid #e5e5e5; border-right: none;padding-left: inherit;box-shadow: 0 2px 2px rgba(0,0,0,.12);">
							<span> 
							<c:if test="${accom.ro_sub=='h'}">
							<a style="text-decoration: none;" 
								href="../accomDetail/accomDetail_hotel.do?im_ac_num=${accom.acc_num}&check_in=${map.check_in}&check_out=${map.check_out}&people_count=${map.people_count}&search=${map.search}">
							<img class="rounded"
								src="${pageContext.request.contextPath}/accomList/ListimageView.do?im_ac_num=${accom.acc_num}&kind=im_cover"
								style="width: 200px; height: 170px; float: left; margin: 13px 15px 10px 10px;"></a>
							</c:if>
							
							<c:if test="${accom.ro_sub=='p'}">
							<a style="text-decoration: none;" 
								href="../accomDetail/accomDetail_private.do?im_ac_num=${accom.acc_num}&check_in=${map.check_in}&check_out=${map.check_out}&people_count=${map.people_count}&search=${map.search}">
							<img class="rounded"
								src="${pageContext.request.contextPath}/accomList/ListimageView.do?im_ac_num=${accom.acc_num}&kind=im_cover"
								style="width: 200px; height: 170px; float: left; margin: 13px 15px 10px 10px;"></a>
							</c:if>							
								<div style="margin-top: 12px;"><b><span>${accom.acc_name}</span></b></div>
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
							</span>
							<c:if test="${accom.ro_sub=='h'}">						
							<br>
							</c:if>
							<span>${accom.acc_address1}</span><br>
							<div>
							<span class="hobby_small rounded"
												style="height: 30px;  text-align: center;color: green;font-weight: 700;">${accom.acc_theme}</span>							
							</div>
							<div class="service">
								<div class="row">
									<input type="hidden" value="${accom.se_name}"> 
									
									<c:set var="array" value="${fn:split(accom.se_name,',')}" />
									<!-- 서비스를 배열형태로 반환하여 실행함 -->
									<c:forEach var="hobby" items="${array}" begin="0" end="2">
										<div class="col-3" style="margin: auto;display: contents;">
											<div class="hobby_small rounded"
												style="height: 30px; width: max-content; text-align: center;color: #D900ED;margin-right: 10px;">
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
							style="height: 200px; border: 1px solid #e5e5e5;padding-top: 65px;text-align:center;box-shadow: 0 2px 2px rgba(0,0,0,.12);" 
							id="col-3">
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
								name="datepicker1" id="datepicker1" value="${map.check_in}" style="width: 100px;">
							&nbsp;&nbsp;&nbsp; <label for="datepicker2"
								style="display: inline-block;"><i class="fa fa-calendar"></i></label>
							<input type="text" name="datepicker2" value="${map.check_out}" id="datepicker2"
								style="width: 100px;">
						</div>
						<div class="col-auto headcountFilter">
							<label for="headcount" style="display: inline-block;font-weight:bold;">인원수&nbsp;</label>
							<input type="button" value="-" id="count_m" class="plus_minus"> <input
								type="text" value="${map.people_count}" id="headcount"
								style="text-align: center; width: 50px;border: none;font-weight:bold;"> <input
								type="button" value="+" id="count_p" class="plus_minus">
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
						<span style="font-weight:bold;color: rgb(0, 132, 137);">선택한 날짜</span>
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