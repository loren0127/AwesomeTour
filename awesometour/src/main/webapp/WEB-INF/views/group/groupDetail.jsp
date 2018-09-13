<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
	a:link { color: black; text-decoration: none;}
 	a:visited { color: black; text-decoration: none;}
 	a:hover { color: black; text-decoration: none;}
 	
</style>
<script src="//dapi.kakao.com/v2/maps/sdk.js?appkey=463df352b2234b5bac553021b6d8bd14&libraries=services"></script>

<script>

$(function(){
	
	
	
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = { 
        center: new daum.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level: 3 // 지도의 확대 레벨
    };
// 지도를 표시할 div와  지도 옵션으로  지도를 생성합니다
var map = new daum.maps.Map(mapContainer, mapOption); 

//지도를 생성합니다    
var map = new daum.maps.Map(mapContainer, mapOption); 

// 주소-좌표 변환 객체를 생성합니다
var geocoder = new daum.maps.services.Geocoder();

// 주소로 좌표를 검색합니다
geocoder.addressSearch($('#add1').text(), function(result, status) {

    // 정상적으로 검색이 완료됐으면 
     if (status === daum.maps.services.Status.OK) {

        var coords = new daum.maps.LatLng(result[0].y, result[0].x);

        // 인포윈도우로 장소에 대한 설명을 표시합니다
        var infowindow = new daum.maps.InfoWindow();
        infowindow.open(map);

        // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
        map.setCenter(coords);

		var customOverlay = new daum.maps.CustomOverlay({
			map: map,
			position: coords,
		    content: '<div class="arrow_box">'+$('#g_name').val()+'</div>',
		});
		
		// 커스텀 오버레이를 지도에 표시합니다
		customOverlay.setMap(map);
        
    } 
});    


//---------------------------현재날짜 구하기
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth()+1; //January is 0!
	var yyyy = today.getFullYear();

	if(dd<10) {
	    dd='0'+dd
	} 

	if(mm<10) {
	    mm='0'+mm
	} 

	today = yyyy+'/'+mm+'/'+dd;
	
  
  
  
	if($('#chat_btn').val()==0){
	
		$("#group_in").show();
		$("#chat_in").hide();

	}else{
		$("#group_in").hide();
		$("#chat_in").show();
	}
	var group_name = $('#g_name').val();


	//공개 채팅방일 경우 ->그냥 참가
	//비공개일 경우 -> 호스트한테 메세지 보내기
	//그룹 참가 ajax
	$('#group_in').click(function(){
	 if($('#private').val() == '0'){

		 $.ajax({
	 	  	type:'post',
				data:{g_num:$('#g_num').val()},
				url:'groupMemberInsert.do',
				dataType:'json',
				cache:false,
				timeout:30000,
				success:function(data){  
					if(data.result == 'success'){
						alert("모임에 가입되었습니다!")
					}else{
						alert(data.result);
					}
				},
				error:function(request,status,error){
					alert('등록시 네트워크 오류 발생!');
			        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
				}
	 	  
	   })
		 
		 
		 
	 }else{
		 alert("비공개 그룹은 그룹 생성자의 허가가 있어여 가능합니다. 요청 메세지를 보내시겠습니까? ");
		 //넘어오는 값에 따라 메세지 보내기 처리
	 }
	 
	 window.reload();
	})
	
	




$.ajax({
  	type:'post',
		data:{g_address1:$('#add1').html()},
		url:'recommendAccom.do',
		dataType:'json',
		cache:false,
		timeout:30000,
		success:function(data){  
			var appendText = "<h5 style='margin: 30px;'>추천 숙소</h5><br>"; 
			
			if(data.result == 'success'){
				var list = data.accomMap;
				$(list).each(function(index,item){
					appendText+='<div class="rounded"  style=" margin:20px; width:300px; padding: 10px; box-shadow: 2px 2px 2px 2px #efefef; text-align:center">';
					appendText+='<h5><a href="../accomDetail/accomDetail_';
					if(item.acc_grade =="undefined"){
						appendText+='private';
					}else{
						appendText+='hotel';
					}
					appendText+='.do?im_ac_num='+item.ACC_NUM+'&&searchtype=';
					if(typeof  item.ACC_GRADE != "undefined"){
					appendText+='h';							
					}else{
					appendText+='p';		
					}
					appendText+='&check_in='+today+'&check_out='+today+'&people_count=1&search='+item.ACC_ADDRESS1+'">'+item.ACC_NAME+'</a></h5>';
					appendText+='<div class="row">';
					appendText+='<div class="col-5"><img src="../accomDetail/imageView';
					
					if(typeof  item.ACC_GRADE == "undefined"){
						appendText+='Private.do?im_ac_num='+item.ACC_NUM+'&kind=im_cover"';							
					}else{								
					appendText+='.do?im_ac_num='+item.ACC_NUM+'&ro_room_num='+item.RO_NUM+'&kind=im_cover"';
					}
					appendText+='align="center" style="width:100%;height:100px" ></div><div class="col-7">';
					appendText+='<font size="3em ">'+item.ACC_ADDRESS1+item.ACC_ADDRESS2+'</font> <br>';
					
					if(item.COUNT>0){
					appendText+='<font size="2em " color="red">현재<b> '+item.COUNT+'명</b>이 예약중.</font><br>';
					}
					appendText+='</div></div>';
					appendText+='</div>';
				 });
	 				$('#recAcc').append(appendText);

			}else{
				
				alert(data.result)

			}
		},
		error:function(request,status,error){
			alert('등록시 네트워크 오류 발생!');
	        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		}
  
})
	
	
	////////////////////
$('.hobby').each(function(element, index){
	  
	  var hobby = $(this).children('input').val();
	  var hobbyArray = hobby.split(',');
	  var appendText = ''
	  for(var i in hobbyArray){
		  appendText += '<div id="hobby" class="col-4" style="padding : 0  50px "><div class="hobby_small rounded" style="    border: 1px solid #54B7FD; border-radius: 8px;color: #54B7FD;height:30px; width:100%;text-align:center;margin: auto;"><h7>'+hobbyArray[i]+'</h7></div></div>';
	  }		  
	  $(this).append(appendText);
	 
});

 
})


</script>
 <style>
 @media ( max-width: 575px ) {
 #title{text-align:center}
 #title img{
 	margin-left:50px!important;
 }
 #mainImg img{
 	max-height:300px!important;
 }
 #hobby{
 	padding:0 10px!important;
 }
 #recAcc{
  	float:none!important;
 }
 
 #recAcc div{
  	margin:20px 0 !important;
 }
}
 </style>
    
<!DOCTYPE html>
<br><br><br><br>


	<div class="container" >
		<div class="row">
			<div class="col-xs-12 col-sm-10" id="title">
			<font color="gray">${group.g_reg_date}~${group.g_close_date}</font> 
	 		<h1 >${group.g_name}</h1>
	 		<c:forEach var="list" items="${memberList}">
	 		
	 			<c:if test="${list.member_email == group.member_email}">
					<div >
		 			<c:if test="${fn:endsWith(list.member_filename,'.jpg') ||
								 fn:endsWith(list.member_filename,'.JPG') ||
								 fn:endsWith(list.member_filename,'.gif') ||
								 fn:endsWith(list.member_filename,'.GIF') }">
						<img src="../member/imageView.do?member_email=${list.member_email}" style="margin: 0 10px; width:50px; height:50px " align="left">		
					</c:if>
					<c:if test="${empty list.member_filename}">
						<img class="rounded-circle" src="../resources/images/rome.jpg" style="margin: 0 10px;width:50px; height:50px" align="left">
					</c:if>
					주최자 :  ${list.member_nickname} <font color="gray">${list.member_email}</font> <br>
					공개여부 : 
					
					<c:if test="${group.g_isPrivate == 0 }">
					공개
					</c:if>
					<c:if test="${group.g_isPrivate == 1 }">
					비공개
					</c:if>
					</div> 
					 
				</c:if>
			</c:forEach>
			<c:if test="${list.member_email == group.member_email}">
	 			<a href="groupUpdate.do&&g_num='+${group.g_num}+'" class="btn" id="group_update" >수정</a>
	 		</c:if>
	 		<input type="hidden" id="g_num" value="${group.g_num}">
	 		<input type="hidden" id="g_name" value="${group.g_name}">
	 		<input type="hidden" id="chat_btn" value="${chat}">
	 		<input type="hidden" id="private" value="${group.g_isPrivate}">
			</div>
 		
	 		<div class=" col-xs-12 col-sm-2 " style="    text-align: center;"> 		
				<button class="btn" id="group_in" style="margin:30px;    width: 150px;    height: 50px;">그룹 참가</button>
				<button class="btn" id="chat_in" style="margin:30px;    width: 150px;    height: 50px;" onclick='window.open("${pageContext.request.contextPath}/chat/chatFunctionResult.do?selected=groupChatList", "Chat_page_popup", "width=1100, height=620");'>채팅 참가</button>
				
			</div>
		</div>
	</div>

	
<div>
	<div class="container" >
		<div class="row" >


	<div class="col-xs-12 col-sm-9" style=" padding:10px 0 ;"> 

		<div style="margin:50px; min-height:150px; " >
			<h4>소개</h4>
			<hr size="10%" style="width: 80%; float: left;  background-color:#dae0e5;  margin: 8px 0 0;">  			<br>
			<div style="text-align:center" id="mainImg">
			<c:if test="${fn:endsWith(group.g_imageName,'.jpg') ||
					 		 fn:endsWith(group.g_imageName,'.JPG') ||
					 		 fn:endsWith(group.g_imageName,'.gif') ||
					 		 fn:endsWith(group.g_imageName,'.png') ||
					 		 fn:endsWith(group.g_imageName,'.PNG') ||
					 		 fn:endsWith(group.g_imageName,'.GIF') }">
	
					<img  class="rounded" src="../group/imageView.do?g_num=${group.g_num}" style="  max-width:100%; margin:5px 0 ;  max-height:300px;">
			</c:if>
			<c:if test="${!fn:endsWith(group.g_imageName,'.jpg') &&
					 		!fn:endsWith(group.g_imageName,'.JPG') &&
					 		 !fn:endsWith(group.g_imageName,'.gif') &&
					 		 !fn:endsWith(group.g_imageName,'.png') &&
					 		  !fn:endsWith(group.g_imageName,'.PNG') &&
					 		  !fn:endsWith(group.g_imageName,'.GIF') }">
					<img class="rounded" src="../resources/images/mbr-1.jpg"   style=" max-width:100%; margin:5px 0 ;  max-height:20vw;" >
			</c:if>
	 		</div>
	 		<br><span style="margin-left:10px">${group.g_explain}</span><br>
		</div>
	 
		<div style="margin:50px; min-height:150px">
			<h4>위치</h4>
			<hr size="10%" style="width: 80%; float: left;  background-color:#dae0e5;  margin: 8px 0 0;">  			<br>
			<br>
			
			<div id="map" style="width:100%; height:300px; max-height:300px; " ></div>
				<span id="add1">${group.g_address1 }</span> ${group.g_address2 }
				<br>
		</div>
	
		<div style="margin:50px; min-height:150px">
			<h4>관심사 </h4>
			<hr size="10%" style="width: 80%; float: left;  background-color:#dae0e5;  margin: 8px 0 0;">  			<br>
			<br>
			<br>
			<div class="row hobby">
			<input type="hidden" value="${group.g_hobby}"><br>
			</div>
		</div>
	
		<div style="margin:50px; min-height:150px">
			<h4> 사용자</h4>
			<hr size="10%" style="width: 80%; float: left;  background-color:#dae0e5;  margin: 8px 0 0;">  			<br>
			<br><div class="row">
				<c:forEach var="list" items="${memberList}">
					<div class="col-xs-12 col-sm-3  rounded"  style="text-align:center;padding:10px 5px;     box-shadow: 2px 2px 2px 2px #efefef;  margin: 10px 10px">
					
							<c:if test="${fn:endsWith(list.member_filename,'.jpg') ||
									  fn:endsWith(list.member_filename,'.JPG') ||
									  fn:endsWith(list.member_filename,'.gif') ||
									  fn:endsWith(list.member_filename,'.GIF') }">
					
								<div class="align-center"  >
									<img src="../member/imageView.do?member_email=${list.member_email}" style="max-width:150px">
								</div>
							</c:if>
							<c:if test="${empty list.member_filename}">
								<div class="align-center">
									<img class="rounded-circle" src="../resources/images/rome.jpg" style="max-width:150px;">
								</div>
							</c:if><br>
							${list.member_nickname}
							<c:if test="${list.member_email == group.member_email}">(생성자)</c:if><br>
							
					</div>
				</c:forEach>
			</div>	
		</div>
	</div>


	<div class="col-sm-3 rounded"   style="padding:50px; " >
	
	<div class="rounded sticky-top" id="recAcc"  style="border: 1px solid #dae0e5; float:left; top: 100px; box-shadow: 2px 2px 2px 2px #efefef;">
	</div>
	</div>


	</div>
</div>
</div>