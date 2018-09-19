<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>초대</title>
</head>
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">


<script>
$(function(){
	
	$('#searchMember').click(function(){
	
		var email = $("#member").val();
		if (email  == '' ) {
			alert("이메일을 입력하세요")
			return false;
		}
			
		$.ajax({
			type:'post',
			data:{email:email}	,
			url:'../mail/confirmEmail.do',
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){ 
				if(data.result=='EmailDuplicated'){
					var append = '';
					append += '<div class="row" style="width:95%"><div class="col-9 memberId" data-email="'+email+'"style="margin-left:20px;margin-top:10px;">'+email+'</div>';
					append += '<div class="col-2" style="padding:0px;width:90%"><button id="delete" class="btn button">삭제</button></div></div> <hr style="width:100%">';
					
					
					if($('div[data-email="'+email+'"]', opener.document).length>0){
					alert("중복된 아이디 입니다.");	
					$('#member').val('');

					return false;
					}
					if($('div[data-email="'+email+'"]').length>0){
						alert("중복된 아이디 입니다.");	
						$('#member').val('');

						return false;
						}
					$('#memberList').append(append);

				}else if(data.result=="EmailNotFound"){
					
					alert("멤버가 존재하지 않습니다.");
				}	
				
				$('#member').val('');
			},
			error:function(){
			
			}	
		});
		
	})
$(document).on('click',"#delete",function(){
	$(this).parent().parent().empty();
	
})


$("#send").on('click',function(event){
	event.preventDefault();

	var memberList = opener.document.getElementById("inviteMember").value+',';
	$('.memberId').each(  function(index, item){
		var member = $(item).text();
		var append = "<div class='col-4' style='margin: 15px 0px;'><div class='rounded' data-email='"+member+"' style='font-size:10pt;border: 1px solid silver;text-align: center;'>"+member+"</div></div>";
			$("#inviteID", opener.document).prepend(append);

		memberList+=member;
		memberList+=',';
	})
	memberList = memberList.substring(0, memberList.length-1); 
	
	
	//부모창으로 보내기
	opener.document.getElementById("inviteMember").value=memberList;

	window.close();
	
})


});
</script>
<body>
<div class="rounded" style="border:1px solid silver; margin:5px"> 
	<h2 style="text-align:center">멤버 닉네임 조회</h2>
		<div style="text-align:center">
		<input type="email" id="member" class="form-control" style="width:40%;display:inline;padding-top:3px;margin-right:5px"><button id="searchMember" class="btn button">찾기</button>
		</div>
	<hr style="width:100%"> 
		<div id="memberList"style=" height:145px">
		
		</div>
	<div  style="text-align:center; margin-bottom:20px" >
	<button id="send" class="btn button">추가</button>
	</div>
</div>
</body>
</html>