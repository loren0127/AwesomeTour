$(document).ready(function() {	
	
	var checkEmail = 0;
	
	$('#check_email').click(function(){
		$.ajax({
			url:'confirmEmail.do',
			type:'post',
			data:{email:$('#email').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'EmailDuplicated'){
					$('#message_email').css('color','red').text('등록된 이메일');
					checkEmail=0;
				}else if(data.result == 'EmailNotFound'){
					$('#message_email').css('color','black').text('등록가능한 이메일');
					checkEmail=1;
				}
			},error:function(){
				alert('네트워크 오류 발생');
			}
		})
	});
	//아이디 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	$('#regiform #email').keyup(function(){
		checkEmail=0;
		$('#check_email').text('');
	});
	
	//이메일 체크 확인
	$('#regiform').submit(function(){
		/*var email = $('#email_check').val();
		var exptext = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

		if(exptext.test(email)==false){
			alert("이 메일형식이 올바르지 않습니다.<br>" +
					"이메일 형식이 알파벳+숫자@알파벳+숫자.알파벳+숫자 형식으로 입력해주세요");
			return false;
		}*/

		
		if(checkEmail==0){
			alert('이미 사용중인 이메일입니다.');
			if($('#email').val()==''){
				$('#email').focus();
			}
			return false;
		}
	});

	
	
});
