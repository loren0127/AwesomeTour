package kr.spring.accomList.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.accom.domain.ImageCommand;
import kr.spring.accomList.dao.AccomListMapper;
import kr.spring.accomList.domain.AccomListCommand;



@Service("accomListService")
public class AccomListServiceImpl implements AccomListService{
	
	@Resource
	AccomListMapper accomListMapper;
	
	@Override
	public List<AccomListCommand> selectAccomlist(Map<String, Object> map) {
		
		return accomListMapper.selectAccomlist(map);
	}

	@Override
	public int selectAccomListCount(Map<String, Object> map) {
		
		return accomListMapper.selectAccomListCount(map);
	}

	@Override
	public String selectAcoomSerivce(int se_num) {
		
		return accomListMapper.selectAcoomSerivce(se_num);
	}

	@Override
	public List<AccomListCommand> selectAccomTotallist(Map<String, Object> map) {
		
		return accomListMapper.selectAccomTotallist(map);
	}

	@Override
	public int selectAccomTotallistCount(Map<String, Object> map) {
		
		return accomListMapper.selectAccomTotallistCount(map);
	}

	@Override
	public ImageCommand selectImageView(Integer im_ac_num) {
		
		return accomListMapper.selectImageView(im_ac_num);
	}



}
