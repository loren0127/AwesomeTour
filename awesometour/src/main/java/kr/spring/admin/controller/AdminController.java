package kr.spring.admin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.spring.admin.domain.HoldingCommand;
import kr.spring.admin.service.AdminService;

@Controller
public class AdminController {
	
	@Resource
	private AdminService adminService;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	
	//관리자 페이지 호출
	@RequestMapping("/admin/adminMainForm.do")
	public String adminForm() {
		return "adminMainForm";
	}
	
	//홀딩 페이지
	@RequestMapping("/admin/hold.do")
	public String holdForm(Model model) {
		
		List<HoldingCommand> list = adminService.selectHolding();
		
		List<HoldingCommand> list2 = adminService.selecthost();
		
		List<HoldingCommand> list3 = adminService.selectAccount();
		if(log.isDebugEnabled()) {
			log.debug("<<holdingCommand>> : " + list);
		}
		model.addAttribute("list",list);
		
		
		for(HoldingCommand rv_num : list2) {
			adminService.updateHostAccount(rv_num);
		}
		if(log.isDebugEnabled()) {
			log.debug("<<updateHostAccount>> : " + list2);
		}
		
		
		
		/*for(HoldingCommand rv_num3 : list3) {
			adminService.updateAccount(rv_num3);
		}*/
		if(log.isDebugEnabled()) {
			log.debug("<<updateHostAccount>> : " + list3);
		}
		model.addAttribute("list3",list3);
		
		
		
		return "adminHolding";
	}
	
	/*//deposit 변경하는 메서드
	@Scheduled(cron = "0/10 * * * * *")
	public void cronTest1() {
		List<HoldingCommand> list2 = adminService.selectRvnum();
		for(HoldingCommand rv_num: list2){
			adminService.updateDeposit(rv_num);
		}
		System.out.println("!@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
	}*/
	
	
}
