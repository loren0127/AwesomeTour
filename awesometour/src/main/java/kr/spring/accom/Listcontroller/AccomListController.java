package kr.spring.accom.Listcontroller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import kr.spring.util.PagingUtil;

@Controller
public class AccomListController {
	private Logger log = Logger.getLogger(this.getClass());
	private int rowCount = 10;
	private int pageCount = 10;
	
	@Resource
	AccomListService accomListService;
	
	//========게시판 글 목록=========//
		@RequestMapping(value="accomList/accomList.do",method=RequestMethod.GET)
		public ModelAndView process(@RequestParam(value="pageNum",defaultValue="1") int currentPage,
				@RequestParam(value="keyfield",defaultValue="") String keyfield,
				@RequestParam(value="keyword",defaultValue="") String keyword,
				@RequestParam(value="search",defaultValue="") String search,
				@RequestParam(value="searchtype",defaultValue="") String searchtype,
				@RequestParam(value="check_in",defaultValue="") String check_in,
				@RequestParam(value="check_out",defaultValue="") String check_out,
				@RequestParam(value="people_count",defaultValue="") int people_count)  {

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("keyfield", keyfield);
			map.put("keyword", keyword);
			map.put("search", search);
			map.put("searchtype", searchtype);
			map.put("check_in", check_in);
			map.put("check_out", check_out);
			map.put("people_count", people_count);
			

			//총 글의 갯수 또는 검색된 글의 갯수
			int count = accomListService.selectAccomConut(map);

			if(log.isDebugEnabled()) {
				log.debug("<<count>> : " + count);
			}

			PagingUtil page = new PagingUtil(keyfield, keyword, currentPage, count, rowCount,pageCount,"accomList.do");

			//map.put("start", page.getStartCount());
			//map.put("end", page.getEndCount());

			List<AccomListCommand> list = null;
			if(count > 0) {
				list = accomListService.selectAccomlist(map);

				if(log.isDebugEnabled()) {
					log.debug("<<list>> : " + list);
				}
					
			}

			ModelAndView mav = new ModelAndView();
			mav.setViewName("accomList");
			mav.addObject("count",count);
			mav.addObject("list",list);
			mav.addObject("pagingHtml",page.getPagingHtml());
			
			return mav;

		}
}
