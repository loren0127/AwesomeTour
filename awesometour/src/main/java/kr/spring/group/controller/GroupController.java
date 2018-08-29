package kr.spring.group.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.group.domain.GroupCommand;
import kr.spring.group.service.GroupService;
import kr.spring.util.PagingUtil;

@Controller
public class GroupController {
	
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 9;
	private int pageCount = 10;
	
	@Resource
	private GroupService groupService;
	
	@RequestMapping("group/groupMain.do")
	public ModelAndView list( @RequestParam(value="pageNum",defaultValue="1") int currentPage ,
						@RequestParam(value="keyfield",defaultValue="") String keyfield ,
						@RequestParam(value="keyword",defaultValue="") String keyword) {
				
		
		Map<String, Object> map = null;
		map = new HashMap<String, Object>();
		int count = groupService.selectRowCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : "+count);
		}
		
		PagingUtil page = new PagingUtil(keyfield, keyword,currentPage ,count, rowCount, pageCount,"group/groupMain.do");
		
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		List<GroupCommand> list = null;
		list = groupService.selectGroupList(map);
		if(log.isDebugEnabled()) {
			log.debug("<<list>> : "+list);
		}
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("groupMain");
		mav.addObject("count",count);
		mav.addObject("groupList",list);
		mav.addObject("pagingHtml",page.getPagingHtml());
		
		return mav;
	}
	
	@RequestMapping("group/groupDetail.do")
	public String detail(int g_num, Model model  ) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<g_num>> : "+g_num);
		}
		GroupCommand group = groupService.selectGroupDetail(g_num);
		if(log.isDebugEnabled()) {
			log.debug("<group>> : "+group);
		}
		model.addAttribute("group",group);
		
		return "groupDetail";
	}
	
}
