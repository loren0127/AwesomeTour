package kr.spring.reservation.controller;



import java.util.Calendar;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@RequestMapping("/reservation/confirm.do")
	public String confirm(@RequestParam("im_ac_num") int acc_num,@RequestParam("check_in") String check_in,
						  @RequestParam("check_out") String check_out,@RequestParam("people_count") int people_count,
						  @RequestParam("ro_room_num") int ro_num,Model model) {
	
		ReservationCommand reservationCommand = reservationService.selectRerservationAcc(acc_num);
		
		model.addAttribute("rv",reservationCommand); 
		
		return "reservationConfirm";
	}
	

	@RequestMapping("/reservation/payment.do")
	public String payment( @RequestParam("rvCommand") ReservationCommand rvCommand) {
	
		
		
		return "reservationPayment";
	}
	
	@RequestMapping("/reservation/result.do")
	public String result(@RequestParam("payCommand") PaymentCommand paymentCommand,
						 @RequestParam("reservaionCommand") ReservationCommand reservationCommand,
						HttpSession session) {
	
		String email = (String) session.getAttribute("email");
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("payCommand", paymentCommand);
		map.put("reservaionCommand", reservationCommand);
		
		
		//���� ó��
		reservationService.insertReservation(map);
		
		
		
		//--------------���� �׷쿡 �߰�----------
		
		
		//�̸�����
		Calendar c = Calendar.getInstance();

 		String week = String.valueOf(c.get(Calendar.WEEK_OF_MONTH));
 		int month = c.get(Calendar.MONTH+1);

 		String g_name = "�׷��̸� "+month+"�� "+week+"�� �׷�"; 
 		//reservationCommand.getName() 

 		//�̸����� ��ȸ
 		Map<String, Integer> g_num = reservationService.selectReservationGroup(g_name);
 		
 		//�׷� ���� �� �׷� ����
		if(g_num.get("cnt")<0 ){
		GroupCommand groupCommand = new GroupCommand();
	
		groupService.insertGroup(groupCommand);
		}

		//��� ����
		//���ñ⼭��.inset���
		
		//----------------------------------------------
		
		
		
		//-------------------�̸��� ������----------------
		  MimeMessage message = mailSender.createMimeMessage();
	      MimeMessageHelper messageHelper;
		try {
			messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	      String setfrom = "ghcks3916@gmail.com";         
	      
	      messageHelper.setFrom(setfrom); 
	      messageHelper.setTo(email);
	      messageHelper.setSubject("Awesome Tour ����Ϸ� �Ǿ����ϴ�."); 
	      messageHelper.setText("���� ��ȣ �� ��Ÿ ����");  
	      
	      mailSender.send(message);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
		    System.out.println(e);

		}
		//----------------------------------------------

		
		
		
		
		return "reservationResult";
	}
	
	/*//�̹��� ���
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
