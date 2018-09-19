package kr.spring.mypage.service;

import java.util.List;
import java.util.Map;

import kr.spring.mypage.domain.MyPageCommand;
import kr.spring.reservation.domain.ReservationCommand;

public interface MyPageService {
	public List<MyPageCommand> selectHost_Email(String user_email);
	public List<MyPageCommand> selectMypageList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	public MyPageCommand select_complain(int num);
	public void insert_message1(Map<String,Object> mapper);
	
	//Reservation ----
	public int selectReservationRowCount(String user_email);
	
	public List<ReservationCommand> selectReservationList(Map<String, Object> map);
	
	public List<ReservationCommand> selectReservationListOld(Map<String, Object> map);
	
	public int selectReservationRowCountOld(String user_email);
	
	public int selectGradeCount(Integer acc_num);
	
	public ReservationCommand selectReservationDetail(Map<String, Object> map);
}
