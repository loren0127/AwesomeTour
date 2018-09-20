$(document).ready(function(){
	var prev = 0;
	$(window).scroll(function() {
		
		/*if($(this).scrollTop() < 50)
	        $('#mainNav').css('opacity','1');
	    else
	        $('#mainNav').css('opacity','.9');*/
		
		//스크롤 내리면 헤더 숨김
		var scrollTop = $(this).scrollTop();
		$('#mainNav').toggleClass('hidden', scrollTop > prev);
		prev = scrollTop;
	});
	
	$('#map_search').click(function(){
		if($('#searchyouwant').val()==''){
			alert('검색어를 입력하세요.');
			return false;
		}
	});
	
	//====================성원이가 만든 예약바====================//
	$("#rv_h").hide();
	
	var date = new Date();
	date.setDate(date.getDate() + 1);
	var today = $.datepicker.formatDate('yy/mm/dd', date); //오늘 날짜 구하기
	//today.setDate(today.getDate()+1);
	
	$('.date_in_h').val(today);
	$("#rv_btn").click(function(){
		$("#rv_h").animate({
			    height: "toggle"
			});
	});
	
	//인원수 부분
	if($('#peo_sum_btn_h').text() == 1){
		$('#people_minus_h').attr('disabled',true); 		
	}
	var peo_num = $('#peo_sum_btn_h').text();
	
	//플러스 버튼 클릭시
	$('#people_plus_h').on("click",function(event){ 
		var new_peo_num = ++ peo_num;
		$('#peo_sum_btn_h').text(new_peo_num);
		$('#people_count_h').val(new_peo_num);
		if(new_peo_num>7){ 
			$('#people_plus_h').attr('disabled',true);
		}

		if(new_peo_num>0){//1초후 다시 버튼활성화
			setTimeout(function(){
				$('#people_minus_h').removeAttr('disabled');
			}, 1000);
		}
		event.preventDefault();
	});

	//마이너스 버튼 클릭시
	$('#people_minus_h').on("click",function(event){ 
		var new_peo_minus = -- peo_num;
		$('#peo_sum_btn_h').text(new_peo_minus);
		$('#people_count_h').val(new_peo_minus);
		if(new_peo_minus<=1){
			$('#people_minus_h').attr('disabled',true); 
		}
		if(new_peo_minus>=2){
			$('#people_minus_h').attr('disabled',false); 
		}
		if(new_peo_minus>1){//1초후 다시 버튼활성화
			setTimeout(function(){
				$('#people_plus_h').removeAttr('disabled');
			}, 1000);
		}
		event.preventDefault();
	});
	
	//============================================================
	$('#search_btn_h').on("click change submit",function(event){
		var date = $('.date_out_h').val();
		var search = $('#address_h').val();
		if(date == '' || date == null){
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

	//====================날짜 설정시 선택한 날짜로부터 한달동안만 보여지게 활성화====================//
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