$(document).ready(function() {	
	
	//이메일 인증시 약관동의
	$('#regiform').submit(function() {
		if(!($('#ch1').is(':checked'))) {
			alert('약관동의를 체크해 주세요');
			return false;
		}
		if($('#email').val()==''){
			alert('이메일을 입력하세요');
			return false;
		}
	});
	
	
	
	//코드 체크시 빈칸 확인
	$('#mailCheck').submit(function(){
		if($('#code_code').val() == ''){
			alert('코드를 입력해주세요!');
			return false;
		}
	});
	
	$('#register_form').submit(function(){
		if($('#member_passwd').val() == ''){
			alert('비밀번호를 입력하세요!');
			return false;
		}
		if($('#member_nickname').val() == ''){
			alert('닉네임을 입력하세요!');
			return false;
		}
		if($('#member_content').val() == ''){
			alert('자기소개를 입력하세요!');
			return false;
		}
	});
	
	
});

