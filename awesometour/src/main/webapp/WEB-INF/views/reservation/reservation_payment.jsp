<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<script>
$(function(){
	$("#bankType").hide();

	$("#type").on('change',function(){
		
	var type = $("#type option:selected").val();
	
	if(type == '신용카드' ){
		$("#bankType").hide();
		$("#cardType").show();

	}else{
		$("#bankType").show();
		$("#cardType").hide();
	}

	})
})

</script>    
<br><br><br><br>
<div class="container">

<!-- ------------ 사이드 메뉴 ---------------- -->
<div class="rounded sticky-top" style="float:right; border:1px solid black ; top: 100px; padding-left:10px" >
숙소이름  / 숙소사진<br>
별점 (후기보기 연결)<br>
<hr  style="width:90%" noshade>
인원수 $(값)<br>
날짜 $(값) ~ $(값)<br>
<hr  style="width:90%" noshade>$(1박가격)원 x $(숙박일수계산)박 = $(값)<br>
우리가받는 수수료 <br>
<hr  style="width:90%" noshade>
총합계 = $(값)<br>
<hr  style="width:90%" noshade>
<span style="font-size:12px; padding-left:15px">
	안심할 수 있는 예약 모든 여행 <br> 
	<a href="#" style="font-size:12px; padding-left:15px"> 게스트 환불 정책</a>에 따라 보호받습니다.
</span>
</div>
<!-- ------------ 사이드 메뉴 ---------------- -->

<div style="width:70%">
<h1>결제하기</h1>
<%-- <c:if test=${}member }> --%>
<div class="rounded" style="border:1px solid green ; width:60%; padding:15px 0; text-align:center "   >
서두르세요! 오늘 이 호텔 예약한 사람은 $(값)입니다!
</div>
<%--  </c:if>  --%>

<div>
  

<h3>결제 수단</h3><br> 
<select name="type" id="type" class="form-control"  style="width:60%">
<option value="신용카드">신용카드</option>
<option value="무통장 입금">무통장입금</option>
</select><br>   

<h4><label>이름</label></h4>
<input class="form-control" type="text" name="name"  placeholder="홍길동" style="width:60%"><br>
<h4><label>이메일</label></h4>
<input class="form-control"  type="email" name="email" placeholder="example@email.com" style="width:60%"><br>
<div id="cardType">
	<h4><label>카드번호</label></h4>
	<input class="form-control"  type="text" name="card"  placeholder="카드번호" style="width:60%"><br>
	<input class="form-control"  type="text" name="만료일"  placeholder="만료일" style="width:30%; display:inline" >
	<input class="form-control" type="text" name="CVC" placeholder="CVC" style="width:30%;display:inline" >
</div>
<div id="bankType">
	<h4><label>입금계좌 선택</label></h4>
	<input class="form-control"  type="text" name="만료일"  placeholder="은행 선택" style="width:30%; display:inline" >
	<input class="form-control" type="text" name="CVC" placeholder="계좌번호" style="width:30%;display:inline" ><br>
		<input class="form-control"  type="text" name="card"  placeholder="입금주" style="width:60%"><br>
	
</div>
<br>
<br> 
<h3>회원님의 호스트는 $(값)입니다.</h3>
인사말을 전하고 여행 목적을 알려주세요<br>
<textarea class="form-control"  rows="5" cols="20"  placeholder="회원님의 여행에 대해 자세히 알려주시면 호스트가 준비하는데 도움이 됩니다."></textarea>
</div>

<div>
<h3>환불 정책</h3>
체크인 30일 전까지 예약을 취소하면 전액 환불됩니다. 그 이후 체크인 전에 취소하면 50%가 환불됩니다.<br>
숙소 이용규칙, 환불 정책, 및 게스트 환불 정책에 동의합니다. 또한, 서비스 수수료를 포함하여 명시된 총 금액을 결제하는 데 동의합니다<br>

<input type="checkbox">위의 환불 정책에 동의 합니다.<br>
</div>

<div class="state" style="margin-bottom:30px">

<a href="result.do"  class="btn btn-primary">예약 요청</a>
</div>
</div>

</div>
