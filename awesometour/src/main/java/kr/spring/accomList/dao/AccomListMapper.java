package kr.spring.accomList.dao;

import java.util.List;
import java.util.Map;

import kr.spring.accomList.domain.AccomListCommand;


public interface AccomListMapper {
	public List<AccomListCommand> selectAccomlist(Map<String, Object> map);
	public int selectAccomListCount(Map<String,Object> map);
}
