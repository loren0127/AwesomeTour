package kr.spring.accom.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import kr.spring.accom.domain.AccomCommand;

public interface AccomMapper {
	@Select("SELECT * FROM accom ORDER BY acc_num DESC")
	public List<AccomCommand> selectList();
}
