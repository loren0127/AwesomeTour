package kr.spring.accomList.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

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

}
