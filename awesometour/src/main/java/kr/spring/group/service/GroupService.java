package kr.spring.group.service;

import java.util.List;
import java.util.Map;

import kr.spring.group.domain.GroupCommand;

public interface GroupService {

	public List<GroupCommand> selectGroupList(Map<String,Object> map);
	public GroupCommand selectGroupDetail(Integer g_num);
	public void insertGroup(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);

}
