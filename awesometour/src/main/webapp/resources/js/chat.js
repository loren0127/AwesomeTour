var webSocket;

//Message schema: User_nickname|Room_number|Message_type|Message_content

//WebSocket connect method
function connect() {
	var request = new Request();
	var chat_all_num = request.getParameter("chat_all_num");
	
	webSocket = new WebSocket("ws://localhost:8080/awesometour/chat-ws.do");
	//주석
	webSocket.onopen = function(event) {
		var message = chat_all_num + '|connect';
		webSocket.send(message);
		
		$('#message').attr('disabled', false);
		$('#msg_history').scrollTop($(document).height());
	};
	
	//WebSocket receive message from server method
	webSocket.onmessage = function(event) {
		var request = new Request();
		var chat_all_num = request.getParameter("chat_all_num") + '';
		
		var data = event.data;
		var arrayData = data.split('|');
		
		if(arrayData[1] == chat_all_num) {
			if(arrayData[2] == 'normal') {
				appendMessage(arrayData[0] + ': ' +arrayData[3]);
				//appendMessage(arrayData[0] + '|' + arrayData[1] + '|' + arrayData[2] + '|' + removeArrayByIndex(removeArrayByIndex(removeArrayByIndex(arrayData, 0), 0), 0));
			} else if(arrayData[2] == 'connect') {
				appendMessage('알람 - ' + arrayData[0] + '님 입장!');
			} else if(arrayData[2] == 'disconnect') {
				webSocket.close();
				appendMessage('알람 - ' + arrayData[0] + '님 퇴장!');
			}
		}
		$('#msg_history').scrollTop($(document).height());
	};
};

//Chat exit
function disconnect() {
	var request = new Request();
	var chat_all_num = request.getParameter("chat_all_num");
	
	var message = chat_all_num + '|disconnect';
	webSocket.send(message);
};

//Send message (from client, to server)
function send() {
	var request = new Request();
	var chat_all_num = request.getParameter("chat_all_num");
	
	var message = $('#message').val();
	
	//메세지 구분자('|') 표현식을 숫자 표현식으로 치환
	while(message.indexOf('|') != -1) {
		message = message.replace('|', '&#124;');
	}
	
	//메세지 XSS 공격 방어, 괄호('<', '>') 표현식을 숫자 표현식으로 치환
	message = message.replace('<', '&#60;');
	message = message.replace('>', '&#62;');
	
	webSocket.send(chat_all_num + '|normal|' + message);
	
	//Initialization message
	$('#message').val('');
};

//Send message to server(Output UI)
function appendMessage(message) {
	var request = new Request();
	var user_email = request.getParameter("user_email");
	var splitMessage = message.split(':');
	
	if(splitMessage[0] == user_email) {
		//Right user(Me)
		var sendMessage = '<div class="outgoing_msg"><div class="sent_msg"><p>' + splitMessage[1] + '</p><span class="time_date">' + getTimeStamp() + '</span> </div></div><hr>'
		$('#chatMessageArea').append(sendMessage);
		
		$("#mesgs").scrollTop($("#mesgs")[0].scrollHeight);
		$("#chatMessageArea").scrollTop($("#chatMessageArea")[0].scrollHeight);
	} else if(splitMessage[1] == undefined) {
		var sendMessage = '<div class="outgoing_msg"><div class="received_msg_long"><div class="received_withd_msg_long"><p>' + splitMessage[0] + '</p><span class="time_date">' + getTimeStamp() + '</span></div></div></div><hr>'
		$('#chatMessageArea').append(sendMessage);
		
		$("#mesgs").scrollTop($("#mesgs")[0].scrollHeight);
		$("#chatMessageArea").scrollTop($("#chatMessageArea")[0].scrollHeight);
	} else {
		//Left chat(Other user)
		var sendMessage = '<div class="outgoing_msg"><div class="incoming_msg_img"><img src="https://ptetutorials.com/images/user-profile.png" alt="sunil">' + splitMessage[0] + '</div><div class="received_msg"><div class="received_withd_msg"><p>' + splitMessage[1] + '</p><span class="time_date">' + getTimeStamp() + '</span></div></div></div><hr>'
		$('#chatMessageArea').append(sendMessage);
		
		$("#mesgs").scrollTop($("#mesgs")[0].scrollHeight);
		$("#chatMessageArea").scrollTop($("#chatMessageArea")[0].scrollHeight);
	}
};

//Connect event method
$(document).ready(function() {
	connect();
	
	//Enter key press event(Send message)
	$('#message').keypress(function(event) {
		
		//If used explorer: event.keyCode method / Else used other(Chrome, Opera ...): event.which
		var keycode = (event.keyCode ? event.keyCode : event.which);
		
		if(keycode == '13') {
			send();
		}
		
		event.stopPropagation();
	});
	
	//Click send button event(Send message)
	$('#sendBtn').click(function() {
		send();
	});
});


//Other method start

//Request function
function Request() {
	var requestParam = "";

	//Request(Get) getParameter function
	this.getParameter = function(param) {
		//현재 주소를 decoding
		var url = unescape(location.href);
		//파라미터만 자르고, 다시 &그분자를 잘라서 배열에 넣는다. 
		var paramArr = (url.substring(url.indexOf("?") + 1, url.length)).split("&");
		
		for (var i = 0; i < paramArr.length; i++) {
			var temp = paramArr[i].split("="); //파라미터 변수명을 담음

			if (temp[0].toUpperCase() == param.toUpperCase()) {
				// 변수명과 일치할 경우 데이터 삽입
				requestParam = paramArr[i].split("=")[1];
				break;
			}
		}
		return requestParam;
	}
}

//Time return function
function getTimeStamp() {
	var d = new Date();
	var s = leadingZeros(d.getFullYear(), 4) + '-' + leadingZeros(d.getMonth() + 1, 2) + '-' + leadingZeros(d.getDate(), 2) + ' ' +
			leadingZeros(d.getHours(), 2) + ':' + leadingZeros(d.getMinutes(), 2);
	return s;
}
function leadingZeros(n, digits) {
	var zero = '';
	n = n.toString();
	if (n.length < digits) {
		for (i = 0; i < digits - n.length; i++) zero += '0';
	}
	return zero + n;
}


//Remove array, used index
/* function removeArrayByIndex(array, index) {
	return array.filter(function(arrayElem, arrayIndex) {
		return index != arrayIndex;
	});
} */
//Other method end