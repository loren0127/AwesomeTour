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

	@Override
	public void insertChatMember(ChatMemberCommand command) {
		chatMapper.insertChatMember(command);
	}

	@Override
	public void updateChatAll(Integer chat_all_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateChatMember(Integer chat_member_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteChatAll(Integer chat_all_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteChatMember(Integer chat_member_num) {
		// TODO Auto-generated method stub
		
	}
	
	//Chat overlap 獄쎻뫗占�
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
		// TODO Auto-generated method stub
		chatMapper.insertChatAllGroup(command);
	}


}
