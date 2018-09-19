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
	@Select("SELECT i.im_cover,i.im_cover_name,i.im_ac_num,i.im_num FROM  accom_image i,(select max(im_ro_num)im_ro_num ,im_ac_num FROM accom_image group by im_ac_num) z  WHERE z.im_ro_num = i.im_ro_num and i.im_ac_num=#{im_ac_num} and z.im_ac_num = i.im_ac_num")
	public ImageCommand selectImageView(Integer im_ac_num);
	public List<AccomListCommand> selectSearch_auto(Map<String, Object> map);
	public int selectSearch_count(Map<String,Object> map);
}

