package kr.spring.mypage.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.mypage.domain.MyPageCommand;
import kr.spring.mypage.service.MyPageService;
import kr.spring.util.PagingUtil;


@Controller
public class MypageAjaxController {
	
	@Resource
	private MyPageService mypageService;
	
	int rowCount = 5;//媛쒖닔
	int pageCount = 5;//�럹�씠吏�移댁슫�듃
	
	@RequestMapping("/mypage/complainList_ajax.do")
	@ResponseBody
	public Map<String, Object> complainList_ajax(@RequestParam(value="pageNum",defaultValue="1")int currentPage,@RequestParam("user_email")String user_email){
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_email", user_email);
		
		//珥� 而댄뵆�젅�씤 媛쒖닔
		int count = mypageService.selectRowCount(map);
		
		//start,end 援ы븯湲�
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, null);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<MyPageCommand> list = null;
		if(count > 0) {
			list = mypageService.selectMypageList(map);
		}else {
			//null�씠 �쟾�떖�릺吏� �븡寃� 鍮꾩썙�꽌 蹂대궡湲�
			list = Collections.emptyList();
		}
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);
		
		return mapJson;
	}
	
	@RequestMapping(value="/mypage/mypageReservationListAjax.do", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> mypageReservationListAjax() {
		
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		
		return jsonMap;
	}
}
