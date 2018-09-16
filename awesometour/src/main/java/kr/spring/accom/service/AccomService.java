package kr.spring.accom.service;

import java.util.List;
import java.util.Map;

import kr.spring.accom.domain.AccomCommand;
import kr.spring.accom.domain.ImageCommand;
import kr.spring.accom.domain.ReviewCommand;
import kr.spring.group.domain.GroupCommand;

public interface AccomService {
	public List<AccomCommand> selectList(Map<String,Object> map);
	public ImageCommand selectImage(Integer im_ac_num);
	public List<AccomCommand> selectRecommendList(Map<String,Object> map);
	public ReviewCommand selectReviewList(Integer acc_num);
	public List<GroupCommand> selectGroupList(Map<String,Object> map);
}
