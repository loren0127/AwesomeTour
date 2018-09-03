package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;

@Controller
public class MemberConfirmAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private MemberService memberService;
	
	@RequestMapping("/member/confirmNickname.do")
	@ResponseBody
	public Map<String,String> process(String member_nickname){
		
		if(log.isDebugEnabled()) {
			log.debug("<<member_nickname>> : " + member_nickname);
		}
		Map<String,String> map = new HashMap<String,String>();
		
		MemberCommand member = memberService.selctNickname(member_nickname);
		
		if(member!=null) {
			map.put("result", "nickDuplicated");
		}else {
			map.put("result", "nickNotFound");
		}
		
		return map;
	}
}
