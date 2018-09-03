package kr.spring.member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.dao.MemberMapper;
import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;

@Controller
public class PasswordModifyConfirmAjaxController {
	private Logger log = Logger.getLogger(this.getClass());
	
	
	@Resource
	private MemberService memberService;
	@RequestMapping("/member/confirmPasswd.do")
	@ResponseBody
	public Map<String,String> process(HttpServletRequest request){
		
		
		String member_email = request.getParameter("member_email");
		String member_passwd = request.getParameter("member_passwd");
		
		MemberCommand memberCommand = new MemberCommand();
		
		memberCommand.setMember_email(member_email);
		memberCommand.setMember_passwd(member_passwd);
		
		
		if(log.isDebugEnabled()) {
			log.debug("<<member_email>> : " + member_email);
		}
		
		
		Map<String,String> map = new HashMap<String,String>();
		
		MemberCommand member = memberService.selectCheckPw(memberCommand);
		
		if(member!=null) {
			map.put("result", "PassDuplicated");
		}else {
			map.put("result", "PassNotFound");
		}
		
		return map;
	}
}
