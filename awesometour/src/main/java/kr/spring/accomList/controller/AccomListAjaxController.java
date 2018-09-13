package kr.spring.accomList.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.accomList.domain.AccomListCommand;
import kr.spring.accomList.service.AccomListService;
import kr.spring.util.PagingUtil;

@Controller
public class AccomListAjaxController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	AccomListService accomListService;
	
	int rowCount = 5;
	int pageCount = 0;
	
	//편의시설 체크 관련 ajax 
	@RequestMapping("accomList/accomlistajax.do")
	@ResponseBody
	public Map<String, Object> se_name(HttpServletRequest request,Model model, 
										@RequestParam(value="pageNum",defaultValue="1") int currentPage,
										@RequestParam(value="search",defaultValue="") String search, //검색창
										@RequestParam(value="searchtype",defaultValue="") String searchtype, //호텔/프라이빗구분
										@RequestParam(value="check_in",defaultValue="") String check_in, //검색시작날짜
										@RequestParam(value="check_out",defaultValue="") String check_out, //검색 끝날짜 
										@RequestParam(value="people_count",defaultValue="") int people_count,//인원수
										@RequestParam(value="price",defaultValue="") String price, //가격정렬
										@RequestParam(value="se_name",defaultValue="") String se_name,
										@RequestParam(value="hotel_grade",defaultValue="") String hotel_grade) {  
		

		List<AccomListCommand> list = null;
		String check[] = se_name.split(",");
 		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("searchtype", searchtype);
		map.put("check_in", check_in);
		map.put("check_out", check_out);
		map.put("people_count", people_count);
		map.put("price", price);
		map.put("se_name", se_name);
		List<String> checklist = Arrays.asList(check);
		map.put("checklist", checklist);
		map.put("hotel_grade", hotel_grade);
		
		if(log.isDebugEnabled()) {
			log.debug("<<pageNum>> : " + currentPage);
			log.debug("<<list>> : " + list);
			log.debug("<<search>> : " + search);
			log.debug("<<searchtype>> : " + searchtype);
			log.debug("<<check_in>> : " + check_in);
			log.debug("<<check_in>> : " + check_out);
			log.debug("<<check_out>> : " + check_out);
			log.debug("<<people_count>> : " + people_count);
			log.debug("<<price>> : " + price);
			log.debug("<<se_name>> : " + se_name);
			log.debug("<<checklist>> : " + checklist);
			log.debug("<<hotel_grade>> : " + hotel_grade);
		}
		
		
		
		//총 글의 갯수 또는 검색된 글의 갯수
		int count = accomListService.selectAccomTotallistCount(map);
		if(count%rowCount == 0) {			
			pageCount = count/rowCount;
		}else {
			pageCount = count/rowCount +1; 
		}
		
		PagingUtil page = new PagingUtil(currentPage,count,rowCount,pageCount,null);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		list = accomListService.selectAccomTotallist(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("result", "success");
		mapJson.put("list", list);
		mapJson.put("count", count);
		mapJson.put("map", map);
		mapJson.put("pageHtml", page.getPagingHtml());
		
		return mapJson;
	}
	

}
