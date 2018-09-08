package kr.spring.accomList.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.accomList.domain.AccomListCommand;


public interface AccomListMapper {
	public List<AccomListCommand> selectAccomlist(Map<String, Object> map);
	public int selectAccomListCount(Map<String,Object> map);
	@Select("SELECT se_name FROM accom_service WHERE se_acc_num=#{se_num}")
	public String selectAcoomSerivce(int se_num);
	public List<AccomListCommand> selectAccomTotallist(Map<String, Object> map);
	public int selectAccomTotallistCount(Map<String,Object> map);
	
}

