package kr.spring.reservation.service;

import java.util.Map;

import kr.spring.reservation.domain.ReservationCommand;

public interface ReservationService {	
	public void insertReservationSet(Map<String,Object> map);
	public int selectReservationGroup(Map<String,Object> map);
	public ReservationCommand selectRerservationAcc(Map<String,Integer> map);
	public int selectReservationCount(Integer acc_num);
	public String selectHostNick(Integer acc_num);
	public int selectGroupMemberCount(Map<String,Object> map);



}
