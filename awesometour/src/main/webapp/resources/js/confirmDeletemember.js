$(document).ready(function() {	
	
	var deleteCheck = 0;
	
	//회원 탈퇴시 코드 빈칸 체크
	$('#memberDelete').submit(function(){
		if($('#code_code').val()== ''){
			alert('코드를 입력하세요');
			return false;
		}
		
		if(deleteCheck==1){
			alert('코드가 맞지 않습니다.');
			return false;
		}
	});
	
	//회원 탈퇴시 코드 체크
	$('#delete_check').click(function(){
		$.ajax({
			url:'confirmDeleteMember.do',
			type:'post',
			data:{code_email:$('#code_email').val(),code_code:$('#code_code').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'CodeDuplicated'){
					$('#message_check').css('color','black').text('코드확인');
					deleteCheck=0;
				}else if(data.result == 'CodeNotFound'){
					$('#message_check').css('color','red').text('코드가 맞지 않습니다.');
					deleteCheck=1;
				}
			},error:function(){
				alert('네트워크 오류 발생')
			}
		});
	});
	
	//아이디 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	$('#memberDelete #delete_check').keyup(function(){
		deleteCheck=1;
		$('#code_code').text('');
	});
	
});
