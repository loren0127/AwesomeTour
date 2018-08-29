<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<br><br><br><br>
<div style="background-color:gray; height:300px">
	<div class="container" style="height:300px; display:inline ">
		<h2 style="vertical-align: bottom;">${group.g_name}</h2>
	<button class="btn pull-right" style="margin:50px">채팅 참가</button>
	</div>
	
</div>
<div style="float:right">
	근처 숙소

<div style="margin:20px; background-color:gray; width:200px">
		<h4>숙소</h4>
	 		이것도 예정<br>
	</div>
</div>
<div class="container">

	<div style="margin:20px">
		<h4>소개</h4>
	 		${group.g_explain}<br>
	</div>
	
	<div style="margin:20px">
		<h4>위치</h4>
			${group.g_loc }<br>
	</div>
	
	<div style="margin:20px">
		<h4>그룹에 속한 사용자</h4>
			조인해서 출력 예정<br>
	</div>

</div>
