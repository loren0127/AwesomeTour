package kr.spring.accom.Listcontroller;

import java.util.List;
import java.util.Map;

public interface AccomListService {
	//�θ�� 
	public List<AccomListCommand> selectAccomlist(Map<String, Object> map);
	public int selectAccomConut(Map<String,Object> map);
}
