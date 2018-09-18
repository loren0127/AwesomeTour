package kr.spring.chat.service;

import java.util.List;
import java.util.Map;

import kr.spring.chat.domain.ChatAllAndMemberCommand;
import kr.spring.chat.domain.ChatAllCommand;
import kr.spring.chat.domain.ChatAllTalkCommand;
import kr.spring.chat.domain.ChatMemberCommand;

public interface ChatService {
	
	
	public List<ChatAllCommand> selectChatAllList(Map<String, Object> map);
	
	public List<ChatAllAndMemberCommand> selectChatMemberList(Map<String, Object> map);
	
	public int SelectChatMemberListRowCount(String user_email);
	
	public void insertChatAll(ChatAllCommand command);
	
	public int insertFriendChatCreate(ChatAllCommand command);
	
	public ChatAllCommand selectChatAllJoin(Map<String, Object> map);
	
	//Chat overlap
	public int selectChatMemberCheckedJoin(Map<String, Object> map);
	public void updateChatMemberStatus(Map<String, Object> map);
	public void updateChatMemberStatousCross(Map<String, Object> map);
	
	//Chat message log insert
	public void insertChatAllTalkMessageLog(ChatAllTalkCommand command);
	
	//Chat message log select
	public List<ChatAllTalkCommand> selectChatAallTalkMessageLog(int chat_all_num);
	
	public void updateChatAllJoinChange(Map<String, Object> map);
	
	public void insertChatAllGroup(ChatAllCommand command);
	
	public void insertChatMember(ChatMemberCommand command);
	
	public int selectChatAllNum(String chat_all_title);
	
	public int selectChatOverlapChecked(Map<String, Object> map);
}
