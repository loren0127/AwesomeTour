package kr.spring.reservation.controller;



import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.accom.domain.HotelDetailCommand;
import kr.spring.accom.service.AccomDetailService;
import kr.spring.admin.domain.AccountCommand;
import kr.spring.admin.domain.HoldingCommand;
import kr.spring.admin.service.AdminService;
import kr.spring.chat.domain.ChatAllCommand;
import kr.spring.chat.domain.ChatMemberCommand;
import kr.spring.chat.service.ChatService;
import kr.spring.group.domain.GroupCommand;
import kr.spring.group.service.GroupService;
import kr.spring.reservation.domain.PaymentCommand;
import kr.spring.reservation.domain.ReservationCommand;
import kr.spring.reservation.service.ReservationService;


@Controller
public class ReservationController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	private JavaMailSender mailSender;
	@Resource
	private ReservationService reservationService;
	@Resource
	private GroupService groupService;
	@Resource
	private ChatService chatService;
	@Resource
	private AdminService adminService;
	@Resource
	private AccomDetailService accomDetailService;
	 
	
	//자바용 , 찍기
	public static String toNumFormat(int num) {
		  DecimalFormat df = new DecimalFormat("#,###");
		  return df.format(num);
		 }
	
	//예약 정보 확인
	@RequestMapping("/reservation/confirm.do")
	public String confirm(@RequestParam("im_ac_num") int acc_num,@RequestParam("check_in") String check_in,
						  @RequestParam("check_out") String check_out,@RequestParam("people_count") int people_count,
						  @RequestParam("ro_room_num") int ro_room_num,HttpSession session) {
		
		int reCount = reservationService.selectGradeCount(acc_num);
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("acc_num", acc_num);
		map.put("ro_room_num",ro_room_num);
		map.put("reCount",reCount);
		int ro_num = reservationService.selectRoNum(map);
		if(log.isDebugEnabled()) {
			log.debug("<<ro_num>> : "+ro_num);
		}
		map.put("ro_num",ro_num);

		int count = reservationService.selectReservationCount(acc_num);
		ReservationCommand reservationCommand = reservationService.selectRerservationAcc(map);
		reservationCommand.setAcc_num(acc_num);
		reservationCommand.setRo_num(ro_num);
		reservationCommand.setRv_people(people_count);
		reservationCommand.setRv_start_date(check_in);
		reservationCommand.setRv_end_date(check_out);
		reservationCommand.setHost_email(reservationCommand.getAcc_host());
		if(log.isDebugEnabled()) {
			log.debug("<<rv>> : "+reservationCommand);
			log.debug("<<count>> : "+count);

		}

		
		session.setAttribute("rv",reservationCommand); 
		session.setAttribute("count",count); 

		return "reservationConfirm";
	}

	//결제 정보 입력
	@RequestMapping(value="/reservation/payment.do" ,method=RequestMethod.POST)
	public String payment(@RequestParam("acc_num") int acc_num,@RequestParam("rv_money") int rv_money,
						@RequestParam(value="rv_request", defaultValue="") String rv_request, Model model,HttpSession session) {

		String host_nick = reservationService.selectHostNick(acc_num);
		PaymentCommand pmCommand = new PaymentCommand();
		
		if(log.isDebugEnabled()) {
			log.debug("<<host_nick>> : "+host_nick);
			log.debug("<<rv_request>> : "+rv_request);
		}
		
		ReservationCommand reservationCommand = (ReservationCommand) session.getAttribute("rv");
		reservationCommand.setRv_money(rv_money);
		reservationCommand.setRv_request(rv_request);

		if(log.isDebugEnabled()) {
			log.debug("<<rv>> : "+(ReservationCommand) session.getAttribute("rv"));
		}
		
		model.addAttribute("pmCommand", pmCommand);
		model.addAttribute("host_nick", host_nick);

		return "reservationPayment";
	}
	
	
	//예약 처리
	@RequestMapping(value="/reservation/result.do" ,method=RequestMethod.POST)
	public String result(@ModelAttribute("pmCommand") PaymentCommand paymentCommand,
						HttpSession session,Model model) {
	
		String email = (String) session.getAttribute("user_email");
		ReservationCommand reservationCommand = (ReservationCommand) session.getAttribute("rv");
		reservationCommand.setRv_message(paymentCommand.getRv_message());
		reservationCommand.setMember_email(email);
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("payCommand", paymentCommand);
		map.put("reservationCommand", reservationCommand);
		if(log.isDebugEnabled()) {
			log.debug("<<reservationCommand>> : "+reservationCommand);
			log.debug("<<paymentCommand>> : "+paymentCommand);

		}
		
		if(paymentCommand.getPm_type()=='c') {
			reservationCommand.setRv_status(1);
		}

		//예약 처리
		reservationService.insertReservationSet(map);
		
		
		
		//--------------예약 그룹에 추가----------
		
		
		//이름생성
 		////////////////////////
 		String todayYY = reservationCommand.getRv_start_date().substring(0, 4);
 		String todayMM = reservationCommand.getRv_start_date().substring(5, 7);
 		String todayDD = reservationCommand.getRv_start_date().substring(8, 10);
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

 		 
 	   
 		////////////////
 		
 		String g_name =reservationCommand.getAcc_name()+" "+(month+1)+"월 "+this_week+"주 예약자"; 
 		//reservationCommand.getName() 

 		//이름으로 조회
 		Map<String,Object> gMap = new HashMap<String, Object>();
 		gMap.put("status", 0);
 		gMap.put("g_name", g_name);
 		int count = reservationService.selectReservationGroup(gMap);
		gMap.put("status", 1);
		map.put("reservationCommand", reservationCommand);
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : "+count);
		}
		
 		//그룹 없을 때 그룹 생성
		if(count==0 ){
			//종료일 생성
			GroupCommand groupCommand = new GroupCommand();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	 		Calendar cal = Calendar.getInstance();
	 		cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
	 		to_day.add(Calendar.DATE, 7);
	 		String close_date = formatter.format(to_day.getTime());
	 		
	 		//이미지 가져오기
	 		Map<String, Object> mapImage = new HashMap<String, Object>(); 
	 		mapImage.put("im_ac_num", reservationCommand.getAcc_num());
	 		mapImage.put("ro_room_num", reservationCommand.getRo_room_num());
			HotelDetailCommand image = accomDetailService.selectHotelImage(mapImage);
			if(log.isDebugEnabled()) {
				log.debug("<<image>> : "+image);
			}
	
			//그룹 생성
			groupCommand.setG_close_date(close_date);
			groupCommand.setG_explain(g_name+" 위한 그룹입니다!");
			groupCommand.setG_image(image.getIm_cover());
			groupCommand.setG_name(g_name);
			groupCommand.setG_imageName(image.getIm_cover_name());
			groupCommand.setG_isPrivate(1);
			groupCommand.setG_isSearch(1);
			groupCommand.setMember_email(reservationCommand.getHost_email());
			groupCommand.setG_address1(reservationCommand.getAcc_address1());
			groupCommand.setG_address2(reservationCommand.getAcc_address2());
			groupCommand.setG_hobby("예약전용");
			if(log.isDebugEnabled()) {
				log.debug("<<groupCommand>> : "+groupCommand);
			}
			groupService.insertGroup(groupCommand);
			
		
			//채팅 생성
			ChatAllCommand command = new ChatAllCommand();
			command.setChat_all_title(g_name);
			command.setChat_all_member_list(email);
			int g_num = reservationService.selectReservationGroup(gMap);
			command.setGroup_num(g_num);
			command.setChat_all_member_max(100);
			chatService.insertChatAllGroup(command);
			
			
			//호스트 삽입
			ChatMemberCommand hostCommand = new ChatMemberCommand();
			hostCommand.setMember_email(reservationCommand.getHost_email());
			hostCommand.setChat_all_num_member(groupService.selectGroupChatnum(reservationService.selectReservationGroup(gMap)));
			Map<String,Object> m_map = new HashMap<String, Object>();
			m_map.put("member_email", email);
			m_map.put("chat_all_num", hostCommand.getChat_all_num_member());
			if(reservationService.selectGroupMemberCount(m_map)==0) {		
			chatService.insertChatMember(hostCommand);}
		
		}
		

		
		//멤버 삽입
		ChatMemberCommand memberCommand = new ChatMemberCommand();
		memberCommand.setMember_email(email);
		memberCommand.setChat_all_num_member(groupService.selectGroupChatnum(reservationService.selectReservationGroup(gMap)));
		Map<String,Object> m_map = new HashMap<String, Object>();
		m_map.put("member_email", email);
		m_map.put("chat_all_num", memberCommand.getChat_all_num_member());
		if(reservationService.selectGroupMemberCount(m_map)==0)		
		chatService.insertChatMember(memberCommand);

		
		//----------------------------------------------
		
		
		
		//-------------------이메일 보내기----------------
		  MimeMessage message = mailSender.createMimeMessage();
	      MimeMessageHelper messageHelper;
		try {
			messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	      String setfrom = "AwesomeTour@noreply.com";         
	      
	      messageHelper.setFrom(setfrom); 
	      messageHelper.setTo(paymentCommand.getPm_email());
	      
	      messageHelper.setSubject("Awesome Tour 예약완료 되었습니다."); 
	      String appandEmail ="";
	      appandEmail +="<table style='PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px' cellspacing='0' cellpadding='0' width='672' border='0'> <!-- 헤더 --> <tbody><tr><td style='PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px'><a href='http://192.168.10.41:8080/awesometour/index.jsp' name='ANCHOR14311' target='_blank' rel='noreferrer noopener'> <img style='BORDER-TOP: 0px; BORDER-RIGHT: 0px; VERTICAL-ALIGN: top; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px' alt='AwesomTour!' src='http://i1.ruliweb.com/img/18/09/18/165ec1d1cbcf9e2b.png'></a> </td></tr><!-- //헤더 --> <!-- 컨텐츠 --><tr><td style='BORDER-RIGHT: #e4e4e4 1px solid; PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; BORDER-LEFT: #e4e4e4 1px solid; MARGIN: 0px; PADDING-RIGHT: 0px'> <table style='PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px' cellspacing='0' cellpadding='0' width='670' border='0'> <tbody><tr><td style='FONT-SIZE: 0px; LINE-HEIGHT: 0' height='49' colspan='3'>&nbsp;</td></tr><tr><td style='FONT-SIZE: 0px; LINE-HEIGHT: 0' width='53'>&nbsp;</td><td width='564'> <table style='PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px' cellspacing='0' cellpadding='0' width='564' border='0'> <tbody><tr><td> <table style='PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px' cellspacing='0' cellpadding='0' width='564' border='0'> <tbody><tr><td style='HEIGHT: 20px'> <span style='color:gray'> Awesome Tour에서 알려드립니다. </span> </td></tr><tr><td> <h1>고객님의 예약이 완료 되었습니다</h1></td></tr><tr><td style='FONT-SIZE: 0px; LINE-HEIGHT: 0' height='27'>&nbsp;</td></tr><tr><td> <table style='PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px' cellspacing='0' cellpadding='0' width='564' border='0'> <tbody><tr><td style='COLOR: #666; FONT: 12px/20px dotum'> <strong style='FONT-WEIGHT: bold; COLOR: #333'>안녕하세요, 고객님</strong><br>저희 어썸투어에서 고객님의 예약정보를 알려드리겠습니다. <br><br><h2 style='margin: 10 0 5;'>예약 정보</h2><hr noshade align='left' style='width:75%' size='1px'>";
	
	      appandEmail +="예약 숙소 : "+reservationCommand.getAcc_name()+"<br>";
	      appandEmail +="예약 일시 : "+reservationCommand.getRv_start_date()+"~"+reservationCommand.getRv_end_date()+"<br>";
	      appandEmail +="예약 이메일 : "+email+"<br>";
	      
	      appandEmail +="<br><h2 style='margin: 10 0 5;'>결제 정보</h2>";
	      appandEmail +="<hr noshade align='left' style='width:75%' size='1px'>결제 수단 : ";
	      if(paymentCommand.getPm_type()==0){
	    	  appandEmail +="신용카드<br>";
	      }else {
	    	  appandEmail +="무통장 입금<br>";

	      }
	      appandEmail +="결제 금액 : "+toNumFormat(reservationCommand.getRv_money())+"원<br>";
	      appandEmail +="<br>이상 예약이 완료되었음을 알려드립니다.";

	      appandEmail +="<!-- 받는 부분 --> </td></tr><tr><td style='FONT-SIZE: 0px; LINE-HEIGHT: 0' height='30'>&nbsp;</td></tr> <tr> <td style='TEXT-ALIGN: center'> <div style='margin: 0 auto;padding: 0;max-width: 760px;clear: both;line-height: 1.8;'> <br> <a href='http://192.168.10.41:8080/awesometour/index.jsp' style='background: #ce3176;border: 1px solid #ce3176;color: #fff;cursor: pointer;text-decoration: none;border-radius: 100px; font-size: 16px;padding: 8px 16px;font-weight: 600;' target='_blank'> 홈페이지 바로가기 </a></div> </tr></tbody> </table> </td></tr><tr><td style='FONT-SIZE: 0px; LINE-HEIGHT: 0' height='60'>&nbsp;</td></tr></tbody> </table> </td></tr></tbody> </table> </td><td style='FONT-SIZE: 0px; LINE-HEIGHT: 0' width='53'>&nbsp;</td></tr></tbody> </table> </td></tr><tr><td> <table cellspacing='0' cellpadding='0' width='672' border='0'> <tbody><tr><td style='WIDTH: 155px'><a href='http://192.168.10.41:8080/awesometour/index.jsp' name='ANCHOR14314' target='_blank' rel='noreferrer noopener'> <img style='BORDER-TOP: 0px; BORDER-RIGHT: 0px; VERTICAL-ALIGN: top; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px' alt='GRAVITY' src='http://i3.ruliweb.com/img/18/09/20/165f2923892f9e2b.jpg'></a></td><td style='WIDTH: 517px'> <table cellspacing='0' cellpadding='0' width='517' border='0'> <tbody><tr><td style='FONT-SIZE: 12px; LINE-HEIGHT: 12px'> <img style='BORDER-TOP: 0px; BORDER-RIGHT: 0px; VERTICAL-ALIGN: top; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px' alt='㈜그라비티 대표이사 : 박현철 서울특별시 마포구 월드컵북로 396, 15층(상암동, 누리꿈스퀘어 연구개발타워)' src='http://i1.ruliweb.com/img/18/09/20/165f273122ef9e2b.png'><br> <img style='BORDER-TOP: 0px; BORDER-RIGHT: 0px; VERTICAL-ALIGN: top; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px' alt='전화문의 :1588-9897 팩스 : 02-2132-7077 사업자등록번호 : 201 -81-56197' src='http://imgc.gnjoy.com/gnjoy/2012_gnjoy/dm/images/img_dm_footer03.gif'><br> <img style='BORDER-TOP: 0px; BORDER-RIGHT: 0px; VERTICAL-ALIGN: top; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px' alt='통신판매업 신고번호 : 제 2008-서울마포-0207호 고객지원센터 : 서울특별시 마포구 월드컵북로 396, 15층(상암동, 누리꿈스퀘어 연구개발타워)' src='http://imgc.gnjoy.com/gnjoy/2012_gnjoy/dm/images/img_dm_footer04_150207.gif'><br> <img style='BORDER-TOP: 0px; BORDER-RIGHT: 0px; VERTICAL-ALIGN: top; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px' alt='Copyright (c) GRAVITY Co., Ltd. All Rights Reserved.' src='http://i1.ruliweb.com/img/18/09/20/165f2731386f9e2b.png'><br> </td></tr></tbody> </table> </td></tr></tbody> </table> </td></tr></tbody> </table> </td></tr><!-- //풋터 --></tbody> </table>";
	      messageHelper.setText(appandEmail,true);  
	     
	      
	      mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
		    System.out.println(e);
		}
		//----------------------------------------------
		
		//홀딩
		 
		if(paymentCommand.getPm_type()=='b') {
			
			HoldingCommand hold = new HoldingCommand();
			hold.setAcc_num(reservationCommand.getAcc_num());
			hold.setHd_money(reservationCommand.getRv_money());
			hold.setHd_account(paymentCommand.getPm_deposit_ac());
			hold.setHd_deposit(0);
			adminService.insertHolding(hold);
			//입금----------------------------------
			//시연을 위에서 삽입
			AccountCommand act = new AccountCommand();
			act.setAt_pin(paymentCommand.getPm_deposit_ac());
			act.setAt_name("입금자 입금");
			act.setAt_money(reservationCommand.getRv_money());
			act.setAt_depositor(paymentCommand.getPm_depositor());
			adminService.insertAccount(act);
			//---------------------------------------------------

		}else {
			//카드 대금 홀딩
			HoldingCommand hold = new HoldingCommand();
			hold.setAcc_num(reservationCommand.getAcc_num());
			hold.setHd_money(reservationCommand.getRv_money());
			hold.setHd_account("card");
			hold.setHd_deposit(1);
			adminService.insertHolding(hold);

		}
		
		//슈퍼 호스트 업데이트
		Map<String,Object> upMap = new HashMap<String,Object>();
		upMap.put("re_acc_num", reservationCommand.getAcc_num());
		accomDetailService.updateSuperHost(upMap);
		
		
		model.addAttribute("rv",session.getAttribute("rv"));
		session.removeAttribute("rv");
		session.removeAttribute("count");

		
		return "reservationResult";
	}
	
	//테스트용
	@RequestMapping("/reservation/test.do")

	public String confirm() {
			
		return "reservationResult";
					}

}
