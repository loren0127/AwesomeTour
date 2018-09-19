package kr.spring.accomList.service;

import java.util.List;
import java.util.Map;

import kr.spring.accom.domain.ImageCommand;
import kr.spring.accomList.domain.AccomListCommand;

public interface AccomListService {
	//ºÎ¸ð±Û 
	public List<AccomListCommand> selectAccomlist(Map<String, Object> map);
	public int selectAccomListCount(Map<String,Object> map);
	public String selectAcoomSerivce(int se_num);
	public List<AccomListCommand> selectAccomTotallist(Map<String, Object> map);
	public int selectAccomTotallistCount(Map<String,Object> map);
	public ImageCommand selectImageView(Integer im_ac_num);
	public List<AccomListCommand> selectSearch_auto(Map<String, Object> map);
	public int selectSearch_count(Map<String,Object> map);
}
