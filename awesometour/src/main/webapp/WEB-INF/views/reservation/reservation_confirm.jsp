<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<style>
.state{
	margin : 50px 30px;
}
</style>
<script>
$(function(){
	
var strDate1 = "2015-5-6";
var strDate2 = "2015-6-25";
var arr1 = strDate1.split('-');
var arr2 = strDate2.split('-');
var dat1 = new Date(arr1[0], arr1[1], arr1[2]);
var dat2 = new Date(arr2[0], arr2[1], arr2[2]);
var diff = dat2 - dat1;
var currDay = 24 * 60 * 60 * 1000;// 시 * 분 * 초 * 밀리세컨
var currMonth = currDay * 30;// 월 만듬
var currYear = currMonth * 12; // 년 만듬

$("#during_date").appand("* 일수 차이 : " + parseInt(diff/currDay) + " 일<br/>");


})

</script>

<br><br><br><br>
<div class="container">
<h1>예약 정보 확인</h1>
<div>
<%-- <c:if test=${}member }> --%>
<div class="rounded" style="border:1px solid green ; width:60%; padding:15px 0; text-align:center "   >
서두르세요! 오늘 이 호텔 예약한 사람은 $(값)입니다!
</div>
<%--  </c:if>  --%>
</div>


<!-- ------------ 사이드 메뉴 ---------------- -->


<div class="rounded sticky-top" style="float:right; border:1px solid black ; top: 100px; padding-left:10px" >
<form:form commandName="command">
${rv.acc_name }  / ${이미지 }<br>
${rv.ag_grade } (후기보기 연결)<br>
<hr  style="width:90%" noshade>
인원수 ${rv.rv_people } )<br>
날짜 ${rv.rv_startdate } ~ ${rv.rv_enddate} }<br>
<hr  style="width:90%" noshade>${rv.acc_money}원 x <span id="during_date"></span>박 = $(값)<br>
우리가받는 수수료 <br>
<hr  style="width:90%" noshade>
총합계 = ${rv.rv_money}<br>
<hr  style="width:90%" noshade>
<span style="font-size:12px; padding-left:15px">
	안심할 수 있는 예약 모든 여행 <br> 
	<a href="#" style="font-size:12px; padding-left:15px"> 게스트 환불 정책</a>에 따라 보호받습니다.
</span>
</form:form>
</div>
<!-- ------------ 사이드 메뉴 ---------------- -->

<div class="state">
	<h3>숙박 정보</h3><br>
	<ul> 
		<li>이미지</li>
		<li>숙소 이름 : ${rv.acc_name }</li>
		<li>주소 : ${rv.acc_address1} ${rv.acc_address2}</li>
		<li>숙소 편의 서비스 : ${rv.se_name})</li>
	</ul>
</div>

<div class="state">
	<h3>체크인/아웃 시간</h3><br>
	<ul>
		<li>체크인 가능 시간 : ${rv.rv_startdate } ${rv.acc_in} 이후 </li>
		<li>체크아웃 시간 : ${rv.rv_enddate} ${rv.acc_out} 까지</li>
	</ul>
</div>

<div class="state">  
	<h3>특별 요정하기</h3>
	<span style="font-size:12px">고객님의 특별 요청 사항을 호스트에게 예약 완료 즉시 전달하겠습니다.</span><br>
<div class="rounded" style="background-color:gray; padding:15px; width:60%;" >
	<p>
		<b>애완 동물(이용 가능 여부는 여건에 따라 달라질 수 있음)</b><br> 
		<input type="checkbox">&nbsp;데려간다.<br>
		<b>침대 사이즈 변경(이용 가능 여부는 여건에 따라 달라질 수 있음)</b><br> 
		<input type="radio">&nbsp;대형 &nbsp;<input type="radio">&nbsp;트윈
	</p>
</div>
</div>

<div class="state">
<h3>Awesome Tour 예약 팁</h3>
각 숙소만의 특징이 있으며, 공간과 편의시설이 숙소마다 다릅니다.<br>
숙소를 존중하는 마음으로 이용해 주세요. 호스트의 소중한 집이니까요.<br>
숙박 전과 숙박 기간 중에 그룹 채팅을 통해 활발히 소통하세요!<br>

</div>

<div class="state" style="margin-bottom:30px">
	<a href="payment.do" class="btn btn-primary">동의 및 계속하기</a>
</div>
</div>