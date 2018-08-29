package kr.spring.group.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.group.domain.GroupCommand;
public interface GroupMapper {
	
	@Select("SELECT * FROM group_table")
	public List<GroupCommand> selectGroupList(Map<String,Object> map);
	@Select("SELECT * FROM group_table WHERE g_num=#{g_num}")
	public GroupCommand selectGroupDetail(Integer g_num);
	@Insert("INSERT INTO group_table (g_num,g_title,g_explain,g_loc,g_reg_date,g_close_date,g_isPrivate,g_host) VALUES (board_seq.nextval,#{g_title},#{g_explain},#{g_loc},SYSDATE,#{g_close_date},#{g_isPrivate},#{g_host})")
	public void insertGroup(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM group_table")
	public int selectRowCount(Map<String,Object> map);

}
