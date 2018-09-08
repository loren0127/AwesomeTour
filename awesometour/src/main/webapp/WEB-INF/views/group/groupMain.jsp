<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
.ui-dialog { z-index: 1040 !important ;}

</style>
<script src="../resources/js/jquery.form.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/group.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/group.css" rel="stylesheet">
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=463df352b2234b5bac553021b6d8bd14&libraries=services"></script>
<script>
function count_ck(obj){

	var chkbox = document.getElementsByName("g_hobby");

	var chkCnt = 0;

	for(var i=0;i<chkbox.length; i++){

		if(chkbox[i].checked){

			chkCnt++;

		}

	}

	if(chkCnt>3){

		alert("최대 3개만 가능합니다");

		obj.checked = false;

		return false;

	}

}

var geocoder = new daum.maps.services.Geocoder();
var mapContainer = null;
var map = null;
var marker = null;
function sample5_execDaumPostcode() {
    new daum.Postcode({
        oncomplete: function(data) {
            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var fullAddr = data.address; // 최종 주소 변수
            var extraAddr = ''; // 조합형 주소 변수

            // 기본 주소가 도로명 타입일때 조합한다.
            if(data.addressType === 'R'){
                //법정동명이 있을 경우 추가한다.
                if(data.bname !== ''){
                    extraAddr += data.bname;
                }
                // 건물명이 있을 경우 추가한다.
                if(data.buildingName !== ''){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
            }

            // 주소 정보를 해당 필드에 넣는다.
            document.getElementById("g_address1").value = fullAddr;
            // 주소로 상세 정보를 검색
            geocoder.addressSearch(data.address, function(results, status) {
                // 정상적으로 검색이 완료됐으면
                if (status === daum.maps.services.Status.OK) {

                    var result = results[0]; //첫번째 결과의 값을 활용

                    // 해당 주소에 대한 좌표를 받아서
                    var coords = new daum.maps.LatLng(result.y, result.x);
                    // 지도를 보여준다.
                    mapContainer.style.display = "block";
                	
                    
                		
                	map.relayout();
                	      // 지도 중심을 변경한다.
                	map.setCenter(coords);
                	      // 마커를 결과값으로 받은 위치로 옮긴다.
                	marker.setPosition(coords)
                		
                }
            });
        }
    }).open();
}
$(function(){

$( "#groupAdd" ).button().on( "click", function() {
	

  
	mapContainer = document.getElementById('map') , // 지도를 표시할 div
	mapOption = {
	    center: new daum.maps.LatLng(37.537187, 127.005476), // 지도의 중심좌표
	    level: 5 // 지도의 확대 레벨
	};
      
	//지도를 미리 생성
	map = new daum.maps.Map(mapContainer, mapOption);
	
	//마커를 미리 생성
	marker = new daum.maps.Marker({
	position: new daum.maps.LatLng(37.537187, 127.005476),
	map: map
	});

  });


})
</script>
<div class="container section-sepa1" style="padding-Top:100px;padding-bottom: 25px;">

	<button class="btn pull-right" id="groupAdd">그룹 생성</button>
	<h2 > 모임</h2>
</div>

<!-- ----------------------------네비바 --------------------------------------------- -->
 <nav class="navbar navbar-expand-sm justify-content-center" style="background:gray; padding: 10px 0;  line-height:30px; padding-left:50px" >
   
				<ul class="nav navbar-nav " id="group_nav">
			
				
					<li class="nav-item">
						<select class="form-control" name="address"  id="address">
					    	<option value="">지역</option>
					  	  	<option value="서울">서울</option>
					   		<option value="경기">경기</option>
					   		<option value="부산">부산</option>
						</select>
					</li>

					<li class="nav-item">
						<select class="form-control"  name="hobby" id="hobby">
					    	<option value="">취미</option>
					  	  	<option value="123">123</option>
					   		<option value="55">55</option>
					   		<option value="7">7</option>
						</select>
					</li>
				
					<li class="nav-item">
						<input placeholder="종료일" class="form-control" type="text" onfocus="(this.type='date')"  id="date">
						
					</li>
				
					<li class="nav-item" style="padding: 5px 0px;">
						<input type="checkbox" style="display:inline" name="myGroup" id="myGroup" value="0" <c:if test="${empty user_email }">disabled</c:if>>
						&nbsp;자신이 속한 그룹만 보기
					</li>
				
				</ul>

</nav> 
<!-- ----------------------------네비바 --------------------------------------------- -->

<div class="container" id="list">

	<div class="row" id="group-content" style="       transition: all .50s;  ">
	
			<c:forEach var="list" items="${groupList}">

	<!-- 시작 -->
		<div class="col-lg-4 col-md-6 col-sm-12" id="groupPage"  style="text-align:center; padding:20px ">
			<div class="rounded" onclick="location.href='groupDetail.do?g_num=${list.g_num}'" id="pageArea" style="padding:10px 10px;" >
		<div class="rounded"  style="height:auto;  max-width:100%;">
		<c:if test="${fn:endsWith(list.g_imageName,'.jpg') ||
				 		 fn:endsWith(list.g_imageName,'.JPG') ||
				 		 fn:endsWith(list.g_imageName,'.gif') ||
				 		 fn:endsWith(list.g_imageName,'.png') ||
				 		 fn:endsWith(list.g_imageName,'.PNG') ||
				 		 fn:endsWith(list.g_imageName,'.GIF') }">

				<img  class="rounded" src="../group/imageView.do?g_num=${list.g_num}" style="  max-width:100%; ">
		</c:if>
		<c:if test="${!fn:endsWith(list.g_imageName,'.jpg') &&
				 		!fn:endsWith(list.g_imageName,'.JPG') &&
				 		 !fn:endsWith(list.g_imageName,'.gif') &&
				 		 !fn:endsWith(list.g_imageName,'.png') &&
				 		  !fn:endsWith(list.g_imageName,'.PNG') &&
				 		  !fn:endsWith(list.g_imageName,'.GIF') }">
				<img class="rounded" src="../resources/img/mbr-1.jpg" style=" max-width:100%;   " >
		</c:if>
		</div>
				<h5 style="margin: 10px;">${list.g_name}</h5>
				<div class="row hobby justify-content-start" style="margin: 0 10px;">
 					<input type="hidden" value="${list.g_hobby}">
 				</div>
 			</div>
		</div>
	
	<!--끝 -->
		</c:forEach>

	
	</div>
	
		<div id="paging" style="margin:50px 0; text-align:center">${pagingHtml}</div>

	
</div>
    <!-- ===========================================모달================================================== -->

<div   id="addDialog"  class="container" title="그룹 생성">
<div id="tabs">
  <ul>
    <li><a href="#tabs-1">1.이름 및 소개</a></li>
    <li><a href="#tabs-2">2. 위치</a></li>
    <li><a href="#tabs-3">3.관심사 및 프로필 지정</a></li>
    <li><a href="#tabs-4">4.초대</a></li>
  </ul> 
  <form:form commandName="command"  id="groupInsert" >
    <!-------------------------- 탭 1-------------------- -->
  <div id="tabs-1">
     <label for="g_name">그룹명</label>
      <form:input path="g_name"  class="text ui-widget-content ui-corner-all"/>

      <input type=checkbox name="g_isPrivate"  value="1" class="" style="display:inline"/>
      <label for="g_isPrivate" style="display:inline">비공개 생성</label>
      <input type=checkbox name="g_isSearch" value="1" class="" style="display:inline"/>
      <label for="g_isPrivate" style="display:inline">검색에 안뜨게 하기</label>
    
      
       <label for="g_explain">소개</label>
      <form:textarea path="g_explain" class="text ui-widget-content ui-corner-all"  rows="10" cols="50"  /><br>
<a href="#" class="btn" id="move-2">다음</a>
  </div>
  
      <!-------------------------- 탭 2-------------------- -->
  
  <div id="tabs-2">
      <div id="map" style="width:300px;height:300px;margin-top:10px;display:none"></div>
      <label for="g_address1">모임 위치</label>
      <form:input  path ="g_address1" id="g_address1" class="text ui-widget-content ui-corner-all" style="width:80%; display:inline" />
	  <input type="button" onclick="sample5_execDaumPostcode()" value="주소 검색" style="display:inline"><br>
      <form:input  path ="g_address2" id="g_address2" class="text ui-widget-content ui-corner-all" placeholder="나머지 주소 입력"/><br>
     	
     <a href="#" class="btn" id="move-3">다음</a>
   
  </div> 
  
      <!-------------------------- 탭 3-------------------- -->
  <div id="tabs-3">
  	  <label for="g_close_date">마감일시</label>
       <form:input type="date"  path="g_close_date" id="g_close_date" class="text ui-widget-content ui-corner-all"/>
      미입력시 기본 1달로 생성 됩니다<br><br>
      
       <label for="upload">사진 등록</label>
        <form:input type="file" name="upload" path="upload"/><br>
       <label for="g_close_date">취미(최대 3개까지 가능)
       </label>

     	<form:checkbox path="g_hobby" value="취미 1"  onClick="count_ck(this);" style="display:inline" />취미 1
		<form:checkbox path="g_hobby" value="취미 2"  onClick="count_ck(this);"  style="display:inline"/>취미 2
		<form:checkbox path="g_hobby" value="취미 3"  onClick="count_ck(this);" style="display:inline"/>취미 3 
      <br>
   		
     	 <form:checkbox path="g_hobby" value="취미 4"  onClick="count_ck(this);" style="display:inline"/>취미 1
		<form:checkbox path="g_hobby" value="취미 5"  onClick="count_ck(this);" style="display:inline"/>취미 2
		<form:checkbox path="g_hobby" value="취미 6"  onClick="count_ck(this);" style="display:inline"/>취미 3 
      
  </div>
  <!-------------------------- 탭 3-------------------- -->
  <div id="tabs-4">
  	 <label for="g_close_date">초대 하기</label>
  	  <a href="#">+</a> 
	
  </div>


      <!-- Allow form submission with keyboard without duplicating the dialog button -->
      <input type="submit" tabindex="-1" style="position:absolute; top:-1000px">
 
  </form:form> 
  </div>
</div>



