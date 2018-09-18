package kr.spring.accom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.spring.accom.domain.AccomCommand;
import kr.spring.accom.domain.ReviewCommand;
import kr.spring.accom.service.AccomService;
import kr.spring.group.domain.GroupCommand;

@Controller
public class MainController {
	//컨트롤러 -> 서비스 -> DAO
	
	//디버그 모드
	private Logger log = Logger.getLogger(this.getClass());
	
	//의존관계 주입
	@Resource
	private AccomService accomService;
	
	//==========메인 페이지 추천 숙소 및 리뷰 불러오기==========//
	@RequestMapping("/main/main.do")
	public String mainList(@RequestParam(value="people_count", defaultValue="1") int people_count, Model model){
		if(log.isDebugEnabled()) {
			log.debug("<<people_count>> : "+ people_count);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		//메소드 파라미터의 이름과 key는 동일해야 한다!!!
		map.put("people_count", people_count);
		
		List<AccomCommand> recommedList = null;
		//추천 숙소 리스트
		recommedList = accomService.selectRecommendList(map);
		List<ReviewCommand> reviewList = null;
		//리뷰 리스트
		reviewList = accomService.selectReviewList();
		List<GroupCommand> groupList = null;
		//그룹 리스트
		groupList = accomService.selectGroupList();
		
		if(log.isDebugEnabled()) {
			log.debug("<<추천리스트>> : "+recommedList);
			log.debug("<<리뷰리스트>> : "+reviewList);
			log.debug("<<그룹리스트>> : "+groupList);
		}
		
		model.addAttribute("recommedList", recommedList);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("groupList", groupList);
		model.addAttribute("people_count", people_count);

		return "main";//tiles 식별자
	}
	
	//숙소 이미지 불러오기 --> 맵 뷰 사용
	
	//그룹 이미지 불러오기 --> 그룹 뷰 사용
}
