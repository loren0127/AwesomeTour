package kr.spring.member.controller;

import java.util.Random;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.spring.member.domain.CodeCommand;
import kr.spring.member.domain.MemberCommand;
import kr.spring.member.service.MemberService;

@Controller
public class MemberController {
	private Logger log = Logger.getLogger(this.getClass());

	@Resource
	private MemberService memberService;
	
	@Autowired
	  private JavaMailSender mailSender;
	
	//@Resource
	//private CipherTemplate cipherAES;
	
	//占쌘바븝옙 占십깍옙화
	@ModelAttribute("command")
	public MemberCommand initCommand() {
		return new MemberCommand();
	}
	//====================�쉶�썝媛��엯 �뤌=================//
	@RequestMapping(value="/member/write.do", method=RequestMethod.GET)
	public String form(HttpServletRequest request) {
		return "memberWrite";
	}
	@RequestMapping(value="/member/write.do", method=RequestMethod.POST)
	public String submit(@ModelAttribute("command")@Valid MemberCommand memberCommand,BindingResult result,HttpSession session
			,HttpServletRequest request,Model model) {
		
		String email = request.getParameter("member_email");
		
		if(log.isDebugEnabled()) {
			log.debug("<<memberCommand>> : " + memberCommand);
		}
		/*if(result.hasErrors()) {
			return form();
		}*/
		//CipherTemplate占쏙옙 占싱울옙占쏙옙 占쏙옙호화
		/*memberCommand.setPasswd(cipherAES.encrypt(memberCommand.getPasswd()));*/
		
		//
		/*if(session != null) {
			session.invalidate();
			return "redirect:/main/main.do";
		}*/
		session.setAttribute("command", memberCommand);
		
		return "memberWrite2";
	}
	@RequestMapping("member/success.do")
	public String success(String ch[],String ra[],HttpSession session,String member_email) {
		String ten = "";
		for (int i=0 ; i<ch.length ; i++) {
			ten +=ch[i];
			if(ch.length-1 != i) {
				ten += ",";
			}
		}
		
		String ho = "";
		for (int i=0 ; i<ra.length ; i++) {
			ho +=ra[i];
		}
		
		//�쟾�뿉�엳�뜕 �뜲�씠�꽣
		MemberCommand memberCommand = (MemberCommand) session.getAttribute("command");
		//�뀗�뿉�꽌 �꽑�깮�븳
		memberCommand.setMember_tendency(ten);
		memberCommand.setMember_hobby(ho);
		//�빀爾먯꽌 �씤�꽌�듃
		memberService.insert(memberCommand);
		memberService.deleteCode(member_email);
		
		return "redirect:/main/main.do";
	}
	//====================濡쒓렇�씤 �뤌=================//
	@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
	public String formLogin() {
		return "memberLogin";
	}
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public String submitLogin(@ModelAttribute("command") @Valid MemberCommand memberCommand, BindingResult result, HttpSession session) {
		
		
		/*//id占쏙옙 passwd 占십드만 체크
		if(result.hasFieldErrors("id") || 
				result.hasFieldErrors("passwd")) {
			return formLogin();
		}*/
		
		try {
			MemberCommand member = memberService.selectMember(memberCommand.getMember_email());
			boolean check = false;
			
			if(member!=null) {
			
				check = member.isCheckedPasswd(memberCommand.getMember_passwd());
			}
			if(log.isDebugEnabled()) {
				log.debug("<<check>> : " + check);
			}
			if(check) {
				session.setAttribute("user_email", member.getMember_email());
	            session.setAttribute("user_auth", member.getMember_auth());
	            session.setAttribute("user_nickname", member.getMember_nickname());
				if(log.isDebugEnabled()) {
					log.debug("<<dd>>");
					log.debug("<<user_email>> : " + member.getMember_email());
					log.debug("<<user_auth>> : " + member.getMember_auth());
					
				}
				return "redirect:/main/main.do";
			}else {
				throw new Exception();
			}
		}catch(Exception e) {
			result.reject("invalidIdOrPassword");
			if(log.isDebugEnabled()) {
				log.debug("<<占쏙옙占쏙옙 占쏙옙占쏙옙>>");
			}
			return formLogin();
		}
	}
	
	//====================濡쒓렇�븘�썐=================//
	@RequestMapping("/member/logout.do")
	public String processLogin(HttpSession session) {
		//占싸그아울옙
		session.invalidate();
		
		return "redirect:/main/main.do";
	}
	//====================�젙蹂� 泥댄겕=================//
	@RequestMapping("/member/detailCheck.do")
	public String processCheck(HttpSession session,Model model) {
		//濡쒓렇�씤�븯�씪怨� �쑉�뒗嫄곗��쓬
		//�씤�꽣�뀎�꽣�뿉�꽌
		String user_email = (String)session.getAttribute("user_email");
		
		model.addAttribute("email",user_email);
		
		return "memberViewCheck";
	}
	//====================�젙蹂� 泥듯궧=================//
		@RequestMapping(value="/member/detailChecking.do",method = RequestMethod.POST)
		public String processChecking(String member_email, String member_passwd,HttpSession session) {
			
			MemberCommand memberCommand = new MemberCommand();
			
			memberCommand.setMember_email(member_email);
			memberCommand.setMember_passwd(member_passwd);
			
			session.setAttribute("user_email", member_email);
			
			int count = memberService.selectPasswd(memberCommand);
			if(count == 0) {
			  return "redirect:/member/detailCheck.do";
			}
			return "redirect:/member/detail.do";
		}
	//====================議고쉶=================//
		@RequestMapping("/member/detail.do")
		public String process(HttpSession session,Model model) {
			//濡쒓렇�씤�븯�씪怨� �쑉�뒗嫄곗��쓬
			//�씤�꽣�뀎�꽣�뿉�꽌
			String user_email = (String)session.getAttribute("user_email");
			
			MemberCommand member = memberService.selectMember(user_email);
			
			if(log.isDebugEnabled()) {
				log.debug("<<memberCommand>> : " + member);
			}
			
			session.setAttribute("member", member);
			//model.addAttribute("member", member);
			
			return "memberView";
		}
		//==========�씠誘몄� 酉�==================
		@RequestMapping("/member/imageView.do")
		public ModelAndView viewImage(@RequestParam("member_email")String member_email) {

			MemberCommand board = 
					memberService.selectMemb(member_email);

			ModelAndView mav = new ModelAndView();
			mav.setViewName("imageView");
			//占쌈쇽옙占쏙옙       占쌈쇽옙占쏙옙(byte[]占쏙옙 占쏙옙占쏙옙占쏙옙)
			mav.addObject("imageFile", board.getMember_uploadfile());
			mav.addObject("filename", board.getMember_filename());

			return mav;
		}
	
	//====================�닔�젙=================//
		@RequestMapping(value="/member/update.do",method=RequestMethod.GET)
		public String formUpdate(HttpSession session, Model model) {
			String user_email = (String)session.getAttribute("user_email");
			
			MemberCommand member = memberService.selectMemb(user_email);
			model.addAttribute("member", member);
			
			return "memberModify";
		}
		
		@RequestMapping(value="/member/update.do", method=RequestMethod.POST)
		public String submitUpdate(@ModelAttribute("command") @Valid MemberCommand memberCommand,BindingResult result,HttpSession session) {
			
			if(log.isDebugEnabled()) {
				log.debug("<<memberCommand>> : " + memberCommand);
			}
			
			/*if(result.hasErrors()) {
				return "memberModify";
			}*/
			
			//CipherTemplate占쏙옙 占싱울옙占쏙옙 占쏙옙호화
			//memberCommand.setPasswd(cipherAES.encrypt(memberCommand.getPasswd()));
			//memberCommand.setMember_passwd(memberCommand.getMember_passwd());
			//회占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙
			
			
			session.setAttribute("command", memberCommand);
			
			return "memberModify2";
			//return "redirect:/member/detail.do";
		}
		//==========�쉶�썝�닔�젙 2=======
		@RequestMapping(value="/member/update2.do", method=RequestMethod.POST)
		public String submitUpdate2(String ch[],String ra[],HttpSession session,String member_email) {
			String ten = "";
			for (int i=0 ; i<ch.length ; i++) {
				ten +=ch[i];
				if(ch.length-1 != i) {
					ten += ",";
				}
			}
			
			String ho = "";
			for (int i=0 ; i<ra.length ; i++) {
				ho +=ra[i];
			}
			//�쟾�뿉�엳�뜕 �뜲�씠�꽣
			MemberCommand memberCommand = (MemberCommand) session.getAttribute("command");
			//�뀗�뿉�꽌 �꽑�깮�븳
			memberCommand.setMember_tendency(ten);
			memberCommand.setMember_hobby(ho);
			System.out.println("!$%@%#$%#$%#$#%"+memberCommand);
			memberService.update(memberCommand);
			
			return "redirect:/member/detail.do";
		}
		//=========================�깉�눜 �솗�씤
		@RequestMapping("member/memberdeleteCheck.do")
		public String deleteCheck() {
			
			return "memberdeleteCheck";
		}
		//================�깉�눜 �씤利� �럹�씠吏�=======
		@RequestMapping("member/delete.do")
		public String formDelete(HttpSession session,Model model,HttpServletRequest request) {
			
			CodeCommand codeCommand = new CodeCommand();
			Random random = new Random();
			String user_email = (String)session.getAttribute("user_email");
			
			model.addAttribute("user_email",user_email);
			
			codeCommand.setCode_email(user_email);
			codeCommand.setCode_status("2");
			
			int count = memberService.selectCode2(codeCommand);
			
			if(count != 0) {
				memberService.deleteCode2(codeCommand);
			}
			
			String code_code1 = String.valueOf((char) ((int) (random.nextInt(26)) + 97));
			String code_code2 = String.valueOf((char) ((int) (random.nextInt(26)) + 97));
			String code_code3 = String.valueOf((char) ((int) (random.nextInt(26)) + 97));
			String code_code4 = String.valueOf((char) ((int) (random.nextInt(26)) + 97));
			String code_code5 = String.valueOf((char) ((int) (random.nextInt(26)) + 97));
			
			String code = code_code1+code_code2+code_code3+code_code4+code_code5;
			
		    String setfrom = "ghcks3916@gmail.com";         
		    String a_code_email = user_email;  // 諛쏅뒗 �궗�엺 �씠硫붿씪
		    String title   = "AwesomeTour �쉶�썝�깉�눜  肄붾뱶踰덊샇";  //request.getParameter("title");      // �젣紐�
		    try {
		      MimeMessage message = mailSender.createMimeMessage();
		      MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
		 
		      messageHelper.setFrom(setfrom);  // 蹂대궡�뒗�궗�엺 �깮�왂�븯嫄곕굹 �븯硫� �젙�긽�옉�룞�쓣 �븞�븿
		      messageHelper.setTo(a_code_email);     // 諛쏅뒗�궗�엺 �씠硫붿씪
		      messageHelper.setSubject(title); // 硫붿씪�젣紐⑹� �깮�왂�씠 媛��뒫�븯�떎
		      messageHelper.setText("삭제 코드 : "+code);  // 硫붿씪 �궡�슜
		      
		      
		      codeCommand.setCode_code(code);
		      codeCommand.setCode_email(a_code_email);
		      codeCommand.setCode_status("2");
		      
		      
		      mailSender.send(message);
		      memberService.insertCode2(codeCommand);
		    } catch(Exception e){
		      System.out.println(e);
		    }
			
			return "memberDelete";
		}
		//=============�깉�눜 ing ==============
		@RequestMapping("/member/deleteCheck.do")
		public String delete(String code_email, String code_code, HttpSession session) {
			
			CodeCommand codeCommand =  new CodeCommand();
			  
			  codeCommand.setCode_code(code_code);
			  codeCommand.setCode_email(code_email);
			  session.setAttribute("code_email", code_email);
			  
			  //�씠硫붿씪 肄붾뱶 李얘린
			  int count = memberService.selectCode(codeCommand);
			  if(count == 0) {
				  return "member/delete.do";
			  }
			  memberService.deleteCode(code_email);
			  memberService.updateAuth(code_email);
			  memberService.deleteDetail(code_email);
			  session.invalidate();
			
			return "redirect:/main/main.do";
		}
}








