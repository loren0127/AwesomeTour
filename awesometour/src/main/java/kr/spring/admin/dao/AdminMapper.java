package kr.spring.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.admin.domain.AccountCommand;
import kr.spring.admin.domain.HoldingCommand;

public interface AdminMapper {
	
	//���뵫 由ъ뒪�듃 異쒕젰
	@Select("SELECT * FROM holding")
	public List<HoldingCommand> selectHolding();
	
	//--account 怨꾩쥖�뀒�씠釉붽낵 holding �뀒�씠釉붿쓽 湲덉븸怨� 怨꾩쥖媛� 留욌뒗寃� �엳�뒗吏� �솗�씤
	//SELECT rv_num FROM account a join holding b on a.at_num=b.at_num WHERE at_pin=hd_account AND at_money=hd_money;
	@Select("SELECT b.rv_num  FROM account a,  holding b join payment p on p.rv_num  = b.rv_num WHERE a.AT_DEPOSITOR= p.PM_DEPOSITOR and a.AT_MONEY = b.HD_MONEY and a.AT_PIN = p.PM_DEPOSIT_AC")
	public List<HoldingCommand> selectRvnum();
	
	//rv_num�씠 �엳�쑝硫� hd_deposit媛� �뾽�뜲�씠�듃
	//UPDATE holding SET HD_DEPOSIT=1 WHERE rv_num=1;
	@Update("UPDATE holding SET hd_deposit=1 WHERE rv_num=#{rv_num}")
	public void updateDeposit(HoldingCommand rv_num);
	
	
	//�삁�빟踰덊샇濡� �샇�뒪�듃�쓽 怨꾩쥖�� �엯湲덈맆 �엯湲덉븸 議고쉶
	//SELECT rv_money,host_email FROM reservation r join holding h on r.rv_num=h.rv_num WHERE h.hd_deposit=1
	//SELECT rv_money,host_email FROM reservation r join holding h on r.rv_num=h.rv_num WHERE h.hd_deposit=1 AND TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1
	@Select("SELECT rv_money,host_email FROM reservation r join holding h on r.rv_num=h.rv_num WHERE h.hd_deposit=1 AND h.hd_date is null AND TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1")
	public List<HoldingCommand> selecthost();
	//�샇�뒪�듃�뿉寃� �엯湲�
	@Update("UPDATE member_detail SET member_money=(NVL(member_money,0)+#{rv_money}*0.9) WHERE member_email=#{host_email}")
	public void updateHostAccount(HoldingCommand rv_num);
	
	
	//�룉 �엯湲덈맂 怨꾩쥖 議고쉶
	//SELECT at_pin FROM account a join holding h on a.at_pin=h.hd_account WHERE h.hd_deposit=1
	//SELECT at_pin FROM account a join holding h on a.at_pin=h.hd_account WHERE h.hd_deposit=1 AND a.at_num IN (SELECT a.at_num FROM holding a join reservation b on a.rv_num=b.rv_num WHERE TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1)
	@Select("SELECT at_pin FROM account a join holding h on a.at_pin=h.hd_account WHERE h.hd_deposit=1 AND a.at_num IN (SELECT a.at_num FROM holding a join reservation b on a.rv_num=b.rv_num WHERE TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1)")
	public List<HoldingCommand> selectAccount();
	//怨꾩쥖 �룉 蹂�寃�
	@Update("UPDATE account SET at_money = (at_money*0.3) WHERE at_pin=#{at_pin}")
	public void updateAccount(HoldingCommand rv_num2);
	
	@Select("SELECT h.rv_num FROM reservation r join holding h on r.rv_num=h.rv_num WHERE h.hd_deposit=1 AND TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1")
	public List<HoldingCommand> selecthrv_num();
	
	@Delete("DELETE FROM holding WHERE rv_num=#{rv_num}")
	public void deleteDeposit(HoldingCommand rv_num4);
	
	
	// 諛⑸쾲�샇 援ы븯�뒗 select
	@Select("SELECT DISTINCT member_complain_accom_num FROM\r\n" + 
			"(SELECT COUNT(member_rv_end_date) OVER(partition by member_complain_accom_num) ac_count,member_complain_accom_num FROM member_complain WHERE TO_DATE(member_rv_end_date,'YYYY/MM/DD') < sysdate+1)\r\n" + 
			"WHERE ac_count>=10")
	public List<HoldingCommand> selectComplain();
	
	// 諛⑸쾲�샇濡� host email 援ы븿
	@Select("SELECT acc_host FROM accom WHERE acc_num=#{member_complain_accom_num}")
	public List<HoldingCommand> selectComplain_email(HoldingCommand holdingCommand);
	
	//�뒋�띁�샇�뒪�듃�뿉�꽌 �샇�뒪�듃濡� �궡由�
	@Update("UPDATE member_auth SET member_auth=3 WHERE member_email=#{acc_host}")
	public void updateComplain_auth(HoldingCommand holdingCommand);
	
	//而댄뵆�젅�씤 寃��깋�릺硫� �삤�뒛 �궇吏� 泥댄겕
	@Update("UPDATE holding SET hd_date = sysdate WHERE rv_num=#{member_complain_accom_num}")
	public void updateHd_date(HoldingCommand holdingCommand);
	
	//�빐�떦 而댄봽�젅�씤 �궘�젣c/:
	@Delete("DELETE FROM member_complain WHERE member_complain_accom_num=#{member_complain_accom_num}")
	public void deleteComplain(HoldingCommand holdingCommand);
	
	//20�씪 吏��굹硫� �뜲�씠�듃 null濡� �뾽�럠
	@Update("UPDATE holding SET hd_date = null WHERE hd_date+20 < sysdate AND hd_deposit=1")
	public void updateHd_date2();
	
	@Insert("INSERT INTO holding (hd_num,hd_deposit,hd_account,hd_money,rv_num) VALUES (holding_seq.nextval,#{hd_deposit},#{hd_account},#{hd_money},#{rv_num})")
	public void insertHolding(HoldingCommand holdingCommand);

	@Insert("INSERT INTO account (at_num,at_name,at_pin,at_money,at_depositor,at_reg_date) VALUES (holding_seq.nextval,#{at_name},#{at_pin},#{at_money},#{at_depositor},sysdate)")
	public void insertAccount(AccountCommand accountCommand);

	
	
	
	
	
	
}
