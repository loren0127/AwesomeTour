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
import kr.spring.util.StringUtil;

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

	private ModelAndView chatModelAndView(String viewName, HttpSession session, int sendCurrentPage,
			int receiveCurrentPage, String selected) {
		// My chat list start
		String user_email = (String) session.getAttribute("user_email");
		Map<String, Object> selectChatMemberListMap = new HashMap<String, Object>();
		selectChatMemberListMap.put("user_email", user_email);
		int listCount = chatService.SelectChatMemberListRowCount(user_email);
		List<ChatAllAndMemberCommand> selectChatMemberList = chatService.selectChatMemberList(selectChatMemberListMap);
		// My chat list end

		// My send message list start
		Map<String, Object> selectMap = new HashMap<String, Object>();
		selectMap.put("type", "send");
		selectMap.put("user_email", user_email);
		int sendMessageCount = messageService.selectMessageRowCount(selectMap);

		PagingUtil sendPage = new PagingUtil(sendCurrentPage, sendMessageCount, rowCount, pageCount,
				"chatFunctionResult.do", "&selected=sendMessageList");
		selectMap.put("start", sendPage.getStartCount());
		selectMap.put("end", sendPage.getEndCount());

		List<MessageCommand> sendMessageList = null;
		if (sendMessageCount > 0) {
			sendMessageList = messageService.selectMessageList(selectMap);
		} else {
			sendMessageList = Collections.emptyList();
		}
		// My send message list end

		// My receive message list start
		selectMap.remove("type");
		selectMap.put("type", "receive");
		int receiveMessageCount = messageService.selectMessageRowCount(selectMap);

		PagingUtil receivePage = new PagingUtil(sendCurrentPage, receiveMessageCount, rowCount, pageCount,
				"chatFunctionResult.do", "&selected=receiveMessageList");
		selectMap.put("start", receivePage.getStartCount());
		selectMap.put("end", receivePage.getEndCount());

		List<MessageCommand> receiveMessageList = null;
		if (receiveMessageCount > 0) {
			receiveMessageList = messageService.selectMessageList(selectMap);
		} else {
			receiveMessageList = Collections.emptyList();
		}
		// My receive message list end
		
		//Friend email select(Picture)
		//String friend_email = chatService.selectChatMemberUserEmail(map);
		//

		ModelAndView mav = new ModelAndView();

		// Moving view name
		mav.setViewName(viewName);

		// My send message list
		mav.addObject("sendMessageCount", sendMessageCount);
		mav.addObject("sendMessagePagingHtml", sendPage.getPagingHtml());
		mav.addObject("sendMessageList", sendMessageList);

		// My receive message list
		mav.addObject("receiveMessageCount", receiveMessageCount);
		mav.addObject("receivePagingHtml", receivePage.getPagingHtml());
		mav.addObject("receiveMessageList", receiveMessageList);

		mav.addObject("listCount", listCount);
		mav.addObject("selectChatMemberList", selectChatMemberList);

		if (selected.equals("friend")) {
			mav.addObject("selected", "friendChatList");
		} else if (selected.equals("group")) {
			mav.addObject("selected", "groupChatList");
		} else {
			mav.addObject("selected", selected);
		}

		return mav;
	}

	// Chat_member list select
	@RequestMapping(value = "/chat/chatFunctionResult.do")
	public ModelAndView chatFunctionResultForm(@RequestParam(value = "pageNum", defaultValue = "1") int sendCurrentPage,
			@RequestParam(value = "pageNum", defaultValue = "1") int receiveCurrentPage, HttpSession session,
			@RequestParam(value = "selected", defaultValue = "mainChat") String selected) {
		ModelAndView mav = new ModelAndView();
		mav = chatModelAndView("chatFunctionResult", session, sendCurrentPage, receiveCurrentPage, selected);
		mav.addObject("chatResult", "chatMain");
		return mav;
	}

	@RequestMapping(value = "/chat/selectChatAllJoin.do", method = RequestMethod.GET)
	public ModelAndView selectChatAllJoin(@RequestParam("chat_all_num") int chat_all_num,
			@RequestParam(value = "pageNum", defaultValue = "1") int sendCurrentPage,
			@RequestParam(value = "pageNum", defaultValue = "1") int receiveCurrentPage,
			@RequestParam("checked") String checked, HttpSession session) {
		String user_email = (String) session.getAttribute("user_email");
		String user_nickname = (String) session.getAttribute("user_nickname");

		// Chat join date update start
		Map<String, Object> changeMap = new HashMap<String, Object>();
		changeMap.put("chat_all_num", chat_all_num);
		changeMap.put("user_email", user_email);
		chatService.updateChatAllJoinChange(changeMap);
		// Chat join date update end

		// Select chat message log start
		List<ChatAllTalkCommand> chatAllTalkCommandList = chatService.selectChatAallTalkMessageLog(chat_all_num);
		// Select chat message log end

		ModelAndView mav = chatModelAndView("selectChatAllJoin", session, sendCurrentPage, receiveCurrentPage, checked);
		mav.addObject("chatAllTalkCommandList", chatAllTalkCommandList);
		return mav;
	}

	// Message type: chatInvite
	@RequestMapping(value = "/chat/insertFriendChatSendMessage.do")
	public ModelAndView insertFriendChatSendMessage(@ModelAttribute("messageCommand") MessageCommand messageCommand,
			@RequestParam(value = "pageNum", defaultValue = "1") int sendCurrentPage,
			@RequestParam(value = "pageNum", defaultValue = "1") int receiveCurrentPage, HttpSession session) {
		String user_email = (String) session.getAttribute("user_email");
		
		Map<String, Object> checkedMap = new HashMap<String, Object>();
		checkedMap.put("message_sender", user_email);
		checkedMap.put("message_receiver", messageCommand.getMessage_receiver());
		
		int checked_count = chatService.selectChatOverlapChecked(checkedMap);
		
		if(checked_count != 2 || user_email.equals(messageCommand.getMessage_receiver())) {
		
			messageCommand.setMessage_sender(user_email);
			messageCommand.setMessage_content(StringUtil.useBrNoHtml(messageCommand.getMessage_content()));
			messageCommand.setMessage_title(StringUtil.useBrNoHtml(messageCommand.getMessage_title()));
			messageCommand.setMessage_type("chatInvite");
			messageCommand.setMessage_URL("/awesometour/chat/insertFriendChatAll.do?message_sender=" + user_email);
	
			messageService.insertMessageSend(messageCommand);
			
			ModelAndView mav = new ModelAndView();
			mav = chatModelAndView("chatFunctionResult", session, sendCurrentPage, receiveCurrentPage, "friendChatList");
			mav.addObject("chatResult", "sendChatInvaite");
			return mav;
		} else {
			ModelAndView mav = new ModelAndView();
			mav = chatModelAndView("chatFunctionResult", session, sendCurrentPage, receiveCurrentPage, "friendChatList");
			mav.addObject("chatResult", "sendChatInvaiteFailed");
			return mav;
		}
		
	}

	@RequestMapping(value = "/chat/insertFriendChatAll.do", method=RequestMethod.GET)
	public ModelAndView insertFriendChatAll(HttpSession session,
			@RequestParam(value = "pageNum", defaultValue = "1") int sendCurrentPage,
			@RequestParam(value = "pageNum", defaultValue = "1") int receiveCurrentPage,
			@RequestParam("message_sender") String message_sender) {
		
		String user_email = (String) session.getAttribute("user_email");

		ChatAllCommand chatAllCommand = new ChatAllCommand();
		chatAllCommand.setChat_all_title(message_sender + "(¹æÀå) / " + user_email);

		// Create chat room and return chat_all_num
		chatService.insertFriendChatCreate(chatAllCommand);
		
		int chat_all_num = chatService.selectChatAllNum(chatAllCommand.getChat_all_title());
		
		System.out.println("chat_all_num : " + chat_all_num);
		ChatMemberCommand chatMemberCommand = new ChatMemberCommand();

		chatMemberCommand.setMember_email(user_email);
		chatMemberCommand.setChat_all_num_member(chat_all_num);
		// Receiver chat joining
		chatService.insertChatMember(chatMemberCommand);
		System.out.println("receiver chat joining");

		chatMemberCommand.setMember_email(message_sender);
		// Sender chat joining
		chatService.insertChatMember(chatMemberCommand);
		System.out.println("sender chat joining");
		ModelAndView mav = chatModelAndView("insertMessageSend", session, sendCurrentPage, receiveCurrentPage, "sendMessageList");
		return mav;
	}

	@RequestMapping(value = "/chat/insertMessageSend.do", method = RequestMethod.GET)
	public ModelAndView insertMessageSendForm(HttpSession session,
			@RequestParam(value = "pageNum", defaultValue = "1") int sendCurrentPage,
			@RequestParam(value = "pageNum", defaultValue = "1") int receiveCurrentPage) {
		ModelAndView mav = chatModelAndView("insertMessageSend", session, sendCurrentPage, receiveCurrentPage, "sendMessageList");
		return mav;
	}

	// Message type: normal
	@RequestMapping(value = "/chat/insertMessageSend.do", method = RequestMethod.POST)
	public ModelAndView insertMessageSendSubmit(HttpSession session,
			@ModelAttribute("messageCommand") MessageCommand messageCommand,
			@RequestParam(value = "pageNum", defaultValue = "1") int sendCurrentPage,
			@RequestParam(value = "pageNum", defaultValue = "1") int receiveCurrentPage) {
		String user_email = (String) session.getAttribute("user_email");
		messageCommand.setMessage_sender(user_email);
		// Message send start
		messageCommand.setMessage_content(StringUtil.useBrNoHtml(messageCommand.getMessage_content()));
		messageCommand.setMessage_title(StringUtil.useBrNoHtml(messageCommand.getMessage_title()));
		messageCommand.setMessage_type("normal");
		messageCommand.setMessage_URL("");

		messageService.insertMessageSend(messageCommand);
		// Message send end
		ModelAndView mav = chatModelAndView("chatFunctionResult", session, sendCurrentPage, receiveCurrentPage,
				"sendMessageList");
		mav.addObject("chatResult", "messageSend");
		return mav;
	}

	@RequestMapping(value = "/chat/selectMessageDetail.do", method = RequestMethod.GET)
	public ModelAndView selectMessageDetail(HttpSession session, @RequestParam(value = "message_num") int message_num,
			@RequestParam(value = "pageNum", defaultValue = "1") int sendCurrentPage,
			@RequestParam(value = "pageNum", defaultValue = "1") int receiveCurrentPage,
			@RequestParam(value = "checked", defaultValue = "mainChat") String selected) {
		String user_email = (String) session.getAttribute("user_email");

		// Message select start
		MessageCommand messageCommand = messageService.selectMessageDetail(message_num);
		// Message select end

		Map<String, Object> readMap = new HashMap<String, Object>();
		
		readMap.put("message_num", message_num);
		readMap.put("user_email", user_email);
		messageService.updateMessageRead(readMap);

		ModelAndView mav = chatModelAndView("selectMessageDetail", session, sendCurrentPage, receiveCurrentPage,
				selected);
		mav.addObject("messageCommand", messageCommand);
		return mav;
	}

	@RequestMapping(value = "/chat/updateMessageSendStatus.do")
	public ModelAndView updateMessageSendStatus(HttpSession session,
			@RequestParam(value = "message_num") Integer message_num,
			@RequestParam(value = "pageNum", defaultValue = "1") int sendCurrentPage,
			@RequestParam(value = "pageNum", defaultValue = "1") int receiveCurrentPage) {
		String user_email = (String) session.getAttribute("user_email");
		Map<String, Object> sendStatusMap = new HashMap<String, Object>();

		sendStatusMap.put("user_email", user_email);
		sendStatusMap.put("message_num", message_num);

		messageService.updateMessageSendStatus(sendStatusMap);

		MessageCommand messageCommand = messageService.selectMessageStatus(message_num);

		int send_status = messageCommand.getMessage_sende_status();
		if (send_status == -1) {
			System.out.println("message_num :: " + message_num);
			messageService.deleteMessage(message_num);
		}

		ModelAndView mav = chatModelAndView("chatFunctionResult", session, sendCurrentPage, receiveCurrentPage,
				"sendMessageList");
		mav.addObject("chatResult", "messageSend");
		return mav;
	}

	@RequestMapping(value = "/chat/updateMessageReceiveStatus.do")
	public ModelAndView updateMessageReceiveStatus(HttpSession session,
			@RequestParam(value = "message_num") int message_num,
			@RequestParam(value = "pageNum", defaultValue = "1") int sendCurrentPage,
			@RequestParam(value = "pageNum", defaultValue = "1") int receiveCurrentPage) {
		String user_email = (String) session.getAttribute("user_email");
		Map<String, Object> sendStatusMap = new HashMap<String, Object>();

		sendStatusMap.put("user_email", user_email);
		sendStatusMap.put("message_num", message_num);

		messageService.updateMessageReceiveStatus(sendStatusMap);

		MessageCommand messageCommand = messageService.selectMessageStatus(message_num);

		int receive_status = messageCommand.getMessage_receive_status();

		if (receive_status == -1) {
			messageService.deleteMessage(message_num);
		}

		ModelAndView mav = chatModelAndView("chatFunctionResult", session, sendCurrentPage, receiveCurrentPage,
				"receiveMessageList");
		mav.addObject("chatResult", "messageSend");
		return mav;
	}
}