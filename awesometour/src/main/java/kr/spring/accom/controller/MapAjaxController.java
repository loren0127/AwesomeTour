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
	//��Ʈ�ѷ� -> ���� -> DAO
	
	//����� ���
	@SuppressWarnings("unused")
	private Logger log = Logger.getLogger(this.getClass());
	
	//�������� ����
	@Resource
	private AccomService accomService;
	
	//���� �ּ�->��ǥ ���
	@RequestMapping("/main/mapSearch.do")
	@ResponseBody						//JSON ���ڿ� ����
	public Map<String, Object> doSearch(){
		
		List<AccomCommand> list = null;
		list = accomService.selectList();
			
		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("list", list);
		
		return mapJson;
	}
	
	//==========��(����� �ӽ÷� ����) ������==========//
	@RequestMapping(value="/awesomeMenu/group.do", method=RequestMethod.GET)
	public String callMap() {
		return "group";
	}
	
	//���� �ּ�->��ǥ ���
	@RequestMapping(value="/awesomeMenu/group.do", method=RequestMethod.POST)
	@ResponseBody						//JSON ���ڿ� ����
	public Map<String, Object> getList(){

		List<AccomCommand> list = null;
		list = accomService.selectList();

		Map<String, Object> mapJson = new HashMap<String, Object>();
		mapJson.put("list", list);

		return mapJson;
	}
}
