package kr.spring.accom.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.accom.dao.AccomMapper;
import kr.spring.accom.domain.AccomCommand;

@Service("accomService")
public class AccomServiceImpl implements AccomService{//Ʈ����� ó��

	//�������� ����
	@Resource
	private AccomMapper accomMapper;

	@Override
	public List<AccomCommand> selectList() {
		return accomMapper.selectList();
	}
	
}
