package kr.spring.mypage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.mypage.domain.MyPageCommand;
import kr.spring.reservation.domain.ReservationCommand;

public interface MyPageMapper {
	//SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM map_store ORDER BY ms_num)a) WHERE rnum >= ? AND rnum <= ? 
	//SELECT a.acc_num,a.acc_name,b.MEMBER_COMPLAIN_CONTENT,b.MEMBER_COMPLAIN_REG_DATE,b.MEMBER_RV_END_DATE,b.member_email FROM accom a join member_complain b on a.acc_num=b.member_complain_accom_num WHERE acc_host='ghcks3916@naver.com';
	//SELECT * FROM reservation WHERE host_email=#{user_email}
	@Select("SELECT a.acc_num,a.acc_name,b.MEMBER_COMPLAIN_CONTENT,b.MEMBER_COMPLAIN_REG_DATE,b.MEMBER_RV_END_DATE,b.member_email,b.member_complain_num "
			+ "FROM accom a join member_complain b on a.acc_num=b.member_complain_accom_num WHERE acc_host=#{user_email}")
	public List<MyPageCommand> selectHost_Email(String user_email);
	public List<MyPageCommand> selectMypageList(Map<String,Object> map);
	@Select("SELECT COUNT(*) FROM member_complain a join accom b on a.member_complain_accom_num=b.acc_num WHERE acc_host=#{user_email}")
	public int selectRowCount(Map<String,Object> map);
	
	@Select("SELECT a.member_complain_title,a.member_email,a.member_complain_content,b.rv_start_date,b.rv_end_date \r\n" + 
			"FROM member_complain a join reservation b on a.member_rv_num=b.rv_num WHERE a.member_complain_num=#{member_complain_num}")
	public MyPageCommand select_complain(int num);
	
	@Insert("INSERT INTO message (message_num,message_receiver,message_sender,message_title,message_reg_date,message_receive_status,message_send_status,message_content,message_url,message_type)\r\n" + 
			"VALUES (message_seq.nextval,#{message_receiver},#{message_sender},#{message_title},sysdate,-1,1,#{message_content},0,0)")
	public void insert_message1(Map<String,Object> mapper);
	
	//Reservation --------------------
	
	//Select young reservation
	@Select("SELECT COUNT(*) FROM reservation WHERE member_email=#{user_email} AND SYSDATE < TO_DATE(rv_end_date)")
	public int selectReservationRowCount(String user_email);
	@Select("SELECT a.acc_num, r.rv_num, r.ro_num, acc_name, rv_start_date, rv_end_date, rv_people, rv_money FROM reservation r JOIN accom a ON r.acc_num=a.acc_num WHERE r.member_email=#{user_email} AND SYSDATE < TO_DATE(r.rv_end_date)")
	public List<ReservationCommand> selectReservationList(Map<String, Object> map);
	
	//Select old reservation
	@Select("SELECT COUNT(*) FROM reservation WHERE member_email=#{user_email} AND SYSDATE > TO_DATE(rv_end_date)")
	public int selectReservationRowCountOld(String user_email);
	@Select("SELECT acc_num, acc_name, rv_num, rv_start_date, rv_end_date, rv_people, rv_money, ro_num FROM (SELECT rownum rnum, a.acc_num, a.acc_name, r.rv_num, r.rv_start_date, r.rv_end_date, r.rv_people, r.rv_money, r.ro_num FROM accom a, reservation r WHERE (a.acc_num=r.acc_num) AND (r.member_email=#{user_email}) AND SYSDATE > (TO_DATE(r.rv_end_date))) WHERE rnum >= #{start} AND rnum <= #{end}")
	public List<ReservationCommand> selectReservationListOld(Map<String, Object> map);
	
	
	//Grade counting(Reservation detail used SQL)
	@Select("SELECT count(*) FROM  accom_grade WHERE ag_acc_num = #{acc_num}")
	public int selectGradeCount(Integer acc_num);
	
	//XML mapping
	public ReservationCommand selectReservationDetail(Map<String, Object> map);
	
	//Reservation cancel ----------------------------------------------------------------------------
	
	//Reservation status setting
	@Update("UPDATE resercation SET rv_status=-1 WHERE rv_num=#{rv_num}")
	public void updateReservation(int rv_num);
	
	//Reservation holding(All commission return(7day not yet))
	@Delete("DELETE holding FROM holding h JOIN reservation a ON h.rv_num=a.rv_num WHERE rv_num=#{rv_num} AND member_email=#{member_email} AND (TO_DATE(a.rv_start_date) - SYSDATE) < 7")
	public void deleteHolding(Map<String, Object> map);
	
	//Reservation holding(Commission not return(After 7day))
	@Update("UPDATE holding SET hd_money=(hd_money/11) FROM holding h JOIN reservation r ON h.rv_num=r.rv_num WHERE rv_num=#{rv_num} AND member_email=#{member_email} AND (TO_DATE(a.rv_start_date) - SYSDATE) > 7")
	public void updateHolding(Map<String, Object> map);
	
	
	//Complain send ------------------------------------------------------------
	@Insert("INSERT INTO member_complain(member_complain_num, member_complain_accom_num, member_complain_title, member_complain_content, member_complain_reg_date, member_email, member_rv_end_date, member_rv_num) VALUES(member_complain_seq.NEXTVAL, #{member_complain_accom_num}, #{member_complain_title}, #{member_complain_content}, SYSDATE, #{member_email}, #{member_rv_end_date}, #{member_rv_num})")
	public void insertComplainSend(MyPageCommand command);
	
	
}
