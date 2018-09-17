package kr.spring.mypage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@RequestMapping(value="/mypage/mypageComplainList.do")
	public String mypageComplainList(HttpSession session,Model model) {
		
		String user_email = (String)session.getAttribute("user_email");
		
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_email", user_email);
		
		
		int count = mypageService.selectRowCount(map);
		
		int more = 1;//�떎�쓬�럹�씠吏��엳�쓬
		if(1>=Math.ceil((double)count/5)) {//�옄諛� �뒪�겕由쏀듃�� �떎瑜닿쾶 �옄諛붾뒗 臾댁“嫄� int �뿰�궛�� int�씠誘�濡� ceil�씠 �룞�옉�븯吏� �븡�쑝誘�濡� �븯�굹�쓽 媛믪쓣 double濡� 留뚮뱾�뼱�꽌 �궗�슜
			more = 0;//�떎�쓬�럹�씠吏��뾾�쓬
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
	
	
	@RequestMapping(value="/mypage/mypageMemberDetail.do", method=RequestMethod.GET)
	public ModelAndView selectMypageMemberDetail(HttpSession session) {
		String user_email = (String)session.getAttribute("user_email");
		MemberCommand memberCommand = memberService.selectMemb(user_email);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypageMemberDetail");
		mav.addObject("memberCommand", memberCommand);
		
		return mav;
	}
	
	@RequestMapping(value="/mypage/mypageReservationList.do", method=RequestMethod.GET)
	public ModelAndView mypageReservationList() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypageReservationList");
		return mav;
	}
}
