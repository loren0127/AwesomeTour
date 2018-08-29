package kr.spring.group.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import kr.spring.group.dao.GroupMapper;
import kr.spring.group.domain.GroupCommand;

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
	public void insertGroup(Map<String, Object> map) {
		// TODO Auto-generated method stub
		groupMapper.insertGroup(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return groupMapper.selectRowCount(map);
	}

}
