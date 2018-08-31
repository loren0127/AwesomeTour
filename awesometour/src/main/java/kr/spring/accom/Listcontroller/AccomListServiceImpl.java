package kr.spring.accom.Listcontroller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;



@Service("accomListService")
public class AccomListServiceImpl implements AccomListService{
	
	@Resource
	AccomMapperList accomMapper;
	
	@Override
	public List<AccomListCommand> selectAccomlist(Map<String, Object> map) {
		
		return accomMapper.selectAccomlist(map);
	}

	@Override
	public int selectAccomConut(Map<String, Object> map) {
	
		return 2;
	}
	
}
