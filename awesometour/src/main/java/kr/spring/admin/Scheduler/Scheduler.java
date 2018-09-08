package kr.spring.admin.Scheduler;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.spring.admin.domain.HoldingCommand;
import kr.spring.admin.service.AdminService;

@Component
public class Scheduler {
	@Resource
	private AdminService adminService;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	//@Scheduled(cron = "0 38 16  * * *")
	//컴플레인 해제
	@Scheduled(cron = "0 36 10  * * *")
	public void complain_delete() {
		adminService.updateHd_date2();
	}
	
	//오후 에 호출되는 스케쥴러
	//deposit 1로 변경
	//@Scheduled(cron = "0 38 16  * * *")
	@Scheduled(cron = "0 36 10  * * *")
	public void hd_Deposit() {
		List<HoldingCommand> list2 = adminService.selectRvnum();
		for(HoldingCommand rv_num: list2){
			adminService.updateDeposit(rv_num);
		}
		
		
	}
	
	
	//오후 에 호출되는 스케쥴러
	//돈 입금해주는 스케쥴러
	@Scheduled(cron = "0 43 10 * * *")
	public void account_Change() {
		
		List<HoldingCommand> list2 = adminService.selecthost();
		
		for(HoldingCommand rv_num2 : list2) {
			adminService.updateHostAccount(rv_num2);
			System.out.println("----------------------------------2번 작동");
		}
		if(log.isDebugEnabled()) {
			log.debug("<<updateHostAccount>> : " + list2);
		}
		
		//-------------------------------------------------------------
		
		List<HoldingCommand> list3 = adminService.selectAccount();
		
		for(HoldingCommand rv_num3 : list3) {
			adminService.updateAccount(rv_num3);
			System.out.println("----------------------------------3번 작동");
		}
		if(log.isDebugEnabled()) {
			log.debug("<<updateHostAccount>> : " + list3);
		}
		
		//-----------------------------------------------------------
		
		List<HoldingCommand> list4 = adminService.selecthrv_num();
		
		for(HoldingCommand rv_num4 : list4) {
			adminService.deleteDeposit(rv_num4);
			System.out.println("----------------------------------4번 작동");
		}
		if(log.isDebugEnabled()) {
			log.debug("<<deleteDeposit>> : " + list4);
		}
	
	
	}
	//컴플레인
	@Scheduled(cron = "0 43 10 * * *")
	public void account_Change2() {
		List<HoldingCommand> list2 = adminService.selectComplain();
		
		for(HoldingCommand rv_num4 : list2) {
			List<HoldingCommand> list3 = adminService.selectComplain_email(rv_num4);
			for(HoldingCommand rv_num5 : list3) {
				adminService.updateComplain_auth(rv_num5);
			}
			adminService.updateHd_date(rv_num4);
			adminService.deleteComplain(rv_num4);
		}
	}
	

	
		
}
