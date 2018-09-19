<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<script>
	window.onpopstate = function(event) {
		history.go(1);
	};
	
	function checkedConfirm() {
		var retVal = confirm("예약을 취소하시겠습니까?");
		if (retVal == true) {
			alert('확인 선택!');
			location.href='#.do';
		} else {
			alert('취소 선택!');
		}
	}
	
	$(function() {

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
		var subDate = parseInt(diff / currDay + 1);
		$("#during_date").append(subDate);
		$("#money_sp").append("₩" + numberWithCommas($("#money").val()));

		$("#money_sum_sp").append("₩" + numberWithCommas(($("#money").val() * subDate)));
		$("#money_sum").val(($("#money").val() * subDate));

		$("#fee_sp").append("₩" + numberWithCommas($("#money_sum").val() * 0.1));
		$("#fee").val($("#money_sum").val() * 0.1);

		$("#rv_money_sp").append("₩" + numberWithCommas(Number($("#money_sum").val())
								+ Number($("#fee").val())));
		$("#rv_money").val(Number($("#money_sum").val()) + Number($("#fee").val()));

		$("#recGr").hide();
	});
</script>

<div style="margin: 20px 0">
	<div class="container">
		<h3>예약 상세정보</h3>
		<br> <input type="hidden" id="money" name="money"
			value="${rv.ro_price}"> <input type="hidden" id="money_sum"
			name="money_sum"> <input type="hidden" id="fee" name="fee">
		<input type="hidden" id="rv_money" name="rv_money">


		<div class="rounded " style="border: 1px solid gray">
			<div class="container">
				<div class="row ">
					<div class="col-12" style="text-align: center; padding: 5% 0;">
						<div style="padding: 2% 0;">
							<img style="max-height: 200px; max-width: 300px" src="../accomDetail/imageView.do?im_ac_num=${rv.acc_num}&ro_room_num=${rv.ro_room_num}&kind=im_cover">
						</div>
						<h3>${rv.acc_name }</h3>
						${rv.acc_address1} ${rv.acc_address2}<br>
						<c:if test=" ${!empty rv.ag_grade }">
							<i class="fa fa-heart" style="color: #ffc107"></i> ${rv.ag_grade}점<br>
						</c:if>
						<c:set var="array" value="${fn:split(rv.se_name,',')}" />
						<div class="row"
							style="padding-left: 20%; text-align: center; margin: 5px auto">
							<!-- 서비스를 배열형태로 반환하여 실행함 -->
							<c:forEach var="hobby" items="${array}" begin="0" end="4">
								<div class="" style="padding: 0;">
									<div class="hobby_small rounded"
										style="display: inline-block; height: 30px; width: max-content; text-align: center; color: #D900ED; margin-right: 10px;">
										<h6>&nbsp;${hobby}</h6>
									</div>
								</div>
							</c:forEach>
							<div class="col-4" style="margin: auto; display: contents;">
								<div class="hobby_small rounded"
									style="height: 30px; margin-right: 10px;">등</div>
							</div>

						</div>
						<b>체크인 가능 시간</b> : ${rv.acc_in} 이후 <br> <b>체크아웃 가능 시간</b>
						:${rv.acc_out}까지<br>

					</div>

					<hr style="width: 90%" noshade>

					<div class="col-4" style="padding: 0 50px;">
						인원수 <br> 날짜
					</div>

					<div class="col-1">
						:<br> :
					</div>

					<div class="col-7">
						${rv.rv_people }명<br> <span id="check_in">${rv.rv_start_date }</span>
						~ <span id="check_out">${rv.rv_end_date}</span> <br>
					</div>

					<hr style="width: 90%" noshade>

					<div class="col-4" style="padding: 0 50px;">
						<span id="money_sp"></span>원 x <span id="during_date"></span>박<br>
						서비스 수수료 (10%)
					</div>

					<div class="col-1">
						:<br> :
					</div>

					<div class="col-7">
						<span id="money_sum_sp"></span>원<br> <span id="fee_sp"></span>원
					</div>

					<hr style="width: 90%" noshade>

					<div class="col-4" style="padding: 0 50px;">총합계</div>
					<div class="col-1">:</div>

					<div class="col-7">
						<span id="rv_money_sp"></span>원<br>
					</div>

					<hr style="width: 90%" noshade>
				</div>
			</div>
		</div>

		<div id="recGr" style="margin: 30px 0">
			<h3>예약 지역의 그룹 추천</h3>
			<br>
			<br> 미완성
		</div>

		<div id="recGr" style="margin: 30px auto">
			<a class="btn btn-primary" href="#" onclick="history.go(-1)">이전</a> 
			<a class="btn btn-primary" href="#" onclick="checkedConfirm();">예약 취소</a> 
			<a class="btn btn-primary" data-toggle="modal" data-target="#myModal">컴플레인 보내기</a>
			
			<!--  -->
			<!-- The Modal -->
			<div class="modal fade" id="myModal">
				<div class="modal-dialog modal-dialog-centered">
					<div class="modal-content">

						<!-- Modal Header -->
						<div class="modal-header">
							<h4 class="modal-title">컴플레인 보내기</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						
						<form:form id="complainSendForm" commandName="mypageCommand" action="mypageComplainSend.do">
						<!-- Modal body -->
						<div class="modal-body">
							<label for="complain_title">제목</label>
							<form:input class="form-control form-control-sm" path="member_complain_title"/>
							<hr>
							<label for="member_complain_content">컴플레인 사유</label>
							<form:textarea class="form-control form-control-sm" rows="5" path="member_complain_content"/>
							
							:: Test 구간(${rv.rv_end_date} --- ${rv.rv_num} --- ${rv.acc_num})
							<form:hidden path="member_rv_end_date" value="${rv.rv_end_date}"/>
							<form:hidden path="member_rv_num" value="${rv.rv_num}"/>
							<form:hidden path="member_complain_accom_num" value="${rv.acc_num}"/>
						</div>

						<!-- Modal footer -->
						<div class="modal-footer">
							<button type="submit" class="btn btn-secondary">전송</button>
						</div>
						</form:form>
					</div>
				</div>
			</div>
			<!--  -->
		</div>
	</div>
</div>