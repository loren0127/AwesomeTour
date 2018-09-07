<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/search.js"></script>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/jquery.form.min.js"></script>	
	
<script>
//----------------------------------------------ajax 편의시설 클릭시----------------
$(function(){
	//var form = $('#se_form').serialize()
	
$('input[type="checkbox"]').on('click',function(){
	search();

});	
	
$('select[name="price"]').on('click change',function(){
	search();
});
	
$('#se_form').on("submit",function(event){
	//alert(form)
	event.preventDefault();
	search();
});

	function search(){
		$('#se_form').ajaxSubmit({
			
			type:'post',
			url:'accomlistajax.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				
				if(data.result =='success'){
					$('#col-6').empty();
					var list = data.list;
					var count = data.count;
					var pagingAppend = '';
					
					var service_1 = [];
					
					var serviceArr =[];
					var sear = [
						WIFI,개인금고,주차장,장애인용 시설, 스파(호텔전용),피트니스 센터,
						프로젝트 룸,주방,픽업,24시간 프론트(호텔전용),룸 서비스(식사),세탁 서비스,
						어메니티(샴푸, 칫솔 등),조식,반려동물 허용,파티 행사 허용
					];
					
					
					$(list).each(function(index,item){

						
						pagingAppend +='	<div class="row">';
						pagingAppend +='<div class="col-6" style="height: 150px; border: 1px solid;">';
						
						$('input:checkbox[name="se_name"]:checked').each(function(index,item) {
							serviceArr.push($(this).val());//체크된 값 저장 
							
							
					    }); 
						
						if(item.ro_sub == 'h'){
						pagingAppend +='<span>'+item.acc_grade+'</span>';
						pagingAppend +='<br>';
							}
								pagingAppend +='<span>'+item.acc_name+'</span> <br>';
								pagingAppend +='<span>'+item.acc_grade+'</span><br>';
								pagingAppend +='<span>'+item.acc_address1+'</span><br>';
								pagingAppend +='<span>'+item.acc_theme+'</span><br>';
						
						if(item.ro_sub == 'h'){
						pagingAppend +='<a href="../accomDetail/accomDetail_hotel.do">자세히보기</a>';
						
						}
						if(item.ro_sub == 'p'){
							pagingAppend +='<a href="../accomDetail/accomDetail_private.do">자세히보기</a>';
						}
						
						pagingAppend +='</div>';
						
					
							pagingAppend +='<div class="col-3" style="height: 150px; border: 1px solid;">';
							pagingAppend +='<span>1박 요금</span><br>';
							pagingAppend +='<span>'+item.ro_price+'</span><br>';
							pagingAppend +='</div>';
							pagingAppend +='</div>';

					
				
					});		
					$('#col-6').append($(pagingAppend).hide().fadeIn(200));
					
					// 페이지붙여넣기
					
					/*$(list).each(function(index,item){
						var output = '<div class="item">';
						output += '	<h4>' + item.id + '	</h4>';

						//문서 객체에 추가
						$('#output').append(output);
					});
					 */
				}else{
					alert('오류');
				}
			
			},
			error:function(request,status,error){
				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			}
		});
	}

});
</script>
		<form id="se_form">
<div>
	
	<nav class="navbar sticky-top navbar-light bg-light">
		<div class="col-md-10 col-lg-8 col-xl-7">
			<!-- 검색 시작 -->
				<div class="form-row"
					style="width: 1200px; margin-left: 200px; margin-top: 75px;">
					<div>
						 <input
							type="hidden" name="searchtype" value="${map.searchtype}">
						<input type="text" id="date_in" name="check_in"
							value="${map.check_in}" style="height: 50px; width: 150px;"
							autocomplete="off">

					</div>
					<div>

						<input type="text" id="date_out" name="check_out"
							value="${map.check_out}" style="height: 50px; width: 150px;"
							autocomplete="off"> 
					</div>
					<div class="people_pop">
						<input type="hidden" name="people_count" id="people_count"
							value="${map.people_count}">

						<button id="people" name="people"
							style="height: 50px; width: 150px;">
							인원 <span id="peo_sum_btn">1</span>명
						</button>

						<div class="check" id="pe_pop">
							<span>인원<input type="button" name="people_plus"
								id="people_plus" value="+"> <span id="people_sum">1</span>
								<input type="button" name="minus" id="people_minus" value="-"></span>

						</div>
					</div>
					<div>
						<input type="text" style="width: 300px; height: 50px;"
							name="search" value="${map.search}">
					</div>
					
					<div>
						<button type="submit" class="btn btn-block btn-lg btn-primary">검색</button>
					</div>
				</div>
				<div></div>

		</div>
</nav>	
	<!-- 검색창 끝-->


	<!-- 숙소 리스트 시작-->
	<div class="container">
	<h2>숙소 목록</h2>
				<input type="button" value="지도" style="margin-right: 300px;">
				<button value="DESC" name="best">추천숙소</button>
						<span>가격</span>
						  <select  name="price">
							<option value="ASC">낮은순</option>
							<option value="DESC">높은순</option>
						</select>
						
				<button value="DESC" name="avg">평점</button>
				
		<div class="row">
			<div id="map" class="col-3">
			
				<span style="text-align:center;"><b>편의시설</b></span>
			<ul style="list-style:none;">
				<li><input type="checkbox" value="${se_array[0]}" name="se_name">${se_array[0]}</li>
				<li><input type="checkbox" value="${se_array[1]}" name="se_name">${se_array[1]}</li>
				<li><input type="checkbox" value="${se_array[2]}" name="se_name">${se_array[2]}</li>
				<li><input type="checkbox" value="${se_array[3]}" name="se_name">${se_array[3]}</li>
				<li><input type="checkbox" value="${se_array[4]}" name="se_name">${se_array[4]}</li>
				<li><input type="checkbox" value="${se_array[5]}" name="se_name">${se_array[5]}</li>
				<li><input type="checkbox" value="${se_array[6]}" name="se_name">${se_array[6]}</li>
				<li><input type="checkbox" value="${se_array[7]}" name="se_name">${se_array[7]}</li>
			</ul>
				<span style="text-align:center;"><b>서비스 및 이용규칙</b></span>
			<ul style="list-style:none;">
				<li><input type="checkbox" value="${se_array[8]}" name="se_name">${se_array[8]}</li>
				<li><input type="checkbox" value="${se_array[9]}" name="se_name">${se_array[9]}</li>
				<li><input type="checkbox" value="${se_array[10]}" name="se_name">${se_array[10]}</li>
				<li><input type="checkbox" value="${se_array[11]}" name="se_name">${se_array[11]}</li>
				<li><input type="checkbox" value="${se_array[12]}" name="se_name">${se_array[12]}</li>
				<li><input type="checkbox" value="${se_array[13]}" name="se_name">${se_array[13]}</li>
				<li><input type="checkbox" value="${se_array[14]}" name="se_name">${se_array[14]}</li>
				<li><input type="checkbox" value="${se_array[15]}" name="se_name">${se_array[15]}</li>
				<li><input type="checkbox" value="${se_array[16]}" name="se_name">${se_array[16]}</li>
			</ul>
				<span style="text-align:center;"><b>호텔성급</b></span>
			<ul style="list-style:none;">
				<li><input type="checkbox" value="1">1</li>
				<li><input type="checkbox" value="2">2</li>
				<li><input type="checkbox" value="3">3</li>
				<li><input type="checkbox" value="4">4</li>
				<li><input type="checkbox" value="5">5</li>
			</ul>	
					
			</div>



				<div id="col-6" class="col-9">
				
				
				<c:if test="${count == 0}">
				<div class="align-center">등록된 게시물이 없습니다.</div>
				</c:if>
				<c:forEach var="accom" items="${list}">
					<div class="row">
						<div class="col-8" style="height: 150px; border: 1px solid;" >
							<c:if test="${accom.ro_sub=='h'}">
								<span>호텔성급 : ${accom.acc_grade}</span>
								<br>
							</c:if>
							<span>
							<img src="">
							번호 : ${accom.acc_num}</span><br> <span>이름 :
								${accom.acc_name}</span><br> <span>주소 :
								${accom.acc_address1}</span><br>
							<c:if test="${accom.ro_sub=='h'}">
								<a href="../accomDetail/accomDetail_hotel.do">자세히보기</a>
							</c:if>
							<c:if test="${accom.ro_sub=='p'}">
								<a href="../accomDetail/accomDetail_private.do">자세히보기</a>
							</c:if>

						</div>
						<div class="col-4" style="height: 150px; border: 1px solid;" id="col-3">

							<span>1박요금</span><br> <span>${accom.ro_price}</span><br>
							<div></div>
						</div>
					</div>
				</c:forEach>
</div>
				<!-- 숙소 리스트 끝-->
			</div>				
			</div>

		</div>
		</form>