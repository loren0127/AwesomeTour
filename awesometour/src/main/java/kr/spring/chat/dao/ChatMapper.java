package kr.spring.chat.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.chat.domain.ChatAllTalkCommand;
import kr.spring.chat.domain.ChatAllAndMemberCommand;
import kr.spring.chat.domain.ChatAllCommand;
import kr.spring.chat.domain.ChatMemberCommand;

public interface ChatMapper {
	@Select("SELECT * FROM chat_all WHERE chat_all_num=#{chat_all_num}")
	public List<ChatAllCommand> chatAllSelectList(Map<String, Object> map);
	
	@Select("SELECT a.chat_member_num, a.chat_all_num, a.member_email, a.chat_member_mod_date, chat_member_mod_date, b.group_num, b.chat_all_title, b.chat_all_sort, b.chat_all_member_list FROM chat_member a, chat_all b WHERE a.chat_all_num=b.chat_all_num AND a.member_email=#{user_email}")
	public List<ChatAllAndMemberCommand> selectChatMemberList(Map<String, Object> map);
	
	@Select("SELECT COUNT(*) FROM chat_member WHERE member_email=#{user_email}")
	public int selectChatMemberListRowCount(String user_email);
	
	//ChatMapper.xml
	public void insertChatAll(ChatAllCommand command);
	
	public void insertChatAllGroup(ChatAllCommand command);
		
	@Update("UPDATE chat_all SET chat_all_content=#{chat_all_content} WHERE chat_all_num=#{chat_all_num}")
	public void updateChatAllNotice(Map<String, Object> map);
	
	//ChatMapper.xml
	public int insertFriendChatCreate(ChatAllCommand command);
	
	//Chat join
	@Select("SELECT * FROM  chat_member INNER JOIN chat_all ON chat_all.chat_all_num=chat_member.chat_all_num WHERE member_email=#{user_email} AND chat_member.chat_all_num=#{chat_all_num}")
	public ChatAllCommand SelectChatAllJoin(Map<String, Object> map);
	
	@Select("SELECT member_email FROM chat_member WHERE chat_all_num=#{chat_all_num} AND member_email!=#{user_email}")
	public String selectChatMemberUserEmail(Map<String, Object> map);
	
	//Chat overlap
	@Select("SELECT chat_member_status FROM chat_member WHERE member_email=#{user_email} AND chat_all_num=#{chat_all_num}")
	public int selectChatMemberCheckedJoin(Map<String, Object> map);
	
	@Update("UPDATE chat_member SET chat_member_status=#{chat_member_status} WHERE member_email=#{user_email} AND chat_all_num=#{chat_all_num}")
	public void updateChatMemberStatus(Map<String, Object> map);
	
	@Update("UPDATE chat_member SET chat_member_status = CASE WHEN chat_member_status=0 THEN 1 WHEN chat_member_status=1 THEN 0 END WHERE member_email=#{user_email} AND (chat_all_num=#{chat_all_num} OR chat_all_num=#{chat_all_num_overlap})")
	public void updateChatMemberStatousCross(Map<String, Object> map);
	
	//Chat insert message(chat_talk_reg_date is default SYSDATE)
	@Insert("INSERT INTO chat_all_talk(chat_all_talk_num, chat_all_num, member_email, chat_all_talk_content, chat_all_talk_reg_date, chat_all_talk_nickname) VALUES(chat_all_talk_seq.NEXTVAL, #{chat_all_num}, #{member_email}, #{chat_all_talk_content}, TO_DATE(#{chat_all_talk_reg_date}, 'YYYY-MM-DD HH24:MI:SS'), #{chat_all_talk_nickname})")
	public void insertChatAllTalkMessageLog(ChatAllTalkCommand command);
	
	@Select("SELECT * FROM chat_all_talk WHERE chat_all_num=#{chat_all_num} ORDER BY chat_all_talk_reg_date ASC")
	public List<ChatAllTalkCommand> selectChatAallTalkMessageLog(int chat_all_num);
	
	@Update("UPDATE chat_member SET chat_member_mod_date=SYSDATE WHERE chat_all_num=#{chat_all_num} AND member_email=#{user_email}")
	public void updateChatAllJoinChange(Map<String, Object> map);

	public void insertChatMember(ChatMemberCommand command);
	
	@Select("SELECT chat_all_num FROM chat_all WHERE chat_all_title=#{chat_all_title}")
	public int selectChatAllNum(String chat_all_title);
	
	@Select("SELECT COUNT(*) FROM chat_all, chat_member WHERE chat_all.chat_all_num = chat_member.chat_all_num AND chat_all.group_num=-1 AND (member_email=#{message_sender} OR member_email=#{message_receiver})")
	public int selectChatOverlapChecked(Map<String, Object> map);
	
	//Group chat delete need group_num / Friend chat need group_num = -1
	@Delete("DELETE chat_member WHERE group_num=#{group_num} AND member_email=#{user_email}")
	public void deleteChatMember(Map<String, Object> map);
}