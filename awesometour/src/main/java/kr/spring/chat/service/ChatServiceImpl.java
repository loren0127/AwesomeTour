package kr.spring.chat.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.chat.dao.ChatMapper;
import kr.spring.chat.domain.ChatAllAndMemberCommand;
import kr.spring.chat.domain.ChatAllCommand;
import kr.spring.chat.domain.ChatAllTalkCommand;
import kr.spring.chat.domain.ChatMemberCommand;

@Service("chatService")
public class ChatServiceImpl implements ChatService {
	
	@Resource
	private ChatMapper chatMapper;

	@Override
	public List<ChatAllCommand> selectChatAllList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatAllAndMemberCommand> selectChatMemberList(Map<String, Object> map) {
		return chatMapper.selectChatMemberList(map);
	}

	@Override
	public int SelectChatMemberListRowCount(String user_email) {
		return chatMapper.selectChatMemberListRowCount(user_email);
	}

	@Override
	public void insertChatAll(ChatAllCommand command) {
		chatMapper.insertChatAll(command);
	}
	
	public void updateChatAllNotice(Map<String, Object> map) {
		chatMapper.updateChatAllNotice(map);
	}
	
	//Chat overlap �뛾�렮維쀥뜝占�
	@Override
	public ChatAllCommand selectChatAllJoin(Map<String, Object> map) {
		return chatMapper.SelectChatAllJoin(map);
	}
	
	@Override
	public int selectChatMemberCheckedJoin(Map<String, Object> map) {
		
		return chatMapper.selectChatMemberCheckedJoin(map);
	}
	
	public void updateChatMemberStatus(Map<String, Object> map) {
		chatMapper.updateChatMemberStatus(map);
	}
	
	public void updateChatMemberStatousCross(Map<String, Object> map) {
		chatMapper.updateChatMemberStatousCross(map);
	}

	@Override
	public void insertChatAllTalkMessageLog(ChatAllTalkCommand command) {
		chatMapper.insertChatAllTalkMessageLog(command);
	}
	
	@Override
	public List<ChatAllTalkCommand> selectChatAallTalkMessageLog(int chat_all_num) {
		return chatMapper.selectChatAallTalkMessageLog(chat_all_num);
	}
	
	public void updateChatAllJoinChange(Map<String, Object> map) {
		chatMapper.updateChatAllJoinChange(map);
	}

	@Override
	public void insertChatAllGroup(ChatAllCommand command) {
		chatMapper.insertChatAllGroup(command);
	}
	
	public int insertFriendChatCreate(ChatAllCommand command) {
		return chatMapper.insertFriendChatCreate(command);
	}
	
	public void insertChatMember(ChatMemberCommand command) {
		chatMapper.insertChatMember(command);
	}
	
	public int selectChatAllNum(String chat_all_title) {
		return chatMapper.selectChatAllNum(chat_all_title);
	}
	
	public int selectChatOverlapChecked(Map<String, Object> map) {
		return chatMapper.selectChatOverlapChecked(map);
	}
}
