package kr.spring.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.admin.domain.HoldingCommand;

public interface AdminMapper {
	
	//홀딩 리스트 출력
	@Select("SELECT * FROM holding")
	public List<HoldingCommand> selectHolding();
	
	//--account 계좌테이블과 holding 테이블의 금액과 계좌가 맞는게 있는지 확인
	//SELECT rv_num FROM account a join holding b on a.at_num=b.at_num WHERE at_pin=hd_account AND at_money=hd_money;
	@Select("SELECT rv_num FROM account a join holding b on a.at_num=b.at_num WHERE at_pin=hd_account AND at_money=hd_money")
	public List<HoldingCommand> selectRvnum();
	
	//rv_num이 있으면 hd_deposit값 업데이트
	//UPDATE holding SET HD_DEPOSIT=1 WHERE rv_num=1;
	@Update("UPDATE holding SET hd_deposit=1 WHERE rv_num=#{rv_num}")
	public void updateDeposit(HoldingCommand rv_num);
	
	
	//예약번호로 호스트의 계좌와 입금될 입금액 조회
	//SELECT rv_money,host_email FROM reservation r join holding h on r.rv_num=h.rv_num WHERE h.hd_deposit=1
	//SELECT rv_money,host_email FROM reservation r join holding h on r.rv_num=h.rv_num WHERE h.hd_deposit=1 AND TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1
	@Select("SELECT rv_money,host_email FROM reservation r join holding h on r.rv_num=h.rv_num WHERE h.hd_deposit=1 AND TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1")
	public List<HoldingCommand> selecthost();
	//호스트에게 입금
	@Update("UPDATE member_detail SET member_money=(NVL(member_money,0)+#{rv_money}*0.9) WHERE member_email=#{host_email}")
	public void updateHostAccount(HoldingCommand rv_num);
	
	
	//돈 입금된 계좌 조회
	//SELECT at_pin FROM account a join holding h on a.at_pin=h.hd_account WHERE h.hd_deposit=1
	//SELECT at_pin FROM account a join holding h on a.at_pin=h.hd_account WHERE h.hd_deposit=1 AND a.at_num IN (SELECT a.at_num FROM holding a join reservation b on a.rv_num=b.rv_num WHERE TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1)
	@Select("SELECT at_pin FROM account a join holding h on a.at_pin=h.hd_account WHERE h.hd_deposit=1 AND a.at_num IN (SELECT a.at_num FROM holding a join reservation b on a.rv_num=b.rv_num WHERE TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1)")
	public List<HoldingCommand> selectAccount();
	//계좌 돈 변경
	@Update("UPDATE account SET at_money = (at_money*0.3) WHERE at_pin=#{at_pin}")
	public void updateAccount(HoldingCommand rv_num2);
	
	@Select("SELECT h.rv_num FROM reservation r join holding h on r.rv_num=h.rv_num WHERE h.hd_deposit=1 AND TO_DATE(rv_end_date,'YYYY/MM/DD') < sysdate+1")
	public List<HoldingCommand> selecthrv_num();
	
	@Delete("DELETE FROM holding WHERE rv_num=#{rv_num}")
	public void deleteDeposit(HoldingCommand rv_num4);
	
	
	// 방번호 구하는 select
	@Select("SELECT DISTINCT member_complain_accom_num FROM\r\n" + 
			"(SELECT COUNT(member_rv_end_date) OVER(partition by member_complain_accom_num) ac_count,member_complain_accom_num FROM member_complain WHERE TO_DATE(member_rv_end_date,'YYYY/MM/DD') < sysdate+1)\r\n" + 
			"WHERE ac_count>=10")
	public List<HoldingCommand> selectComplain();
	
	// 방번호로 host email 구함
	@Select("SELECT acc_host FROM accom WHERE acc_num=#{member_complain_accom_num}")
	public List<HoldingCommand> selectComplain_email(HoldingCommand holdingCommand);
	
	//슈퍼호스트에서 호스트로 내림
	@Update("UPDATE member_auth SET member_auth=3 WHERE member_email=#{acc_host}")
	public void updateComplain_auth(HoldingCommand holdingCommand);
	
	
}
