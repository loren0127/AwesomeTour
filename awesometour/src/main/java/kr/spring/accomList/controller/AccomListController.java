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
	
		//========�Խ��� �� ���=========//
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
		
			

			//�� ���� ���� �Ǵ� �˻��� ���� ����
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
			
			//���ǽü� ��
			String se_name = accomListService.selectAcoomSerivce(se_num);
			String se_array[] = se_name.split(",");
			
			//�������� �κ�
	
			
			
/*			ArrayList<String> arr = new ArrayList<String>();
			
			arr.add("����");
			arr.add("����");
			
			HashSet<String> arr2 = new HashSet<String>(arr); // HashSet�� arr������ ����
			ArrayList<String> arr3 = new ArrayList<String>(arr2); // �ߺ��� ���ŵ� HashSet�� �ٽ� ArrayList�� ����
			
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
