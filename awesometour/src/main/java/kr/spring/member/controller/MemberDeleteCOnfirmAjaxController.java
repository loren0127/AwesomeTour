package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.domain.CodeCommand;
import kr.spring.member.service.MemberService;

@Controller
public class MemberDeleteCOnfirmAjaxController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	
	@Resource
	private MemberService memberService;
	@RequestMapping("/member/confirmDeleteMember.do")
	@ResponseBody
	public Map<String,String> process(HttpServletRequest request){
		
		
		String member_email = request.getParameter("code_email");
		String member_code = request.getParameter("code_code");
		
		CodeCommand codeCommand = new CodeCommand();
		
		codeCommand.setCode_email(member_email);
		codeCommand.setCode_code(member_code);
		
		Map<String,String> map = new HashMap<String,String>();
		
		int count = memberService.selectCode(codeCommand);
		if(log.isDebugEnabled()) {
			log.debug("<<member_email>> : " + codeCommand);
		}
		
		System.out.println(count);
		if(count == 1) {
			map.put("result", "CodeDuplicated");
		}else {
			map.put("result", "CodeNotFound");
		}
		
		return map;
		
		
		
	}
	
}
