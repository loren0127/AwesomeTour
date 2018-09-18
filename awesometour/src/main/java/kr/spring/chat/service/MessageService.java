package kr.spring.chat.service;

import java.util.List;
import java.util.Map;

import kr.spring.chat.domain.MessageCommand;


public interface MessageService {
	public int selectMessageRowCount(Map<String, Object> map);
	public List<MessageCommand> selectMessageList(Map<String, Object> map);
	
	public void insertMessageSend(MessageCommand command); 
	
	public MessageCommand selectMessageDetail(int message_num);
	public void updateMessageRead(Map<String, Object> map);
	
	public void updateMessageSendStatus(Map<String, Object> map);
	
	public void updateMessageReceiveStatus(Map<String, Object> map);
	
	public MessageCommand selectMessageStatus(int message_num);
	
	public void deleteMessage(int message_num);
}