package kr.spring.util;

import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import kr.spring.chat.domain.ChatAllTalkCommand;
import kr.spring.chat.service.ChatService;

public class WebSocketHandler extends TextWebSocketHandler {
	
	//Key: String / Value: WebSocketSession
	private Map<String, WebSocketSession> user = new ConcurrentHashMap<String, WebSocketSession>();
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private ChatService chatService;

	@Override
	public void afterConnectionEstablished(WebSocketSession webSocketSession) throws Exception {
		Map<String, Object> webSocketSessionMap = webSocketSession.getAttributes();
		log.info("Session list(Connect) :: " + webSocketSessionMap);
		String user_email = (String)webSocketSessionMap.get("user_email");
		if(user.containsKey(user_email)) {
			user.remove(user_email);
			user.put(user_email, webSocketSession);
		} else {
			user.putIfAbsent(user_email, webSocketSession);
		}
	}
	
	
	@Override
	protected void handleTextMessage(WebSocketSession webSocketSession, TextMessage message) throws Exception {
		Map<String, Object> webSocketSessionMap = webSocketSession.getAttributes();
		
		String user_email = (String)webSocketSessionMap.get("user_email");
		String user_nickname = (String)webSocketSessionMap.get("user_nickname");
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd kk:mm");
		String setChat_all_talk_reg_date = dateFormat.format(new java.util.Date());
		
		log.info(user_nickname + "(handleTextMessage): " + message);
		
		//Insert chat message log
		ChatAllTalkCommand chatAllTalkCommand = new ChatAllTalkCommand();
		chatAllTalkCommand.setMember_email(user_email);
		chatAllTalkCommand.setChat_all_talk_content(StringUtil.useNoHtml(message.getPayload()));
		chatAllTalkCommand.setChat_all_talk_reg_date(setChat_all_talk_reg_date);
		chatAllTalkCommand.setChat_all_talk_nickname(user_nickname);
		
		String talk_split[] = message.getPayload().split("\\|");
		String chat_all_talk_content = "";
		int chat_all_num = 0;
		if(talk_split.length == 3) { //Insert chat message
			chat_all_talk_content = talk_split[2];
			chat_all_num = Integer.parseInt(talk_split[0]);
			chatAllTalkCommand.setChat_all_talk_content(chat_all_talk_content);
			chatAllTalkCommand.setChat_all_num(chat_all_num);
			chatService.insertChatAllTalkMessageLog(chatAllTalkCommand);
		} else if(talk_split.length == 2) { //Not insert chat message
			chat_all_talk_content = talk_split[1];
		} else { //Error
			throw new Exception();
		}
		
		for(WebSocketSession loopWebSocketSession : user.values()) {
			log.info(user_email + "(handleTextMessage): " + message.getPayload() + "|" + setChat_all_talk_reg_date + "|" + user_email);
			loopWebSocketSession.sendMessage(new TextMessage(user_nickname + "|" + message.getPayload() + "|" + setChat_all_talk_reg_date + "|" + user_email));
		}
	}
	
	
	@Override
	public void afterConnectionClosed(WebSocketSession webSocketSession, CloseStatus status) throws Exception {
		Map<String, Object> webSocketSessionMap = webSocketSession.getAttributes();
		String user_email = (String)webSocketSessionMap.get("user_email");
		System.out.println("Session list(Close) :: " + webSocketSessionMap);
		
		log.info("Session list(Close) :: " + webSocketSessionMap);
		user.remove(user_email);
	}
	
	
	@Override
	public void handleTransportError(WebSocketSession webSocketSession, Throwable throwable) throws Exception {
		Map<String, Object> webSocketSessionMap = webSocketSession.getAttributes();
		String user_email = (String)webSocketSessionMap.get("user_email");
		System.out.println("Session list(Error) :: " + webSocketSessionMap);
		
		log.error(user_email + "Error(" + throwable.getMessage() + ")");
		user.remove(user_email);
	}
}