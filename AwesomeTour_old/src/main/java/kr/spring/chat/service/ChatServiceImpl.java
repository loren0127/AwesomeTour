package kr.spring.chat.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.chat.dao.ChatMapper;
import kr.spring.chat.domain.ChatAllCommand;
import kr.spring.chat.domain.ChatMemberCommand;

@Service("chatService")
public class ChatServiceImpl implements ChatService {

	@Override
	public List<ChatAllCommand> chatAllSelectList(Map<String, Object> chatAllMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ChatMemberCommand> chatMemberSelectList(Map<String, Object> chatMemberMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int chatAllSelectRowListCount(Map<String, Object> chatUtil) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void chatAllInsert(ChatAllCommand chatAllCommand) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chatMemberInsert(ChatMemberCommand chatMemberCommand) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chatAllUpdate(Integer chat_all_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chatMemberUpdate(Integer chat_member_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chatAllDelete(Integer chat_all_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void chatMemberDelete(Integer chat_member_num) {
		// TODO Auto-generated method stub
		
	}
	
}
