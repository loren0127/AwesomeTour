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
		
		if(log.isDebugEnabled()) {
			log.debug("<<selectHolding>> : " + list);
		}
		model.addAttribute("list",list);
		return "adminHolding";
	}
	
}
