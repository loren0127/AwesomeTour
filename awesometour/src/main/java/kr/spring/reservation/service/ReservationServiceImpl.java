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

//수정용 주석
@Service("reservationService")
public class ReservationServiceImpl implements ReservationService{
 
	@Resource
	 private ReservationMapper reservationMapper;
	
	@Override
	public void insertReservationSet(Map<String,Object> map) {
		reservationMapper.insertReservation((ReservationCommand) map.get("reservationCommand"));
		reservationMapper.insertPayment((PaymentCommand) map.get("payCommand"));
	}

	@Override
	public int selectReservationGroup(Map<String,Object> map) {
		return reservationMapper.selectReservationGroup(map);
	}

	@Override
	public ReservationCommand selectRerservationAcc(Map<String,Integer> map) {
		ReservationCommand rc = reservationMapper.selectRerservationAcc(map);
		return rc;
	}

	@Override
	public int selectReservationCount(Integer acc_num) {
		// TODO Auto-generated method stub
		return reservationMapper.selectReservationCount(acc_num);
	}

	@Override
	public String selectHostNick(Integer acc_num) {
		// TODO Auto-generated method stub
		return reservationMapper.selectHostNick(acc_num);
	}

	@Override
	public int selectGroupMemberCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return reservationMapper.selectGroupMemberCount(map);
	}

	@Override
	public int selectRoNum(Map<String, Integer> map) {
		// TODO Auto-generated method stub
		return reservationMapper.selectRoNum(map);
	}

	@Override
	public int selectGradeCount(Integer acc_num) {
		// TODO Auto-generated method stub
		return reservationMapper.selectGradeCount(acc_num);
	}

}
