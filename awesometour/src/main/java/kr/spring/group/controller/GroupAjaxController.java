package kr.spring.group.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.chat.domain.ChatAllCommand;
import kr.spring.chat.domain.ChatMemberCommand;
import kr.spring.chat.service.ChatService;
import kr.spring.group.domain.GroupCommand;
import kr.spring.group.service.GroupService;
import kr.spring.member.domain.MemberCommand;
import kr.spring.reservation.service.ReservationService;
import kr.spring.util.PagingUtil;
import kr.spring.util.PagingUtilCircle;

@Controller
public class GroupAjaxController {
	
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 6;
	private int pageCount = 10;
	@Resource
	private ReservationService reservationService;
	@Resource
	private GroupService groupService;
	@Resource
	private ChatService chatService;
	
	//리스트 출력(필터)
	@RequestMapping("group/groupListFilter.do")
	@ResponseBody
	public Map<String,Object> selectGroupListFilter (@RequestParam(value="pageNum",defaultValue="1") int currentPage ,
															  @RequestParam(value="address" ,defaultValue="") String address,
															  @RequestParam(value="hobby" ,defaultValue="") String hobby,
															  @RequestParam(value="date" ,defaultValue="") String date,
															  @RequestParam(value="myGroup" ,defaultValue="") int myGroup,
															  HttpSession session,HttpServletRequest request) {
		
		String user_email = (String)session.getAttribute("user_email");
		if(log.isDebugEnabled()) {
			log.debug("<<user_email>> : "+user_email);
		}
		if(user_email == null) {
			user_email="";
		}
		Map<String,Object > map = new HashMap<String, Object>();
		map.put("address", address);
		map.put("hobby", hobby);
		map.put("date", date);
		map.put("myGroup", myGroup);
		map.put("user_email", user_email);
		
		if(log.isDebugEnabled()) {
			log.debug("<<map>> : "+map);
		}
		
		int count = groupService.selectRowCountFilter(map);
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : "+count);
		}
		if(count <= 0) {
			map.put("result","noCount");
			return map;
		}
		
		PagingUtilCircle page = new PagingUtilCircle(currentPage ,count, rowCount, pageCount,"groupListFilterPaging.do","&&address="+address+"&&hobby="+hobby+"&&date"+date+"&&myGroup="+myGroup);
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<GroupCommand> list = null;
		list = groupService.selectGroupListFilter(map);
		if(log.isDebugEnabled()) {
			log.debug("<<list>> : "+list);
		}

		
		if(list==null) {
			map.put("result","fail");
			
		}else {
			map.put("result", "success");
			map.put("list",list );
			map.put("pagingHtml",page.getPagingHtml());
		}
		
		return map;
		
	}
	
	//그룹 추가
	@RequestMapping("/group/groupInsert.do")
	@ResponseBody
	public Map<String,String> insertGroup(GroupCommand groupCommand, HttpSession session) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<GroupCommand>> : " + groupCommand);
		}
		
		String user_email = (String)session.getAttribute("user_email");
		if(log.isDebugEnabled()) {
			log.debug("<<user_email>> : " + user_email);
		}
		Map<String,String > map = new HashMap<String, String>();

		
		if(user_email==null) {
			map.put("result","logout");
		}else {
			//그룹생성
			groupCommand.setMember_email(user_email);
			groupService.insertGroup(groupCommand);
			
			Map<String,Object> gMap = new HashMap<String, Object>();
			gMap.put("status", 1);
			gMap.put("g_name", groupCommand.getG_name());
			
			//그룹 채팅 생성
			ChatAllCommand command = new ChatAllCommand();
			command.setChat_all_title(groupCommand.getG_name());
			command.setChat_all_member_list(user_email);
			int g_num = reservationService.selectReservationGroup(gMap);
			command.setGroup_num(g_num);
			command.setChat_all_member_max(100);
			chatService.insertChatAllGroup(command);
			
			//멤버 삽입
			ChatMemberCommand memberCommand = new ChatMemberCommand();
			memberCommand.setMember_email(user_email);
			memberCommand.setChat_all_num_member(groupService.selectGroupChatnum(reservationService.selectReservationGroup(gMap)));
			Map<String,Object> m_map = new HashMap<String, Object>();
			m_map.put("member_email", user_email);
			m_map.put("chat_all_num", memberCommand.getChat_all_num_member());
			
			if(reservationService.selectGroupMemberCount(m_map)==0)		
			chatService.insertChatMember(memberCommand);

			map.put("result", "success");
		}
		
		return map;
		
	}
	
	//그룹 수정
	@RequestMapping("group/groupUpdate.do")
	@ResponseBody
	public Map<String,String> updateGroup(GroupCommand groupCommand, HttpSession session) {
		
		String user_email = (String)session.getAttribute("user_email");

		Map<String,String > map = new HashMap<String, String>();

		
		if(user_email==null) {
			map.put("result","logout");
		}else {
			groupService.updateGroup(groupCommand);
			map.put("result", "success");
		}
		
		return map;
		
	}
	
	//그룹 삭제
	@RequestMapping("group/groupDelete.do")
	@ResponseBody
	public Map<String,String> deleteGroup(int g_num, HttpSession session) {
		
		String user_email = (String)session.getAttribute("user_email");

		Map<String,String > map = new HashMap<String, String>();

		
		if(user_email==null) {
			map.put("result","logout");
		}else {
			groupService.deleteGroup(g_num);
			map.put("result", "success");
		}
		
		return map;
	}
	
	//숙소 출력
		@RequestMapping("group/recommendAccom.do")
		@ResponseBody
		public Map<String,Object> recommendAccom(String g_address1) {
			
			List<Map<String, Object>> accomMap = null;
			Map<String,Object > map = new HashMap<String, Object>();

			String[] locArray = g_address1.split(" ");
			if(log.isDebugEnabled()) {
				log.debug("<<locArray>> : " + locArray);
				log.debug("<<g_address1>> : " + g_address1);

			}
			boolean checkCount = false;
			
				for(int i = locArray.length-1; i>=0;i-- ) {
					String loc = locArray[i];
					accomMap = groupService.selecRecommendAccom(loc);
					
					if(log.isDebugEnabled()) {
						log.debug("<<accomMap>> : " + accomMap);
					}
					///////////////////map의 count를 확인//////////////////
					for(Map<String, Object> accom :  accomMap ){
						int getNum = Integer.parseInt(accom.get("COUNT").toString());
				        if( getNum!= 0) {
				        	checkCount = true;
				        }
				    }
					///////////////////////////////
					}
				if(checkCount == true) {
					map.put("result", "success");
					map.put("accomMap", accomMap);
					return map;
					
				}
				map.put("result", "fail");
				return map;
			
		}
	
		//멤버 추가
		@RequestMapping("group/groupMemberInsert.do")
		@ResponseBody
		public Map<String,String> memberInsertGroup(int g_num, HttpSession session) {
			
			String user_email = (String)session.getAttribute("user_email");

			Map<String,String > map = new HashMap<String, String>();

			if(user_email==null) {
				map.put("result","logout");
			}else {
				//멤버 삽입
				ChatMemberCommand memberCommand = new ChatMemberCommand();
				memberCommand.setMember_email(user_email);
				memberCommand.setChat_all_num_member(groupService.selectGroupChatnum(g_num));
				Map<String,Object> m_map = new HashMap<String, Object>();
				m_map.put("member_email", user_email);
				m_map.put("chat_all_num", memberCommand.getChat_all_num_member());
				
				if(reservationService.selectGroupMemberCount(m_map)==0)		
					chatService.insertChatMember(memberCommand);
				
				map.put("result", "success");
			}
			return map;
			
		}
	
	
	
}
