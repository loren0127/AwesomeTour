package kr.spring.reservation.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.group.dao.GroupMapper;
import kr.spring.member.dao.MemberMapper;
import kr.spring.member.domain.CodeCommand;
import kr.spring.member.domain.MemberCommand;
import kr.spring.reservation.dao.ReservationMapper;
import kr.spring.reservation.domain.PaymentCommand;
import kr.spring.reservation.domain.ReservationCommand;


@Service("reservationService")
public class ReservationServiceImpl implements ReservationService{

	 @Resource
	 private ReservationMapper reservationMapper;
	
	@Override
	public void insertReservation(Map<String,Object> map) {
		reservationMapper.insertReservation((ReservationCommand) map.get("reservationCommand"));
		reservationMapper.insertPayment((PaymentCommand) map.get("paymentCommand"));
	}

	@Override
	public Map<String,Integer> selectReservationGroup(String g_name) {
		Map<String,Integer> g_num = reservationMapper.selectReservationGroup(g_name);
		return g_num;
	}

	@Override
	public ReservationCommand selectRerservationAcc(int acc_num) {
		ReservationCommand rc = reservationMapper.selectRerservationAcc(acc_num);
		return rc;
	}


	

}
