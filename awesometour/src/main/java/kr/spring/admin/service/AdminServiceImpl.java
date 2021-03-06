package kr.spring.admin.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.admin.dao.AdminMapper;
import kr.spring.admin.domain.AccountCommand;
import kr.spring.admin.domain.HoldingCommand;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	
	@Resource
	private AdminMapper adminMapper;

	@Override
	public List<HoldingCommand> selectHolding() {
		return adminMapper.selectHolding();
	}

	@Override
	public List<HoldingCommand> selectRvnum() {
		return adminMapper.selectRvnum();
	}

	@Override
	public void updateDeposit(HoldingCommand rv_num) {
		adminMapper.updateDeposit(rv_num);
	}

	@Override
	public List<HoldingCommand> selecthost() {
		return adminMapper.selecthost();
	}

	@Override
	public void updateHostAccount(HoldingCommand rv_num) {
		adminMapper.updateHostAccount(rv_num);
	}

	@Override
	public List<HoldingCommand> selectAccount() {
		return adminMapper.selectAccount();
	}

	@Override
	public void updateAccount(HoldingCommand rv_num) {
		adminMapper.updateAccount(rv_num);
	}

	@Override
	public List<HoldingCommand> selecthrv_num() {
		return adminMapper.selecthrv_num();
	}

	@Override
	public void deleteDeposit(HoldingCommand holdingCommand) {
		adminMapper.deleteDeposit(holdingCommand);
	}

	@Override
	public List<HoldingCommand> selectComplain() {
		return adminMapper.selectComplain();
	}

	@Override
	public List<HoldingCommand> selectComplain_email(HoldingCommand holdingCommand) {
		return adminMapper.selectComplain_email(holdingCommand);
	}

	@Override
	public void updateComplain_auth(HoldingCommand holdingCommand) {
		adminMapper.updateComplain_auth(holdingCommand);
	}

	@Override
	public void updateHd_date(HoldingCommand holdingCommand) {
		adminMapper.updateHd_date(holdingCommand);
	}

	@Override
	public void deleteComplain(HoldingCommand holdingCommand) {
		adminMapper.deleteComplain(holdingCommand);
	}

	@Override
	public void updateHd_date2() {
		adminMapper.updateHd_date2();
	}

	@Override

	public List<HoldingCommand> selectGroup_gnum() {
		return adminMapper.selectGroup_gnum();
	}

	@Override
	public void delete_gnum(HoldingCommand holdingCommand) {
		adminMapper.delete_gnum(holdingCommand);
	}


	public void insertHolding(HoldingCommand holdingCommand) {
		// TODO Auto-generated method stub
		adminMapper.insertHolding(holdingCommand);
	}

	@Override
	public void insertAccount(AccountCommand accountCommand) {
		// TODO Auto-generated method stub
		adminMapper.insertAccount(accountCommand);
	}










	








}
