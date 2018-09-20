package kr.spring.member.controller;
 
import java.util.Random;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.spring.member.domain.CodeCommand;
import kr.spring.member.service.MemberService;

 
@Controller
public class MailController {
 
  @Autowired
  private JavaMailSender mailSender;
 
  @Resource
  private MemberService memberService;
  
  
  // mailForm
  @RequestMapping(value="/mail/mailForm.do")
  public String mailForm() {
   
    return "mailForm";
  }  
 
  // mailSending 肄붾뱶
  @RequestMapping(value="/mail/mailSending.do")
  public String mailSending(HttpServletRequest request,String email,HttpSession session) {
   
	CodeCommand codeCommand =  new CodeCommand();
	Random random = new Random();
	
	//String code_email2 = request.getParameter("email");
	
	session.setAttribute("email",email);
	
	int checkemail = memberService.selectEmail(email);
	if(checkemail != 0) {
		return mailCheck();
	}
	
	/*int checkcount = memberService.checkEmail(email);
	if(checkcount != 0) {
		session.invalidate();
		return mailForm();
	}*/
	
	
	String code_code1 = String.valueOf((char) ((int) (random.nextInt(26)) + 97));
	String code_code2 = String.valueOf((char) ((int) (random.nextInt(26)) + 97));
	String code_code3 = String.valueOf((char) ((int) (random.nextInt(26)) + 97));
	String code_code4 = String.valueOf((char) ((int) (random.nextInt(26)) + 97));
	String code_code5 = String.valueOf((char) ((int) (random.nextInt(26)) + 97));
	
	String code_code = code_code1+code_code2+code_code3+code_code4+code_code5;
	
    String setfrom = "AwesomeTour@noreply.com";         
    String code_email = request.getParameter("email");  // 諛쏅뒗 �궗�엺 �씠硫붿씪
    String title   = "AwesomeTour 회원가입 인증코드입니다.";  //request.getParameter("title");      // �젣紐�
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
 
      messageHelper.setFrom(setfrom);  // 蹂대궡�뒗�궗�엺 �깮�왂�븯嫄곕굹 �븯硫� �젙�긽�옉�룞�쓣 �븞�븿
      messageHelper.setTo(code_email);     // 諛쏅뒗�궗�엺 �씠硫붿씪
      messageHelper.setSubject(title); // 硫붿씪�젣紐⑹� �깮�왂�씠 媛��뒫�븯�떎
     
      String append = "<table style='PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px' cellspacing='0' cellpadding='0' width='672' border='0'> <!-- 헤더 --> <tbody><tr><td style='PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px'><a href='http://192.168.10.41:8080/awesometour/index.jsp' name='ANCHOR14311' target='_blank' rel='noreferrer noopener'> <img style='BORDER-TOP: 0px; BORDER-RIGHT: 0px; VERTICAL-ALIGN: top; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px' alt='AwesomeTour!' src='http://i1.ruliweb.com/img/18/09/18/165ec1d1cbcf9e2b.png'></a> </td></tr><!-- //헤더 --> <!-- 컨텐츠 --><tr><td style='BORDER-RIGHT: #e4e4e4 1px solid; PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; BORDER-LEFT: #e4e4e4 1px solid; MARGIN: 0px; PADDING-RIGHT: 0px'> <table style='PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px' cellspacing='0' cellpadding='0' width='670' border='0'> <tbody><tr><td style='FONT-SIZE: 0px; LINE-HEIGHT: 0' height='49' colspan='3'>&nbsp;</td></tr><tr><td style='FONT-SIZE: 0px; LINE-HEIGHT: 0' width='53'>&nbsp;</td><td width='564'> <table style='PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px' cellspacing='0' cellpadding='0' width='564' border='0'> <tbody><tr><td> <table style='PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px' cellspacing='0' cellpadding='0' width='564' border='0'> <tbody><tr><td style='HEIGHT: 20px'> <span style='color:gray'> Awesome Tour에서 알려드립니다. </span> </td></tr><tr><td> <h1>회원가입 코드가 발송되었습니다.</h1></td></tr><tr><td style='FONT-SIZE: 0px; LINE-HEIGHT: 0' height='27'>&nbsp;</td></tr><tr><td> <table style='PADDING-BOTTOM: 0px; PADDING-TOP: 0px; PADDING-LEFT: 0px; MARGIN: 0px; PADDING-RIGHT: 0px' cellspacing='0' cellpadding='0' width='564' border='0'> <tbody><tr><td style='COLOR: #666; FONT: 12px/20px dotum'> <strong style='FONT-WEIGHT: bold; COLOR: #333'>안녕하세요, 고객님</strong> <br>저희 어썸투어에 가입절차 진행을 위한 코드번호를 알려드리겠습니다<br>";
      append +="<h2 style=\"margin: 10 0 5;\">가입 코드 : "+code_code+"</h2>";
      append +="<br> 위의 코드를 홈페이지에 입력후 가입을 절차를 이어서 진행해주시기 바랍니다.<br> 감사합니다. <br> <!-- 받는 부분 --> </td></tr><tr><td style='FONT-SIZE: 0px; LINE-HEIGHT: 0' height='30'>&nbsp;</td></tr> <tr> <td style='TEXT-ALIGN: center'> <div style='margin: 0 auto;padding: 0;max-width: 760px;clear: both;line-height: 1.8;'> <br> <a href='http://192.168.10.41:8080/awesometour/index.jsp' style='background: #ce3176;border: 1px solid #ce3176;color: #fff;cursor: pointer;text-decoration: none;border-radius: 100px; font-size: 16px;padding: 8px 16px;font-weight: 600;' target='_blank'> 홈페이지 바로가기 </a></div> </tr></tbody> </table> </td></tr><tr><td style='FONT-SIZE: 0px; LINE-HEIGHT: 0' height='60'>&nbsp;</td></tr></tbody> </table> </td></tr></tbody> </table> </td><td style='FONT-SIZE: 0px; LINE-HEIGHT: 0' width='53'>&nbsp;</td></tr></tbody> </table> </td></tr><tr><td> <table cellspacing='0' cellpadding='0' width='672' border='0'> <tbody><tr><td style='WIDTH: 155px'><a href='http://192.168.10.41:8080/awesometour/index.jsp' name='ANCHOR14314' target='_blank' rel='noreferrer noopener'> <img style='BORDER-TOP: 0px; BORDER-RIGHT: 0px; VERTICAL-ALIGN: top; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px' alt='GRAVITY' src='http://i3.ruliweb.com/img/18/09/20/165f2923892f9e2b.jpg'></a></td><td style='WIDTH: 517px'> <table cellspacing='0' cellpadding='0' width='517' border='0'> <tbody><tr><td style='FONT-SIZE: 12px; LINE-HEIGHT: 12px'> <img style='BORDER-TOP: 0px; BORDER-RIGHT: 0px; VERTICAL-ALIGN: top; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px' alt='㈜그라비티 대표이사 : 박현철 서울특별시 마포구 월드컵북로 396, 15층(상암동, 누리꿈스퀘어 연구개발타워)' src='http://i1.ruliweb.com/img/18/09/20/165f273122ef9e2b.png'><br> <img style='BORDER-TOP: 0px; BORDER-RIGHT: 0px; VERTICAL-ALIGN: top; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px' alt='전화문의 :1588-9897 팩스 : 02-2132-7077 사업자등록번호 : 201 -81-56197' src='http://imgc.gnjoy.com/gnjoy/2012_gnjoy/dm/images/img_dm_footer03.gif'><br> <img style='BORDER-TOP: 0px; BORDER-RIGHT: 0px; VERTICAL-ALIGN: top; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px' alt='통신판매업 신고번호 : 제 2008-서울마포-0207호 고객지원센터 : 서울특별시 마포구 월드컵북로 396, 15층(상암동, 누리꿈스퀘어 연구개발타워)' src='http://imgc.gnjoy.com/gnjoy/2012_gnjoy/dm/images/img_dm_footer04_150207.gif'><br> <img style='BORDER-TOP: 0px; BORDER-RIGHT: 0px; VERTICAL-ALIGN: top; BORDER-BOTTOM: 0px; BORDER-LEFT: 0px' alt='Copyright (c) GRAVITY Co., Ltd. All Rights Reserved.' src='http://i1.ruliweb.com/img/18/09/20/165f2731386f9e2b.png'><br> </td></tr></tbody> </table> </td></tr></tbody> </table> </td></tr></tbody> </table> </td></tr><!-- //풋터 --></tbody> </table>";
      
      messageHelper.setText(append,true);  // 硫붿씪 �궡�슜
      
      
      codeCommand.setCode_code(code_code);
      codeCommand.setCode_email(code_email);
      memberService.insertCode(codeCommand);
      mailSender.send(message);
    } catch(Exception e){
      System.out.println(e);
    }
    
    return "mailCheck";
  }
  //�씠硫붿씪 蹂대궦 �썑 �럹�씠吏�
  @RequestMapping(value="/mail/mailComp.do")
  public String mailComp() {
	  
	return "/mail/mailComp";
  }
  // �씠硫붿씪 泥댄겕 �럹�씠吏�
  @RequestMapping(value="/mail/mailCheck.do")
  public String mailCheck() {
	  
	return "mailCheck";
  }
  //�씠硫붿씪 肄붾뱶�씤利�
  @RequestMapping(value="/mail/mailChecking.do",method = RequestMethod.POST)
  public String mailChecking(String code_email, String code_code, HttpSession session) {
	 
	  CodeCommand codeCommand =  new CodeCommand();
	  
	  codeCommand.setCode_code(code_code);
	  codeCommand.setCode_email(code_email);
	  session.setAttribute("code_email", code_email);
	  
	  //�씠硫붿씪 肄붾뱶 李얘린
	  int count = memberService.selectCode(codeCommand);
	  if(count == 0) {
		  return mailCheck();
	  }
	  
	  
	  return "redirect:/member/write.do";
  }
} 