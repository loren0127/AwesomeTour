package kr.spring.accom.service;

import java.util.List;
import java.util.Map;

import kr.spring.accom.domain.AccomCommand;
import kr.spring.accom.domain.ImageCommand;

public interface AccomService {
	public List<AccomCommand> selectList(Map<String,Object> map);
	public ImageCommand selectImage(Integer im_ac_num);
}
