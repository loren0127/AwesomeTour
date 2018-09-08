package kr.spring.reservation.dao;


import java.util.Map;

import org.apache.ibatis.annotations.Select;

import kr.spring.reservation.domain.PaymentCommand;
import kr.spring.reservation.domain.ReservationCommand;

public interface ReservationMapper {
	
	@Select("SELECT COUNT(*) FROM group_table WHERE g_isSearch = 0")
	public int selectRowCount(Map<String,Object> map);
	
	public int insertReservation(ReservationCommand command);
	
	public int insertPayment(PaymentCommand command);
	
	@Select("SELECT COUN(*) cnt,g_num FROM group_table WHERE g_name = #{g_name} GROUP BY g_num")
	public Map<String,Integer>selectReservationGroup(String g_name);

	@Select("SELECT * FROM  (SELECT acc_num, acc_address1, acc_address2,acc_in, acc_out, acc_num anum  FROM accom ) a  join reservation r on  a.anum = r.acc_num join (SELECT se_name,se_acc_num  FROM accom_service ) s on  r.acc_num = s.se_acc_num  join (SELECT ag_grade,ag_acc_num FROM  accom_grade) g  on  s.se_acc_num = g.ag_acc_num  join (SELECT  im_cover, im_cover_name, im_ac_num  FROM accom_image) i on  g.ag_acc_num = i.im_ac_num  join (SELECT  ro_room_num,ro_acc_num, ro_sub  FROM accom_Room) o on  i.im_ac_num = o.ro_acc_num WHERE   a.acc_num = #{acc_num} AND r.ro_num=#{ro_num}")
	public ReservationCommand selectRerservationAcc(int acc_num);


}
