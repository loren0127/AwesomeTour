<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style>


#list_nav_h li{
	padding:0 3px 15px;; 
}
#people_plus_h{
display: inline!important;
 	}
 	 #search_h:hover{
		color: #212529;
		background-color: #fff;
	    border-color: #ffc107;
	  }
 @media ( max-width:766px ) {

 	#rv_h input[type="text"] {
 		width:100%!important;
 		margin: 0 5px!important;
 	}
 	#rv_h select {
 		width:100%!important;
 		margin: 0 5px!important;
 	}


 	#plus_minus_h{
 		padding-left: 33%!important;
 	}
 	#search_btn_h{
 		text-align:center;
 	}


 }

</style>

<script>
$(function(){
	
	var date = new Date();
	date.setDate(date.getDate() + 1);
	var today = $.datepicker.formatDate('yy/mm/dd', date); //오늘 날짜 구하기
	//today.setDate(today.getDate()+1);
	
	$('.date_in_h').val(today);
	$("#rv_h").hide();
	$("#rv_btn").click(function(){
		$("#rv_h").animate({
			    height: "toggle"
			});
	});
	var peo_num = $('#peo_sum_btn_h').text();
	var room_num = $('#room_sum_h').text();
	
	$('#people_plus_h').on("click",function(event){ 
		var new_peo_num = ++ peo_num;
		$('#people_sum_h').text(new_peo_num);
		$('#peo_sum_btn_h').text(new_peo_num);
		$('#people_count_h').val(new_peo_num);
		if(new_peo_num>7){ 
			$('#people_plus').attr('disabled',true);
		}

		if(new_peo_num>0){//1초후 다시 버튼활성화
			setTimeout(function(){
				$('#people_minus').removeAttr('disabled');
			}, 1000);
		}
		event.preventDefault();
	});

	$('#people_minus_h').on("click",function(event){ //인원수 설정시 
		var new_peo_minus = -- peo_num;
		$('#people_sum').text(new_peo_minus);
		$('#peo_sum_btn').text(new_peo_minus);
		$('#people_count').val(new_peo_minus);
		if(new_peo_minus<=1){
			$('#people_minus_h').attr('disabled',true); 
		}
		if(new_peo_minus>=2){
			$('#people_minus_h').attr('disabled',false); 
		}
		if(new_peo_minus>1){ //2초후 다시 버튼활성화
			setTimeout(function(){
				$('#people_plus_h').removeAttr('disabled');
			}, 1000);
		}
		event.preventDefault();
	});
	// ------------------------------------------------------------------------	
	
	$('#search_h').on("click change submit",function(event){
		var date = $('.date_out_h').val();
		var search = $('#address_h').val();
		if(date == ''){
			alert('체크인 날짜와 체크아웃 날짜를 선택하세요.');
			return false;
		}
		if(search != ''){
			 //검색 시작일 비우기
			return true;
		}else{
		
			$('#address_h').val('서울');
			return true;
		}
		
		
		event.preventDefault();
	});

	//----------------------------날짜 설정시 선택한 날짜로부터 한달동안만 보여지게 활성화 -----------------------//
	$(function(selectedDate) {
		var date = new Date();
		date.setDate(date.getDate() + 1);

		$('.date_in_h').datepicker({
			showMonthAfterYear:true, //default 월 년
			dateFormat:'yy/mm/dd',
			monthNames: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'], // 개월 텍스트 설정
		    monthNamesShort: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'], // 개월 텍스트 설정
			dayNamesMin:['일','월','화','수','목','금','토'],//default 영문
			monthNamesShort:['1','2','3','4','5','6','7','8','9','10','11','12'],//default 영문
			yearSuffix: '년',
			defaultDate: +7, 
			minDate :  date,
			onSelect: function(selected){
				var maxDate = new Date(selected);
				maxDate.setDate(maxDate.getDate() + 31);
				$('.date_out_h').datepicker('option','minDate',selected);//문자열로 반환
				$('.date_out_h').datepicker('option','maxDate',maxDate); //객체로 전달하여 toString에 인해 문자열로 반환
				$('.date_in_h').val(selected);
			}
		
		});
	/*	$('#date_in1').datepicker({
			showMonthAfterYear:true, //default 월 년
			dateFormat:'yy/mm/dd',
			monthNames: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'], // 개월 텍스트 설정
		    monthNamesShort: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'], // 개월 텍스트 설정
			dayNamesMin:['일','월','화','수','목','금','토'],//default 영문
			monthNamesShort:['1','2','3','4','5','6','7','8','9','10','11','12'],//default 영문
			yearSuffix: '년',
			minDate : minDate,
			onSelect: function(selected){
				var maxDate = new Date(selected);
				maxDate.setDate(maxDate.getDate() + 31);

				$('#date_out').datepicker('option','minDate',selected);//문자열로 반환
				$('#date_out').datepicker('option','maxDate',maxDate); //객체로 전달하여 toString에 인해 문자열로 반환
			}
		});*/
		$('.date_out_h').datepicker({
			showMonthAfterYear:true, //default 월 년
			dateFormat:'yy/mm/dd',
			monthNames: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'], // 개월 텍스트 설정
		    monthNamesShort: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'], // 개월 텍스트 설정
			dayNamesMin:['일','월','화','수','목','금','토'],//default 영문
			monthNamesShort:['1','2','3','4','5','6','7','8','9','10','11','12'],//default 영문
			yearSuffix: '년',
			minDate : date

		
		});

	});

	
	
});
</script>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-light fixed-top bg-white" id="mainNav" >
	<div class="container">
		<a class="navbar-brand js-scroll-trigger" href="${pageContext.request.contextPath}/main/main.do"><i class="fa fa-font"></i><span style="font-weight:bold;">wesome Tour</span></a>
		<button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" 
				aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
          <i class="fa fa-bars"></i>
        </button>
        <div class="navbar-collapse collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="#" id="rv_btn">예약</a></li>
				<li class="nav-item"><a class="nav-link js-scroll-trigger" id="opener1" style="cursor:pointer;" data-tooltip-text="지도에서 직접 검색하세요.">지도</a></li>
				<li class="nav-item"><a class="nav-link js-scroll-trigger"
					href="${pageContext.request.contextPath}/group/groupMain.do">모임</a></li>
				<c:if test="${empty user_email}">
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="${pageContext.request.contextPath}/member/login.do">로그인</a></li><!-- 로그아웃 -->
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="${pageContext.request.contextPath}/mail/mailForm.do">회원가입</a></li><!-- 마이페이지 -->
				</c:if>
				<c:if test="${!empty user_email}">
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li><!-- 테스트 -->
					<li class="nav-item"><a class="nav-link js-scroll-trigger"
						href="${pageContext.request.contextPath}/mypage/mypageMemberDetail.do">마이페이지</a></li><!-- 테스트 -->
				</c:if>
				<c:if test="${(!empty user_email) && (user_auth == 4) }">
					<li class="nav-item"><a class="nav-link js-scroll-trigger" href="${pageContext.request.contextPath}/admin/adminMainForm.do">관리자 페이지</a></li><!-- 테스트 -->
				</c:if>
			</ul>
		</div>
	</div>

	
</nav>

<!-- 성원이가 만든 예약 바 시작 -->
 <nav class="navbar-dark navbar-expand-sm  fixed-top" id="rv_h" style="margin-top:82px;position:absolute">
 				<form id="search_form" action="../accomList/accomList.do">
 
	<ul class="nav navbar-nav navbar-dark bg-dark justify-content-center"
		id="list_nav_h">
		<li class="nav-item">
		<select name="searchtype" style="height: 40px; width: 150px; margin-top: 15px; padding-left: 5px;">
		<option value="p">프라이빗 하우스</option>
		<option value="h">호텔</option>
		
		</select>
		</li>
		<li class="nav-item"> <input type="text" name="check_in"
			class="date_in_h" value="${map.check_in}"
			style="height: 40px; width: 150px; margin-top: 15px; padding-left: 5px;"
			autocomplete="off"></li>
		<li class="nav-item"><input type="text" name="check_out"
			class="date_out_h" value="${map.check_out}"
			style="height: 40px; width: 150px; margin-top: 15px;"
			autocomplete="off"></li>
		<li class="nav-item" id="plus_minus_h"><input type="hidden"
			name="people_count" id="people_count" value="1">
			<button id="people_h" name="people"
				style="height: 40px; width: 150px; position: absolute; background-color: white; border: 1px solid #A9A9A9; z-index: 0; margin-top: 15px;display: inline;">
				<span id="peo_sum_btn_h">1</span>명
			</button> <input type="button" name="minus" id="people_minus_h" value="-"
			class="form-control"
			style="border: 0;; font-size: 20px; width: 40px; position: relative; margin-top: 16px; margin-left: 1px;display: inline;">
			<input type="button" name="people_plus" class="form-control"
			id="people_plus_h" value="+"
			style="border: 0; font-size: 20px; position: relative; z-index: 1; width: 40px; margin-left: 65px; margin-top: 16px;display: inline;">
		</li>
		<li class="nav-item"><input type="text"
			style="margin-top: 15px; width: 100%; height: 40px;" id="je_search_h"
			name="search" placeholder="구 또는 이름을 검색하세요" value="${map.search}" autocomplete="off">
		</li>
		<li class="nav-item" id="search_btn_h">
			<button type="submit" class="btn btn-warning" id="search_h"
				style=" height: 40px; width: 100px; margin-top: 15px;">검색</button>
		</li>
	</ul>
	</form>
</nav>
<!-- 성원이가 만든 예약 바 끝 -->

<!-- Map -->
<div class="container">
	<div id="mapSearch_dialog">
		<div>
			<form action="main.do" method="get">
				<div style="display:inline-flex;margin-bottom:10px;">
					<div><i class="fa fa-search" style="margin-top:9px"></i></div>
					<input type="text" class="form-control" id="searchyouwant" placeholder="숙소명/지역/종류로 검색" style="width:100%;padding-left:40px;">
					<div><i class="fa fa-close" style="margin-top:10px"></i></div>
					<input type="submit" value="검색" id="map_search" class="btn btn-warning" style="margin-left:10px;">
				</div>
			<span class="closer">&times;</span>
			</form>
		</div>
		<div id="mapSearch" style="width: 100%; height: 700px;"></div>
	</div>
</div>