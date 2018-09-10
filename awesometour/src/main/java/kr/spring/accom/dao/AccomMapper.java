package kr.spring.accom.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.accom.domain.AccomCommand;
import kr.spring.accom.domain.ImageCommand;

public interface AccomMapper {
	public List<AccomCommand> selectList(Map<String,Object> map);
	
	//숙소 이미지 불러오기
	@Select("SELECT im_cover,im_cover_name,im_image2,im_image2_name FROM accom_image WHERE im_ac_num=#{im_ac_num}")
	public ImageCommand selectImage(Integer im_ac_num);
}
