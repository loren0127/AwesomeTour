package kr.spring.admin.service;

import java.util.List;

import org.apache.ibatis.annotations.Insert;

import kr.spring.admin.domain.AccountCommand;
import kr.spring.admin.domain.HoldingCommand;

public interface AdminService {
	public List<HoldingCommand> selectHolding();
	public List<HoldingCommand> selectRvnum();
	public void updateDeposit(HoldingCommand rv_num);
	public List<HoldingCommand> selecthost();
	public void updateHostAccount(HoldingCommand rv_num);
	public List<HoldingCommand> selectAccount();
	public void updateAccount(HoldingCommand rv_num);
	public List<HoldingCommand> selecthrv_num();
	public void deleteDeposit(HoldingCommand rv_num4);
	public List<HoldingCommand> selectComplain();
	public List<HoldingCommand> selectComplain_email(HoldingCommand holdingCommand);
	public void updateComplain_auth(HoldingCommand holdingCommand);
	public void updateHd_date(HoldingCommand holdingCommand);
	public void deleteComplain(HoldingCommand holdingCommand);
	public void updateHd_date2();

	public List<HoldingCommand> selectGroup_gnum();
	public void delete_gnum(HoldingCommand holdingCommand);

	public void insertHolding(HoldingCommand holdingCommand);
	public void insertAccount(AccountCommand accountCommand);
}
