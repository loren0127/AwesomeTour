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
	
	
	//오후 5:50:00에 호출되는 스케쥴러
	@Scheduled(cron = "00 30 * * * *")
	public void cronTest1() {
		List<HoldingCommand> list2 = adminService.selectRvnum();
		for(HoldingCommand rv_num: list2){
			adminService.updateDeposit(rv_num);
		}
	}
	
	
}
