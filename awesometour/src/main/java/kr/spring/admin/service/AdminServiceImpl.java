package kr.spring.admin.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.spring.admin.dao.AdminMapper;
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








	








}
