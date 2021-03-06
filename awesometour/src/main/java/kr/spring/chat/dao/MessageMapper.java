package kr.spring.chat.dao;
 import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.chat.domain.MessageCommand;
 public interface MessageMapper {
	public int selectMessageRowCount(Map<String, Object> map);
	public List<MessageCommand> selectMessageList(Map<String, Object> map);
	
	@Insert("INSERT INTO message(message_num, message_receiver, message_sender, message_title, message_content, message_url, message_type) VALUES(message_seq.NEXTVAL, #{message_receiver}, #{message_sender}, #{message_title}, #{message_content}, #{message_URL}, #{message_type})")
	public void insertMessageSend(MessageCommand command);
	
	@Select("SELECT * FROM message WHERE message_num=#{message_num}")
	public MessageCommand selectMessageDetail(int message_num);
	
	@Update("UPDATE message SET message_receive_status=0 WHERE message_receiver=#{user_email} AND message_num=#{message_num}")
	public void updateMessageRead(Map<String, Object> map);
	
	@Update("UPDATE message SET message_send_status=-1 WHERE message_sender=#{user_email} AND message_num=#{message_num}")
	public void updateMessageSendStatus(Map<String, Object> map);
	
	@Update("UPDATE message SET message_receive_status=-1 WHERE message_receiver=#{user_email} AND message_num=#{message_num}")
	public void updateMessageReceiveStatus(Map<String, Object> map);
	
	@Select("SELECT message_send_status, message_receive_status FROM message WHERE message_num=#{message_num}")
	public MessageCommand selectMessageStatus(int message_num);
	
	@Delete("DELETE message WHERE message_num=#{message_num}")
	public void deleteMessage(int message_num);
}