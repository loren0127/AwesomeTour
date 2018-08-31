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
	
	//호텔,프라이빗 하우스 클릭시 프라이빗하우스클릭하였을 때 객실 버튼 비활성화
	$('#type').click(function(event){
		var check = $("#type option:selected").val(); //셀렉트에서 선택한 데이터의 값
		if(check == $('#p').val()){
			$('#room_plus').attr('disabled',true); //버튼 비활성화
			$('#room_minus').attr('disabled',true);	//버튼 비활성화
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
		
	});

	$('#people_minus').on("click",function(){ //인원수 설정시 
		var new_peo_minus = -- peo_num;
		$('#people_sum').text(new_peo_minus);
		$('#peo_sum_btn').text(new_peo_minus);
		$('#people_count').val(new_peo_minus);
	});
	// ------------------------------------------------------------------------	

});

function peopleChange(){


}
//----------------------------날짜 설정시 선택한 날짜로부터 한달동안만 보여지게 활성화 -----------------------//
$(function(selectedDate) {
	var minDate = new Date();

	$('#date_in').datepicker({
		showMonthAfterYear:true, //default 월 년
		dateFormat:'yy/mm/dd',
		dayNamesMin:['일','월','화','수','목','금','토'],//default 영문
		monthNamesShort:['1','2','3','4','5','6','7','8','9','10','11','12'],//default 영문
		changeYear:true,
		changeMonth:true,
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
		dayNamesMin:['일','월','화','수','목','금','토'],//default 영문
		monthNamesShort:['1','2','3','4','5','6','7','8','9','10','11','12'],//default 영문
		changeYear:true,
		changeMonth:true
	});

});

