$(document).ready(function(){
	//숫자 3자리마다 콤마(comma)를 추가
	function numberWithCommas(x) {
	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}
	
	//JSON 문자열로 생성된 주소와 상호명 받을 배열 선언 -> push 함수 사용시 초기화 먼저!
	var locations=[],contents=[],prices=[],styles=[],grades=[];
	//select태그(id=detailInMap)에서 선택되는 옵션값을 받기 위한 변수 선언
	var orderby;
	var check_in;//-->정은이 검색 리스트 받을 때는 $(selector).val()값 선언
	var check_out;
	var people_count;
	var search;
	
	//지도가 뜨는 걸 한 발 늦도록 하기 위해 함수화
	//정은이 데이터가 넘어와야하므로 인자 받기
	function callMap2(orderby,check_in,check_out,people_count,search){
		//정렬 기준에 맞춰 지도에서 마커를 다시 띄워야 하기 때문에
		//이전에 있던 내용물을 비워야 해서 함수 안에서 초기화!!!
		locations=[];
		contents=[];
		prices=[];
		styles=[];
		grades=[];
		
		/*
		 * $.ajax({옵션}) 메소드는 HTTP 요청을 만드는 강력하고도 직관적인 방법을 제공
		 * 옵션은 HTTP 요청을 구성하는 키와 값의 쌍으로 구성되는 헤더의 집합
		 * type: HTTP 요청 방식(GET, POST)
		 * url: 클라이언트가 요청을 보낼 서버의 URL 주소
		 * data: HTTP 요청과 함께 서버로 보낼 데이터
		 * dataType: 서버에서 보내줄 데이터의 타입
		 */
		$.ajax({
			type:'post',
			url:'accomList.do',
			dataType:'json',
			data:{orderby:orderby, check_in:check_in, check_out:check_out, people_count:people_count, search:search},//정렬 조건 선택시 HTTP 요청과 함께 서버로 보낼 데이터(옵션값)
			async:false,//코드가 일시 중지(완료 되기를 기다리는 다른 코드)
			cache:false,
			timeout:30000,
			success:function(data){
				var list = data.list;
				
				//반복문을 통해 데이터 출력
				$(list).each(function(index, item){
					locations.push(item.acc_address1);
					contents.push(item.acc_name);
					prices.push(item.ro_price);
					styles.push(item.acc_theme);
					grades.push(item.ag_grade);
					
					var serviceSplit = item.se_name.split(',', 2);
					var service = serviceSplit[0]+' '+serviceSplit[1];
					
					//최대,최소값 구하기
					getPrice();
															
					//검색 리스트 사이드에 표시하기
					var output = '<div class="mapsCard-container" style="margin-top: 10px;" data-address1="'+item.acc_address1+'" data-accnum="'+item.acc_num+'">';
					output += '<div class="mapsCard-photo"><img src="../accomList/imageView.do?im_ac_num='+item.acc_num+'&kind=im_cover" data-imgnum="'+item.acc_num+'" style="height:150px;"></div>';
					output += '<div class="mapsCard-content">';
					output += '	<div class="mapsCard-name">';
					output += '		<h6>'+item.acc_name+'</h6>';
					output += '	</div>';
					output += '	<div class="2ndRow">';
					output += '		<div class="2ndRowLeftCol" style="float: left;">';
					if(item.ag_grade != null){
						output += '			<div class="starRates" style="color:#70A5FA;font-size:15px;font-weight:bold;">'+item.ag_grade+'점</div>';
					}else if(item.ag_grade == null){
						output += '			<div class="starRates" style="font-style:italic;font-size:15px;">아직 평점이 없네요.</div>';					
					}
					output += '			<div class="reviews" style="font-size:12px;">이용후기 <b>'+item.review_count+'</b>건</div>';
					output += '			<div class="benefits" style="font-size:15px;font-weight:bold;color:#63C355;">'+service+'</div>';
					output += '		</div>';
					output += '		<div class="2ndRowRightCol" style="float: right;">';
					output += '			<div class="accomPrice" data-price="'+item.ro_price+'" style="font-weight:bold;">'+numberWithCommas(item.ro_price)+'원</div>';
				if(item.ro_sub == 'h'){
					//커스텀 데이터 속성 사용 : JQuery에서 HTML 태그의 click 기능을 구현할 때 HTML에 있는 특정 값을 가져와서 사용해야 할 경우
					output += '			<input type="button" data-linknum="'+item.acc_num+'" class="btn btn-warning hotelLink" value="자세히 보기" style="font-size: .8em;font-weight: bold;">';
				}
				if(item.ro_sub == 'p'){
					output += '			<input type="button" data-linknum="'+item.acc_num+'" class="btn btn-warning houseLink" value="자세히 보기" style="font-size: .8em;font-weight: bold;">';
				}
					output += '		</div>';
				if(item.member_auth == 3){
					output += '		<div class="3rdRow" style="color:#D900ED;font-weight:bold;float:right;padding-top:10px;">슈퍼호스트입니다.</div>';
				}
					output += '	</div>';
					output += '</div>';//.mapsCard-content end
					output += '</div>';//.mapsCard-container end
				
				//문서 객체에 추가
				$('#output').append(output);
				});
			},
			error:function(request,status,error){
				alert('네트워크 오류');
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
				
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new daum.maps.services.Geocoder();
		
		var mapContainer = document.getElementById('mapList'), // 지도를 표시할 div 
		mapOption = {
			center: new daum.maps.LatLng(37.5577441, 127.0054455), // 지도의 중심좌표
			level: 7 // 지도의 확대 레벨
		};
		
		//지도를 생성합니다
		var map = new daum.maps.Map(mapContainer, mapOption);
		
		for (var i = 0; i < locations.length; i++) {
			//지오코드의 함수가 내부적으로 반복문의 변수를 받지 못하므로 주의!!!
			
			// 주소로 좌표를 검색합니다
			geocoder.addressSearch(locations[i], function(result, status) {
				
				var address = result[0].address_name;
				//console.log(locations.indexOf(address));//브라우저 콘솔에 인덱스 출력해서 확인
				
				var content = '<div class="arrow_box">대한민국 호텔미텔미</div>';
				if(locations.indexOf(address)>=0){
					//content = contents[locations.indexOf(address)];
					content = prices[locations.indexOf(address)];
					content2 = styles[locations.indexOf(address)];
				}
				
				// 정상적으로 검색이 완료됐으면 
				if (status === daum.maps.services.Status.OK) {
					
					// 위치를 기준으로 지도 중심을 재설정합니다
			        map.setCenter(new daum.maps.LatLng(result[0].y, result[0].x));
			        
					var coords = new daum.maps.LatLng(result[0].y, result[0].x);
					var custom_content = '<div class="arrow_box" data-address="'+address+'">'+numberWithCommas(content)+'원<br>테마:'+content2+'</div>';
					
					// 커스텀 오버레이를 생성합니다
					var customOverlay = new daum.maps.CustomOverlay({
						map: map,
						position: coords,
					    content: custom_content
					});
					
					// 커스텀 오버레이를 지도에 표시합니다
					customOverlay.setMap(map);
				}
			});
		}
	}
	
	//====================상세 페이지====================//
	//jQuery UI 다이얼로그
	$('#mapList_dialog').dialog({
		width:'100%',
		height:900,
		autoOpen: false
	});
	$('#opener2').on('click', function() {
		$('#mapList_dialog').dialog('open');
		callMap2(orderby,check_in,check_out,people_count,search);
		$('.ui-dialog-titlebar').hide();
		$('.ui-dialog').css('zIndex','10000');
		$('#perNight1').blur();
		$('#perNight2').blur();
	});
	$('.closer').on('click', function() {
		$('#mapList_dialog').dialog('close');
	});
	
	//====================맵에서 가격 조절====================//
	function getPrice(){
		var max = Math.max.apply(null, prices);
		var min = Math.min.apply(null, prices);
		$('#slider-range').slider({
			range: true,
			min: min,
			max: max,
			values: [min, max],
			slide: function(event, ui) {
				$('#perNightPriceAbove').val(ui.values[0]+'원');
				$('#perNightPriceBelow').val(ui.values[1]+'원');
			}
		});
		$('#perNightPriceAbove').val($('#slider-range').slider('values', 0)+'원');
		$('#perNightPriceBelow').val($('#slider-range').slider('values', 1)+'원');
	}
	
	/*$('#perNightPrice').text($('#myRange').val());

	$('#myRange').on('input',function(event) {
		$('#perNightPrice').text($(this).val());
	});*/
	
	//====================맵에서 데이트피커====================//
	$(function(selectedDate) {
		var minDate = new Date();

		$('#datepicker1').datepicker({
			dateFormat: 'yy/mm/dd',
			prevText: '이전 달',
			nextText: '다음 달',
			monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
			monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
			dayNames: ['일', '월', '화', '수', '목', '금', '토'],
			dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
			dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
			showMonthAfterYear: true,
			yearSuffix: '년',
			altField: '#perNight1',
			altFormat: 'mm월 dd일',
			minDate : minDate,
			onSelect: function(selected){
				var maxDate = new Date(selected);
				maxDate.setDate(maxDate.getDate() + 31);

				$('#datepicker2').datepicker('option','minDate',selected);//문자열로 반환
				$('#datepicker2').datepicker('option','maxDate',maxDate); //객체로 전달하여 toString에 인해 문자열로 반환
				
				if($('#datepicker1').val()!='' && $('#datepicker2').val()!=''){
					calDate();
				}
			}
		});
		$('#datepicker2').datepicker({
			dateFormat: 'yy/mm/dd',
			prevText: '이전 달',
			nextText: '다음 달',
			monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
			monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월'],
			dayNames: ['일', '월', '화', '수', '목', '금', '토'],
			dayNamesShort: ['일', '월', '화', '수', '목', '금', '토'],
			dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'],
			showMonthAfterYear: true,
			yearSuffix: '년',
			altField: '#perNight2',
			altFormat: 'mm월 dd일',
			onSelect: function(selected){
				if($('#datepicker1').val()!='' && $('#datepicker2').val()!=''){
					calDate();
				}
			}
		});
	});
	
	//일수 계산해서 span태그에 전달하는 함수
	function calDate(){
		var checkInDate = new Date($('#datepicker1').datepicker('getDate'));
		var dt1 = checkInDate.getDate();
		var checkOutDate = new Date($('#datepicker2').datepicker('getDate'));
	    var dt2 = checkOutDate.getDate();
	    console.log(dt1+','+dt2);
	    $('#perNight3').text(dt2-dt1);
	}
	
	//재검색시 날짜 선택 필수
	$('#research').click(function(){
		if($('#datepicker1').val()=='' || $('#datepicker2').val()==''){
			alert('체크인 날짜와 체크아웃 날짜를 선택하세요.');
			return false;
		}
	});
			
	//====================맵에서 인원수 설정====================//
	$('.headcountFilter input[type="button"]').click(function(e){
        e.preventDefault();
        var type = $(this).attr('id');
        var $count = $(this).parent().children('#headcount');
        var headcount = $count.val();
        if(type=='count_m'){
            if(headcount<2){
            	alert('최소인원은 1명입니다.');
                return;
            }
            $count.val(parseInt(headcount)-1);
        }else if(type=='count_p'){
        	if(headcount>7){
            	alert('8명 이상 검색할 수 없습니다.');
                return;
            }
            $count.val(parseInt(headcount)+1);
        }
    });
	
	//====================정렬기준 선택박스====================//
	$('#detailInMap').selectmenu({
		change:function(event,ui){//이벤트 객체, jQuery UI에 대한 정보를 받는 객체
			orderby = $(this).val();
			alert(orderby);
			//기존에 존재하던 데이터 초기화
			$('#output').empty();
			$('#mapList').empty();
			$('#perNightPriceAbove').empty();
			$('#perNightPriceBelow').empty();
			callMap2(orderby,check_in,check_out,people_count,search);
		}
	}).selectmenu('menuWidget').addClass('overflow');
		
	//자세히 보기 버튼 클릭시 연결될 링크
	$(document).on('click', '.hotelLink', function(e){
		e.preventDefault();
		//숙소 번호
		var acc_num = $(this).attr('data-linknum');
		var acc_in = $(this).attr('data-in');
		var acc_out = $(this).attr('data-out');
		location.href = '../accomDetail/accomDetail_hotel.do?im_ac_num='+acc_num+'&check_in='+check_in+'&check_out='+check_out+'&people_count='+people_count+'&search='+search+'';
	});
	$(document).on('click', '.houseLink', function(e){
		e.preventDefault();
		//숙소 번호
		var acc_num = $(this).attr('data-linknum');
		var acc_in = $(this).attr('data-in');
		var acc_out = $(this).attr('data-out');
		location.href = '../accomDetail/accomDetail_private.do?im_ac_num='+acc_num+'&check_in='+check_in+'&check_out='+check_out+'&people_count='+people_count+'&search='+search+'';
	});
	
	//컨테이너에 마우스 올리면 반응
	$(document).on('mouseenter', '.mapsCard-container', function(){
		var target = $(this).attr('data-address1');
		var accnum = $(this).attr('data-accnum');
		$('.mapsCard-photo img').each(function(index,item){
			var imgnum = $(this).attr('data-imgnum');
			if(accnum == imgnum){
				var src = '../accomList/imageView.do?im_ac_num='+accnum+'&kind=im_image2';
				$(this).attr('src', src);
			}
		});
		$('.arrow_box').each(function(index,item){
			var check = $(this).attr('data-address');
			if(target == check){
				$(this).addClass('changed');
			}
		});
	});
	
	$(document).on('mouseleave', '.mapsCard-container', function(){
		var accnum = $(this).attr('data-accnum');
		$('.mapsCard-photo img').each(function(index,item){
			var imgnum = $(this).attr('data-imgnum');
			if(accnum == imgnum){
				var src = '../accomList/imageView.do?im_ac_num='+accnum+'&kind=im_cover';
				$(this).attr('src', src);
			}
		});
		$('.arrow_box').removeClass('changed');
	});
	
	//말풍선에 마우스 올리면 반응
	$(document).on('mouseenter', '.arrow_box', function(){
		var target = $(this).attr('data-address');
		
		$('.mapsCard-container').each(function(index,item){
			var check = $(this).attr('data-address1');
			//스크롤 탑은 무조건 0에서 시작
			//인덱스 * 태그 높이 --> 1 * 162, 2* 162...반복
			var offsetTop = 0+(index*162);
			var scmove = $(this).offset().top;//실제 태그 top좌표
			
			if(target == check){
				$(this).addClass('on');
				$('#output').animate({scrollTop:offsetTop}, 600);//두번째 속성은 duration(number), easing(String) 등
			}
		});
	});
	
	$(document).on('mouseleave', '.arrow_box', function(){
		$('.mapsCard-container').removeClass('on');
	});
	
	//====================재검색 버튼 클릭시====================//
	$(document).on('click', '#research', function(event){
		event.preventDefault();
		$('#output').empty();
		$('#mapList').empty();
		$('#perNightPriceAbove').empty();
		$('#perNightPriceBelow').empty();
		check_in = $('#datepicker1').val();
		check_out = $('#datepicker2').val();
		people_count = $('#headcount').val();
		//alert(check_in+','+check_out+','+people_count);
		callMap2(orderby,check_in,check_out,people_count,search);
	});
});