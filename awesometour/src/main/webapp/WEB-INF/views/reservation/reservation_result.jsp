<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
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
$("#money_sp").append(numberWithCommas($("#money").val()));

$("#money_sum_sp").append(numberWithCommas(($("#money").val()*subDate)));
$("#money_sum").val(($("#money").val()*subDate));

$("#fee_sp").append(numberWithCommas($("#money_sum").val()*0.1));
$("#fee").val($("#money_sum").val()*0.1);

$("#rv_money_sp").append(numberWithCommas(Number($("#money_sum").val())+Number($("#fee").val())));
$("#rv_money").val(Number($("#money_sum").val())+Number($("#fee").val()));

})

</script>
<br><br><br><br>
<div class="container">
<h1>예약 정보 확인</h1>




<div class="rounded " >
<div class="row">

${rv.acc_name }   <br>
${rv.ag_grade }점<br>

<img style="max-height:300px;max-width:300px" src="../accomDetail/imageView.do?im_ac_num=${rv.acc_num}&ro_room_num=${rv.ro_num}&kind=im_cover">
</div>

<hr  style="width:90%" noshade>
인원수 ${rv.rv_people }명<br>
날짜 <span id="check_in">${rv.rv_startdate }</span> ~<span id="check_out">${rv.rv_enddate}</span> <br>
<hr  style="width:90%" noshade>
<span id="money_sp"></span>원 x <span id="during_date"></span>박 = <span id="money_sum_sp"></span>원<br>
수수료 (10%) = <span id="fee_sp"></span>원<br>
<input type="hidden" id="money" name="money" value="${rv.ro_price}">
<input type="hidden" id="money_sum" name="money_sum" >
<input type="hidden" id="fee" name="fee">
<hr  style="width:90%" noshade>
총합계 = <span id="rv_money_sp"></span>원<br>
<input type="hidden" id="rv_money" name="rv_money">


</div>
<!-- ------------ 사이드 메뉴 ---------------- -->



<div>
<h3>예약 지역의 그룹 </h3>

미완성

</div>


<a href="../main/main.do">홈으로</a>
<a href="../main/main.do">마이페이지</a>
</div>