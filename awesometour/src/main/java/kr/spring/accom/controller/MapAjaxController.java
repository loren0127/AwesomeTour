package kr.spring.accom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.accom.domain.AccomCommand;
import kr.spring.accom.service.AccomService;

@Controller
public class MapAjaxController {
	//컨트롤러 -> 서비스 -> DAO
	
	//디버그 모드
	@SuppressWarnings("unused")
	private Logger log = Logger.getLogger(this.getClass());
	
	//의존관계 주입
	@Resource
	private AccomService accomService;
	
	//지도 주소->좌표 목록
	@RequestMapping("/main/mapSearch.do")
	@ResponseBody						//JSON 문자열 저장
	public Map<String, Object> doSearch(){
		
		List<AccomCommand> list = null;
		list = accomService.selectList();
			
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("list", list);
		
		return mapJson;
	}
	
	//==========상세(현재는 임시로 모임) 페이지==========//
	@RequestMapping(value="/awesomeMenu/group.do", method=RequestMethod.GET)
	public String callMap() {
		return "group";
	}
	
	//지도 주소->좌표 목록
	@RequestMapping(value="/awesomeMenu/group.do", method=RequestMethod.POST)
	@ResponseBody						//JSON 문자열 저장
	public Map<String, Object> getList(){

		List<AccomCommand> list = null;
		list = accomService.selectList();

		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("list", list);

		return mapJson;
	}
}
