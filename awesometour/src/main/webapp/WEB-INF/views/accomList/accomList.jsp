<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/search.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.form.min.js"></script>	
	
<script>
//----------------------------------------------ajax 편의시설 클릭시----------------
$(function(){
	//var form = $('#se_form').serialize()
	
$('input[type="checkbox"]').on('click',function(){
	search();

});	
	
$('select[name="price"]').on('click change',function(){
	search();
});
	
$('#se_form').on("submit",function(event){
	//alert(form)
	event.preventDefault();
	search();
});

	function search(){
		$('#se_form').ajaxSubmit({
			
			type:'post',
			url:'accomlistajax.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				
				if(data.result =='success'){
					$('#col-6').empty();
					var list = data.list;
					var count = data.count;
					var pagingAppend = '';
					
					var serviceArr =[];
					var sear = ["WIFI","개인금고","주차장","장애인용 시설","스파(호텔전용)","피트니스 센터",
						"프로젝트 룸","주방","픽업","24시간 프론트(호텔전용)","룸 서비스(식사)","세탁 서비스",
						"어메니티(샴푸,칫솔 등)","조식","반려동물 허용","파티 행사 허용"];

					var se_list = $('#se_num').val();
					$(list).each(function(index,item){

						pagingAppend +='	<div class="row">';
						pagingAppend +='<div class="col-6" style="height: 150px; border: 1px solid;">';
						
						$('input:checkbox[name="se_name"]:checked').each(function(index,item) {
							serviceArr.push($(this).val());//체크된 값 저장 
							
							var se = sear.indexOf();
					    }); 
						
						if(item.ro_sub == 'h'){
						pagingAppend +='<span>호텔성급:'+item.acc_grade+'</span>';
						pagingAppend +='<br>';
							}
								pagingAppend +='<span>'+item.acc_name+'</span> <br>';
								pagingAppend +='<span>호텔성급 : '+item.acc_grade+'</span><br>';
								pagingAppend +='<span>'+item.acc_address1+'</span><br>';
								pagingAppend +='<span>'+item.acc_theme+'</span><br>';
						
						if(item.ro_sub == 'h'){
						pagingAppend +='<a href="../accomDetail/accomDetail_hotel.do?im_ac_num=${accom.acc_num}&check_in=${map.check_in}&check_out=${map.check_out}&people_count=${map.people_count}&search=${map.search}">자세히보기</a>';
						
						}
						if(item.ro_sub == 'p'){
							pagingAppend +='<a href="../accomDetail/accomDetail_private.do?im_ac_num=${accom.acc_num}&check_in=${map.check_in}&check_out=${map.check_out}&people_count=${map.people_count}&search=${map.search}">자세히보기</a>';
						}
						
						pagingAppend +='</div>';
						
					
							pagingAppend +='<div class="col-3" style="height: 150px; border: 1px solid;">';
							pagingAppend +='<span>1박 요금</span><br>';
							pagingAppend +='<span>'+item.ro_price+'</span><br>';
							pagingAppend +='</div>';
							pagingAppend +='</div>';

					
				
					});		
					$('#col-6').append($(pagingAppend).hide().fadeIn(200));
					
					// 페이지붙여넣기
					
					/*$(list).each(function(index,item){
						var output = '<div class="item">';
						output += '	<h4>' + item.id + '	</h4>';

						//문서 객체에 추가
						$('#output').append(output);
					});
					 */
				}else{
					alert('오류');
				}
			
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}

});
</script>
		<form id="se_form">
<div>
	
	<nav class="navbar sticky-top navbar-light bg-light">
		<div class="col-md-10 col-lg-8 col-xl-7">
			<!-- 검색 시작 -->
				<div class="form-row"
					style="width: 1200px; margin-left: 200px; margin-top: 75px;">
					<div>
						 <input
							type="hidden" name="searchtype" value="${map.searchtype}">
							
						<input type="text" id="date_in1" name="check_in"
							value="${map.check_in}" style="height: 50px; width: 150px;"
							autocomplete="off">

					</div>
					<div>

						<input type="text" id="date_out" name="check_out"
							value="${map.check_out}" style="height: 50px; width: 150px;"
							autocomplete="off"> 
					</div>
					<div class="people_pop">
						<input type="hidden" name="people_count" id="people_count"
							value="${map.people_count}">

						<button id="people" name="people"
							style="height: 50px; width: 150px;">
							인원 <span id="peo_sum_btn">1</span>명
						</button>

						<div class="check" id="pe_pop">
							<span>인원<input type="button" name="people_plus"
								id="people_plus" value="+"> <span id="people_sum">1</span>
								<input type="button" name="minus" id="people_minus" value="-"></span>

						</div>
					</div>
					<div>
						<input type="text" style="width: 300px; height: 50px;"
							name="search" value="${map.search}">
					</div>
					
					<div>
						<button type="submit" class="btn btn-block btn-lg btn-primary">검색</button>
					</div>
				</div>
				<div></div>

		</div>
</nav>	
	<!-- 검색창 끝-->


	<!-- 숙소 리스트 시작-->
	<div class="container">
	<h2>숙소 목록</h2>
				<!-- Map Dialog -->
				<button id="opener2" style="margin-top:100px;">지도보기</button>
				
				<button value="DESC" name="best">추천숙소</button>
						<span>가격</span>
						  <select  name="price">
							<option value="ASC">낮은순</option>
							<option value="DESC">높은순</option>
						</select>
						
				<button value="DESC" name="avg">평점</button>
				
		<div class="row">
			<div id="map" class="col-3">
				<span style="text-align:center;"><b>편의시설</b></span>
			<ul style="list-style:none;">
				<li><input type="checkbox" value="WIFI" name="se_name">WIFI</li>
				<li><input type="checkbox" value="개인금고" name="se_name">개인금고</li>
				<li><input type="checkbox" value="주차장" name="se_name">주차장</li>
				<li><input type="checkbox" value="장애인용 시설" name="se_name">장애인용 시설</li>
				<li><input type="checkbox" value="스파(호텔전용)" name="se_name">스파(호텔전용)</li>
				<li><input type="checkbox" value="피트니스 센터" name="se_name">피트니스 센터</li>
				<li><input type="checkbox" value="프로젝트 룸" name="se_name">프로젝트 룸</li>
				<li><input type="checkbox" value="주방" name="se_name">주방</li>
			</ul>
				<span style="text-align:center;"><b>서비스 및 이용규칙</b></span>
			<ul style="list-style:none;">
				<li><input type="checkbox" value="픽업" name="se_name">픽업</li>
				<li><input type="checkbox" value="24시간 프론트(호텔전용)" name="se_name">24시간 프론트(호텔전용)</li>
				<li><input type="checkbox" value="룸 서비스" name="se_name">룸 서비스</li>
				<li><input type="checkbox" value="세탁" name="se_name">세탁</li>
				<li><input type="checkbox" value="어메니티" name="se_name">어메니티</li>
				<li><input type="checkbox" value="조식" name="se_name">조식</li>
				<li><input type="checkbox" value="반려동물 허용" name="se_name">반려동물 허용</li>
				<li><input type="checkbox" value="파티 행사 허용" name="se_name">파티 행사 허용</li>
			</ul>
				<span style="text-align:center;"><b>호텔성급</b></span>
			<ul style="list-style:none;">
				<li><input type="radio" value="1" name="hotel_grade">1</li>
				<li><input type="radio" value="2" name="hotel_grade">2</li>
				<li><input type="radio" value="3" name="hotel_grade">3</li>
				<li><input type="radio" value="4" name="hotel_grade">4</li>
				<li><input type="radio" value="5" name="hotel_grade">5</li>
			</ul>	
					
			</div>



				<div id="col-6" class="col-9">
				
				
				<c:if test="${count == 0}">
				<div class="align-center">등록된 게시물이 없습니다.</div>
				</c:if>
				<c:forEach var="accom" items="${list}">
					<div class="row">
						<div class="col-8" style="height: 150px; border: 1px solid;" >
							<c:if test="${accom.ro_sub=='h'}">
								<span>호텔성급 : ${accom.acc_grade}</span>
								<br>
							</c:if>
							<span>
							<input type="hidden" name="se_num" value="${accom.se_num}" id="se_num">
							${accom.se_num}r
							번호 : ${accom.acc_num}</span><br> <span>이름 :
								${accom.acc_name}</span><br> <span>주소 :
								${accom.acc_address1}</span><br>
							<c:if test="${accom.ro_sub=='h'}">
								<a href="../accomDetail/accomDetail_hotel.do?im_ac_num=${accom.acc_num}&check_in=${map.check_in}&check_out=${map.check_out}&people_count=${map.people_count}&search=${map.search}">자세히보기</a>
							</c:if>
							<c:if test="${accom.ro_sub=='p'}">
								<a href="../accomDetail/accomDetail_private.do?im_ac_num=${accom.acc_num}&check_in=${map.check_in}&check_out=${map.check_out}&people_count=${map.people_count}&search=${map.search}">자세히보기</a>
							</c:if>

						</div>
						<div class="col-4" style="height: 150px; border: 1px solid;" id="col-3">

							<span>1박요금</span><br> <span>${accom.ro_price}</span><br>
							<div></div>
						</div>
					</div>
				</c:forEach>
</div>
				<!-- 숙소 리스트 끝-->
			</div>				
			</div>

		</div>
		</form>
<!-- Map Dialog -->
<div id="mapList_dialog">
			<div>
				<span class="closer">&times;</span>
			</div>
			<div class="map-header">
				<div class="mapFilter" style="padding-bottom:10px">
					<form class="form-inline" action="" method="get">
						<div class="col-3 col-xs-3 priceFilter" style="padding-right:10px;">
							<span style="font-size:13px;">1박당 요금</span>
							<div id="slider-range" style="width:260px;font-size: .7em;"></div>
								<input type="text" id="perNightPriceAbove" readonly>
								<label for="perNightPriceAbove" style="display:inline-block;font-size:13px;">이상&nbsp;&nbsp;&nbsp;</label>
								<input type="text" id="perNightPriceBelow" readonly>
								<label for="perNightPriceBelow" style="display:inline-block;font-size:13px;">이하</label>
						</div>
						<div class="col-auto dateFilter" style="padding-right:10px;">
							<label for="datepicker1" style="display:inline-block;"><i class="fa fa-calendar"></i></label>
							<input type="text" name="datepicker1" id="datepicker1" style="width:100px;">
							&nbsp;&nbsp;&nbsp;
							<label for="datepicker2" style="display:inline-block;"><i class="fa fa-calendar"></i></label>
							<input type="text" name="datepicker2" id="datepicker2" style="width:100px;">
						</div>
						<div class="col-auto headcountFilter">
							<label for="headcount" style="display:inline-block;">인원수&nbsp;</label>
							<input type="button" value="-" id="count_m">
							<input type="text" value="1" id="headcount" style="text-align:center; width:50px;">
							<input type="button" value="+" id="count_p">
						</div>
						<div class="col-auto">
							<input type="submit" value="재검색" id="research" class="btn btn-warning" style="font-size: .8em;font-weight: bold;">
						</div>
					</form>
				</div>
			</div>
			<div class="map-body" style="height: 765px;">
				<div class="map-mapArea" id="mapList" style="width: calc(100% - 450px);height: 765px;float:right;"></div>
				<div class="map-sidePanel" style="width: 450px;">
					<div>예약 가능 숙소&nbsp;<span>0</span>개</div>
					<div>
						<i class="fa fa-calendar-check-o"></i>&nbsp;<span id="perNight3"></span>박
						(<input type="text" id="perNight1" style="width:100px;border:none;">-<input type="text" id="perNight2" style="width:100px;border:none;">)
					</div>
					<!-- <div id="selectedInMap">선택 조건 표시박스</div> -->
					<div id="checkedInMap" style="margin-top:5px;">
						<form action="#">
							<fieldset style="border:none;">
								<label for="detailInMap" style="display:inline-block;">정렬 기준&nbsp;</label>
								<select name="detailInMap" id="detailInMap">
									<option disabled selected>상세 조건을 직접 선택하세요.</option>
									<option value="recommend">오썸투어 추천으로 볼래요!</option>
									<option value="rating">투숙객 평점이 좋은 곳은?</option>
									<option value="minimum">최저가부터 확인하실래요?</option>
									<optgroup label="호텔 성급을 직접 선택!" id="hotelGrade">
										<option value="5성">★★★★★</option>
										<option value="4성">★★★★</option>
										<option value="3성">★★★</option>
									</optgroup>
									<optgroup label="숙소 테마별로 확인!" id="accomTheme">
										<option value="quiet">조용한</option>
										<option value="relax">편안한</option>
										<option value="luxury">럭셔리</option>
										<option value="business">비지니스</option>
										<option value="party">파티</option>
										<option value="romantic">로맨틱</option>
									</optgroup>
								</select>
							</fieldset>	
						</form>
					</div>
					<div id="output" style="padding-bottom:15px;height: 700px; overflow-y: scroll;"></div>
				</div>
			</div>
		</div>