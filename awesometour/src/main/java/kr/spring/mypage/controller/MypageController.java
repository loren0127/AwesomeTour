package kr.spring.mypage.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.accom.domain.HotelDetailCommand;
import kr.spring.chat.domain.ChatAllCommand;
import kr.spring.chat.service.ChatService;
import kr.spring.group.domain.GroupCommand;
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
	
	@Resource
	private ChatService chatService;
	
	@ModelAttribute("mypageCommand")
	public MyPageCommand mypageCommandInit() {
		return new MyPageCommand();
	}
	
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
	public String mypageComplainDetail(@RequestParam("num")int num, Model model) {
		
		MyPageCommand mypageCommand = mypageService.select_complain(num);
		System.out.println("mypageCommand :: " + mypageCommand.getAcc_name());
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
		
		reservationAccMap.put("user_email", user_email);
		int checkedComplain = mypageService.selectComplainSelectCount(reservationAccMap);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypageReservationDetail");
		mav.addObject("rv", reservationDetail);
		mav.addObject("checkedComplain", checkedComplain);
		return mav;
	}
	
	@RequestMapping(value="/mypage/mypageComplainSend.do", method=RequestMethod.POST)
	public ModelAndView mypageComplainSendForm(HttpSession session, @ModelAttribute @Valid MyPageCommand complainCommand) {
		String user_email = (String)session.getAttribute("user_email");
		complainCommand.setMember_email(user_email);
		System.out.println("complainCommand : " + complainCommand);
		Map<String, Object> checkMap = new HashMap<String, Object>();
		checkMap.put("rv_num", complainCommand.getRv_num());
		checkMap.put("acc_num", complainCommand.getAcc_num());
		checkMap.put("user_email", user_email);
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("mypageReservationList");
		if(mypageService.selectComplainSelectCount(checkMap) > 0) {
			System.out.println("중복 발생이오!");
			mav.addObject("result", "fail");
			return mav;
		}
		
		mypageService.insertComplainSend(complainCommand);
		
		return mav;
	}
	
	//Reservation delete
	@RequestMapping(value="/mypage/mypageReservationCancel.do")
	public ModelAndView mypageReservationCancel(HttpSession session, @RequestParam("rv_start_date")String rv_start_date, @RequestParam("acc_name")String acc_name, @RequestParam("rv_num")String rv_num) {
		String user_email = (String)session.getAttribute("user_email");
		//이름생성
 		////////////////////////
 		String todayYY = rv_start_date.substring(0, 4);
 		String todayMM = rv_start_date.substring(5, 7);
 		String todayDD = rv_start_date.substring(8, 10);
 		
 		// 오늘 일자를 받아서 int 형으로 치환 한다.
 		int year = Integer.parseInt(todayYY);
 		int month = Integer.parseInt(todayMM)-1;   // 월은 0 부터 시작이기 때문에 -1 을 해준다.
 		int day = Integer.parseInt(todayDD);
 		
 		int monday = 0;
 		int friday = 0;

 		// calendar 선언.
 		Calendar to_day = Calendar.getInstance();
 		//오늘 일자를 setup 한다.
 		to_day.set(year, month, day);
 		// 오늘을 기준으로 해당 주의 월요일 과 금요일을 구한다.
 		int today_week =  to_day.get(Calendar.DAY_OF_WEEK); // 오늘이 무슨 요일인지 int 형으로 반환.

 		if(today_week == Calendar.MONDAY) // 먼저 월요일을 구하기 위해 오늘이 월요일인지 체크 한다.
 		{
 		    monday = day;
 		    friday = day + (Calendar.FRIDAY - Calendar.MONDAY); 
 		    // 오늘이 월요일이면 오늘 일자에  금요일과 월요일의 일차수를 뺀 만큼 더하면 금요일 일자가 나온다.
 		}
 		else if(today_week == Calendar.FRIDAY) // 오늘 일자가 금요일인지 체크 한다.
 		{
 		    // 오늘이 금요일이면 오늘 일자에  금요일과 월요일의 일차수를 뺀 만큼 빼면 월요일 일자가 나온다.
 		    monday = day - (Calendar.FRIDAY - Calendar.MONDAY);
 		    friday = day;
 		}
 		else if(today_week == Calendar.SUNDAY)
 		{
 		    // today_week 가 일요일 이면 오늘 일자에 sunday(1)를 더하여 monday 일자를 구함.
 		    monday = day + Calendar.SUNDAY; 
 		    friday = day + (Calendar.FRIDAY - Calendar.SUNDAY);
 		    // 오늘이 일요일이면 friday(6) 에 sunday(1)을 뺀 만큼 더하여 금요일 일자를 구함.
 		}
 		else if(today_week == Calendar.SATURDAY)
 		{
 		    // 오늘이 토요일이면 saturday(7) 에서 monday(2)를 뺀 후 오늘일자에서 뺀 뒤 월요일 일자를 구함.
 		    monday = day - (Calendar.SATURDAY - Calendar.MONDAY);
 		    // 오늘이 토요일이면 saturday(7) 에서 friday(6)을 뺀 후 오늘일자에서 뺀 뒤 금요일 일자를 구함.
 		    friday = day - (Calendar.SATURDAY - Calendar.FRIDAY);    
 		}
 		else  // 화 , 수 , 목 일 때
 		{
 		    // 오늘 일자 표시 에서 monday(2) 를 뺀 후 오늘 일자에서 해당 일자를 뺀다.
 		    monday = day - (today_week - Calendar.MONDAY);
 		    // friday(6) 에서 오늘 일자 표시를 뺀 후 오늘일자에 해당 일자를 더한다. 
 		    friday = day + (Calendar.FRIDAY - today_week);
 		}

 		//이번주의 주차를 구한다.
 		//주차는 월요일을 기준으fw로 해당 달의 주차를 표시 한다.
 		to_day = Calendar.getInstance(); // to_day 를 초기화 한다.
 		to_day.set(year, month, monday); // 월요일에 해당하는 주차 구하기 위해 월요일 일자를 입력.
 		int this_week =  to_day.get(Calendar.WEEK_OF_MONTH); // 이번 주의 월요일에 해당하는 주차를 가져온다.
 		
 		String g_name =acc_name+" "+(month+1)+"월 "+this_week+"주 예약자"; 
 		//reservationCommand.getName() 

 		//이름으로 조회
 		Map<String,Object> gMap = new HashMap<String, Object>();
 		gMap.put("status", 0);
 		gMap.put("g_name", g_name);
 		int count = reservationService.selectReservationGroup(gMap);
		gMap.put("status", 1);
		
		ModelAndView mav = new ModelAndView();
		
 		//그룹 없을 때 그룹 생성
		if(count==0) {
			mav.setViewName("mypageReservationList");
			return mav;
		}
		int g_num = reservationService.selectReservationGroup(gMap);
		System.out.println("g_num :: " + g_num);
		
		Map<String, Object> recycleMap = new HashMap<String, Object>();
		recycleMap.put("g_num", g_num);
		recycleMap.put("user_email", user_email);
		
		//Delete group chat list
		chatService.deleteChatMember(recycleMap);
		
		recycleMap.clear();
		
		recycleMap.put("user_email", user_email);
		recycleMap.put("rv_num", rv_num);
		
		//rv_num, member_email
		mypageService.updateHolding(recycleMap);
		mypageService.deleteHolding(recycleMap);
		
		//Reservation status setting(-1)
		//mypageService.
		
		mav.setViewName("mypageReservationList");
		return mav;
	}
}
