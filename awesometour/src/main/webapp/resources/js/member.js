$(document).ready(function() {	
	
	
	
	//회원정보 조회시 비밀번호 체크
	$('#view_Check').submit(function(){
		if($('#member_passwd').val() == ''){
			alert('비밀번호를 입력해주세요');
			return false;
		}
	});
	
	

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

	//회원가입시 빈칸 체크
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
		//비밀번호가 같지 않을 때 체크
		if($('#member_passwd').val() != $('#member_passwd2').val()){
			alert('비밀번호를 확인해주세요');
			return false;
		}
	});



	//비밀번호 입력시 비밀번호 체크
	$('#member_passwd').keyup(function(){
		/*if(!$('#message_passwd').test('/^.*(?=.{6,20})(?=.*[0-9])(?=.*[a-zA-Z]).*$/')){
			alert('asdasd');
		}*/
		/**/
		/*var newPassword1 = $('#member_passwd').val();
		var idReg = /([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/;
		
		
		 if(idReg.test(newPassword1)){
			 $('#message_unique').text('');
		 }else{
			 $('#message_unique').text("비밀번호는 영문,숫자,특수문자(!@$%^&* 만 허용)를 사용하여 6~16자까지, 영문은 대소문자를 구분합니다.");
		 }*/


		if($('#member_passwd').val() != $('#member_passwd2').val()){
			$('#message_check').text('비밀번호가 같지 않습니다.');
		}else{
			$('#message_check').text('');	
		}

	});
	$('#member_passwd2').keyup(function(){
		
		var newPassword1 = $('#member_passwd2').val();
		var idReg = /([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/;

		
		if(idReg.test(newPassword1)){
			 $('#message_unique').text('');
		 }else{
			 $('#message_unique').text("비밀번호는 영문,숫자,특수문자(!@$%^&* 만 허용)를 사용하여 6~16자까지, 영문은 대소문자를 구분합니다.");
		 }
		
		
		if($('#member_passwd').val() != $('#member_passwd2').val()){
			$('#message_check').text('비밀번호가 같지 않습니다.');
		}else{
			$('#message_check').text('');
		}
	});
	
	
	//회원정보 수정 2 체크박스 체크
	$('#register_form3').submit(function(){
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











