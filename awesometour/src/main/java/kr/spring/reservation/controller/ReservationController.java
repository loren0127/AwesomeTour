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
	
	//���� ���� Ȯ��
	@RequestMapping("/reservation/confirm.do")
	public String confirm(@RequestParam("im_ac_num") int acc_num,@RequestParam("check_in") String check_in,
						  @RequestParam("check_out") String check_out,@RequestParam("people_count") int people_count,
						  @RequestParam("ro_room_num") int ro_room_num,HttpSession session) {
		Map<String,Integer> map = new HashMap<String,Integer>();
		map.put("acc_num", acc_num);
		map.put("ro_room_num",ro_room_num);
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
		reservationCommand.setRv_startdate(check_in);
		reservationCommand.setRv_enddate(check_out);
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
 		String todayYY = reservationCommand.getRv_startdate().substring(0, 4);
 		String todayMM = reservationCommand.getRv_startdate().substring(5, 7);
 		String todayDD = reservationCommand.getRv_startdate().substring(8, 10);
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
 		
 		String g_name =reservationCommand.getAcc_name()+" "+month+"�� "+this_week+"�� ������"; 
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
	      String setfrom = "ghcks3916@gmail.com";         
	      
	      messageHelper.setFrom(setfrom); 
	      messageHelper.setTo(paymentCommand.getPm_email());
	      messageHelper.setSubject("Awesome Tour ����Ϸ� �Ǿ����ϴ�."); 
	      String appandEmail ="<h1>AWESOME TOUR</h1>"; 
	      appandEmail +="<h2>���� ����</h2>";
	      appandEmail +="-------------------------------------------------<br>";

	      appandEmail +="���� ���� : "+reservationCommand.getAcc_name()+"<br>";
	      appandEmail +="���� �Ͻ� : "+reservationCommand.getRv_startdate()+"~"+reservationCommand.getRv_enddate()+"<br>";
	      appandEmail +="���� �̸��� : "+email+"<br>";
	      appandEmail +="<h2>���� ����</h2>";
	      appandEmail +="���� ���� : ";
	      if(paymentCommand.getPm_type()==0){
	    	  appandEmail +="�ſ�ī��<br>";
	      }else {
	    	  appandEmail +="������ �Ա�<br>";

	      }
	      appandEmail +="���� �ݾ� : "+reservationCommand.getRv_money()+"<br>";
	      appandEmail +="-------------------------------------------------<br>";
	      appandEmail +="�̻� ������ �Ϸ�Ǿ����� �˷��帳�ϴ�.";
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
			hold.setAt_money(reservationCommand.getRv_money());
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
			hold.setAt_money(reservationCommand.getRv_money());
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
