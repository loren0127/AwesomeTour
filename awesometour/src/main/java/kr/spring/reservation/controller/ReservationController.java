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
	 
	
	//�ڹٿ� , ���
	public static String toNumFormat(int num) {
		  DecimalFormat df = new DecimalFormat("#,###");
		  return df.format(num);
		 }
	
	//���� ���� Ȯ��
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

	//���� ���� �Է�
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
	
	
	//���� ó��
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

		//���� ó��
		reservationService.insertReservationSet(map);
		
		
		
		//--------------���� �׷쿡 �߰�----------
		
		
		//�̸�����
 		////////////////////////
 		String todayYY = reservationCommand.getRv_start_date().substring(0, 4);
 		String todayMM = reservationCommand.getRv_start_date().substring(5, 7);
 		String todayDD = reservationCommand.getRv_start_date().substring(8, 10);
 		// ���� ���ڸ� �޾Ƽ� int ������ ġȯ �Ѵ�.
 		int year = Integer.parseInt(todayYY);
 		int month = Integer.parseInt(todayMM)-1;   // ���� 0 ���� �����̱� ������ -1 �� ���ش�.
 		int day = Integer.parseInt(todayDD);
 		
 		int monday = 0;
 		int friday = 0;

 		// calendar ����.
 		Calendar to_day = Calendar.getInstance();
 		//���� ���ڸ� setup �Ѵ�.
 		to_day.set(year, month, day);
 		// ������ �������� �ش� ���� ������ �� �ݿ����� ���Ѵ�.
 		int today_week =  to_day.get(Calendar.DAY_OF_WEEK); // ������ ���� �������� int ������ ��ȯ.

 		if(today_week == Calendar.MONDAY) // ���� �������� ���ϱ� ���� ������ ���������� üũ �Ѵ�.
 		{
 		    monday = day;
 		    friday = day + (Calendar.FRIDAY - Calendar.MONDAY); 
 		    // ������ �������̸� ���� ���ڿ�  �ݿ��ϰ� �������� �������� �� ��ŭ ���ϸ� �ݿ��� ���ڰ� ���´�.
 		}
 		else if(today_week == Calendar.FRIDAY) // ���� ���ڰ� �ݿ������� üũ �Ѵ�.
 		{
 		    // ������ �ݿ����̸� ���� ���ڿ�  �ݿ��ϰ� �������� �������� �� ��ŭ ���� ������ ���ڰ� ���´�.
 		    monday = day - (Calendar.FRIDAY - Calendar.MONDAY);
 		    friday = day;
 		}
 		else if(today_week == Calendar.SUNDAY)
 		{
 		    // today_week �� �Ͽ��� �̸� ���� ���ڿ� sunday(1)�� ���Ͽ� monday ���ڸ� ����.
 		    monday = day + Calendar.SUNDAY; 
 		    friday = day + (Calendar.FRIDAY - Calendar.SUNDAY);
 		    // ������ �Ͽ����̸� friday(6) �� sunday(1)�� �� ��ŭ ���Ͽ� �ݿ��� ���ڸ� ����.
 		}
 		else if(today_week == Calendar.SATURDAY)
 		{
 		    // ������ ������̸� saturday(7) ���� monday(2)�� �� �� �������ڿ��� �� �� ������ ���ڸ� ����.
 		    monday = day - (Calendar.SATURDAY - Calendar.MONDAY);
 		    // ������ ������̸� saturday(7) ���� friday(6)�� �� �� �������ڿ��� �� �� �ݿ��� ���ڸ� ����.
 		    friday = day - (Calendar.SATURDAY - Calendar.FRIDAY);    
 		}
 		else  // ȭ , �� , �� �� ��
 		{
 		    // ���� ���� ǥ�� ���� monday(2) �� �� �� ���� ���ڿ��� �ش� ���ڸ� ����.
 		    monday = day - (today_week - Calendar.MONDAY);
 		    // friday(6) ���� ���� ���� ǥ�ø� �� �� �������ڿ� �ش� ���ڸ� ���Ѵ�. 
 		    friday = day + (Calendar.FRIDAY - today_week);
 		}

 		 

 		//�̹����� ������ ���Ѵ�.
 		//������ �������� ������fw�� �ش� ���� ������ ǥ�� �Ѵ�.

 		to_day = Calendar.getInstance(); // to_day �� �ʱ�ȭ �Ѵ�.

 		to_day.set(year, month, monday); // �����Ͽ� �ش��ϴ� ���� ���ϱ� ���� ������ ���ڸ� �Է�.

 		int this_week =  to_day.get(Calendar.WEEK_OF_MONTH); // �̹� ���� �����Ͽ� �ش��ϴ� ������ �����´�.

 		 
 	   
 		////////////////
 		
 		String g_name =reservationCommand.getAcc_name()+" "+(month+1)+"�� "+this_week+"�� ������"; 
 		//reservationCommand.getName() 

 		//�̸����� ��ȸ
 		Map<String,Object> gMap = new HashMap<String, Object>();
 		gMap.put("status", 0);
 		gMap.put("g_name", g_name);
 		int count = reservationService.selectReservationGroup(gMap);
		gMap.put("status", 1);
		map.put("reservationCommand", reservationCommand);
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : "+count);
		}
		
 		//�׷� ���� �� �׷� ����
		if(count==0 ){
			//������ ����
			GroupCommand groupCommand = new GroupCommand();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
	 		Calendar cal = Calendar.getInstance();
	 		cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
	 		to_day.add(Calendar.DATE, 7);
	 		String close_date = formatter.format(to_day.getTime());
	 		
	 		//�̹��� ��������
	 		Map<String, Object> mapImage = new HashMap<String, Object>(); 
	 		mapImage.put("im_ac_num", reservationCommand.getAcc_num());
	 		mapImage.put("ro_room_num", reservationCommand.getRo_room_num());
			HotelDetailCommand image = accomDetailService.selectHotelImage(mapImage);
			if(log.isDebugEnabled()) {
				log.debug("<<image>> : "+image);
			}
	
			//�׷� ����
			groupCommand.setG_close_date(close_date);
			groupCommand.setG_explain(g_name+" ���� �׷��Դϴ�!");
			groupCommand.setG_image(image.getIm_cover());
			groupCommand.setG_name(g_name);
			groupCommand.setG_imageName(image.getIm_cover_name());
			groupCommand.setG_isPrivate(1);
			groupCommand.setG_isSearch(1);
			groupCommand.setMember_email(reservationCommand.getHost_email());
			groupCommand.setG_address1(reservationCommand.getAcc_address1());
			groupCommand.setG_address2(reservationCommand.getAcc_address2());
			groupCommand.setG_hobby("��������");
			if(log.isDebugEnabled()) {
				log.debug("<<groupCommand>> : "+groupCommand);
			}
			groupService.insertGroup(groupCommand);
			
		
			//ä�� ����
			ChatAllCommand command = new ChatAllCommand();
			command.setChat_all_title(g_name);
			command.setChat_all_member_list(email);
			int g_num = reservationService.selectReservationGroup(gMap);
			command.setGroup_num(g_num);
			command.setChat_all_member_max(100);
			chatService.insertChatAllGroup(command);
			
			
			//ȣ��Ʈ ����
			ChatMemberCommand hostCommand = new ChatMemberCommand();
			hostCommand.setMember_email(reservationCommand.getHost_email());
			hostCommand.setChat_all_num_member(groupService.selectGroupChatnum(reservationService.selectReservationGroup(gMap)));
			Map<String,Object> m_map = new HashMap<String, Object>();
			m_map.put("member_email", email);
			m_map.put("chat_all_num", hostCommand.getChat_all_num_member());
			if(reservationService.selectGroupMemberCount(m_map)==0) {		
			chatService.insertChatMember(hostCommand);}
		
		}
		

		
		//��� ����
		ChatMemberCommand memberCommand = new ChatMemberCommand();
		memberCommand.setMember_email(email);
		memberCommand.setChat_all_num_member(groupService.selectGroupChatnum(reservationService.selectReservationGroup(gMap)));
		Map<String,Object> m_map = new HashMap<String, Object>();
		m_map.put("member_email", email);
		m_map.put("chat_all_num", memberCommand.getChat_all_num_member());
		if(reservationService.selectGroupMemberCount(m_map)==0)		
		chatService.insertChatMember(memberCommand);

		
		//----------------------------------------------
		
		
		
		//-------------------�̸��� ������----------------
		  MimeMessage message = mailSender.createMimeMessage();
	      MimeMessageHelper messageHelper;
		try {
			messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	      String setfrom = "AwesomeTour@noreply.com";         
	      
	      messageHelper.setFrom(setfrom); 
	      messageHelper.setTo(paymentCommand.getPm_email());
	      
	      messageHelper.setSubject("Awesome Tour ����Ϸ� �Ǿ����ϴ�."); 
	      String appandEmail ="";
	      appandEmail +="<table style='PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px' cellspacing='0' cellpadding='0' width='672' border='0'> <!-- ��� --> <tbody><tr><td style='PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px'><a href='http://192.168.10.41:8080/awesometour/index.jsp' name='ANCHOR14311' target='_blank' rel='noreferrer noopener'> <img style='BORDER-TOP: 0px; BORDER-RIGHT: 0px; VERTICAL-ALIGN: top; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px' alt='AwesomTour!' src='http://i1.ruliweb.com/img/18/09/18/165ec1d1cbcf9e2b.png'></a> </td></tr><!-- //��� --> <!-- ������ --><tr><td style='BORDER-RIGHT: #e4e4e4 1px solid; PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; BORDER-LEFT: #e4e4e4 1px solid; MARGIN: 0px; PADDING-RIGHT: 0px'> <table style='PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px' cellspacing='0' cellpadding='0' width='670' border='0'> <tbody><tr><td style='FONT-SIZE: 0px; LINE-HEIGHT: 0' height='49' colspan='3'>&nbsp;</td></tr><tr><td style='FONT-SIZE: 0px; LINE-HEIGHT: 0' width='53'>&nbsp;</td><td width='564'> <table style='PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px' cellspacing='0' cellpadding='0' width='564' border='0'> <tbody><tr><td> <table style='PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px' cellspacing='0' cellpadding='0' width='564' border='0'> <tbody><tr><td style='HEIGHT: 20px'> <span style='color:gray'> Awesome Tour���� �˷��帳�ϴ�. </span> </td></tr><tr><td> <h1>������ ������ �Ϸ� �Ǿ����ϴ�</h1></td></tr><tr><td style='FONT-SIZE: 0px; LINE-HEIGHT: 0' height='27'>&nbsp;</td></tr><tr><td> <table style='PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px' cellspacing='0' cellpadding='0' width='564' border='0'> <tbody><tr><td style='COLOR: #666; FONT: 12px/20px dotum'> <strong style='FONT-WEIGHT: bold; COLOR: #333'>�ȳ��ϼ���, ����</strong><br>���� �������� ������ ���������� �˷��帮�ڽ��ϴ�. <br><br><h2 style='margin: 10 0 5;'>���� ����</h2><hr noshade align='left' style='width:75%' size='1px'>";
	
	      appandEmail +="���� ���� : "+reservationCommand.getAcc_name()+"<br>";
	      appandEmail +="���� �Ͻ� : "+reservationCommand.getRv_start_date()+"~"+reservationCommand.getRv_end_date()+"<br>";
	      appandEmail +="���� �̸��� : "+email+"<br>";
	      
	      appandEmail +="<br><h2 style='margin: 10 0 5;'>���� ����</h2>";
	      appandEmail +="<hr noshade align='left' style='width:75%' size='1px'>���� ���� : ";
	      if(paymentCommand.getPm_type()==0){
	    	  appandEmail +="�ſ�ī��<br>";
	      }else {
	    	  appandEmail +="������ �Ա�<br>";

	      }
	      appandEmail +="���� �ݾ� : "+toNumFormat(reservationCommand.getRv_money())+"��<br>";
	      appandEmail +="<br>�̻� ������ �Ϸ�Ǿ����� �˷��帳�ϴ�.";

	      appandEmail +="<!-- �޴� �κ� --> </td></tr><tr><td style='FONT-SIZE: 0px; LINE-HEIGHT: 0' height='30'>&nbsp;</td></tr> <tr> <td style='TEXT-ALIGN: center'> <div style='margin: 0 auto;padding: 0;max-width: 760px;clear: both;line-height: 1.8;'> <br> <a href='http://192.168.10.41:8080/awesometour/index.jsp' style='background: #ce3176;border: 1px solid #ce3176;color: #fff;cursor: pointer;text-decoration: none;border-radius: 100px; font-size: 16px;padding: 8px 16px;font-weight: 600;' target='_blank'> Ȩ������ �ٷΰ��� </a></div> </tr></tbody> </table> </td></tr><tr><td style='FONT-SIZE: 0px; LINE-HEIGHT: 0' height='60'>&nbsp;</td></tr></tbody> </table> </td></tr></tbody> </table> </td><td style='FONT-SIZE: 0px; LINE-HEIGHT: 0' width='53'>&nbsp;</td></tr></tbody> </table> </td></tr><tr><td> <table cellspacing='0' cellpadding='0' width='672' border='0'> <tbody><tr><td style='WIDTH: 155px'><a href='http://192.168.10.41:8080/awesometour/index.jsp' name='ANCHOR14314' target='_blank' rel='noreferrer noopener'> <img style='BORDER-TOP: 0px; BORDER-RIGHT: 0px; VERTICAL-ALIGN: top; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px' alt='GRAVITY' src='http://i3.ruliweb.com/img/18/09/20/165f2923892f9e2b.jpg'></a></td><td style='WIDTH: 517px'> <table cellspacing='0' cellpadding='0' width='517' border='0'> <tbody><tr><td style='FONT-SIZE: 12px; LINE-HEIGHT: 12px'> <img style='BORDER-TOP: 0px; BORDER-RIGHT: 0px; VERTICAL-ALIGN: top; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px' alt='�߱׶��Ƽ ��ǥ�̻� : ����ö ����Ư���� ������ �����źϷ� 396, 15��(��ϵ�, �����޽����� ��������Ÿ��)' src='http://i1.ruliweb.com/img/18/09/20/165f273122ef9e2b.png'><br> <img style='BORDER-TOP: 0px; BORDER-RIGHT: 0px; VERTICAL-ALIGN: top; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px' alt='��ȭ���� :1588-9897 �ѽ� : 02-2132-7077 ����ڵ�Ϲ�ȣ : 201 -81-56197' src='http://imgc.gnjoy.com/gnjoy/2012_gnjoy/dm/images/img_dm_footer03.gif'><br> <img style='BORDER-TOP: 0px; BORDER-RIGHT: 0px; VERTICAL-ALIGN: top; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px' alt='����Ǹž� �Ű��ȣ : �� 2008-���︶��-0207ȣ ���������� : ����Ư���� ������ �����źϷ� 396, 15��(��ϵ�, �����޽����� ��������Ÿ��)' src='http://imgc.gnjoy.com/gnjoy/2012_gnjoy/dm/images/img_dm_footer04_150207.gif'><br> <img style='BORDER-TOP: 0px; BORDER-RIGHT: 0px; VERTICAL-ALIGN: top; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px' alt='Copyright (c) GRAVITY Co., Ltd. All Rights Reserved.' src='http://i1.ruliweb.com/img/18/09/20/165f2731386f9e2b.png'><br> </td></tr></tbody> </table> </td></tr></tbody> </table> </td></tr></tbody> </table> </td></tr><!-- //ǲ�� --></tbody> </table>";
	      messageHelper.setText(appandEmail,true);  
	     
	      
	      mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
		    System.out.println(e);
		}
		//----------------------------------------------
		
		//Ȧ��
		 
		if(paymentCommand.getPm_type()=='b') {
			
			HoldingCommand hold = new HoldingCommand();
			hold.setAcc_num(reservationCommand.getAcc_num());
			hold.setHd_money(reservationCommand.getRv_money());
			hold.setHd_account(paymentCommand.getPm_deposit_ac());
			hold.setHd_deposit(0);
			adminService.insertHolding(hold);
			//�Ա�----------------------------------
			//�ÿ��� ������ ����
			AccountCommand act = new AccountCommand();
			act.setAt_pin(paymentCommand.getPm_deposit_ac());
			act.setAt_name("�Ա��� �Ա�");
			act.setAt_money(reservationCommand.getRv_money());
			act.setAt_depositor(paymentCommand.getPm_depositor());
			adminService.insertAccount(act);
			//---------------------------------------------------

		}else {
			//ī�� ��� Ȧ��
			HoldingCommand hold = new HoldingCommand();
			hold.setAcc_num(reservationCommand.getAcc_num());
			hold.setHd_money(reservationCommand.getRv_money());
			hold.setHd_account("card");
			hold.setHd_deposit(1);
			adminService.insertHolding(hold);

		}
		
		//���� ȣ��Ʈ ������Ʈ
		Map<String,Object> upMap = new HashMap<String,Object>();
		upMap.put("re_acc_num", reservationCommand.getAcc_num());
		accomDetailService.updateSuperHost(upMap);
		
		
		model.addAttribute("rv",session.getAttribute("rv"));
		session.removeAttribute("rv");
		session.removeAttribute("count");

		
		return "reservationResult";
	}
	
	//�׽�Ʈ��
	@RequestMapping("/reservation/test.do")

	public String confirm() {
			
		return "reservationResult";
					}

}
