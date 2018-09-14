package kr.spring.mypage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;
import kr.spring.mypage.domain.MyPageCommand;
import kr.spring.mypage.service.MyPageService;

@Controller
public class MypageController {
	
	@Resource
	private MemberService memberService;
	
	@Resource
	private MyPageService mypageService;
	
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
	
	
	@RequestMapping(value="/mypage/mypageComplainList.do")
	public String mypageComplainList(HttpSession session,Model model) {
		
		String user_email = (String)session.getAttribute("user_email");
		
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_email", user_email);
		
		
		int count = mypageService.selectRowCount(map);
		
		int more = 1;//다음페이지있음
		if(1>=Math.ceil((double)count/5)) {//자바 스크립트와 다르게 자바는 무조건 int 연산은 int이므로 ceil이 동작하지 않으므로 하나의 값을 double로 만들어서 사용
			more = 0;//다음페이지없음
		}
		model.addAttribute("more",more);
		model.addAttribute("user_email",user_email);
		
		
		return "mypageComplainList";
	}
	
	
	@RequestMapping("/mypage/mypageComplainDetail.do")
	public String mypageComplainDetail(@RequestParam("num")int num,Model model) {
		
		MyPageCommand mypageCommand = mypageService.select_complain(num);
		
		model.addAttribute("mypage",mypageCommand);
		
		return "mypageComplainDetail";
		
	}
}
