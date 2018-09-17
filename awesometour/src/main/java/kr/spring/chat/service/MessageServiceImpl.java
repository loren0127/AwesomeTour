package kr.spring.chat.service;
 import java.util.List;
import java.util.Map;
 import javax.annotation.Resource;
 import org.springframework.stereotype.Service;
 import kr.spring.chat.dao.MessageMapper;
import kr.spring.chat.domain.MessageCommand;
 @Service("messageService")
public class MessageServiceImpl implements MessageService {
	
	@Resource
	private MessageMapper messageMapper;
 	@Override
	public int selectMessageRowCount(Map<String, Object> map) {
		return messageMapper.selectMessageRowCount(map); 
	}
 	@Override
	public List<MessageCommand> selectMessageList(Map<String, Object> map) {
		return messageMapper.selectMessageList(map);
	}
	
	public void insertMessageSend(MessageCommand command) {
		messageMapper.insertMessageSend(command);
	}
	
	public MessageCommand selectMessageDetail(int message_num) {
		return messageMapper.selectMessageDetail(message_num);
	}
	public void updateMessageRead(Map<String, Object> map) {
		messageMapper.updateMessageRead(map);
	}
	
	public void updateMessageSendStatus(Map<String, Object> map) {
		messageMapper.updateMessageSendStatus(map);
	}
	
	public void updateMessageReceiveStatus(Map<String, Object> map) {
		messageMapper.updateMessageReceiveStatus(map);
	}
	
	public Map<String, Object> selectMessageStatus(int message_num) {
		return messageMapper.selectMessageStatus(message_num);
	}
}