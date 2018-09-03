$(document).ready(function() {	
	
	
	$('#confirm_nickname').click(function(){
		$.ajax({
			//컨드롤러에 명시한 주소
			url:'confirmNickname.do',
			type:'post',	//
			data:{member_nickname:$('#member_nickname').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'nickNotFound'){
					$('#message_nickname').text('등록가능 닉네임');
				}else if(data.result == 'nickDuplicated'){
					$('#message_nickname').text('중복된 닉네임');
				}
			},error:function(){
				alert('네트워크 오류 발생');
			}
		})
	});
	
	$('#register_form').submit(function(){
		
		var nick = $('#member_nickname').val();
		var exptext =  /^[a-z0-9]{2,10}$/;
		
		
		if(!exptext.test(nick)){
			alert('닉네임에 특수문자를 입력할 수 없고 2~10자 사이로 입력해주세요');
			return false;
		}
	});
	

});
