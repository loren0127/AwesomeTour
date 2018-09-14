package kr.spring.chat.service;

import java.util.List;
import java.util.Map;

import kr.spring.chat.domain.MessageCommand;


public interface MessageService {
	public int selectMessageRowCount(Map<String, Object> map);
	public List<MessageCommand> selectMessageList(Map<String, Object> map);
	
	public void insertMessageSend(MessageCommand command); 
	
	public MessageCommand selectMessageDetail(int message_num);
}