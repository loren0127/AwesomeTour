package kr.spring.reservation.controller;



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
	@RequestMapping("/reservation/confirm.do")
	public String confirm(@RequestParam("im_ac_num") int acc_num,@RequestParam("check_in") String check_in,
						  @RequestParam("check_out") String check_out,@RequestParam("people_count") int people_count,
						  @RequestParam("ro_room_num") int ro_num,HttpSession session) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("acc_num", acc_num);
		map.put("ro_num",ro_num);
		int count = reservationService.selectReservationCount(acc_num);
		ReservationCommand reservationCommand = reservationService.selectRerservationAcc(map);
		reservationCommand.setAcc_num(acc_num);
		reservationCommand.setRo_num(ro_num);
		reservationCommand.setRv_people(people_count);
		reservationCommand.setRv_startdate(check_in);
		reservationCommand.setRv_enddate(check_out);
		reservationCommand.setRo_num(ro_num);
		reservationCommand.setHost_email(reservationCommand.getAcc_host());
		if(log.isDebugEnabled()) {
			log.debug("<<rv>> : "+reservationCommand);
			log.debug("<<count>> : "+count);

		}

		
		session.setAttribute("rv",reservationCommand); 
		session.setAttribute("count",count); 

		return "reservationConfirm";
	}
	
	@RequestMapping("/reservation/test.do")

	public String confirm() {
			
		return "reservationResult";
					}

	@RequestMapping(value="/reservation/payment.do" ,method=RequestMethod.GET)
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
	
	@RequestMapping(value="/reservation/payment.do" ,method=RequestMethod.POST)
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
		Calendar c = Calendar.getInstance();

 		String week = String.valueOf(c.get(Calendar.WEEK_OF_MONTH));
 		int month = c.get(Calendar.MONTH+1);

 		String g_name =reservationCommand.getAcc_name()+" "+month+"월 "+week+"주 예약자"; 
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
		GroupCommand groupCommand = new GroupCommand();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
 		Calendar cal = Calendar.getInstance();
 		cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
 		String close_date = formatter.format(c.getTime());

		groupCommand.setG_close_date(close_date);
		groupCommand.setG_explain(g_name+" 위한 그룹입니다!");
		groupCommand.setG_image(reservationCommand.getIm_cover());
		groupCommand.setG_name(g_name);
		groupCommand.setG_imageName(reservationCommand.getIm_cover_name());
		groupCommand.setG_isPrivate(1);
		groupCommand.setG_isSearch(1);
		groupCommand.setMember_email(reservationCommand.getHost_email());
		groupCommand.setG_address1(reservationCommand.getAcc_address1());
		groupCommand.setG_address2(reservationCommand.getAcc_address2());
		if(log.isDebugEnabled()) {
			log.debug("<<groupCommand>> : "+groupCommand);
		}
		groupService.insertGroup(groupCommand);
		
	
		ChatAllCommand command = new ChatAllCommand();
		command.setChat_all_title(g_name);
		command.setChat_all_member_list(email);
		int g_num = reservationService.selectReservationGroup(gMap);
		command.setGroup_num(g_num);
		command.setChat_all_member_max(100);
		//그룹 채팅 생성
		chatService.insertChatAllGroup(command);
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
	      String setfrom = "ghcks3916@gmail.com";         
	      
	      messageHelper.setFrom(setfrom); 
	      messageHelper.setTo(paymentCommand.getPm_email());
	      messageHelper.setSubject("Awesome Tour 예약완료 되었습니다."); 
	      String appandEmail ="<h1>AWESOME TOUR</h1>"; 
	      appandEmail +="<h2>예약 정보</h2>";
	      appandEmail +="-------------------------------------------------<br>";

	      appandEmail +="예약 숙소 : "+reservationCommand.getAcc_name()+"<br>";
	      appandEmail +="예약 일시 : "+reservationCommand.getRv_startdate()+"~"+reservationCommand.getRv_enddate()+"<br>";
	      appandEmail +="예약 이메일 : "+email+"<br>";
	      appandEmail +="<h2>결제 정보</h2>";
	      appandEmail +="결제 수단 : ";
	      if(paymentCommand.getPm_type()==0){
	    	  appandEmail +="신용카드<br>";
	      }else {
	    	  appandEmail +="무통장 입금<br>";

	      }
	      appandEmail +="결제 금액 : "+reservationCommand.getRv_money()+"<br>";
	      appandEmail +="-------------------------------------------------<br>";
	      appandEmail +="이상 예약이 완료되었음을 알려드립니다.";
	      messageHelper.setText(appandEmail,true);  
	     
	      
	      mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
		    System.out.println(e);

		}
		//----------------------------------------------
		if(paymentCommand.getPm_type()=='b') {
		HoldingCommand hold = new HoldingCommand();
		hold.setAcc_num(reservationCommand.getAcc_num());
		hold.setAt_money(reservationCommand.getRv_money());
		hold.setHd_account(paymentCommand.getPm_deposit_ac());
		hold.setHd_deposit(0);
		adminService.insertHolding(hold);
		AccountCommand act = new AccountCommand();
		act.setAt_pin(paymentCommand.getPm_deposit_ac());
		act.setAt_name("입금자 입금");
		act.setAt_money(reservationCommand.getRv_money());
		act.setAt_depositor(paymentCommand.getPm_depositor());
		adminService.insertAccount(act);


		}else {
			HoldingCommand hold = new HoldingCommand();
			hold.setAcc_num(reservationCommand.getAcc_num());
			hold.setAt_money(reservationCommand.getRv_money());
			hold.setHd_account(paymentCommand.getPm_deposit_ac());
			hold.setHd_deposit(1);
			adminService.insertHolding(hold);

		}
		
		
		
		model.addAttribute("rv",session.getAttribute("rv"));
		session.removeAttribute("rv");
		session.removeAttribute("count");

		
		return "reservationResult";
	}

}
