$(document).ready(function() {
	var checkedEmail = 0;
	 
	$('#checkedEmail').click(function() {
		$.ajax({
			url:'chatEmailChecked.do',
			type:'post',
			data:{member_email:$('#message_sender').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data) {
				if(data.result == 'EmailDuplicated') {
					$('#receiverCheckedMessage').css('color', 'black').text('쪽지 전송이 가능합니다!');
					checkedEmail = 1;
				} else if(data.result == 'EmailNotFound') {
					$('#receiverCheckedMessage').css('color', 'red').text('이메일이 정확한지 확인해 주세요!');
					checkedEmail = 0;
				}
			},error:function(request, status, error) {
				alert("code = "+ request.status + " message = " + request.responseText + " error = " + error);
			}
		});
	});
	
	$('#messageSendForm').submit(function() {
		if(checkedEmail == 0) {
			alert('받는이 확인을 해주세요!');
			$('#message_sender').text('');
			$('#message_sender').focus();
			return false;
		}
	});
	
	
	//chat invite
	var checkedEmailChat = 0;
	
	$('#checkedEmailChat').click(function() {
		$.ajax({
			url:'chatEmailChecked.do',
			type:'post',
			data:{member_email:$('#message_sender_chat').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data) {
				if(data.result == 'EmailDuplicated') {
					$('#receiverCheckedMessageChat').css('color', 'black').text('채팅 신청이 가능합니다!');
					checkedEmailChat = 1;
				} else if(data.result == 'EmailNotFound') {
					$('#receiverCheckedMessageChat').css('color', 'red').text('이메일이 정확한지 확인해 주세요!');
					checkedEmailChat = 0;
				}
			},error:function(request, status, error) {
				alert("code = "+ request.status + " message = " + request.responseText + " error = " + error);
			}
		});
	});
	
	$('#friendChatInvite').submit(function() {
		if(checkedEmailChat == 0) {
			alert('받는이 확인을 해주세요!');
			$('#message_sender').text('');
			$('#message_sender').focus();
			return false;
		}
	});
});