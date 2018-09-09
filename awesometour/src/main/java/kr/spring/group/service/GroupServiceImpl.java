package kr.spring.group.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import kr.spring.group.dao.GroupMapper;
import kr.spring.group.domain.GroupCommand;
import kr.spring.member.domain.MemberCommand;

@Service("groupService")
public class GroupServiceImpl implements GroupService{

	 @Resource
	private GroupMapper groupMapper;
	
	@Override
	public List<GroupCommand> selectGroupList(Map<String, Object> map) {

		return groupMapper.selectGroupList(map);
	}

	@Override
	public GroupCommand selectGroupDetail(Integer g_num) {
		// TODO Auto-generated method stub
		return groupMapper.selectGroupDetail(g_num);
	}


	@Override
	public int selectRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return groupMapper.selectRowCount(map);
	}

	@Override
	public List<MemberCommand> selectGroupMemberList(Integer g_num) {
		// TODO Auto-generated method stub
		return groupMapper.selectGroupMemberList(g_num);
	}

	@Override
	public void insertGroup(GroupCommand groupCommand) {
		// TODO Auto-generated method stub
		groupMapper.insertGroup(groupCommand);

	}

	@Override
	public void updateGroup(GroupCommand groupCommand) {
		// TODO Auto-generated method stub
		groupMapper.updateGroup(groupCommand);
		
	}

	@Override
	public void deleteGroup(Integer g_num) {
		// TODO Auto-generated method stub
		groupMapper.deleteGroup(g_num);
	}

	@Override
	public List<GroupCommand> selectGroupListFilter(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return groupMapper.selectGroupListFilter(map);
	}

	@Override
	public GroupCommand selectReservationAcc(Integer g_num) {
		// TODO Auto-generated method stub
		return groupMapper.selectReservationAcc(g_num);
	}

	@Override
	public int selectRowCountFilter(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return groupMapper.selectRowCountFilter(map);
	}

	@Override
	public List<Map<String,Object>> selecRecommendAccom(String loc) {
		// TODO Auto-generated method stub
		return groupMapper.selecRecommendAccom(loc);
	}

	@Override
	public int selectGroupChatnum(Integer group_num) {
		// TODO Auto-generated method stub
		return groupMapper.selectGroupChatnum(group_num);
	}

	

}
