$(document).ready(function(){
	//지도가 뜨는 걸 한 발 늦도록 하기 위해 함수화
	function callMap(){
		var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
		mapOption = {
			center: new daum.maps.LatLng(37.5577441, 127.0054455), // 지도의 중심좌표
			level: 7 // 지도의 확대 레벨
		};  
		
		//지도를 생성합니다
		var map = new daum.maps.Map(mapContainer, mapOption);
		
		// 주소-좌표 변환 객체를 생성합니다
		var geocoder = new daum.maps.services.Geocoder();
		
		//주소 배열 생성 -> 반드시 도로명 주소로 입력!
		var positions = ['서울 중구 동호로 249','서울 중구 명동8길 27','서울 중구 퇴계로 52','서울 중구 명동길 61','서울 영등포구 국제금융로 10'];
		//상호명 배열 생성
		var contents = ['서울신라호텔','솔라리아 니시테츠 호텔 서울','티마크 그랜드 호텔 명동','Royal Hotel Seoul','콘래드 서울'];
		
		for (var i = 0; i < positions.length; i++) {
			//지오코드의 함수가 내부적으로 반복문의 변수를 받지 못하므로 주의!!!
			
			// 주소로 좌표를 검색합니다
			geocoder.addressSearch(positions[i], function(result, status) {
				
				var address = result[0].address_name;
				console.log(positions.indexOf(address));//블라우저 콘솔에 인덱스 출력해서 확인
				
				var content = '<div class="arrow_box">대한민국 호텔미텔미</div>';
				if(positions.indexOf(address)>=0){
					content = contents[positions.indexOf(address)];
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
	$("#map_dialog").dialog({
		  width:'100%',
		  height:700,
	      autoOpen: false,
	      title: "지도"
	    });
	$("#opener").on("click", function() {
	      $( "#map_dialog" ).dialog("open");
	      callMap();
	      $('.ui-dialog-titlebar').hide();
	    });
	$(".close").on("click", function() {
	      $( "#map_dialog" ).dialog("close");
	    });
});