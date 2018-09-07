package kr.spring.accomList.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.accomList.domain.AccomListCommand;
import kr.spring.accomList.service.AccomListService;

@Controller
public class AccomListAjaxController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	AccomListService accomListService;
	
	//편의시설 체크 관련 ajax 
	@RequestMapping("accomList/accomlistajax.do")
	@ResponseBody
	public Map<String, Object> se_name(//@RequestParam(value="se_name",defaultValue="") String se_name,
										@RequestParam(value="pageNum",defaultValue="1") int currentPage,
										@RequestParam(value="search",defaultValue="") String search, //검색창
										@RequestParam(value="searchtype",defaultValue="") String searchtype, //호텔/프라이빗구분
										@RequestParam(value="check_in",defaultValue="") String check_in, //검색시작날짜
										@RequestParam(value="check_out",defaultValue="") String check_out, //검색 끝날짜 
										@RequestParam(value="people_count",defaultValue="") int people_count,//인원수
										//@RequestParam(value="ro_price",defaultValue="") int ro_price,//가격
										//@RequestParam(value="avg",defaultValue="") int avg, //평점 
										@RequestParam(value="price",defaultValue="") String price){ //가격정렬
									
	
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("search", search);
		map.put("searchtype", searchtype);
		map.put("check_in", check_in);
		map.put("check_out", check_out);
		map.put("people_count", people_count);
		//map.put("avg", avg);
		map.put("price", price);

		
		List<AccomListCommand> list = null;
		list = accomListService.selectAccomTotallist(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<list>> : " + list);
		}
		if(log.isDebugEnabled()) {
			log.debug("<<search>> : " + search);
			log.debug("<<searchtype>> : " + searchtype);
			log.debug("<<check_in>> : " + check_in);
			log.debug("<<check_in>> : " + check_out);
			log.debug("<<check_out>> : " + check_out);
			log.debug("<<people_count>> : " + people_count);
			log.debug("<<price>> : " + price);
		}
		
		//총 글의 갯수 또는 검색된 글의 갯수
		int count = accomListService.selectAccomListCount(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<count>> : " + count);
		}

		
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("result", "success");
		mapJson.put("list", list);
		mapJson.put("count", count);
		mapJson.put("map", map);
		
		return mapJson;
	}
	

}
