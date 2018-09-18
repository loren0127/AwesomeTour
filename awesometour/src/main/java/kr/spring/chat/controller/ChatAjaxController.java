package kr.spring.chat.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;

@Controller
public class ChatAjaxController {
	
	@Resource
	private MemberService memberService;
	
	@RequestMapping("/chat/chatEmailChecked.do")
	@ResponseBody
	public Map<String, Object> chatEmailChecked(String member_email) {
		MemberCommand member = memberService.seleccheckEmail(member_email);
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if(member != null) {
			jsonMap.put("result", "EmailDuplicated");
		} else {
			jsonMap.put("result", "EmailNotFound");
		}
		System.out.println("jsonMap : " + jsonMap);
		return jsonMap;
	}

}
