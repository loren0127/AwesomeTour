package kr.spring.mypage.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;
import kr.spring.mypage.domain.MyPageCommand;
import kr.spring.mypage.service.MyPageService;
import kr.spring.reservation.domain.ReservationCommand;
import kr.spring.reservation.service.ReservationService;
import kr.spring.util.PagingUtil;

@Controller
public class MypageController {
	private int rowCount = 5;
	private int pageCount = 10;
	
	@Resource
	private MemberService memberService;
	
	@Resource
	private MyPageService mypageService;
	
	@Resource
	private ReservationService reservationService;
	
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
		mav.addObject("member", memberCommand);
		
		return mav;
	}
	
	@RequestMapping(value="/mypage/mypageReservationList.do", method=RequestMethod.GET)
	public ModelAndView mypageReservationList(HttpSession session, @RequestParam(value="pageNum",defaultValue="1")int currentPage) {
		String user_email = (String)session.getAttribute("user_email");
		int count = mypageService.selectReservationRowCount(user_email);
		int countOld = mypageService.selectReservationRowCountOld(user_email);
		System.out.println("count :: " + count + " / countOld :: " + countOld);
		PagingUtil pageUtil = new PagingUtil(currentPage, countOld, rowCount, pageCount, "mypageReservationList.do");
		
		Map<String, Object> reservationMap = new HashMap<String, Object>();
		reservationMap.put("user_email", user_email);
		reservationMap.put("start", pageUtil.getStartCount());
		reservationMap.put("end", pageUtil.getEndCount());
		List<ReservationCommand> reservationList = null;
		ModelAndView mav = new ModelAndView();
		if(count > 0) {
			reservationList = mypageService.selectReservationList(reservationMap);
			mav.addObject("reservationList", reservationList);
			reservationList = mypageService.selectReservationListOld(reservationMap);
			mav.addObject("reservationListOld", reservationList);
		} else {
			reservationList = Collections.emptyList();
		}
		
		System.out.println(reservationList.isEmpty());
		mav.setViewName("mypageReservationList");
		mav.addObject("countOld", countOld);
		mav.addObject("count", count);
		mav.addObject("pagingHtml", pageUtil.getPagingHtml());
		return mav;
	}
	
	@RequestMapping(value="/mypage/mypageReservationDetail.do")
	public ModelAndView mypageReservationDetail(HttpSession session, @RequestParam("acc_num")int acc_num, @RequestParam("ro_num")int ro_num, @RequestParam("rv_num")int rv_num) {
		int acc_grade_count = mypageService.selectGradeCount(acc_num);
		
		String user_email = (String)session.getAttribute("user_email");
		Map<String, Object> reservationAccMap = new HashMap<String, Object>();
		reservationAccMap.put("acc_grade_count", acc_grade_count);
		reservationAccMap.put("user_email", user_email);
		reservationAccMap.put("acc_num", acc_num);
		reservationAccMap.put("ro_num", ro_num);
		reservationAccMap.put("rv_num", rv_num);
		ReservationCommand reservationDetail = mypageService.selectReservationDetail(reservationAccMap);
		System.out.println("rv :: " + reservationDetail);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypageReservationDetail");
		mav.addObject("rv", reservationDetail);
		return mav;
	}
	
	@RequestMapping(value="/mypage/mypageReservationCancel.do")
	public ModelAndView mypageReservationCancel() {
		
		ModelAndView mav = new ModelAndView();
		return mav;
	}
	
	@RequestMapping(value="/mypage/mypageComplainSend.do", method=RequestMethod.GET)
	public ModelAndView mypageComplainSendForm(HttpSession session, @ModelAttribute(value="mypageCommand")MyPageCommand mypageCommand) {
		System.out.println("Complain insert 진입...");
		ModelAndView mav = new ModelAndView();
		
		return mav;
	}
}
