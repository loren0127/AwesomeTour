<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html>
<br><br><br><br>
<div class="container">
<h1>예약 정보 확인</h1>
<div>
<%-- <c:if test=${}member }> --%>
서두르세요! 오늘 이 호텔 예약한 사람은 $(값)입니다!
<%--  </c:if>  --%>
</div>

<div class="존나크게">
숙소이름  / 숙소사진<br>
별점 (후기보기 연결)<br>
-------------------------<br>
인원수 $(값)<br>
날짜 $(값) ~ $(값)<br>
-------------------------<br>
$(1박가격)원 x $(숙박일수계산)박 = $(값)<br>
-------------------------<br>
총합계 = $(값)<br>
환불 정책 보기<br>
</div>



<div>
<h3>예약 지역의 그룹 </h3>
이건 내가 알아서 한다

</div>


<a href="../main/main.do">홈으로</a>
<a href="../main/main.do">마이페이지</a>
</div>