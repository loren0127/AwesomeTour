package kr.spring.reservation.service;

import java.util.Map;

import kr.spring.reservation.domain.ReservationCommand;

public interface ReservationService {	
	public void insertReservation(Map<String,Object> map);
	public Map<String,Integer> selectReservationGroup(String g_name);
	public ReservationCommand selectRerservationAcc(int acc_num);

}
