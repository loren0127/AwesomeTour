<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix='spring' uri='http://www.springframework.org/tags'%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<style>

#noSeePrice{
 	display:none;
 	}

 @media ( max-width:766px ) {
 
 	input[type="text"]{
 	width:100%!important}
 	input[type="email"]{
 	width:100%!important}
 	
 	.sticky-top{
 	display:none;
 	}
 	#yogu{
 	width:100%!important ;
 	}
 	#payMain{
 	width:100%!important ;
 	}
 	#noSeePrice{
 	display:inline!important ;
 	}
 	#pm_validity{
 	margin-bottom:30px!important;
 	}
 	#checkSub{
 	 	width:100%!important ;
 	}
 	#rv_message{
 	 	width:100%!important ;
 	}
 	#pm_type{
 	 	width:100%!important ;
 	}
	.state{
	margin : 50px 30px!important;}
	#mainCon{
	PADDING: 30PX;
	}
	#cntRv{
 	 	width:100%!important ;
 	
 	}
}
 }
 
</style>
    
<script>
$(function(){

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
	$("#during_date2").append(subDate);

	$("#money_sp").append("₩"+numberWithCommas($("#money").val()));
	$("#money_sp2").append("₩"+numberWithCommas($("#money").val()));

	$("#money_sum_sp").append("₩"+numberWithCommas(($("#money").val()*subDate)));
	$("#money_sum_sp2").append("₩"+numberWithCommas(($("#money").val()*subDate)));

	$("#money_sum").val(($("#money").val()*subDate));
	
	$("#fee_sp").append("₩"+numberWithCommas($("#money_sum").val()*0.1));
	$("#fee_sp2").append("₩"+numberWithCommas($("#money_sum").val()*0.1));

	$("#fee").val($("#money_sum").val()*0.1);
	
	$("#acc_money_sp").append("₩"+numberWithCommas(Number($("#money_sum").val())+Number($("#fee").val())));
	$("#acc_money_sp2").append("₩"+numberWithCommas(Number($("#money_sum").val())+Number($("#fee").val())));

	$("#acc_money").val(Number($("#money_sum").val())+Number($("#fee").val()));
	


	$("#bankType").hide();
	$("#cardType").hide();
 	$("#pm_cardNum").val("");
	$("#pm_validity").val("");
	$("#pm_cvc").val("");
	$('#pm_deposit_ac option:selected').val("");
	$("#pm_depositor").val("");

	var type = $("#pm_type option:selected").val();
	
	$("#pm_type").on('change',function(){
		
		type = $("#pm_type option:selected").val();
		
		
		if(type == 'c' ){
			$("#bankType").hide();
			$("#cardType").show();
			$('#pm_deposit_ac option:selected').val("");
			$("#pm_depositor").val("");
			$("#pm_cardNum").val("");
			$("#pm_validity").val("");
			$("#pm_cvc").val("");
		}else{
			$("#bankType").show();
			$("#cardType").hide();
			$("#pm_cardNum").val("");
			$("#pm_validity").val("");
			$("#pm_cvc").val("");

		}

	})
	

	function numberWithCommas(x) {
	    return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}

	
	
	  
	  
    var pm_type = $( "#pm_type" ),
   		pm_name = $( "#pm_name" ),
   		pm_email = $( "#pm_email" ),
   		pm_cardNum = $( "#pm_cardNum" ),
   		pm_validity = $( "#pm_validity" ),
   		pm_cvc = $( "#pm_cvc" ),
   		pm_deposit_ac= $( "#pm_deposit_ac_ac" ),
   		pm_depositor = $( "#pm_depositor" ),
	      allFields = $( [] ).add(  pm_type ).add( pm_name ).add( pm_email ).add( pm_cardNum ).add( pm_validity ).add( pm_cvc  ).add( pm_deposit_ac ).add( pm_depositor ),

      tips = $( ".validateTips" );
  
    
   function updateTips( t ) {
      tips
        .html(' <span class="fa fa-exclamation-circle fa-fw"></span>'+ t )
        .addClass( "ui-state-highlight" );
      var offset = $("#mainCon").offset();
      $('html, body').animate({scrollTop : offset.top}, 400);
   

       setTimeout(function() {
    	   $('html, body').animate(function(){tips.removeClass( "ui-state-highlight" )}, 400);
      }, 3000 ); 
    }
 
    function checkLength( o, n, min, max ) {
      if ( o.val().length > max || o.val().length < min ) {
        o.addClass( "ui-state-error" );
        updateTips(  n + " 는(은)  " +
          min + "자 이상 and " + max + "자 미만 입니다." );


        return false;
      } else {
        return true;
      }
    }
 
    function checkRegexp( o, regexp, n ) {
      if ( !( regexp.test( o.val() ) ) ) {
        o.addClass( "ui-state-error" );
        updateTips( n );


        return false;
      } else {
        return true;
      }
    }
    
    function checkEmpty( o, n ) {
	      if (  o.val() == '' || typeof o.val() == 'undefined' ) {
	        o.addClass( "ui-state-error" );
	        updateTips( n );
	        return false;
	      } else {
	        return true;
	      }
	    }
    

    


	$('#payForm').submit(function(){
		


	      if(!$('#agree').prop("checked")){
				alert("환불 정책에 동의 해 주십시오.");
				return false;
				}
				
      
      var valid = true;
      allFields.removeClass( "ui-state-error" );
      
      valid = valid && checkRegexp( pm_type,  /^[b-c]$/g,"결제 수단을 선택해 주세요!" );
      valid = valid && checkLength( pm_name, "이름", 2, 16);
      valid = valid && checkRegexp( pm_email, /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i, "이메일 형식을 입력해 주세요." );
     
      if(pm_type.val()=='c'){
    	  var pattern=/^[0-9]{16}$/g;
    	  var pattern_vali=/^[0-9][0-9][0-1][0-9]$/g;


    /* 	  if( !(/^[0-9]{16}$/g.test(123)) ){
    		  pm_cardNum.val(pm_cardNum.val().replace(/[^0-9+]/g,''));
    		  return;
    		  }
    	  if( !pattern_vali.test(pm_validity.val()) ){
    		  pm_validity.val(pm_validity.val().replace(/[^0-9+]/g,''));
    		  return;
    		  } */
      
      
    	  valid = valid && checkRegexp( pm_cardNum,  /^[0-9]{16}$/g  , "16자리 숫자만 입력가능합니다." );

    	  valid = valid && checkRegexp( pm_validity,  /^[0-9][0-9][0-1][0-9]$/g  , "####(YYMM) 의 4자리의 숫자만 입력가능합니다." );
    	  valid = valid && checkRegexp(	pm_cvc ,/^[0-9]{3}$/g, "3자리의 숫자만 입력가능합니다." );

      }
  
 	
      if(pm_type.val()=='b'){
    	
    	  valid = valid && checkEmpty( $('#pm_deposit_ac option:selected') , "결제계좌를 선택해주세요!" );
    	  valid = valid && checkEmpty( pm_depositor , "입금주를 입력해주세요!" );
    	  
      }
      
 

      
      
      if ( valid ) {
    	
    	  return true;
      }
      
		return false;

   	
	});
})

</script>    
<br><br><br><br>
<div class="container" id="mainCon">

<!-- ------------ 사이드 메뉴 ---------------- -->
<div class="rounded sticky-top" style="float:right; border:1px solid silver ; top: 100px; padding-left:10px; font-size:10pt" >
<div class="row">
<div class="col-6" style="    padding: 10px;    text-align: center;">
<h6>${rv.acc_name }</h6> 
<c:if test=" ${!empty rv.ag_grade }">
<i class="fa fa-heart" style="color:#ffc107"></i> ${rv.ag_grade }점<br>
</c:if></div>
<div class="col-6">
<img style=" padding: 10px; max-height:100px;max-width:100px" src="../accomDetail/imageView.do?im_ac_num=${rv.acc_num}&ro_room_num=${rv.ro_room_num}&kind=im_cover">
</div>
</div>
<hr  style="width:90%" noshade>
인원수 : ${rv.rv_people }명<br>
날짜 : <span id="check_in">${rv.rv_startdate }</span> ~<span id="check_out">${rv.rv_enddate}</span> <br>
<hr  style="width:90%" noshade>
<span id="money_sp"></span>원 x <span id="during_date"></span>박 = <span id="money_sum_sp"></span>원<br>
수수료 (10%) = <span id="fee_sp"></span>원<br>
<input type="hidden" id="money" name="money" value="${rv.ro_price}">
<input type="hidden" id="money_sum" name="money_sum" >
<input type="hidden" id="fee" name="fee">
<hr  style="width:90%" noshade>
총합계 = <span id="acc_money_sp"></span>원<br>
<input type="hidden" id="acc_money" name="acc_money">

<hr  style="width:90%" noshade>
<span style="font-size:12px; padding-left:15px">
	안심할 수 있는 예약 모든 여행 <br> 
	<a href="#" style="font-size:12px; padding-left:15px"> 게스트 환불 정책</a>에 따라 보호받습니다.
</span>
</div>
<!-- ------------ 사이드 메뉴 ---------------- -->


<div style="width:70%" id="payMain">
<h1>결제하기</h1>
<br>
 <c:if test="${count>0}" > 
<div class="rounded" style="border:1px solid green ; width:60%; padding:15px 0; text-align:center "  id="cntRv" >
  <i class="fa fa-info-circle" style="color:green;font-size:15pt;margin-left:30px;float:left"></i> 서두르세요! 오늘 이 호텔 예약한 사람이 ${count}명 있습니다!
</div>
</c:if>
  <div style="  margin-bottom: 10px;   font-size: 15pt;  width: 80%;" class="validateTips ">               
  </div>

<div style="width:100%" id="noSeePrice">
<h2>가격</h2>
<div class="row">
	<div class="col-6">
	<span id="money_sp2"></span>원 x <span id="during_date2"></span>박<br>
	수수료 (10%) <br>
 
	</div>
	
	<div class="col-1">
	 = <br>
	 = <br>

	</div>
	
	<div class="col-4">
	<span id="money_sum_sp2"></span>원<br>
	<span id="fee_sp2"></span>원<br>

	</div>
</div>
	 <hr  noshade>
<div class="row">	
	
	<div class="col-6">
	총합계 <br> 
	</div>
	<div class="col-1">
	 = <br>
	</div>
	
	<div class="col-4">
	<span id="acc_money_sp2"></span>원<br>
	</div>
</div>
</div>
<br>




 <form:form commandName="pmCommand" id="payForm" action="result.do">
	<div>
 
	<h3>결제 수단</h3>
	<br> 
	<select class="form-control"  name="pm_type" id="pm_type" style="width:60%">
		<option value="" selected disabled>--원하시는 수단을 선택해 주세요--</option>
		<option value="c" >신용카드</option>
		<option value="b">무통장입금</option>
	</select>
	<br>   

<h4><label>이름</label></h4>
<form:input class="form-control"  path="pm_name"  placeholder="홍길동" style="width:60%"/><br>
<h4><label>이메일</label></h4>
<form:input type="email" class="form-control"  path="pm_email" placeholder="example@email.com" style="width:60%"/>
예약 내역이 이메일을 통해 전송됩니다.<br><br> 
 <div id="cardType">
	<h4><label>카드번호</label></h4>

	<form:input class="form-control"  path="pm_cardNum" name="card"  placeholder="카드번호" style="width:60%" value=""/><br>
	<form:input class="form-control"  path="pm_validity" name="만료일"  placeholder="만료일(YYMM)" value="" style="width:30%; display:inline" />
	<form:input class="form-control"  path="pm_cvc" placeholder="CVC"  value="" style="width:30%;display:inline" />
</div>

<div id="bankType">
	<h4><label>입금계좌 선택</label></h4>
	<form:select class="form-control"  path="pm_deposit_ac" placehoder="입금계좌 선택"  style="width:60%">
	    <form:option value="" disabled="disabled" selected="selected" >-----선택해주세요</form:option>
		<form:option value="신한 123">신한 123-123-12312</form:option>
		<form:option value="우리 123">우리 123-123-12312</form:option>
	</form:select>		 
 	 	<form:input class="form-control"   path="pm_depositor"  placeholder="입금주" style="width:60%"/><br>
 	

</div> 
<br>
<br> 
<h3>회원님의 호스트는 ${host_nick }입니다.</h3>
인사말을 전하고 여행 목적을 알려주세요<br>
<form:textarea class="form-control" path="rv_message"   rows="5" cols="20"  placeholder="회원님의 여행에 대해 자세히 알려주시면 호스트가 준비하는데 도움이 됩니다." style="width:80%;"/>
</div>
<br><br>
<div id="checkSub" style="width:80%;">
<h3>환불 정책</h3>
체크인 30일 전까지 예약을 취소하면 전액 환불됩니다. 그 이후 체크인 전에 취소하면 50%가 환불됩니다.<br>
숙소 이용규칙, 환불 정책, 및 게스트 환불 정책에 동의합니다. 또한, 서비스 수수료를 포함하여 명시된 총 금액을 결제하는 데 동의합니다<br>
<BR>

<input type="checkbox" id="agree">위의 환불 정책에 동의 합니다.<br>
</div>
<div class="state" style="    margin: 30px 0;">

<input type="submit" class="btn btn-primary" value="결제 요청">
</div>
</form:form>
</div>

</div>
