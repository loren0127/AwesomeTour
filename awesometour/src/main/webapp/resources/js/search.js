$(document).ready(function() {
	var directorList=[];
	if($('#peo_sum_btn').text() ==1){
		$('#people_minus').attr('disabled',true); 		
	}
//	--------------------------인원수 클릭시 숫자변경----------------------------------------------
	var peo_num = $('#peo_sum_btn').text();
	var room_num = $('#room_sum').text();
	
	$('#people_plus').on("click",function(event){ 
		var new_peo_num = ++ peo_num;
		$('#people_sum').text(new_peo_num);
		$('#peo_sum_btn').text(new_peo_num);
		$('#people_count').val(new_peo_num);
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

	$('#people_minus').on("click",function(event){ //인원수 설정시 
		var new_peo_minus = -- peo_num;
		$('#people_sum').text(new_peo_minus);
		$('#peo_sum_btn').text(new_peo_minus);
		$('#people_count').val(new_peo_minus);
		if(new_peo_minus<=1){
			$('#people_minus').attr('disabled',true); 
		}
		if(new_peo_minus>=2){
			$('#people_minus').attr('disabled',false); 
		}
		if(new_peo_minus>1){ //2초후 다시 버튼활성화
			setTimeout(function(){
				$('#people_plus').removeAttr('disabled');
			}, 1000);
		}
		event.preventDefault();
	});
	// ------------------------------------------------------------------------	
	
	$('#search').on("click change submit",function(event){
		var date = $('.date_out').val();
		var search = $('#address').val();
		if(date == ''){
			alert('체크인 날짜와 체크아웃 날짜를 선택하세요.');
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
	
/*	var directorList = new Array();
	$('#address').keyup(function (event) {
		search_auto = $(this).val();
		director_List(search_auto);
	});*/

/*	var director_value = '';
	var director_count = 0;
	$('#address').keydown(function (event) {
		search_auto = $(this).val();
		console.log('search_auto : ' + search_auto);
		director_List(search_auto);
	});*/

	$('#address').keypress(function (event) {
		var search = $(this).val();
		console.log('search_auto : ' + search);
		director_List(search);
	});

	function director_List(search){
		$.ajax({
			type: 'post',
			url: '../main/mainAjax.do',
			data: {search:search},
			dataType: 'json',
			success: function(data) {
				var list = data.list;
				var count = data.count;
				var rowCount = data.rowCount;
				var currentPage = data.currentPage;
				directorList = [];
				$(list).each(function (index, element) {
						directorList.push(element.acc_name);
						directorList.push(element.acc_address1);
						console.log('--------------' +element.acc_name);
						console.log('--------------' +element.acc_address1);
				});
				console.log('list: ' + list + 'directorList : ' + directorList);
			},
			error:function(){
					
		       }
		});	
		$('#address').autocomplete({
			source: directorList,
			minLength: 2,
			max:5
		}); //자동완성 처리	
	}
});

//----------------------------날짜 설정시 선택한 날짜로부터 한달동안만 보여지게 활성화 -----------------------//
$(function(selectedDate) {
	var date = new Date();
	date.setDate(date.getDate() + 1);

	$('.date_in').datepicker({
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
			$('.date_out').datepicker('option','minDate',selected);//문자열로 반환
			$('.date_out').datepicker('option','maxDate',maxDate); //객체로 전달하여 toString에 인해 문자열로 반환
			$('.date_in').val(selected);
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
	$('.date_out').datepicker({
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

//----------------------------------------------ajax------------------------------------------------------

