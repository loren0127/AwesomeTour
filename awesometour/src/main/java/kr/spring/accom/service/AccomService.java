package kr.spring.accom.service;

import java.util.List;

import kr.spring.accom.domain.AccomCommand;

public interface AccomService {
	public List<AccomCommand> selectList();
}
