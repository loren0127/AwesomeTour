package kr.spring.mypage.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.member.dao.MemberMapper;
import kr.spring.mypage.dao.MyPageMapper;
import kr.spring.mypage.domain.MyPageCommand;

@Service("mypageService")
public class MyPageServiceImpl implements MyPageService{
	
	@Resource
	private MyPageMapper mypageMapper;
	
	@Override
	public List<MyPageCommand> selectHost_Email(String user_email) {
		return mypageMapper.selectHost_Email(user_email);
	}


	@Override
	public MyPageCommand select_complain(int num) {
	
		return 	mypageMapper.select_complain(num);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return mypageMapper.selectRowCount(map);
	}


	@Override
	public List<MyPageCommand> selectMypageList(Map<String, Object> map) {
		return mypageMapper.selectMypageList(map);
	}


	@Override
	public void insert_message1(Map<String, Object> mapper) {
		mypageMapper.insert_message1(mapper);
	}







	
}
