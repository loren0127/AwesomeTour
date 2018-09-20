<%@page import="java.util.Enumeration"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<!DOCTYPE html>
<style>
.state{
	margin : 50px 30px;
}

  input[type="submit"]:hover{
		color: #FEC019;
		background-color: #fff;
	    border: 1px solid #FEC019; 
	  }
 @media ( max-width:766px ) {
 
 	.sticky-top{
 	display:none;
 	}
 	#yogu{
 	width:100%!important ;
 	}
 }
 
</style>
<script>
$(function(){
	
	function numberWithCommas(x) {
	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}


	
var strDate1 = $('#check_in').text();
var strDate2 = $('#check_out').text();
var arr1 = strDate1.split('/');
var arr2 = strDate2.split('/');
var dat1 = new Date(arr1[0], arr1[1], arr1[2]);
var dat2 = new Date(arr2[0], arr2[1], arr2[2]);
var diff = dat2 - dat1;
var currDay = 24 * 60 * 60 * 1000;// 시 * 분 * 초 * 밀리세컨
var currMonth = currDay * 30;// 월 만듬
var currYear = currMonth * 12; // 년 만듬
var subDate = parseInt(diff/currDay+1);

$("#during_date").append(subDate);
$("#money_sp").append("₩"+numberWithCommas($("#money").val()));

$("#money_sum_sp").append("₩"+numberWithCommas(($("#money").val()*subDate)));
$("#money_sum").val(($("#money").val()*subDate));

$("#fee_sp").append("₩"+numberWithCommas($("#money_sum").val()*0.1));
$("#fee").val($("#money_sum").val()*0.1);

$("#rv_money_sp").append("₩"+numberWithCommas(Number($("#money_sum").val())+Number($("#fee").val())));
$("#rv_money").val(Number($("#money_sum").val())+Number($("#fee").val()));


})

</script>
<form action="payment.do"  method="post">
<br><br><br><br>
<div class="container">
<h1>예약 정보 확인</h1>
<div>
 <c:if test="${count>0}" > 
<div class="rounded" style="border:1px solid green ; width:60%; padding:15px 0; text-align:center "   >
  <i class="fa fa-info-circle" style="margin-top: 2px;color:green;font-size:15pt;margin-left:30px;float:left"></i> 서두르세요! 오늘 이 호텔 예약한 사람이 ${count}명 있습니다!
</div>
</c:if>
</div>


<!-- ------------ 사이드 메뉴 ---------------- -->
<div class="rounded sticky-top" style="float:right; border:1px solid silver ; top: 100px; padding-left:10px; font-size:10pt;overflow:hidden" >
<div class="row">
<div class="col-6" style="    padding: 10px;    text-align: center;">
<h6>${rv.acc_name }</h6> 
<c:if test=" ${!empty rv.ag_grade }">
<i class="fa fa-heart" style="color:#ffc107"></i> ${rv.ag_grade }점<br>
</c:if>
</div>
<div class="col-6">
<img style=" padding: 10px; max-height:100px;max-width:100px" src="../accomDetail/imageView.do?im_ac_num=${rv.acc_num}&ro_room_num=${rv.ro_room_num}&kind=im_cover">
</div>
</div>
<hr  style="width:90%" noshade>
인원수 : ${rv.rv_people }명<br>
날짜 <span id="check_in">${rv.rv_start_date }</span> ~<span id="check_out">${rv.rv_end_date}</span> <br>
<hr  style="width:90%" noshade>
<span id="money_sp"></span>원 x <span id="during_date"></span>박 = <span id="money_sum_sp"></span>원<br>
수수료 (10%) = <span id="fee_sp"></span>원<br>
<input type="hidden" id="money" name="money" value="${rv.ro_price}">
<input type="hidden" id="money_sum" name="money_sum" >
<input type="hidden" id="fee" name="fee">
<hr  style="width:90%" noshade>
총합계 = <span id="rv_money_sp"></span>원<br>
<input type="hidden" id="rv_money" name="rv_money">

<hr  style="width:90%" noshade>
<span style="font-size:12px;" >
	안심할 수 있는 예약 모든 여행 <br> 
	<a href="#" style="font-size:12px;"> 게스트 환불 정책</a>에 따라 보호받습니다.
</span>
</div>
<!-- ------------ 사이드 메뉴 ---------------- -->

<div class="state">
	<input type="hidden" name="acc_num" value="${rv.acc_num}">
	<h3>숙박 정보</h3><br>
	<ul style="list-style: none;"> 
		<li ><img style="max-height:300px;max-width:300px" src="../accomDetail/imageView.do?im_ac_num=${rv.acc_num}&ro_room_num=${rv.ro_room_num}&kind=im_cover"></li>
		<li><b>숙소 이름</b> : ${rv.acc_name }</li>
		<li><b>주소</b> : ${rv.acc_address1} ${rv.acc_address2}</li>
		<c:set var="array" value="${fn:split(rv.se_name,',')}" />
		<li><div class="row" style="padding-left: 15px;"><b>서비스 </b>: 
									<!-- 서비스를 배열형태로 반환하여 실행함 -->
									<c:forEach var="hobby" items="${array}" begin="0" end="2">
										<div class="col-3" style="margin: auto;display: contents;">
											<div class="hobby_small rounded"
												style="height: 30px; width: max-content; text-align: center;color: #D900ED;margin-right: 10px;">
												<h6>&nbsp;${hobby}</h6>
											</div> 
										</div>
									</c:forEach> <div class="col-3" style="margin: auto;display: contents;">
									<div class="hobby_small rounded"
												style="height: 30px; margin-right: 10px;">
												<h6> 외 ${fn:length(array)-3}개</h6>
											</div></div>
		</div></li>
	</ul>
</div>

<div class="state">
	<h3>체크인/아웃 시간</h3><br>
	<ul>
		<li>체크인 가능 시간 : ${rv.rv_start_date } ${rv.acc_in} 이후 </li>
		<li>체크아웃 시간 : ${rv.rv_end_date} ${rv.acc_out} 까지</li>
	</ul>
</div>

<div class="state">  
	<h3>특별 요청하기</h3>
	<span style="font-size:12px">고객님의 특별 요청 사항을 호스트에게 예약 완료 즉시 전달하겠습니다.</span><br>
<div class="rounded" style="background-color:#aaaaaa; padding:15px; width:60%;" id="yogu">
	<p>
		<b>애완 동물(이용 가능 여부는 여건에 따라 달라질 수 있음)</b><br> 
		<input type="checkbox" name="rv_request" value="애완동물 데려감">&nbsp;데려간다.<br>
		<b>침대 사이즈 변경(이용 가능 여부는 여건에 따라 달라질 수 있음)</b><br> 
		<input type="radio" name="rv_request" value="대형침대">&nbsp;대형 &nbsp;
		<input type="radio" value="트윈침대" name="rv_request">&nbsp;트윈 &nbsp;
	</p>
</div>
</div>

<div class="state">
<h3>Awesome Tour 예약 팁</h3>
<span style="font-size:10pt">
각 숙소만의 특징이 있으며, 공간과 편의시설이 숙소마다 다릅니다.<br>
숙소를 존중하는 마음으로 이용해 주세요. 호스트의 소중한 집이니까요.<br>
숙박 전과 숙박 기간 중에 그룹 채팅을 통해 활발히 소통하세요!<br>
</span>
</div>

<div class="state" style="margin-bottom:30px">
<input type="submit"  class="btn btn-warning" value="예약 요청">
</div>
</div>
</form>
