package kr.spring.group.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.group.domain.GroupCommand;
import kr.spring.member.domain.MemberCommand;

public interface GroupService {

	public int selectRowCount(Map<String,Object> map);
	public int selectRowCountFilter(Map<String,Object> map);
	public List<GroupCommand> selectGroupList(Map<String,Object> map);
	public List<GroupCommand> selectGroupListFilter(Map<String,Object> map);
	
	public GroupCommand selectGroupDetail(Integer g_num);
	public List<MemberCommand> selectGroupMemberList(Integer g_num);
	public GroupCommand selectReservationAcc(Integer g_num);

	public void insertGroup(GroupCommand groupCommand);
	public void updateGroup(GroupCommand groupCommand);
	public void deleteGroup(Integer g_num);
	public List<Map<String,Object>> selecRecommendAccom(String loc);
	public int selectGroupChatnum(Integer group_num);




}
