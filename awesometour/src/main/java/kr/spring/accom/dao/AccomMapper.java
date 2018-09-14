package kr.spring.accom.dao;

import java.util.List;
import java.util.Map;

import kr.spring.accom.domain.AccomCommand;
import kr.spring.accom.domain.ImageCommand;

public interface AccomMapper {
	public List<AccomCommand> selectList(Map<String,Object> map);
	
	//���� �̹��� �ҷ�����
	public ImageCommand selectImage(Integer im_ac_num);
}
