package kr.spring.accom.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.accom.domain.AccomCommand;
import kr.spring.accom.domain.ImageCommand;
import kr.spring.accom.service.AccomService;

@Controller
public class MapAjaxController {
	//컨트롤러 -> 서비스 -> DAO
	
	//디버그 모드
	private Logger log = Logger.getLogger(this.getClass());
	
	//의존관계 주입
	@Resource
	private AccomService accomService;
	
	//지도 주소->좌표 목록
	@RequestMapping("/main/mapSearch.do")
	@ResponseBody						//JSON 문자열 저장
	public Map<String, Object> doSearch(@RequestParam(value="keyword", defaultValue="") String keyword){
		if(log.isDebugEnabled()) {
			log.debug("<<keyword>> : "+keyword);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		
		List<AccomCommand> list = null;
		list = accomService.selectList(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<검색>> : "+list);
		}
			
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("list", list);
		
		return mapJson;
	}
	
	//==========상세(현재는 임시로 모임) 페이지==========//
	/*@RequestMapping(value="/accomList/accomList.do", method=RequestMethod.GET)
	public String callMap() {
		return "accomList";
	}*/
	
	//지도 주소->좌표 목록
	@RequestMapping(value="/accomList/accomList.do", method=RequestMethod.POST)
	@ResponseBody						//JSON 문자열 저장
	public Map<String, Object> getList(@RequestParam(value="orderby", defaultValue="") String orderby, 
									   @RequestParam("check_in") String check_in, 
									   @RequestParam("check_out") String check_out, 
									   @RequestParam("people_count") int people_count, 
									   @RequestParam("search") String search){
		
		if(log.isDebugEnabled()) {
			log.debug("<<check_in>> : "+ check_in);
			log.debug("<<check_out>> : "+ check_out);
			log.debug("<<people_count>> : "+ people_count);
			log.debug("<<search>> : "+ search);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderby", orderby);//메소드 파라미터의 이름과 key는 동일해야 한다!!!
		map.put("check_in", check_in);
		map.put("check_out", check_out);
		map.put("people_count", people_count);
		map.put("search", search);

		List<AccomCommand> list = null;
		list = accomService.selectList(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<리스트>> : "+list);
		}

		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("list", list);

		return mapJson;
	}
	
	//숙소 이미지 불러오기
	@RequestMapping("/accomList/imageView.do")
	public ModelAndView viewImage(@RequestParam("im_ac_num") int im_ac_num, @RequestParam("kind") String kind) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<im_ac_num>> : "+ im_ac_num);
			log.debug("<<kind>> : " + kind);
		}

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("im_ac_num", im_ac_num);

		ImageCommand image = accomService.selectImage(im_ac_num);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");

		if(kind.equals("im_cover")) {
			mav.addObject("imageFile", image.getIm_cover());
			mav.addObject("filename", image.getIm_cover_name());
		}else if(kind.equals("im_image2")) {
			mav.addObject("imageFile", image.getIm_image2());
			mav.addObject("filename", image.getIm_image2_name());
		}
		
		if(log.isDebugEnabled()) {
			log.debug("<<im_ac_num>> : "+ im_ac_num);
			log.debug("<<kind>> : " + kind);
		}

		return mav;
	}
}
