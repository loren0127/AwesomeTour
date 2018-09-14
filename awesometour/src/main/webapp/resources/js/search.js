$(document).ready(function() {	
	$('#pe_pop').hide();
	var today = $.datepicker.formatDate('yy/mm/dd', new Date()); //오늘 날짜 구하기
	$('#date_in').val(today);

	//인원수 클릭시 div 활성화
	$('.people_pop').click(function(event){
		event.stopPropagation();
		$('#pe_pop').show();

		event.preventDefault();
	});
	
	
	
	//인원수 체크부분 외 다른 영역을 클릭시 인원수,객실수 버튼이 닫힘.
	$('body').click(function(e){
		if(!$(e.target).hasClass('check')){
			$('#pe_pop').hide();
		}
	});
	


	
//	--------------------------인원수 클릭시 숫자변경----------------------------------------------
	var peo_num = $('#people_sum').text();
	var room_num = $('#room_sum').text();
	
	$('#people_plus').on("click",function(){ 
		var new_peo_num = ++ peo_num;
		$('#people_sum').text(new_peo_num);
		$('#peo_sum_btn').text(new_peo_num);
		$('#people_count').val(new_peo_num);
		if(new_peo_num>7){
			alert('8명이상 검색할수 없습니다.');
			$('#people_plus').attr('disabled',true); 
		}
		if(new_peo_num>0){//1초후 다시 버튼활성화
			setTimeout(function(){
				$('#people_minus').removeAttr('disabled');
			}, 1000);
		}
	});

	$('#people_minus').on("click",function(){ //인원수 설정시 
		var new_peo_minus = -- peo_num;
		$('#people_sum').text(new_peo_minus);
		$('#peo_sum_btn').text(new_peo_minus);
		$('#people_count').val(new_peo_minus);
		
		if(new_peo_minus<2){
			alert('최소인원은 1명입니다.');
			$('#people_minus').attr('disabled',true); 
		}
		if(new_peo_minus>1){
			$('#people_minus').attr('disabled',false); 
		}
		if(new_peo_minus>1){ //2초후 다시 버튼활성화
			setTimeout(function(){
				$('#people_plus').removeAttr('disabled');
			}, 1000);
		}
		
	});
	// ------------------------------------------------------------------------	
	
	$('#search').on("click change submit",function(event){
		var date = $('#date_out').val();
		var search = $('#address').val();
		if(date == ''){
			alert('시작날짜와 끝날짜를 설정하세요');
			return false;
		}
		if(search != ''){
			 //검색 시작일 비우기
			return true;
		}else{
		
			$('#address').val('서울');
			return true;
		}
		
		
		event.preventDefault();
	});
	
});

//----------------------------날짜 설정시 선택한 날짜로부터 한달동안만 보여지게 활성화 -----------------------//
$(function(selectedDate) {
	var minDate = new Date();

	$('#date_in').datepicker({
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
	});
	$('#date_in1').datepicker({
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
	});
	$('#date_out').datepicker({
		showMonthAfterYear:true, //default 월 년
		dateFormat:'yy/mm/dd',
		monthNames: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'], // 개월 텍스트 설정
	    monthNamesShort: ['1 월','2 월','3 월','4 월','5 월','6 월','7 월','8 월','9 월','10 월','11 월','12 월'], // 개월 텍스트 설정
		dayNamesMin:['일','월','화','수','목','금','토'],//default 영문
		monthNamesShort:['1','2','3','4','5','6','7','8','9','10','11','12'],//default 영문
		yearSuffix: '년'
	
	});

});

//----------------------------------------------ajax------------------------------------------------------

