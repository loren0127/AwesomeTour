package kr.spring.chat.dao;
 import java.util.List;
import java.util.Map;
 import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.chat.domain.MessageCommand;
 public interface MessageMapper {
	public int selectMessageRowCount(Map<String, Object> map);
	public List<MessageCommand> selectMessageList(Map<String, Object> map);
	
	@Insert("INSERT INTO message(message_num, message_receiver, message_sender, message_title, message_content, message_url, message_type) VALUES(message_seq.NEXTVAL, #{message_receiver}, #{message_sender}, #{message_title}, #{message_content}, #{message_URL}, #{message_type})")
	public void insertMessageSend(MessageCommand command);
	
	@Select("SELECT * FROM message WHERE message_num=#{message_num}")
	public MessageCommand selectMessageDetail(int message_num);
} 