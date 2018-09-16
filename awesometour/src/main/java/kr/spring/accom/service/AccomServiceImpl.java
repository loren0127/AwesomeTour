package kr.spring.accom.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.accom.dao.AccomMapper;
import kr.spring.accom.domain.AccomCommand;
import kr.spring.accom.domain.ImageCommand;
import kr.spring.accom.domain.ReviewCommand;
import kr.spring.group.domain.GroupCommand;

@Service("accomService")
public class AccomServiceImpl implements AccomService{//트랜잭션 처리

	//의존관계 주입
	@Resource
	private AccomMapper accomMapper;

	@Override
	public List<AccomCommand> selectList(Map<String,Object> map) {
		return accomMapper.selectList(map);
	}

	@Override
	public ImageCommand selectImage(Integer im_ac_num) {
		return accomMapper.selectImage(im_ac_num);
	}

	@Override
	public List<AccomCommand> selectRecommendList(Map<String, Object> map) {
		return accomMapper.selectRecommendList(map);
	}

	@Override
	public ReviewCommand selectReviewList(Integer acc_num) {
		return accomMapper.selectReviewList(acc_num);
	}

	@Override
	public List<GroupCommand> selectGroupList(Map<String, Object> map) {
		return accomMapper.selectGroupList(map);
	}
}
