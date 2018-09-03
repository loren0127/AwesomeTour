package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;

@Controller
public class EmailConfirmAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource 
	private MemberService memberService;
	
	@RequestMapping("/mail/confirmEmail.do")
	@ResponseBody
	public Map<String,String> process(String email){
		
		
		if(log.isDebugEnabled()) {
			log.debug("<<member_email>> : " + email);
		}
		
		Map<String,String> map = new HashMap<String,String>();
		
		
		MemberCommand member = memberService.seleccheckEmail(email);
		
		if(member != null) {
			map.put("result", "EmailDuplicated");
		}else {
			map.put("result", "EmailNotFound");
		}
		
		return map;
		
	}
}
