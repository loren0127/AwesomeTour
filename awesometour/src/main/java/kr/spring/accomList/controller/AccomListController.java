package kr.spring.accomList.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Select;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.accomList.domain.AccomListCommand;
import kr.spring.accomList.domain.AccomServiceCommand;
import kr.spring.accomList.service.AccomListService;
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
		public ModelAndView process (
				@RequestParam(value="pageNum",defaultValue="1") int currentPage,
				@RequestParam(value="search",defaultValue="") String search,
				@RequestParam(value="searchtype",defaultValue="") String searchtype,
				@RequestParam(value="check_in",defaultValue="") String check_in,
				@RequestParam(value="check_out",defaultValue="") String check_out,
				@RequestParam(value="people_count",defaultValue="") int people_count/*,
				@RequestParam(value="ro_price",defaultValue="") int ro_price*/
		) {	
	

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("search", search);
			map.put("searchtype", searchtype);
			map.put("check_in", check_in);
			map.put("check_out", check_out);
			map.put("people_count", people_count);
			//map.put("ro_price", ro_price);
		
			

			//총 글의 갯수 또는 검색된 글의 갯수
			int count = accomListService.selectAccomListCount(map);
			
			if(log.isDebugEnabled()) {
				log.debug("<<count>> : " + count);
			}

			PagingUtil page = new PagingUtil(currentPage, count, rowCount,pageCount,"accomList.do");

			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());

			List<AccomListCommand> list = null;
			if(count > 0) {
				list = accomListService.selectAccomlist(map);

				if(log.isDebugEnabled()) {
					log.debug("<<list>> : " + list);
				}
					
			}
			
			int se_num = 32;
			
			//편의시설 명
			String se_name = accomListService.selectAcoomSerivce(se_num);
			String se_array[] = se_name.split(",");
			
			//가격정렬 부분
	
			
			
/*			ArrayList<String> arr = new ArrayList<String>();
			
			arr.add("서울");
			arr.add("서울");
			
			HashSet<String> arr2 = new HashSet<String>(arr); // HashSet에 arr데이터 삽입
			ArrayList<String> arr3 = new ArrayList<String>(arr2); // 중복이 제거된 HashSet을 다시 ArrayList에 삽입
			
			System.out.println(arr3);*/
			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("accomList");
			mav.addObject("map",map);
			mav.addObject("count",count);
			mav.addObject("list",list);
			mav.addObject("se_array",se_array);
			

			mav.addObject("pagingHtml",page.getPagingHtml());
			
			return mav;

		}
		
		
		
}
