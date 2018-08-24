$(document).ready(function(){
	//좌표 -> 행정구역정보 변환
	
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new daum.maps.LatLng(37.525425, 126.926617), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };  

// 지도를 생성합니다    
var map = new daum.maps.Map(mapContainer, mapOption); 

//커스텀 오버레이에 표시할 내용입니다
//HTML 문자열 또는 DOM Element 입니다
var content = '<div class="arrow_box">콘래드 서울</div>';

//커스텀 오버레이가 표시될 위치입니다
var position = new daum.maps.LatLng(37.525425, 126.926617);  

//커스텀 오버레이를 생성합니다
var customOverlay = new daum.maps.CustomOverlay({
 position: position,
 content: content,
 xAnchor: 0.5,
 yAnchor: 0.5
});

//커스텀 오버레이를 지도에 표시합니다
customOverlay.setMap(map);

//커스텀 오버레이에 커서가 오버됐을 때 커스텀 오버레이 위에 표시할 인포윈도우를 생성합니다
//인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
var iwContent = '<a href="#"><p class="iwContent">숙소사진 및 가격</p></a>';

// 인포윈도우를 생성합니다
var infowindow = new daum.maps.InfoWindow({
    content : iwContent
});

// 마커에 마우스오버 이벤트를 등록합니다
daum.maps.event.addListener(customOverlay, 'mouseover', function() {
  // 마커에 마우스오버 이벤트가 발생하면 인포윈도우를 마커위에 표시합니다
    infowindow.open(map, customOverlay);
});

// 마커에 마우스아웃 이벤트를 등록합니다
daum.maps.event.addListener(customOverlay, 'mouseout', function() {
    // 마커에 마우스아웃 이벤트가 발생하면 인포윈도우를 제거합니다
    infowindow.close();
});
});