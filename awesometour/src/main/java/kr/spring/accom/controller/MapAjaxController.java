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
	//��Ʈ�ѷ� -> ���� -> DAO
	
	//����� ���
	private Logger log = Logger.getLogger(this.getClass());
	
	//�������� ����
	@Resource
	private AccomService accomService;
	
	//==========�������� �˻���� �˻�==========//
	@RequestMapping("/main/mapSearch.do")
	@ResponseBody						//JSON ���ڿ� ����
	public Map<String, Object> doSearch(@RequestParam(value="check_in", defaultValue="") String check_in, 
										@RequestParam(value="check_out", defaultValue="") String check_out, 
										@RequestParam(value="people_count", defaultValue="1") int people_count, 
										@RequestParam(value="search", defaultValue="") String search){
		
		if(log.isDebugEnabled()) {
			log.debug("<<check_in>> : "+ check_in);
			log.debug("<<check_out>> : "+ check_out);
			log.debug("<<people_count>> : "+ people_count);
			log.debug("<<search>> : "+search);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("check_in", check_in);
		map.put("check_out", check_out);
		map.put("people_count", people_count);
		map.put("search", search);
		
		List<AccomCommand> list = null;
		list = accomService.selectList(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<�˻�>> : "+list);
			log.debug("<<people_count>> : "+ people_count);
		}
			
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("list", list);
		mapJson.put("people_count", people_count);//�ٺ��� ���⼭ �־���� �����Ͱ� �Ѿ��(��)
		
		return mapJson;
	}
	
	//==========�󼼰� �Ѿ���� ������==========//
	@RequestMapping(value="/accomList/accomList.do", method=RequestMethod.GET)
	public String callMap() {
		return "accomList";
	}
	
	//���� �ּ�->��ǥ ���
	@RequestMapping(value="/accomList/accomList.do", method=RequestMethod.POST)
	@ResponseBody						//JSON ���ڿ� ����
	public Map<String, Object> getList(@RequestParam(value="orderby", defaultValue="") String orderby,  
									   @RequestParam(value="check_in", defaultValue="") String check_in, 
									   @RequestParam(value="check_out", defaultValue="") String check_out, 
									   @RequestParam(value="people_count", defaultValue="1") int people_count, 
									   @RequestParam(value="search", defaultValue="") String search){
		
		if(log.isDebugEnabled()) {
			log.debug("<<check_in>> : "+ check_in);
			log.debug("<<check_out>> : "+ check_out);
			log.debug("<<people_count>> : "+ people_count);
			log.debug("<<search>> : "+ search);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("orderby", orderby);//�޼ҵ� �Ķ������ �̸��� key�� �����ؾ� �Ѵ�!!!
		map.put("check_in", check_in);
		map.put("check_out", check_out);
		map.put("people_count", people_count);
		map.put("search", search);

		List<AccomCommand> list = null;
		list = accomService.selectList(map);
		
		if(log.isDebugEnabled()) {
			log.debug("<<��>> : "+list);
		}

		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("list", list);

		return mapJson;
	}
	
	//���� �̹��� �ҷ�����
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
		
		return mav;
	}
}
