package kr.spring.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.admin.domain.AccountCommand;
import kr.spring.admin.domain.HoldingCommand;

public interface AdminMapper {
	
	//�뜝�룞�삕�뜝�럥�럠 占쎈뎨占쎈봾裕욃뜝�럥諭� 占쎈퉲占쎈츊占쎌졑
	@Select("SELECT * FROM holding")
	public List<HoldingCommand> selectHolding();
	
	//--account 占썩몿�뫒鴉딅슁�삕占쎈���삕占쎈턄占쎈눀�겫�럥沅� holding �뜝�럥占쎈���삕占쎈턄占쎈눀�겫�슦踰� �뼨���쐠�뇡硫⑼옙�뫅�삕 占썩몿�뫒鴉딅쉘泥롥뜝占� 嶺뚮씮�돳占쎈츎�뇦猿볦삕 �뜝�럩肉녑뜝�럥裕됬춯�쉻�삕 �뜝�럩�꼪�뜝�럩逾�
	//SELECT rv_num FROM account a join holding b on a.at_num=b.at_num WHERE at_pin=hd_account AND at_money=hd_money;
	@Select("SELECT b.rv_num  FROM account a,  holding b join payment p on p.rv_num  = b.rv_num WHERE a.AT_DEPOSITOR= p.PM_DEPOSITOR and a.AT_MONEY = b.HD_MONEY and a.AT_PIN = p.PM_DEPOSIT_AC")
	public List<HoldingCommand> selectRvnum();
	
	//rv_num�뜝�럩逾� �뜝�럩肉녑뜝�럩紐든춯濡녹삕 hd_deposit�뤆�룊�삕 �뜝�럥�뵜�뜝�럥�몥�뜝�럩逾졾뜝�럥諭�
	//UPDATE holding SET HD_DEPOSIT=1 WHERE rv_num=1;
	@Update("UPDATE holding SET hd_deposit=1 WHERE rv_num=#{rv_num}")
	public void updateDeposit(HoldingCommand rv_num);
	
	
	//�뜝�럩援쇿뜝�럥�뒎�뵓怨뺣쐡占쎄퉰�슖�댙�삕 �뜝�럩源덂뜝�럥裕욃뜝�럥諭쒎뜝�럩踰� 占썩몿�뫒鴉딅슁�삕�뜝占� �뜝�럩肉��뼨���쐞筌랃옙 �뜝�럩肉��뼨���쐠�뇡占� �댖怨뚰�э옙�뤂
	//SELECT rv_money,host_email FROM reservation r join holding h on r.rv_num=h.rv_num WHERE h.hd_deposit=1
	//SELECT rv_money,host_email FROM reservation r join holding h on r.rv_num=h.rv_num WHERE h.hd_deposit=1 AND TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1
	@Select("SELECT rv_money,host_email FROM reservation r join holding h on r.rv_num=h.rv_num WHERE h.hd_deposit=1 AND h.hd_date is null AND TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1")
	public List<HoldingCommand> selecthost();
	//�뜝�럩源덂뜝�럥裕욃뜝�럥諭쒎뜝�럥�뱺�뇦猿볦삕 �뜝�럩肉��뼨�먯삕
	@Update("UPDATE member_detail SET member_money=(NVL(member_money,0)+#{rv_money}*0.9) WHERE member_email=#{host_email}")
	public void updateHostAccount(HoldingCommand rv_num);
	
	
	//�뜝�럥利� �뜝�럩肉��뼨���쐞筌랃옙 占썩몿�뫒鴉딉옙 �댖怨뚰�э옙�뤂
	//SELECT at_pin FROM account a join holding h on a.at_pin=h.hd_account WHERE h.hd_deposit=1
	//SELECT at_pin FROM account a join holding h on a.at_pin=h.hd_account WHERE h.hd_deposit=1 AND a.at_num IN (SELECT a.at_num FROM holding a join reservation b on a.rv_num=b.rv_num WHERE TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1)
	@Select("SELECT at_pin FROM account a join holding h on a.at_pin=h.hd_account WHERE h.hd_deposit=1 AND a.at_num IN (SELECT a.at_num FROM holding a join reservation b on a.rv_num=b.rv_num WHERE TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1)")
	public List<HoldingCommand> selectAccount();
	//占썩몿�뫒鴉딉옙 �뜝�럥利� �솻洹⑥삕�뇦猿볦삕
	@Update("UPDATE account SET at_money = (at_money*0.3) WHERE at_pin=#{at_pin}")
	public void updateAccount(HoldingCommand rv_num2);
	
	@Select("SELECT h.rv_num FROM reservation r join holding h on r.rv_num=h.rv_num WHERE h.hd_deposit=1 AND TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1")
	public List<HoldingCommand> selecthrv_num();
	
	@Delete("DELETE FROM holding WHERE rv_num=#{rv_num}")
	public void deleteDeposit(HoldingCommand rv_num4);
	
	
	// �뛾�렮維뽬떋�먯삕占쎄퉰 占쎈쨨占쎈뿫由��뜝�럥裕� select
	@Select("SELECT DISTINCT member_complain_accom_num FROM\r\n" + 
			"(SELECT COUNT(member_rv_end_date) OVER(partition by member_complain_accom_num) ac_count,member_complain_accom_num FROM member_complain WHERE TO_DATE(member_rv_end_date,'YYYY/MM/DD') < sysdate+1)\r\n" + 
			"WHERE ac_count>=10")
	public List<HoldingCommand> selectComplain();
	
	// �뛾�렮維뽬떋�먯삕占쎄퉰�슖�댙�삕 host email 占쎈쨨占쎈뿫留�
	@Select("SELECT acc_host FROM accom WHERE acc_num=#{member_complain_accom_num}")
	public List<HoldingCommand> selectComplain_email(HoldingCommand holdingCommand);
	
	//�뜝�럥萸쇔뜝�럥�뱺�뜝�럩源덂뜝�럥裕욃뜝�럥諭쒎뜝�럥�뱺�뜝�럡�맋 �뜝�럩源덂뜝�럥裕욃뜝�럥諭쒎슖�댙�삕 �뜝�럡��占쎈뎨�뜝占�
	@Update("UPDATE member_auth SET member_auth=3 WHERE member_email=#{acc_host}")
	public void updateComplain_auth(HoldingCommand holdingCommand);
	
	//占쎈슢�샑�얜냵�삕占쎌읉�뜝�럩逾� �뇦猿볦삕�뜝�럡�돰�뜝�럥�뵹嶺뚮〕�삕 �뜝�럩沅롥뜝�럥裕� �뜝�럡�뀏嶺뚯쉻�삕 嶺뚳퐢�샑野껓옙
	@Update("UPDATE holding SET hd_date = sysdate WHERE rv_num=#{member_complain_accom_num}")
	public void updateHd_date(HoldingCommand holdingCommand);
	
	//�뜝�럥�돵�뜝�럥堉� 占쎈슢�샑�걡�룞�삕占쎌읉�뜝�럩逾� �뜝�럡�뀭�뜝�럩�젷c/:
	@Delete("DELETE FROM member_complain WHERE member_complain_accom_num=#{member_complain_accom_num}")
	public void deleteComplain(HoldingCommand holdingCommand);
	
	//20�뜝�럩逾� 嶺뚯쉻�삕�뜝�럡�룎嶺뚮〕�삕 �뜝�럥�몥�뜝�럩逾졾뜝�럥諭� null�슖�댙�삕 �뜝�럥�뵜�뜝�럥�쐻
	@Update("UPDATE holding SET hd_date = null WHERE hd_date+20 < sysdate AND hd_deposit=1")
	public void updateHd_date2();
	

	//�넫�굝利븝옙留� 域밸챶竊�
	@Select("SELECT g_num FROM group_table WHERE g_close_date < sysdate+1")
	public List<HoldingCommand> selectGroup_gnum();
	
	@Delete("DELETE FROM group_table WHERE g_num=#{g_num}")
	public void delete_gnum(HoldingCommand holdingCommand);
	@Insert("INSERT INTO holding (hd_num,hd_deposit,hd_account,hd_money,rv_num) VALUES (holding_seq.nextval,#{hd_deposit},#{hd_account},#{hd_money},rv_seq.currval)")
	public void insertHolding(HoldingCommand holdingCommand);

	@Insert("INSERT INTO account (at_num,at_name,at_pin,at_money,at_depositor,at_reg_date) VALUES (holding_seq.nextval,#{at_name},#{at_pin},#{at_money},#{at_depositor},sysdate)")
	public void insertAccount(AccountCommand accountCommand);


}
