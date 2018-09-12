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
	@RequestMapping("/reservation/confirm.do")
	public String confirm(@RequestParam("im_ac_num") int acc_num,@RequestParam("check_in") String check_in,
						  @RequestParam("check_out") String check_out,@RequestParam("people_count") int people_count,
						  @RequestParam("ro_room_num") int ro_num,HttpSession session  ) {
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
	

	@RequestMapping(value="/reservation/payment.do" ,method=RequestMethod.GET)
	public String payment(@RequestParam("acc_num") int acc_num,@RequestParam("rv_money") int rv_money,Model model,HttpSession session) {

		String host_nick = reservationService.selectHostNick(acc_num);
		PaymentCommand pmCommand = new PaymentCommand();
		
		if(log.isDebugEnabled()) {
			log.debug("<<host_nick>> : "+host_nick);
		}
		ReservationCommand reservationCommand = (ReservationCommand) session.getAttribute("rv");
		reservationCommand.setRv_money(rv_money);
		
		model.addAttribute("pmCommand", pmCommand);
		model.addAttribute("host_nick", host_nick);

		return "reservationPayment";
	}
	
	@RequestMapping(value="/reservation/payment.do" ,method=RequestMethod.POST)
	public String result(@ModelAttribute("pmCommand") PaymentCommand paymentCommand,
						HttpSession session) {
	
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
	      messageHelper.setText("예약 번호 및 기타 정보");  
	      
	      mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
		    System.out.println(e);

		}
		//----------------------------------------------

		
		return "reservationResult";
	}
	
	/*//이미지 출력
	@RequestMapping("/reservation/imageView.do")
	public ModelAndView download(@RequestParam("g_num") int g_num) {
		
		GroupCommand group = reservationService.selectGroupDetail(g_num);
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("imageView");
		mav.addObject("imageFile",group.getG_image());
		mav.addObject("filename",group.getG_imageName());

		return mav;
	}*/
	
}
