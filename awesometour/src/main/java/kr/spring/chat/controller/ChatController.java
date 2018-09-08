package kr.spring.chat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.chat.domain.ChatAllAndMemberCommand;
import kr.spring.chat.domain.ChatAllCommand;
import kr.spring.chat.domain.ChatAllTalkCommand;
import kr.spring.chat.domain.ChatMemberCommand;
import kr.spring.chat.service.ChatService;

@Controller
public class ChatController {
	
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
	
	//Chat_member list select
	@RequestMapping(value="/chat/selectChatMemberList.do")
	public ModelAndView selectChatMemberList(HttpSession session) {
		String user_email = (String)session.getAttribute("user_email");

		Map<String, Object> selectChatMemberListMap = new HashMap<String, Object>();
		selectChatMemberListMap.put("user_email", user_email);

		int listCount = chatService.SelectChatMemberListRowCount(user_email);
		
		//Friend chat setting(1:1)
		selectChatMemberListMap.put("group_num", -1);
		List<ChatAllAndMemberCommand> selectChatMemberList = chatService.selectChatMemberList(selectChatMemberListMap);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("selectChatMemberList");
		mav.addObject("listCount", listCount);
		mav.addObject("selectChatMemberList", selectChatMemberList);
		mav.addObject("selected", "mainChat");
		return mav;
	}
	
//	//Chat room make
//	@RequestMapping(value="/chat/insertChatAll.do", method=RequestMethod.GET)
//	public String insertChatAllForm(HttpSession session, Model model) {
//		return "/chat/insertChatAll";
//	}
//	
//	@RequestMapping(value="/chat/insertChatAll.do", method=RequestMethod.POST)
//	public String insertChatAllSubmit(@ModelAttribute("chatAllCommand") @Valid ChatAllCommand chatAllCommand, BindingResult bindingResult, HttpSession session, Model model) {
//		String user_email = (String)session.getAttribute("user_email");
//
//		System.out.println("<<ChatAllComman.toString()>> :: " + chatAllCommand.toString());
//
//		chatAllCommand.setChat_all_member_list(user_email);
//		chatService.insertChatAll(chatAllCommand);
//		int chat_all_num = chatAllCommand.getChat_all_num();
//
//		Map<String, Object> chatNoticeMap = new HashMap<String, Object>();
//		chatNoticeMap.put("chat_all_num", chat_all_num);
//		chatService.updateChatAllNotice(chatNoticeMap);
//
//		ChatMemberCommand chatMemberCommand = new ChatMemberCommand();
//		chatMemberCommand.setMember_email(user_email);
//		chatMemberCommand.setChat_member_num(chat_all_num);
//		chatService.insertChatMember(chatMemberCommand);
//
//		return "redirect:/chat/selectChatMemberList.do";
//	}
	
	@RequestMapping(value="/chat/selectChatAllJoin.do", method=RequestMethod.GET)
	public ModelAndView selectChatAllJoin(@RequestParam("chat_all_num") int chat_all_num, @RequestParam("chat_all_num_overlap")int chat_all_num_overlap, HttpSession session) {
		String user_email = (String)session.getAttribute("user_email");
		Map<String, Object> selectChatAllJoinMap = new HashMap<String, Object>();
		selectChatAllJoinMap.put("user_email", user_email);
		selectChatAllJoinMap.put("chat_all_num", chat_all_num);
		
		ChatAllCommand chatAllCommand = chatService.selectChatAllJoin(selectChatAllJoinMap);
		Map<String, Object> changeMap = new HashMap<String, Object>();
		changeMap.put("chat_all_num", chat_all_num);
		changeMap.put("user_email", user_email);
		chatService.updateChatAllJoinChange(changeMap);
//		int checkedJoinStatus = chatService.selectChatMemberCheckedJoin(selectChatAllJoinMap);
//		
//		//Join overlap exception
//		if(checkedJoinStatus == 1) {
//			return new ModelAndView("redirect:/chat/selectChatMemberList.do");
//		}
		
		int listCount = chatService.SelectChatMemberListRowCount(user_email);
		
		ModelAndView mav = new ModelAndView();
		if(listCount > 0) {
			List<ChatAllAndMemberCommand> selectChatAllAndMemberList = chatService.selectChatMemberList(selectChatAllJoinMap);
			mav.addObject("selectChatMemberList", selectChatAllAndMemberList);
		}
		
		System.out.println("Controller session(user_email) :: " + session.getAttribute("user_email"));
		
		//Select chat message log
		List<ChatAllTalkCommand> chatAllTalkCommandList = chatService.selectChatAallTalkMessageLog(chat_all_num);
		System.out.println(chatAllTalkCommandList.toString());
		
		mav.setViewName("selectChatAllJoin");
		mav.addObject("listCount", listCount);
		mav.addObject("chatAllCommand", chatAllCommand);
		mav.addObject("chatAllTalkCommandList", chatAllTalkCommandList);
		mav.addObject("chat_all_num_overlap", chat_all_num);
		mav.addObject("selected", "friendChatList");
		return mav;
	}
	
	//Add friend
	@RequestMapping(value="/chat/insertChatFriendAdd.do", method=RequestMethod.GET)
	public ModelAndView insertAddFriendForm(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		String user_email = (String)session.getAttribute("user_email");
		
		Map<String, Object> selectChatMemberListMap = new HashMap<String, Object>();
		selectChatMemberListMap.put("user_email", user_email);
		
		int listCount = chatService.SelectChatMemberListRowCount(user_email);
		
		//Friend chat setting(1:1)
		selectChatMemberListMap.put("group_num", -1);
		List<ChatAllAndMemberCommand> selectChatMemberList = chatService.selectChatMemberList(selectChatMemberListMap);
		mav.setViewName("insertChatFriendAdd");
		mav.addObject("listCount", listCount);
		mav.addObject("selectChatMemberList", selectChatMemberList);
		mav.addObject("selected", "settingList");
		return mav;
	}
	
	@RequestMapping(value="/chat/insertChatFriendAdd.do", method=RequestMethod.POST)
	public String insertAddFriendSubmit() {
		return "/chat/insertChatFriendAdd";
	}
}
