package kr.spring.mypage.service;

import java.util.List;
import java.util.Map;

import kr.spring.mypage.domain.MyPageCommand;

public interface MyPageService {
	public List<MyPageCommand> selectHost_Email(String user_email);
	public List<MyPageCommand> selectMypageList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public MyPageCommand select_complain(int num);
}
