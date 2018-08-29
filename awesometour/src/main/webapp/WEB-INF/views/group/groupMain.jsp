<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    <style>
#group_nav li {
        margin : 10px 10px;
        width:250px;
        
    }

    </style>
   
  <script>
   $(function(){
	  
	  $('.hobby').each(function(element, index){
		  
		  var hobby = $(this).children('input').val();
		  var hobbyArray = hobby.split('/');
		  var appendText = ''
		  for(var i in hobbyArray){
			  appendText += '<div class="col-3 hobby_small" style="height:30px; margin: 0 auto; background-color:white">'+hobbyArray[i]+'</div>';
		  }
		  
		  $(this).append(appendText);
	
	  });
	  
  }) 
  
  
  </script>
<div class="container section-sepa1" style="padding-Top:100px;padding-bottom: 25px;">

	<button class="btn pull-right">그룹 생성</button>
	<h2 > 모임</h2>
</div>
 <nav class="navbar navbar-expand-sm justify-content-center" style="background:gray; padding: 10px 0;  line-height:30px; padding-left:50px">
   
				<ul class="nav navbar-nav " id="group_nav">
			
				
					<li class="nav-item">
						<select class="form-control" name="loc" >
					    	<option value="">지역</option>
					  	  	<option value="학생">서울</option>
					   		<option value="회사원">회사원</option>
					   		<option value="기타">기타</option>
						</select>
					</li>

					<li class="nav-item">
						<select class="form-control"  name="loc">
					    	<option value="">취미</option>
					  	  	<option value="학생">서울</option>
					   		<option value="회사원">회사원</option>
					   		<option value="기타">기타</option>
						</select>
					</li>
				
					<li class="nav-item">
						<input class="form-control" type="date" >
					</li>
				
					<li class="nav-item">
						<input type="checkbox" name="chk_info" value="HTML" disabled>자신이 속한 그룹만 보기
					</li>
				</ul>

</nav> 

<div class="container">

	<div class="row "  >
	
			<c:forEach var="list" items="${groupList}">

	<!-- 시작 -->
		<div class="col-lg-4 col-md-6 col-sm-12" style="text-align:center; padding:20px; ">
			<div  style="padding:5px 3px; background-color:gray" >
				<img src="../resources/img/mbr-1.jpg"style="height:auto;  max-width:100%; " >
				<a href="groupDetail.do?g_num=${list.g_num }"><h4>${list.g_name}</h4></a>
				<div class="row hobby justify-content-start" style="margin: 0 10px;">
 					<input type="hidden" value="${list.g_hobby}">
 					
 					
 				</div>
 			</div>
		</div>
	<!--끝 -->
		</c:forEach>

	
	</div>
	
		<div class="align-center" style="margin:50px 0">${pagingHtml}</div>

	
	</div>
</div>
