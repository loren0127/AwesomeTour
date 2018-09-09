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
import org.springframework.web.servlet.ModelAndView;

import kr.spring.group.domain.GroupCommand;
import kr.spring.group.service.GroupService;
import kr.spring.member.domain.MemberCommand;
import kr.spring.reservation.service.ReservationService;
import kr.spring.util.PagingUtilCircle;

@Controller
public class GroupController {
	
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 6;
	private int pageCount = 10;
	
	@Resource
	private ReservationService reservationService;
	@Resource
	private GroupService groupService;
	//리스트 출력(기본)
	@RequestMapping("/group/groupMain.do")
	public ModelAndView list( @RequestParam(value="pageNum",defaultValue="1") int currentPage) {
				
		
		Map<String, Object> map = null;
		map = new HashMap<String, Object>();
		int count = groupService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : "+count);
		}
		
		PagingUtilCircle page = new PagingUtilCircle(currentPage ,count, rowCount, pageCount,"groupMain.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		List<GroupCommand> list = null;
		list = groupService.selectGroupList(map);
		if(log.isDebugEnabled()) {
			log.debug("<<list>> : "+list);
		}
		GroupCommand command = new GroupCommand();
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("command",command);

		mav.setViewName("groupMain");
		mav.addObject("count",count);
		mav.addObject("groupList",list);
		mav.addObject("pagingHtml",page.getPagingHtml());
		
		return mav;
	}
	
	@RequestMapping("group/groupListFilterPaging.do")
	public ModelAndView selectGroupListFilter (@RequestParam(value="pageNum",defaultValue="1") int currentPage ,
															  @RequestParam(value="address" ,defaultValue="") String address,
															  @RequestParam(value="hobby" ,defaultValue="") String hobby,
															  @RequestParam(value="date" ,defaultValue="") String date,
															  @RequestParam(value="myGroup" ,defaultValue="") int myGroup,
															  HttpSession session,HttpServletRequest request) {
		
		String user_email = (String)session.getAttribute("user_email");
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
		
		PagingUtilCircle page = new PagingUtilCircle(currentPage ,count, rowCount, pageCount,"groupListFilterPaging.do","&&address="+address+"&&hobby="+hobby+"&&date"+date+"&&myGroup="+myGroup);
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<GroupCommand> list = null;
		list = groupService.selectGroupListFilter(map);
		if(log.isDebugEnabled()) {
			log.debug("<<list>> : "+list);
		}
		GroupCommand command = new GroupCommand();
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("command",command);

		mav.setViewName("groupMain");
		mav.addObject("count",count);
		mav.addObject("groupList",list);
		mav.addObject("pagingHtml",page.getPagingHtml());
		
		return mav;
		
		
	}
	//그룹 상세페이지 출력
	@RequestMapping("/group/groupDetail.do")
	public String detail(int g_num, Model model, HttpSession session  ) {
		int chat_count = 0;
		if(log.isDebugEnabled()) {
			log.debug("<<g_num>> : "+g_num);
		}
		GroupCommand group = groupService.selectGroupDetail(g_num);
		if(log.isDebugEnabled()) {
			log.debug("<group>> : "+group);
		}
		List<MemberCommand> memberList = groupService.selectGroupMemberList(g_num);
		if(log.isDebugEnabled()) {
			log.debug("<list>> : "+memberList);
		}
		
		String user_email = (String)session.getAttribute("user_email");
		
		if(user_email != null) {
		Map<String,Object> m_map = new HashMap<String, Object>();
		m_map.put("member_email", user_email);
		m_map.put("chat_all_num", groupService.selectGroupChatnum(g_num));
		chat_count = reservationService.selectGroupMemberCount(m_map);
		}
		if(log.isDebugEnabled()) {
			log.debug("<chat>> : "+chat_count);
		}
		model.addAttribute("group",group);
		model.addAttribute("chat",chat_count);

		model.addAttribute("memberList",memberList);

		
		return "groupDetail";
	}
	
	//이미지 출력
	@RequestMapping("/group/imageView.do")
	public ModelAndView download(@RequestParam("g_num") int g_num) {
		
		GroupCommand group = groupService.selectGroupDetail(g_num);
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("imageView");
		mav.addObject("imageFile",group.getG_image());
		mav.addObject("filename",group.getG_imageName());

		return mav;
	}
	
}
