package kr.spring.accomList.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.accom.domain.ImageCommand;
import kr.spring.accomList.domain.AccomListCommand;


public interface AccomListMapper {
	public List<AccomListCommand> selectAccomlist(Map<String, Object> map);
	public int selectAccomListCount(Map<String,Object> map);
	@Select("SELECT se_name FROM accom_service WHERE se_acc_num=#{se_num}")
	public String selectAcoomSerivce(int se_num);
	public List<AccomListCommand> selectAccomTotallist(Map<String, Object> map);
	public int selectAccomTotallistCount(Map<String,Object> map);
	@Select("SELECT im_cover,im_cover_name,im_image2,im_image2_name FROM accom_image WHERE im_ac_num=#{im_ac_num}")
	public ImageCommand selectImageView(Integer im_ac_num);
	
}

