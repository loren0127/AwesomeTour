package kr.spring.admin.dao;

import java.util.List;

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
	@Select("SELECT rv_money,host_email FROM reservation r join holding h on r.rv_num=h.rv_num WHERE h.hd_deposit=1")
	public List<HoldingCommand> selecthost();
	//호스트에게 입금
	@Update("UPDATE member_detail SET member_money=(NVL(member_money,0)+#{rv_money}*0.9) WHERE member_email=#{host_email}")
	public void updateHostAccount(HoldingCommand rv_num);
	
	
	//돈 입금된 계좌 조회
	@Select("SELECT at_pin FROM account a join holding h on a.at_pin=h.hd_account WHERE h.hd_deposit=1")
	public List<HoldingCommand> selectAccount();
	//계좌 돈 변경
	@Update("UPDATE account SET at_money = (at_money*0.3) WHERE at_pin=#{at_pin}")
	public void updateAccount(HoldingCommand rv_num2);
	
	
}
