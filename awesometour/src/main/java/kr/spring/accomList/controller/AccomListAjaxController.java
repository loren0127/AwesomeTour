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
	
	//���ǽü� üũ ���� ajax 
	@RequestMapping("accomList/accomlistajax.do")
	@ResponseBody
	public Map<String, Object> se_name(//@RequestParam(value="se_name",defaultValue="") String se_name,
										@RequestParam(value="pageNum",defaultValue="1") int currentPage,
										@RequestParam(value="search",defaultValue="") String search, //�˻�â
										@RequestParam(value="searchtype",defaultValue="") String searchtype, //ȣ��/�����̺�����
										@RequestParam(value="check_in",defaultValue="") String check_in, //�˻����۳�¥
										@RequestParam(value="check_out",defaultValue="") String check_out, //�˻� ����¥ 
										@RequestParam(value="people_count",defaultValue="") int people_count,//�ο���
										//@RequestParam(value="ro_price",defaultValue="") int ro_price,//����
										//@RequestParam(value="avg",defaultValue="") int avg, //���� 
										@RequestParam(value="price",defaultValue="") String price){ //��������
									
	
		
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
		
		//�� ���� ���� �Ǵ� �˻��� ���� ����
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
