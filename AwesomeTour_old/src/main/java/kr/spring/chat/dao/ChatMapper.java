package kr.spring.chat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.chat.domain.ChatAllCommand;
import kr.spring.chat.domain.ChatMemberCommand;

public interface ChatMapper {
	public List<ChatAllCommand> chatAllSelectList(Map<String, Object> chatAllMap);
	
	public List<ChatMemberCommand> chatMemberSelectList(Map<String, Object> chatMemberMap);
	
	public int chatAllSelectRowListCount(Map<String, Object> chatUtil);
	
	public void chatAllInsert(ChatAllCommand chatAllCommand);
	
	public void chatMemberInsert(ChatMemberCommand chatMemberCommand);
	
	public void chatAllUpdate(Integer chat_all_num);
	
	public void chatMemberUpdate(Integer chat_member_num);
	
	public void chatAllDelete(Integer chat_all_num);
	
	public void chatMemberDelete(Integer chat_member_num);
	
	public List<ChatAllCommand> chatSelectJoin(Map<String, Object> chatAllMap);
}
