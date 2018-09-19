package kr.spring.reservation.dao;


import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.reservation.domain.PaymentCommand;
import kr.spring.reservation.domain.ReservationCommand;

public interface ReservationMapper {
	
	@Select("SELECT COUNT(*) FROM reservation WHERE  TO_CHAR(rv_reg_date,'YYYY/MM/DD') = TO_CHAR(SYSDATE,'YYYY/MM/DD') AND acc_num = #{acc_num}")
	public int selectReservationCount(Integer acc_num);
	@Insert("INSERT INTO RESERVATION (RV_NUM,ACC_NUM,RO_NUM,MEMBER_EMAIL,RV_REG_DATE,RV_STATUS,RV_MONEY,RV_START_DATE,RV_END_DATE,RV_PEOPLE,RV_MESSAGE,RV_REQUEST,host_email) VALUES (rv_seq.nextval,#{acc_num},#{ro_num},#{member_email},sysdate,0,#{rv_money},#{rv_start_date},#{rv_end_date},#{rv_people},#{rv_message ,jdbcType=VARCHAR},#{rv_request , jdbcType=VARCHAR},#{host_email})")
	public int insertReservation(ReservationCommand reservationCommand);
	
	public int insertPayment(PaymentCommand command);
	
	@Select("SELECT m.MEMBER_NICKNAME FROM member_detail m, accom a  WHERE m.member_email = a.ACC_HOST AND a.acc_num=#{acc_num}") 
	public String selectHostNick(Integer acc_num);

	public int selectReservationGroup(Map<String,Object> map);

	public ReservationCommand selectRerservationAcc(Map<String,Integer> map);
	@Select("SELECT count(*) FROM  chat_member WHERE chat_all_num = #{chat_all_num} and member_email = #{member_email} ")
	public int selectGroupMemberCount(Map<String,Object> map);
	
	@Select("SELECT ro_num FROM  accom_room WHERE ro_room_num = #{ro_room_num} and ro_acc_num = #{acc_num} ")
	public int selectRoNum(Map<String,Integer> map);
	
	@Select("SELECT count(*) FROM  accom_grade WHERE ag_acc_num = #{acc_num} ")
	public int selectGradeCount(Integer acc_num);

}
