<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>	
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/search.js"></script>
<!-- Masthead -->
<header class="masthead text-white text-center">
	<div class="container">
		<div class="row">
			<div class="col-lg-9 col-xs-6 mx-auto">
				<h2 class="mb-5">숙소와 트립을 예약하세요.</h2>
			</div>
			<div class="col-lg-10 col-md-9 col-xs-12 mx-auto">
				<form id="search_form" action="../accomList/accomList.do">
					<div class="form-row" ><!-- style="width: 1200px; margin-left: 100px;" -->
						<!-- 변경한 내용임 -->
						<div>
							<select name="searchtype" style="height: 50px;" id="type" class="form-control">
								<option value="h" id="h">호텔</option>
								<option value="p" id="p">프라이빗하우스</option>
							</select>
						</div>
						<input type="hidden" value="" name="se_num">
						<div>
							<input type="text" id="date_in" name="check_in" value="" class="form-control"
								style="height: 50px; width: 150px;" autocomplete="off">
							
						</div>
						<div>

							<input type="text" id="date_out" name="check_out" value="" class="form-control"
								style="height: 50px; width: 150px;" autocomplete="off">
						</div>
						<div class="people_pop">
						<input type="hidden" name="people_count" id="people_count" value="1" class="form-control">
						
							<button id="people" name="people" class="btn"
								style="height: 50px; width: 150px;">
								인원 <span id="peo_sum_btn">1</span>명
							</button>

							<div class="check" id="pe_pop"  class="form-control">
								<span>인원
									<input type="button" name="minus" id="people_minus" value="-"></span>
									<span id="people_sum">1</span>
								<input type="button" name="people_plus" id="people_plus" value="+"> 

							</div>
						</div>
						<div>
							<input type="text" style=" height: 50px;"
								name="search" id="address" placeholder="서울" value="" class="form-control">
						</div>
						<div>
							<button type="submit" id="search" class="btn btn-block btn-lg btn-primary">검색</button>
						</div>				
					</div>
				</form>
				<!-- 변경한 내용끝-->
			</div>
		</div>
	</div>
</header>

<!-- About Us -->
<div class="section-sepa1" style="max-height: 1000px;">
	<div class="container align-center">
	<h3 class="text-center" style="padding-top: 3rem;">오썸투어는요</h3>
	<div class="underline align-center"><div class="line"></div></div>
		<div class="row">
			<div class="content">
				<!-- 1st row -->
				<div class="row" style="margin:0 20px;">
					<!--first card-->
					<div class="card-us col-xs-3 col-md-6 col-lg-6">
						<div class="row text-center">
							<div style="margin-right: 1rem;">
								<i class="fa-history fa"></i>
							</div>
							<h5>24시간 연중무휴 지원&nbsp;</h5>
						</div>
						<p>하루 24시간 언제나 여러분을 도와드립니다. 전 세계 어디에서나 언제든 오썸투어 지원팀에 연락주세요.</p>
					</div>
					<!--second card-->
					<div class="card-us col-xs-3 col-md-6 col-lg-6">
						<div class="row text-center">
							<div style="margin-right: 1rem;">
								<i class="fa-comments fa"></i>
							</div>
							<h5>모임 뉴스&nbsp;</h5>
						</div>
						<p>오썸투어 뉴스와 함께 주변 알려주는 여행 관련 팁, 모임 정보를 확인하세요.</p>
					</div>
				</div>
				<!-- 2nd row -->
				<div class="row" style="margin:0 20px;">
					<!--third card-->
					<div class="card-us col-xs-3 col-md-6 col-lg-6">
						<div class="row text-center">
							<div style="margin-right: 1rem;">
								<i class="fa-krw fa"></i>
							</div>
							<h5>최적의 숙소를 예약&nbsp;</h5>
						</div>
						<p>오썸투어는 수백만개의 숙소 가격을 공정하고 저렴한 조건으로 쉽고 빠르게 찾아드립니다.
						땡처리, 얼리버드 그리고 특가 숙소까지 검색 할 수 있습니다. 전세계 사람들과 모임을 통해 인연을 맺어보세요.
						고객을 먼저 생각하는 당사의 서비스를 더 자세히 알아보세요.</p>
					</div>
					<!--fourth card-->
					<div class="card-us col-xs-3 col-md-6 col-lg-6">
						<div class="row text-center">
							<div style="margin-right: 1rem;">
								<i class="fa-thumbs-up fa"></i>
							</div>
							<h5>슈퍼 호스트&nbsp;</h5>
						</div>
						<p>깔끔하게 정돈된 침대 시트에서부터 브런치 맛집 정보까지, 오썸투어 호스트는 현지인의 강점을 살린 서비스를 풍성하게 제공합니다.</p>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- Recommendation -->
<div class="section-sepa2" style="max-height: 1000px;">
	<div class="container">
		<h3 class="text-center" style="padding-top: 3rem;">추천 숙소</h3>
		<div class="underline align-center"><div class="line"></div></div>
		<div class="row">
			<c:forEach var="list" items="${recommedList}" varStatus="status">
			<c:if test="${status.count <= 4}">
			<div class="col-sm-12 col-md-6 col-lg-3">
				<div class="card-recom align-center">
					<div>
						<img src="${pageContext.request.contextPath}/main/imageView.do?im_ac_num=${list.acc_num}&kind=im_image2" style="width:100%;">
					</div>
					<div>
						<c:if test="${list.ro_sub} == 'h'">
							<span>호텔</span>
						</c:if>
						<c:if test="${list.ro_sub} == 'p'">
							<span>프라이빗 하우스</span>
						</c:if>
						<span style="font-size:20px;font-weight:bold;">${list.acc_name}</span><br>
					</div>
					<div>
						<span><fmt:formatNumber value="${list.ro_price}" type="currency"></fmt:formatNumber>/박</span>
						<span>이용후기 <b>${list.review_count}</b>건</span>
					</div>
					<div>
						<c:if test="${list.ro_sub} == 'h'">
							<input type="button" class="btn btn-warning hotelLink" value="자세히 보기" style="font-size: .8em;font-weight: bold;" 
							onclick="location.href='${pageContext.request.contextPath}/accomDetail/accomDetail_hotel.do?im_ac_num=${list.acc_num}&check_in=${check_in}&check_out=${check_out}&people_count${people_count}&search=${search}'">
						</c:if>
						<c:if test="${list.ro_sub} == 'p'">
							<input type="button" class="btn btn-warning houseLink" value="자세히 보기" style="font-size: .8em;font-weight: bold;" 
							onclick="location.href='${pageContext.request.contextPath}/accomDetail/accomDetail_private.do?im_ac_num=${list.acc_num}&check_in=${check_in}&check_out=${check_out}&people_count${people_count}&search=${search}'">
						</c:if>
					</div>
				</div>
			</div>
			</c:if>
			</c:forEach>	
		</div>
	</div>
</div>

<!-- REVIEW -->
<div class="section-sepa1" style="max-height: 1000px;">
	<div class="container">
		<h3 class="text-center" style="padding-top: 3rem;">여행객들의 말말말</h3>
		<div class="underline align-center"><div class="line"></div></div>
		<div class="row">
			<div class="col-xs-12 col-md-6 col-lg-3">
				<div class="card align-center">
					<div>
						<h4>쪽쪽쪽 쵝오!!!</h4>
					</div>
					<div>
						<span data-type="id">홍현기</span><br>
						<span data-type="score"><i class="fa-star-o fa"></i><i class="fa-star-o fa"></i><i class="fa-star-o fa"></i><i class="fa-star-o fa"></i><i class="fa-star-o fa"></i></span>
					</div>
					<div>
						블라블라블라
					</div>
					<div>
						<button class="btn">View More</button>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-md-6 col-lg-3">
				<div class="card align-center">
					<div>
						<h4>쪽쪽쪽 쵝오!!!</h4>
					</div>
					<div>
						<span data-type="id">홍현기</span><br>
						<span data-type="score"><i class="fa-star-o fa"></i><i class="fa-star-o fa"></i><i class="fa-star-o fa"></i><i class="fa-star-o fa"></i><i class="fa-star-o fa"></i></span>
					</div>
					<div>
						블라블라블라
					</div>
					<div>
						<button class="btn">View More</button>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-md-6 col-lg-3">
				<div class="card align-center">
					<div>
						<h4>쪽쪽쪽 쵝오!!!</h4>
					</div>
					<div>
						<span data-type="id">홍현기</span><br>
						<span data-type="score"><i class="fa-star-o fa"></i><i class="fa-star-o fa"></i><i class="fa-star-o fa"></i><i class="fa-star-o fa"></i><i class="fa-star-o fa"></i></span>
					</div>
					<div>
						블라블라블라
					</div>
					<div>
						<button class="btn">View More</button>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-md-6 col-lg-3">
				<div class="card align-center">
					<div>
						<h4>쪽쪽쪽 쵝오!!!</h4>
					</div>
					<div>
						<span data-type="id">홍현기</span><br>
						<span data-type="score"><i class="fa-star-o fa"></i><i class="fa-star-o fa"></i><i class="fa-star-o fa"></i><i class="fa-star-o fa"></i><i class="fa-star-o fa"></i></span>
					</div>
					<div>
						블라블라블라
					</div>
					<div>
						<button class="btn">View More</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<!-- MEETING -->
<div class="section-sepa2" style="max-height: 1000px;">
	<div class="container">
		<h3 class="text-center" style="padding-top: 3rem;">인기 모임</h3>
		<div class="underline align-center"><div class="line"></div></div>
				<div class="row">
			<div class="col-xs-12 col-md-6 col-lg-3">
				<div class="card align-center">
					<div>
						<h4>랄랄라</h4>
					</div>
					<div>
						<span>모임1</span><br>
						<span>장소</span><br>
						<span>서울 공중 화장실</span>
					</div>
					<div>
						서울 공중 화장실의 깨끗함을 느껴보아요!()
					</div>
					<div>
						<button class="btn">View More</button>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-md-6 col-lg-3">
				<div class="card align-center">
					<div>
						<h4>랄랄라</h4>
					</div>
					<div>
						<span>모임2</span><br>
						<span>장소</span><br>
						<span>서울 공중 화장실</span>
					</div>
					<div>
						서울 공중 화장실의 깨끗함을 느껴보아요!(구라)
					</div>
					<div>
						<button class="btn">View More</button>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-md-6 col-lg-3">
				<div class="card align-center">
					<div>
						<h4>랄랄라</h4>
					</div>
					<div>
						<span>모임3</span><br>
						<span>장소</span><br>
						<span>서울 공중 화장실</span>
					</div>
					<div>
						서울 공중 화장실의 깨끗함을 느껴보아요!(구라)
					</div>
					<div>
						<button class="btn">View More</button>
					</div>
				</div>
			</div>
			<div class="col-xs-12 col-md-6 col-lg-3">
				<div class="card align-center">
					<div>
						<h4>랄랄라</h4>
					</div>
					<div>
						<span>모임4</span><br>
						<span>장소</span><br>
						<span>서울 공중 화장실</span>
					</div>
					<div>
						서울 공중 화장실의 깨끗함을 느껴보아요!(구라)
					</div>
					<div>
						<button class="btn">View More</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
