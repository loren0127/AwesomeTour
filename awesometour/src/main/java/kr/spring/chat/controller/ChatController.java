package kr.spring.chat.controller;

import java.util.Collections;
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
import kr.spring.chat.domain.MessageCommand;
import kr.spring.chat.service.ChatService;
import kr.spring.chat.service.MessageService;
import kr.spring.util.PagingUtil;

@Controller
public class ChatController {
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	private ChatService chatService;
	
	@Resource
	private MessageService messageService;
	
	@ModelAttribute("chatMemberCommand")
	public ChatMemberCommand initChatMemberCommand() {
		return new ChatMemberCommand();
	}
	
	@ModelAttribute("chatAllCommand")
	public ChatAllCommand initChatAllCommand() {
		return new ChatAllCommand();
	}
	
	@ModelAttribute("chatAllAndMemberCommand")
	public ChatAllAndMemberCommand initChatAllAndMemberCommand() {
		return new ChatAllAndMemberCommand();
	}
	
	@ModelAttribute("chatAllTalkCommand")
	public ChatAllTalkCommand initChatAllTalkCommand() {
		return new ChatAllTalkCommand();
	}
	
	@ModelAttribute("messageCommand")
	public MessageCommand initMessageCommand() {
		return new MessageCommand();
	}
	
	private ModelAndView chatModelAndView(String viewName, HttpSession session, int sendCurrentPage, int receiveCurrentPage) {
		//My chat list start
		String user_email = (String)session.getAttribute("user_email");
		Map<String, Object> selectChatMemberListMap = new HashMap<String, Object>();
		selectChatMemberListMap.put("user_email", user_email);
		int listCount = chatService.SelectChatMemberListRowCount(user_email);
		List<ChatAllAndMemberCommand> selectChatMemberList = chatService.selectChatMemberList(selectChatMemberListMap);
		//My chat list end

		//My send message list start
		Map<String, Object> selectMap = new HashMap<String, Object>();
		selectMap.put("type", "send");
		selectMap.put("user_email", user_email);
		int sendMessageCount = messageService.selectMessageRowCount(selectMap);
		PagingUtil sendPage = new PagingUtil(sendCurrentPage, sendMessageCount, rowCount, pageCount, "selectChatMain.do", "&selected=sendMessageList");
		selectMap.put("start", sendPage.getStartCount());
		selectMap.put("end", sendPage.getEndCount());

		List<MessageCommand> sendMessageList = null;
		if(sendMessageCount > 0) {
			sendMessageList = messageService.selectMessageList(selectMap);
		} else {
			sendMessageList = Collections.emptyList();
		}
		//My send message list end
		
		//My receive message list start
		selectMap.remove("type");
		selectMap.put("type", "receive");
		int receiveMessageCount = messageService.selectMessageRowCount(selectMap);
		PagingUtil receivePage = new PagingUtil(receiveCurrentPage, receiveMessageCount, rowCount, pageCount, "selectChatMain.do", "&selected=receiveMessageList");
		selectMap.put("start", receivePage.getStartCount());
		selectMap.put("end", receivePage.getEndCount());

		List<MessageCommand> receiveMessageList = null;
		if(receiveMessageCount > 0) {
			receiveMessageList = messageService.selectMessageList(selectMap);
		} else {
			receiveMessageList = Collections.emptyList();
		}
		//My receive message list end
		
		ModelAndView mav = new ModelAndView();
		
		//Moving view name
		mav.setViewName(viewName);
		
		//My send message list
		mav.addObject("sendMessageCount", sendMessageCount);
		mav.addObject("sendMessagePagingHtml", sendPage.getPagingHtml());
		mav.addObject("sendMessageList", sendMessageList);

		//My receive message list
		mav.addObject("receiveMessageCount", receiveMessageCount);
		mav.addObject("receivePagingHtml", receivePage.getPagingHtml());
		mav.addObject("receiveMessageList", receiveMessageList);

		mav.addObject("listCount", listCount);
		mav.addObject("selectChatMemberList", selectChatMemberList);
		mav.addObject("selected", "mainChat");
		return mav;
	}
	
	private ModelAndView chatModelAndView(String viewName, HttpSession session, int sendCurrentPage, int receiveCurrentPage, String selected) {
		//My chat list start
		String user_email = (String)session.getAttribute("user_email");
		Map<String, Object> selectChatMemberListMap = new HashMap<String, Object>();
		selectChatMemberListMap.put("user_email", user_email);
		int listCount = chatService.SelectChatMemberListRowCount(user_email);
		List<ChatAllAndMemberCommand> selectChatMemberList = chatService.selectChatMemberList(selectChatMemberListMap);
		//My chat list end
		
		//My send message list start
		Map<String, Object> selectMap = new HashMap<String, Object>();
		selectMap.put("type", "send");
		selectMap.put("user_email", user_email);
		int sendMessageCount = messageService.selectMessageRowCount(selectMap);
		
		PagingUtil sendPage = new PagingUtil(sendCurrentPage, sendMessageCount, rowCount, pageCount, "selectChatMain.do", "&selected=sendMessageList");
		selectMap.put("start", sendPage.getStartCount());
		selectMap.put("end", sendPage.getEndCount());
		
		List<MessageCommand> sendMessageList = null;
		if(sendMessageCount > 0) {
			sendMessageList = messageService.selectMessageList(selectMap);
		} else {
			sendMessageList = Collections.emptyList();
		}
		//My send message list end
		
		//My receive message list start
		selectMap.remove("type");
		selectMap.put("type", "receive");
		int receiveMessageCount = messageService.selectMessageRowCount(selectMap);
		
		PagingUtil receivePage = new PagingUtil(sendCurrentPage, receiveMessageCount, rowCount, pageCount, "selectChatMain.do", "&selected=receiveMessageList");
		selectMap.put("start", receivePage.getStartCount());
		selectMap.put("end", receivePage.getEndCount());

		List<MessageCommand> receiveMessageList = null;
		if(receiveMessageCount > 0) {
			receiveMessageList = messageService.selectMessageList(selectMap);
		} else {
			receiveMessageList = Collections.emptyList();
		}
		//My receive message list end

		ModelAndView mav = new ModelAndView();

		//Moving view name
		mav.setViewName(viewName);

		//My send message list
		mav.addObject("sendMessageCount", sendMessageCount);
		mav.addObject("sendMessagePagingHtml", sendPage.getPagingHtml());
		mav.addObject("sendMessageList", sendMessageList);

		//My receive message list
		mav.addObject("receiveMessageCount", receiveMessageCount);
		mav.addObject("receivePagingHtml", receivePage.getPagingHtml());
		mav.addObject("receiveMessageList", receiveMessageList);

		mav.addObject("listCount", listCount);
		mav.addObject("selectChatMemberList", selectChatMemberList);
		mav.addObject("selected", "mainChat");
		if(selected.equals("friend")) {
			mav.addObject("selected", "friendChatList");
		} else if(selected.equals("group")) {
			mav.addObject("selected", "groupChatList");
		} else {
			mav.addObject("selected", selected);
		}
		
		return mav;
	}
	
	//Chat_member list select
	@RequestMapping(value="/chat/selectChatMain.do")
	public ModelAndView selectChatMain(@RequestParam(value="pageNum", defaultValue="1")int sendCurrentPage, @RequestParam(value="pageNum", defaultValue="1")int receiveCurrentPage, HttpSession session, @RequestParam(value="selected", defaultValue="mainChat")String selected) {
		return chatModelAndView("selectChatMain", session, sendCurrentPage, receiveCurrentPage, selected);
	}
	
	
	@RequestMapping(value="/chat/selectChatAllJoin.do", method=RequestMethod.GET)
	public ModelAndView selectChatAllJoin(@RequestParam("chat_all_num") int chat_all_num, @RequestParam(value="pageNum", defaultValue="1")int sendCurrentPage,  @RequestParam(value="pageNum", defaultValue="1")int receiveCurrentPage, @RequestParam("checked")String checked, HttpSession session) {
		String user_email = (String)session.getAttribute("user_email");
		Map<String, Object> selectChatAllJoinMap = new HashMap<String, Object>();
		
		//Chat join log start
		selectChatAllJoinMap.put("user_email", user_email);
		selectChatAllJoinMap.put("chat_all_num", chat_all_num);
		//Chat join log end
		
		//Chat join date update start
		Map<String, Object> changeMap = new HashMap<String, Object>();
		changeMap.put("chat_all_num", chat_all_num);
		changeMap.put("user_email", user_email);
		chatService.updateChatAllJoinChange(changeMap);
		//Chat join date update end
		
		//Select chat message log start
		List<ChatAllTalkCommand> chatAllTalkCommandList = chatService.selectChatAallTalkMessageLog(chat_all_num);
		//Select chat message log end
		
		ModelAndView mav = chatModelAndView("selectChatAllJoin", session, sendCurrentPage, receiveCurrentPage, checked);
		mav.addObject("chatAllTalkCommandList", chatAllTalkCommandList);
		return mav;
	}
	
	@RequestMapping(value="/chat/insertMessageSend.do", method=RequestMethod.GET)
	public ModelAndView insertMessageSendForm(HttpSession session, @RequestParam(value="pageNum", defaultValue="1")int sendCurrentPage,  @RequestParam(value="pageNum", defaultValue="1")int receiveCurrentPage) {
		ModelAndView mav = chatModelAndView("insertMessageSend", session, sendCurrentPage, receiveCurrentPage);
		mav.addObject("selected", "sendMessageList");
		return mav;
	}
	
	@RequestMapping(value="/chat/insertMessageSend.do", method=RequestMethod.POST)
	public ModelAndView insertMessageSendSubmit(HttpSession session, @ModelAttribute("messageCommand")MessageCommand messageCommand, @RequestParam(value="pageNum", defaultValue="1")int sendCurrentPage,  @RequestParam(value="pageNum", defaultValue="1")int receiveCurrentPage) {
		String user_email = (String)session.getAttribute("user_email");
		messageCommand.setMessage_receiver(user_email);
		
		//Message send start
		messageService.insertMessageSend(messageCommand);
		//Message send end
		
		ModelAndView mav = chatModelAndView("selectChatMain", session, sendCurrentPage, receiveCurrentPage);
		mav.addObject("selected", "receiveMessageList");
		return mav;
	}
	
	@RequestMapping(value="/chat/selectMessageReceive.do", method=RequestMethod.GET)
	public ModelAndView selectMessageReceive(HttpSession session, @RequestParam(value="message_num")int message_num, @RequestParam(value="pageNum", defaultValue="1")int sendCurrentPage,  @RequestParam(value="pageNum", defaultValue="1")int receiveCurrentPage) {
		
		ModelAndView mav = chatModelAndView("selectMessageReceive", session, sendCurrentPage, receiveCurrentPage);
		
		return mav;
	}
}
