$(document).ready(function(){
	//JSON 문자열로 생성된 주소와 상호명 받을 배열 선언 -> push 함수 사용시 초기화 먼저!
	var locations=[],contents=[];
	var keyword;
	
	function callMapFirst(){
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new daum.maps.services.Geocoder();
		
		var mapContainer = document.getElementById('mapSearch'), // 지도를 표시할 div 
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
				console.log(locations.indexOf(address));//브라우저 콘솔에 인덱스 출력해서 확인
				
				var content = '<div class="arrow_box">대한민국 호텔미텔미</div>';
				if(locations.indexOf(address)>=0){
					content = contents[locations.indexOf(address)];
				}
				
				// 정상적으로 검색이 완료됐으면 
				if (status === daum.maps.services.Status.OK) {
					
					// 위치를 기준으로 지도 중심을 재설정합니다
			        map.setCenter(new daum.maps.LatLng(result[0].y, result[0].x));
			        
					var coords = new daum.maps.LatLng(result[0].y, result[0].x);
					var custom_content = '<div class="arrow_box">'+content+'</div>';
					
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
	
	//지도가 뜨는 걸 한 발 늦도록 하기 위해 함수화
	function callMap1(keyword){
		/*
		 * $.ajax({옵션}) 메소드는 HTTP 요청을 만드는 강력하고도 직관적인 방법을 제공
		 * 옵션은 HTTP 요청을 구성하는 키와 값의 쌍으로 구성되는 헤더의 집합
		 * type: HTTP 요청 방식(GET, POST)
		 * url: 클라이언트가 요청을 보낼 서버의 URL 주소
		 * data: HTTP 요청과 함께 서버로 보낼 데이터
		 * dataType: 서버에서 보내줄 데이터의 타입
		 */
		$.ajax({
			type:'get',
			url:'../main/mapSearch.do',
			dataType:'json',
			data:{keyword:keyword},//검색어 입력시 HTTP 요청과 함께 서버로 보낼 데이터
			async:false,//코드가 일시 중지(완료 되기를 기다리는 다른 코드)
			cache:false,
			timeout:30000,
			success:function(data){
				var list = data.list;
				//반복문을 통해 데이터 출력
				$(list).each(function(index, item){
					locations.push(item.acc_address1);
					contents.push(item.acc_name);
				});
			},
			error:function(){
				alert('네트워크 오류');
			}
		});
				
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new daum.maps.services.Geocoder();
		
		var mapContainer = document.getElementById('mapSearch'), // 지도를 표시할 div 
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
				console.log(locations.indexOf(address));//브라우저 콘솔에 인덱스 출력해서 확인
				
				var content = '<div class="arrow_box">대한민국 호텔미텔미</div>';
				if(locations.indexOf(address)>=0){
					content = contents[locations.indexOf(address)];
				}
				
				// 정상적으로 검색이 완료됐으면 
				if (status === daum.maps.services.Status.OK) {
					
					// 위치를 기준으로 지도 중심을 재설정합니다
			        map.setCenter(new daum.maps.LatLng(result[0].y, result[0].x));
			        
					var coords = new daum.maps.LatLng(result[0].y, result[0].x);
					var custom_content = '<div class="arrow_box">'+content+'</div>';
					
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
	
	//==========메인 페이지==========//
	//jQuery UI 다이얼로그
	$('#mapSearch_dialog').dialog({
		width:'100%',
		height:750,
		autoOpen: false
	});
	$('#opener1').on('click', function() {
		$('#mapSearch_dialog').dialog('open');
		callMapFirst();
		$('.ui-dialog-titlebar').hide();
		$('.ui-dialog').css('zIndex','10000');
		$('.ui-dialog-content').css('overflow','hidden');
	});
	$('.closer').on('click', function() {
		$('#mapSearch_dialog').dialog('close');
	});
	
	//==========지도의 검색 버튼 클릭==========//
	$(document).on('click', '#map_search', function(event){
		event.preventDefault();
		$('#mapSearch').empty();
		callMap1(keyword);
	});
});