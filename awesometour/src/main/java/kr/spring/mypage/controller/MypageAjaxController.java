package kr.spring.mypage.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.mypage.domain.MyPageCommand;
import kr.spring.mypage.service.MyPageService;
import kr.spring.util.PagingUtil;


@Controller
public class MypageAjaxController {
	
	@Resource
	private MyPageService mypageService;
	
	int rowCount = 5;//개수
	int pageCount = 5;//페이지카운트
	
	@RequestMapping("/mypage/complainList_ajax.do")
	@ResponseBody
	public Map<String, Object> complainList_ajax(@RequestParam(value="pageNum",defaultValue="1")int currentPage,@RequestParam("user_email")String user_email){
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_email", user_email);
		
		//총 컴플레인 개수
		int count = mypageService.selectRowCount(map);
		
		//start,end 구하기
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, null);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<MyPageCommand> list = null;
		if(count > 0) {
			list = mypageService.selectMypageList(map);
		}else {
			//null이 전달되지 않게 비워서 보내기
			list = Collections.emptyList();
		}
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);
		
		return mapJson;
	}
}
