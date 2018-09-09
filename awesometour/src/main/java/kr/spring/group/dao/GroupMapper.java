package kr.spring.group.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.group.domain.GroupCommand;
import kr.spring.member.domain.MemberCommand;
public interface GroupMapper {
	
	@Select("SELECT COUNT(*) FROM group_table WHERE g_isSearch = 0")
	public int selectRowCount(Map<String,Object> map);
	public int selectRowCountFilter(Map<String,Object> map);
	@Select("SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM group_table WHERE g_isSearch = 0)a)  WHERE rnum >= #{start} AND rnum <= #{end} ")
	public List<GroupCommand> selectGroupList(Map<String,Object> map);
	
	public List<GroupCommand> selectGroupListFilter(Map<String,Object> map);
	
	@Select("SELECT * FROM group_table WHERE g_num=#{g_num}")
	public GroupCommand selectGroupDetail(Integer g_num);
	public List<MemberCommand> selectGroupMemberList(Integer g_num);
	@Select("SELECT * FROM reservation WHERE member_email=(SELECT member_email WHERE CHAT_MEMBER WHERE g_num = #{g_num})")
	public GroupCommand selectReservationAcc(Integer g_num);

	@Insert("INSERT INTO group_table (g_num,g_name,g_explain,g_address1,g_address2,g_reg_date,g_close_date,g_hobby,g_isPrivate,g_isSearch,member_email,g_image,g_imagename) VALUES (board_seq.nextval,#{g_name},#{g_explain},#{g_address1},#{g_address2},SYSDATE,#{g_close_date},#{g_hobby, jdbcType=VARCHAR},#{g_isPrivate},#{g_isSearch},#{member_email},#{g_image},#{g_imageName})")
	public void insertGroup(GroupCommand groupCommand);
	@Update("UPDATE  gorup_table SET g_title = #{g_title},g_explain=#{g_explain},g_address1=#{g_address1},g_address2=#{g_address2},g_close_date=#{g_close_date},g_hobby=#{g_hobby},g_isPrivate=#{g_isPrivate},g_isSearch=#{g_isSearch},g_image=#{g_image},g_imageame=#{g_imageName} WHERE g_num = #{g_num}")
	public void updateGroup(GroupCommand groupCommand);
	@Delete("DELETE FROM group_table WHERE g_num = #{g_num}")
	public void deleteGroup(Integer g_num);
	
	@Select("SELECT chat_all_num FROM chat_all WHERE group_num = #{group_num}")
	public int selectGroupChatnum(Integer group_num);
	
	public List<Map<String,Object>> selecRecommendAccom(String loc);
}