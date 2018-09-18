<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="${pageContext.request.contextPath}/resources/js/member.js"></script> 
<div class="container section-sepa1" style="margin-top:100px;">
<style>
	  @media screen and (max-width: 1280px){
	  	#image{
	  		width:70%;
	  		height:70%;
	  	}
	  
	  }
	  
	  #next_write2:hover{
		color: #212529;
		background-color: #fff;
	    border-color: #ffc107;
	  }
	  #home_write2:hover{
		color: #212529;
		background-color: #fff;
	    border-color: #ffc107;
	  }
</style>
	  <!--================Breadcrumb Area =================-->
        <section class="gallery_area section_gap">
            <div class="container">
                <div class="section_title text-center">
                    <h2 class="title_color">취미 선택</h2>
                    <p>홈페이지 이용에 필요한 사항입니다.</p>
                </div>
                <form:form action="success.do" id="register_form2" commandName="command" enctype="multipart/form-data">
				<form:errors element="div" cssClass="error-color" />	
                	<input type="hidden" name ="member_email" value="${command.member_email}">
	                <div class="row imageGallery1" id="gallery" align="center">
	                    <div class="col-md-4 gallery_item">
	                        <div class="gallery_img" style="align:center;">
	                            <img src="${pageContext.request.contextPath}/resources/images/1.png" alt="" id="image"><br><br>
	                            <input type="checkbox" name="ch" value="야외활등">야외활동<br><br>
	                        </div>
	                    </div>
	                    <div class="col-md-4 gallery_item">
	                        <div class="gallery_img" style="align:center;">
	                            <img src="${pageContext.request.contextPath}/resources/images/2.PNG" alt="" id="image"><br><br>
	                            <input type="checkbox" name="ch" value="기술">기술<br><br>
	                        </div>
	                    </div>
						<div class="col-md-4 gallery_item">
	                        <div class="gallery_img" style="align:center;">
	                            <img src="${pageContext.request.contextPath}/resources/images/3.PNG" alt="" id="image"><br><br>
	                            <input type="checkbox" name="ch" value="가족">가족<br><br>
	                        </div>
	                    </div>
	                    <Br>
	                   	 <div class="col-md-4 gallery_item">
	                        <div class="gallery_img" style="align:center;">
	                            <img src="${pageContext.request.contextPath}/resources/images/4.PNG" alt="" id="image"><br><br>
	                            <input type="checkbox" name="ch" value="건강과 웰빙">웰빙<br><br>
	                        </div>
	                    </div>
	                    <div class="col-md-4 gallery_item">
	                        <div class="gallery_img" style="align:center;">
	                            <img src="${pageContext.request.contextPath}/resources/images/5.PNG" alt="" id="image"><br><br>
	                            <input type="checkbox" name="ch" value="스포츠">스포츠<br><br>
	                        </div>
	                    </div>
						<div class="col-md-4 gallery_item">
	                        <div class="gallery_img" style="align:center;">
	                            <img src="${pageContext.request.contextPath}/resources/images/6.PNG" alt="" id="image"><br><br>
	                            <input type="checkbox" name="ch" value="학습">학습<br><br>
	                        </div>
	                    </div>
	                    <br>
	                     <div class="col-md-4 gallery_item">
	                        <div class="gallery_img" style="align:center;">
	                            <img src="${pageContext.request.contextPath}/resources/images/7.PNG" alt="" id="image"><br>
	                            <input type="checkbox" name="ch" value="사진촬영">사진촬영
	                        </div>
	                    </div>
	                    <div class="col-md-4 gallery_item">
	                        <div class="gallery_img" style="align:center;">
	                            <img src="${pageContext.request.contextPath}/resources/images/8.PNG" alt="" id="image"><br>
	                            <input type="checkbox" name="ch" value="음식">음식
	                        </div>
	                    </div>
						<div class="col-md-4 gallery_item">
	                        <div class="gallery_img" style="align:center;">
	                            <img src="${pageContext.request.contextPath}/resources/images/9.PNG" alt="" id="image"><br>
	                            <input type="checkbox" name="ch" value="글쓰기">글쓰기
	                        </div>
	                    </div>                 
	                </div>
	                <br>
	                <br>
	                <hr style="height:15px;">
	                <h2 class="title_color" align="center">성향 선택</h2>
	                <br>
	                <br>
					<div class="row imageGallery1" id="gallery" align="center">
	                    <div class="col-md-6 gallery_item">
	                        <div class="gallery_img" style="align:center;">
	                            <img src="${pageContext.request.contextPath}/resources/images/1.png" alt="" id="image"><br>
	                            <input type="radio" name="ra" value="외향">외향
	                        </div>
	                    </div>
	                     <div class="col-md-6 gallery_item">
	                        <div class="gallery_img" style="align:center;">
	                            <img src="${pageContext.request.contextPath}/resources/images/2.PNG" alt="" id="image"><br>
	                            <input type="radio" name="ra" value="내향">내향
	                        </div>
	                    </div>
	               </div>
	                 <hr style="height:15px;">
				<div class="align-center">
					<input type="submit" value="가입" class="btn btn-warning mb-2" id="next_write2">
					<input type="button" value="홈으로" class="btn btn-warning mb-2" id="home_write2" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
				</div>
			</form:form>
            </div>
        </section>
        <!--================Breadcrumb Area =================--> 
</div>

	<%--<h1>회원가입2</h1>
	<form:form action="success.do" id="register_form" commandName="command" enctype="multipart/form-data">
		<form:errors element="div" cssClass="error-color" />	
		<div class="align-center">
			<br>
			<input type="hidden" name ="member_email" value="${command.member_email}">
			<input type="checkbox" name="ch" value="야외활등">야외활동
			<input type="checkbox" name="ch" value="기술">기술
			<input type="checkbox" name="ch" value="스포츠">스포츠
			<input type="checkbox" name="ch" value="학습">학습
			<input type="checkbox" name="ch" value="사진촬영">사진촬영
			<input type="checkbox" name="ch" value="음식">음식
			<input type="checkbox" name="ch" value="건강과 웰빙">건강과 웰빙
			<input type="checkbox" name="ch" value="글쓰기">글쓰기
			<input type="checkbox" name="ch" value="게임">게임
		</div>
		<div class="align-center">
			<input type="radio" name="ra" value="외향">외향
			<input type="radio" name="ra" value="내향">내향
		</div>
		<div class="align-center">
			<input type="submit" value="가입">
			<input type="button" value="홈으로"onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
		</div>
	</form:form>
	--%>





