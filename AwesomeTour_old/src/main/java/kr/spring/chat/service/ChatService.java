package kr.spring.chat.service;

import java.util.List;
import java.util.Map;

import kr.spring.chat.domain.ChatAllCommand;
import kr.spring.chat.domain.ChatMemberCommand;

public interface ChatService {
	public List<ChatAllCommand> chatAllSelectList(Map<String, Object> chatAllMap);
	
	public List<ChatMemberCommand> chatMemberSelectList(Map<String, Object> chatUtilMap);
	
	public int chatAllSelectRowListCount(Map<String, Object> chatUtilMap);
	
	public void chatAllInsert(ChatAllCommand chatAllCommand);
	
	public void chatMemberInsert(ChatMemberCommand chatMemberCommand);
	
	public void chatAllUpdate(Integer chat_all_num);
	
	public void chatMemberUpdate(Integer chat_member_num);
	
	public void chatAllDelete(Integer chat_all_num);
	
	public void chatMemberDelete(Integer chat_member_num);
}
