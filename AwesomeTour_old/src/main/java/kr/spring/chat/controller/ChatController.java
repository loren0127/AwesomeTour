package kr.spring.chat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.chat.domain.ChatAllCommand;
import kr.spring.chat.domain.ChatMemberCommand;
import kr.spring.chat.service.ChatService;
import kr.spring.util.PagingUtil;

@Controller
public class ChatController {
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	private ChatService chatService;
	
	@ModelAttribute("chatMemberCommand")
	public ChatMemberCommand initChatMemberCommand() {
		return new ChatMemberCommand();
	}
	
	@ModelAttribute("chatAllCommand")
	public ChatAllCommand initChatAllCommand() {
		return new ChatAllCommand();
	}
	
	@RequestMapping(value="/chat/chatMemberSelectList.do")
	public ModelAndView chatMemberSelectList(@RequestParam(value="pageNum", defaultValue="1")int currentPage, @RequestParam(value="keyfield", defaultValue="")String keyfield, @RequestParam(value="keyword", defaultValue="")String keyword, HttpSession session) {
		//로그인 상태  확인
		
		//로그인 상태 확인
		
		Map<String, Object> chatUtilMap = new HashMap<String, Object>();
		chatUtilMap.put("keyfield", keyfield);
		chatUtilMap.put("keyworld", keyword);
		chatUtilMap.put("member_email", (String)session.getAttribute("user_email"));
		
		int listCount = chatService.chatAllSelectRowListCount(chatUtilMap);
		
		PagingUtil pagingUtil = new PagingUtil(keyfield, keyword, currentPage, listCount, rowCount, pageCount, "chatMemberSelectList.do");
		
		chatUtilMap.put("start", pagingUtil.getStartCount());
		chatUtilMap.put("end", pagingUtil.getEndCount());
		
		List<ChatMemberCommand> chatMemberSelectList = null;
		if(listCount > 0) {
			chatMemberSelectList = chatService.chatMemberSelectList(chatUtilMap);
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("chatMemberSelectList");
		mav.addObject("listCount", listCount);
		mav.addObject("chaatMemberSelectList", chatMemberSelectList);
		mav.addObject("pagingHtml", pagingUtil.getPagingHtml());
		return mav;
	}
}
