package kr.spring.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.admin.domain.AccountCommand;
import kr.spring.admin.domain.HoldingCommand;

public interface AdminMapper {
	
	//占쏙옙占쎈뎃 �뵳�딅뮞占쎈뱜 �빊�뮆�젾
	@Select("SELECT * FROM holding")
	public List<HoldingCommand> selectHolding();
	
	//--account �④쑴伊뽳옙�믭옙�뵠�뇡遺쎈궢 holding 占쎈�믭옙�뵠�뇡遺우벥 疫뀀뜆釉멩�⑨옙 �④쑴伊뽩첎占� 筌띿쉶�뮉野껓옙 占쎌뿳占쎈뮉筌욑옙 占쎌넇占쎌뵥
	//SELECT rv_num FROM account a join holding b on a.at_num=b.at_num WHERE at_pin=hd_account AND at_money=hd_money;
	@Select("SELECT b.rv_num  FROM account a,  holding b join payment p on p.rv_num  = b.rv_num WHERE a.AT_DEPOSITOR= p.PM_DEPOSITOR and a.AT_MONEY = b.HD_MONEY and a.AT_PIN = p.PM_DEPOSIT_AC")
	public List<HoldingCommand> selectRvnum();
	
	//rv_num占쎌뵠 占쎌뿳占쎌몵筌롳옙 hd_deposit揶쏉옙 占쎈씜占쎈쑓占쎌뵠占쎈뱜
	//UPDATE holding SET HD_DEPOSIT=1 WHERE rv_num=1;
	@Update("UPDATE holding SET hd_deposit=1 WHERE rv_num=#{rv_num}")
	public void updateDeposit(HoldingCommand rv_num);
	
	
	//占쎌굙占쎈튋甕곕뜇�깈嚥∽옙 占쎌깈占쎈뮞占쎈뱜占쎌벥 �④쑴伊뽳옙占� 占쎌뿯疫뀀뜄留� 占쎌뿯疫뀀뜆釉� 鈺곌퀬�돳
	//SELECT rv_money,host_email FROM reservation r join holding h on r.rv_num=h.rv_num WHERE h.hd_deposit=1
	//SELECT rv_money,host_email FROM reservation r join holding h on r.rv_num=h.rv_num WHERE h.hd_deposit=1 AND TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1
	@Select("SELECT rv_money,host_email FROM reservation r join holding h on r.rv_num=h.rv_num WHERE h.hd_deposit=1 AND h.hd_date is null AND TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1")
	public List<HoldingCommand> selecthost();
	//占쎌깈占쎈뮞占쎈뱜占쎈퓠野껓옙 占쎌뿯疫뀐옙
	@Update("UPDATE member_detail SET member_money=(NVL(member_money,0)+#{rv_money}*0.9) WHERE member_email=#{host_email}")
	public void updateHostAccount(HoldingCommand rv_num);
	
	
	//占쎈즷 占쎌뿯疫뀀뜄留� �④쑴伊� 鈺곌퀬�돳
	//SELECT at_pin FROM account a join holding h on a.at_pin=h.hd_account WHERE h.hd_deposit=1
	//SELECT at_pin FROM account a join holding h on a.at_pin=h.hd_account WHERE h.hd_deposit=1 AND a.at_num IN (SELECT a.at_num FROM holding a join reservation b on a.rv_num=b.rv_num WHERE TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1)
	@Select("SELECT at_pin FROM account a join holding h on a.at_pin=h.hd_account WHERE h.hd_deposit=1 AND a.at_num IN (SELECT a.at_num FROM holding a join reservation b on a.rv_num=b.rv_num WHERE TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1)")
	public List<HoldingCommand> selectAccount();
	//�④쑴伊� 占쎈즷 癰귨옙野껓옙
	@Update("UPDATE account SET at_money = (at_money*0.3) WHERE at_pin=#{at_pin}")
	public void updateAccount(HoldingCommand rv_num2);
	
	@Select("SELECT h.rv_num FROM reservation r join holding h on r.rv_num=h.rv_num WHERE h.hd_deposit=1 AND TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1")
	public List<HoldingCommand> selecthrv_num();
	
	@Delete("DELETE FROM holding WHERE rv_num=#{rv_num}")
	public void deleteDeposit(HoldingCommand rv_num4);
	
	
	// 獄쎻뫖苡뀐옙�깈 �뤃�뗫릭占쎈뮉 select
	@Select("SELECT DISTINCT member_complain_accom_num FROM\r\n" + 
			"(SELECT COUNT(member_rv_end_date) OVER(partition by member_complain_accom_num) ac_count,member_complain_accom_num FROM member_complain WHERE TO_DATE(member_rv_end_date,'YYYY/MM/DD') < sysdate+1)\r\n" + 
			"WHERE ac_count>=10")
	public List<HoldingCommand> selectComplain();
	
	// 獄쎻뫖苡뀐옙�깈嚥∽옙 host email �뤃�뗫맙
	@Select("SELECT acc_host FROM accom WHERE acc_num=#{member_complain_accom_num}")
	public List<HoldingCommand> selectComplain_email(HoldingCommand holdingCommand);
	
	//占쎈뭼占쎈쓠占쎌깈占쎈뮞占쎈뱜占쎈퓠占쎄퐣 占쎌깈占쎈뮞占쎈뱜嚥∽옙 占쎄땀�뵳占�
	@Update("UPDATE member_auth SET member_auth=3 WHERE member_email=#{acc_host}")
	public void updateComplain_auth(HoldingCommand holdingCommand);
	
	//�뚮똾逾놅옙�쟿占쎌뵥 野껓옙占쎄퉳占쎈┷筌롳옙 占쎌궎占쎈뮎 占쎄텊筌욑옙 筌ｋ똾寃�
	@Update("UPDATE holding SET hd_date = sysdate WHERE rv_num=#{member_complain_accom_num}")
	public void updateHd_date(HoldingCommand holdingCommand);
	
	//占쎈퉸占쎈뼣 �뚮똾遊쏙옙�쟿占쎌뵥 占쎄텣占쎌젫c/:
	@Delete("DELETE FROM member_complain WHERE member_complain_accom_num=#{member_complain_accom_num}")
	public void deleteComplain(HoldingCommand holdingCommand);
	
	//20占쎌뵬 筌욑옙占쎄돌筌롳옙 占쎈쑓占쎌뵠占쎈뱜 null嚥∽옙 占쎈씜占쎈윝
	@Update("UPDATE holding SET hd_date = null WHERE hd_date+20 < sysdate AND hd_deposit=1")
	public void updateHd_date2();
	

	//醫낅즺�맂 洹몃９
	@Select("SELECT g_num FROM group_table WHERE g_close_date < sysdate+1")
	public List<HoldingCommand> selectGroup_gnum();
	
	@Delete("DELETE FROM group_table WHERE g_num=#{g_num}")
	public void delete_gnum(HoldingCommand holdingCommand);
	@Insert("INSERT INTO holding (hd_num,hd_deposit,hd_account,hd_money,rv_num) VALUES (holding_seq.nextval,#{hd_deposit},#{hd_account},#{hd_money},rv_seq.currval)")
	public void insertHolding(HoldingCommand holdingCommand);

	@Insert("INSERT INTO account (at_num,at_name,at_pin,at_money,at_depositor,at_reg_date) VALUES (holding_seq.nextval,#{at_name},#{at_pin},#{at_money},#{at_depositor},sysdate)")
	public void insertAccount(AccountCommand accountCommand);


}
