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
	//��Ʈ�ѷ� -> ���� -> DAO
	
	//����� ���
	private Logger log = Logger.getLogger(this.getClass());
	
	//�������� ����
	@Resource
	private AccomService accomService;
	
	//==========���� ������ ��õ ���� �� ���� �ҷ�����==========//
	@RequestMapping("/main/main.do")
	public String mainList(@RequestParam(value="people_count", defaultValue="1") int people_count, Model model){
		if(log.isDebugEnabled()) {
			log.debug("<<people_count>> : "+ people_count);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		//�޼ҵ� �Ķ������ �̸��� key�� �����ؾ� �Ѵ�!!!
		map.put("people_count", people_count);
		
		List<AccomCommand> recommedList = null;
		//��õ ���� ����Ʈ
		recommedList = accomService.selectRecommendList(map);
		List<ReviewCommand> reviewList = null;
		//���� ����Ʈ
		reviewList = accomService.selectReviewList();
		List<GroupCommand> groupList = null;
		//�׷� ����Ʈ
		groupList = accomService.selectGroupList();
		
		if(log.isDebugEnabled()) {
			log.debug("<<��õ����Ʈ>> : "+recommedList);
			log.debug("<<���丮��Ʈ>> : "+reviewList);
			log.debug("<<�׷츮��Ʈ>> : "+groupList);
		}
		
		model.addAttribute("recommedList", recommedList);
		model.addAttribute("reviewList", reviewList);
		model.addAttribute("groupList", groupList);
		model.addAttribute("people_count", people_count);

		return "main";//tiles �ĺ���
	}
	
	//���� �̹��� �ҷ����� --> �� �� ���
	
	//�׷� �̹��� �ҷ����� --> �׷� �� ���
}
