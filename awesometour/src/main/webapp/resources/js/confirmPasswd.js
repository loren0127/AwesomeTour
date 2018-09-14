$(document).ready(function() {	
	
	var checkPasswd = 0;
	
	$('#passwd_check').click(function(){
		$.ajax({
			url:'confirmPasswd.do',
			type:'post',
			data:{member_email:$('#member_email').val(),member_passwd:$('#member_current_passwd').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				if(data.result == 'PassDuplicated'){
					$('#message_current_passwd').css('color','black').text('비밀번호가 맞습니다.');
					checkPasswd=1;
				}else if(data.result == 'PassNotFound'){
					$('#message_current_passwd').css('color','red').text('비밀번호가 일치하지 않습니다.');
					checkPasswd=0;
				}
			},error:function(){
				alert('네트워크 오류 발생');
				return false;
			}
		})
	});
	
	
	//비밀번호 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	$('#modify_form #member_current_passwd').keyup(function(){
		checkPasswd=0;
		$('#member_current_passwd').text('');
	});
	
	
	$('#modify_form').submit(function(){
		if(checkPasswd==0){
			alert('기존비밀번호 체크 필수');
			if($('#member_current_passwd').val==''){
				$('#member_current_passwd').focus();
			}
			return false;
		}
	});
	
	
	//회원정보 수정 비밀번호 입력 체크
	//회원가입시 빈칸 체크
	$('#modify_form').submit(function(){
		if($('#member_current_passwd').val() == ''){
			alert('기존 비밀번호를 입력하세요!');
			return false;
		}
		if($('#member_passwd').val() == ''){
			alert('변경 비밀번호를 입력하세요!');
			return false;
		}
		if($('#member_passwd2').val() == ''){
			alert('비밀번호 확인을 입력하세요!');
			return false;
		}
		//비밀번호가 같지 않을 때 체크
		if($('#member_passwd').val() != $('#member_passwd2').val()){
			alert('비밀번호를 확인해주세요');
			return false;
		}
		
		if($('#member_current_passwd').val() == $('#member_passwd').val()){
			alert('기존 비밀번호를 사용할 수 없습니다.');
			return false;
		}
		
	});
	
	
	//비밀번호 입력시 비밀번호 체크
	$('#member_passwd').keyup(function(){
		if($('#member_passwd').val() != $('#member_passwd2').val()){
			$('#message_member_passwd').text('비밀번호가 같지 않습니다.');
		}else{
			$('#message_member_passwd').text('');	
		}

	});
	
	$('#member_passwd2').keyup(function(){

		var newPassword1 = $('#member_passwd2').val();
		var idReg = /([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/;


		if(idReg.test(newPassword1)){
			$('#message_member_passwd2').text('');
			$('#message_member_passwd3').text('');
			$('#message_member_passwd4').text('');
			$('#message_member_passwd5').text('');
		}else{
			
			var output = "비밀번호는 영문,숫자,특수문자";
			var output2 = "(!@$%^&* 만 허용)를 사용하여 6~16자까지";
			var output3 = " 사용하여 6~16자까지";
			var output4 = "영문은 대소문자를 구분합니다.";
			/*$('#message_member_passwd2').text("비밀번호는 영문,숫자,특수문자(!@$%^&* 만 허용)를 사용하여 6~16자까지, 영문은 대소문자를 구분합니다.");*/
			$('#message_member_passwd2').text(output);
			$('#message_member_passwd3').text(output2);
			$('#message_member_passwd4').text(output3);
			$('#message_member_passwd5').text(output4);
		}


		if($('#member_passwd').val() != $('#member_passwd2').val()){
			$('#message_member_passwd').text('비밀번호가 같지 않습니다.');
		}else{
			$('#message_member_passwd').text('');
		}
	});
	
	//회원정보 수정 2 체크박스 체크
	//회원가입2 체크박스 체크 여부
	$('#register_form2').submit(function(){
		var ch = document.getElementsByName("ch");
		var ra = document.getElementsByName("ra");
		var count = null;
		for(var i=0;i<ch.length;i++){
		    if(ch[i].checked == true){
		    	count = ch[i].value;
		    	
		    }
		}
		if(count == null){
    		alert("취미를 1개 이상 선택해주세요");
	        return false;	
    	}
		
		
		var sel_type = null;
		for(var i=0;i<ra.length;i++){
			if(ra[i].checked==true){
				sel_type = ra[i].value;
			}
		}
		
		if(sel_type==null){
			alert('성향을 선택해주세요');
			return false;
		}
	});
});
