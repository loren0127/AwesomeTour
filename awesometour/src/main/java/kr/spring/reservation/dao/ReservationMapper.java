package kr.spring.reservation.dao;


import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.reservation.domain.PaymentCommand;
import kr.spring.reservation.domain.ReservationCommand;

public interface ReservationMapper {
	
	@Select("SELECT COUNT(*) FROM reservation WHERE  TO_CHAR(rv_reg_date,'YYYY/MM/DD') = TO_CHAR(SYSDATE,'YYYY/MM/DD') AND acc_num = #{acc_num}")
	public int selectReservationCount(Integer acc_num);
	@Insert("INSERT INTO RESERVATION (RV_NUM,ACC_NUM,RO_NUM,MEMBER_EMAIL,RV_REG_DATE,RV_STATUS,RV_MONEY,RV_START_DATE,RV_END_DATE,RV_PEOPLE,RV_MESSAGE,RV_REQUEST) VALUES (rv_seq.nextval,#{acc_num},#{ro_num},#{member_email},sysdate,0,#{rv_money},#{rv_startdate},#{rv_enddate},#{rv_people},#{rv_message},#{rv_request , jdbcType=VARCHAR})")
	public int insertReservation(ReservationCommand reservationCommand);
	
	public int insertPayment(PaymentCommand command);
	
	@Select("SELECT m.MEMBER_NICKNAME FROM member_detail m, accom a  WHERE m.member_email = a.ACC_HOST AND a.acc_num=#{acc_num}") 
	public String selectHostNick(Integer acc_num);

	public int selectReservationGroup(Map<String,Object> map);

	@Select("SELECT * FROM (SELECT acc_num,acc_host,acc_name, acc_address1, acc_address2,acc_in, acc_out, acc_num anum FROM accom ) a join (SELECT se_name,se_acc_num FROM accom_service ) s on a.acc_num = s.se_acc_num join (SELECT avg(ag_grade) ag_grade,ag_acc_num FROM accom_grade group by ag_acc_num) g on s.se_acc_num = g.ag_acc_num join (SELECT im_cover, im_cover_name, im_ac_num ,im_ro_num FROM accom_image) i on g.ag_acc_num = i.im_ac_num join (SELECT ro_room_num,ro_acc_num, ro_sub,ro_price FROM accom_Room) o on i.im_ac_num = o.ro_acc_num WHERE a.acc_num = #{acc_num} AND o.ro_room_num=#{ro_num}")
	public ReservationCommand selectRerservationAcc(Map<String,Integer> map);
	@Select("SELECT count(*) FROM  chat_member WHERE chat_all_num = #{chat_all_num} and member_email = #{member_email} ")
	public int selectGroupMemberCount(Map<String,Object> map);


}
