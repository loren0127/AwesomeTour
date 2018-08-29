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
 
  // mailSending 코드
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
	
	int checkcount = memberService.checkEmail(email);
	if(checkcount != 0) {
		session.invalidate();
		return mailForm();
	}
	
	
	String code_code1 = String.valueOf((char) ((int) (random.nextInt(26)) + 97));
	String code_code2 = String.valueOf((char) ((int) (random.nextInt(26)) + 97));
	String code_code3 = String.valueOf((char) ((int) (random.nextInt(26)) + 97));
	String code_code4 = String.valueOf((char) ((int) (random.nextInt(26)) + 97));
	String code_code5 = String.valueOf((char) ((int) (random.nextInt(26)) + 97));
	
	String code_code = code_code1+code_code2+code_code3+code_code4+code_code5;
	
    String setfrom = "ghcks3916@gmail.com";         
    String code_email = request.getParameter("email");  // 받는 사람 이메일
    String title   = "AwesomeTour 회원가입 코드 번호";  //request.getParameter("title");      // 제목
    try {
      MimeMessage message = mailSender.createMimeMessage();
      MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
 
      messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
      messageHelper.setTo(code_email);     // 받는사람 이메일
      messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
      messageHelper.setText("코드번호 : "+code_code);  // 메일 내용
      
      
      codeCommand.setCode_code(code_code);
      codeCommand.setCode_email(code_email);
      mailSender.send(message);
      memberService.insertCode(codeCommand);
    } catch(Exception e){
      System.out.println(e);
    }
    
    return "mailCheck";
  }
  //이메일 보낸 후 페이지
  @RequestMapping(value="/mail/mailComp.do")
  public String mailComp() {
	  
	return "/mail/mailComp";
  }
  // 이메일 체크 페이지
  @RequestMapping(value="/mail/mailCheck.do")
  public String mailCheck() {
	  
	return "mailCheck";
  }
  //이메일 코드인증
  @RequestMapping(value="/mail/mailChecking.do",method = RequestMethod.POST)
  public String mailChecking(String code_email, String code_code, HttpSession session) {
	 
	  CodeCommand codeCommand =  new CodeCommand();
	  
	  codeCommand.setCode_code(code_code);
	  codeCommand.setCode_email(code_email);
	  session.setAttribute("code_email", code_email);
	  
	  //이메일 코드 찾기
	  int count = memberService.selectCode(codeCommand);
	  if(count == 0) {
		  return mailCheck();
	  }
	  
	  
	  return "redirect:/member/write.do";
  }
} 