package kr.spring.mypage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import kr.spring.mypage.domain.MyPageCommand;

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
	
	
	
}
