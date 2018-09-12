package kr.spring.mypage.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;

@Controller
public class MypageController {
	
	@Resource
	private MemberService memberService;
	
	@RequestMapping(value="/mypage/mypageMemberDetail.do")
	public ModelAndView mypageMain(HttpSession session) {
		String user_email = (String)session.getAttribute("user_email");
		
		MemberCommand memberCommand = memberService.selectMemb(user_email);
		
		System.out.println(memberCommand);
		ModelAndView mav = new ModelAndView();
		mav.addObject("memberCommand", memberCommand);
		mav.setViewName("mypageMemberDetail");
		return mav;
	}
}
