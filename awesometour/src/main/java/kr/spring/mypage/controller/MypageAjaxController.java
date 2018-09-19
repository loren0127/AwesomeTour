package kr.spring.mypage.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.mypage.domain.MyPageCommand;
import kr.spring.mypage.service.MyPageService;
import kr.spring.reservation.domain.ReservationCommand;
import kr.spring.util.PagingUtil;


@Controller
public class MypageAjaxController {
	
	@Resource
	private MyPageService mypageService;
	
	int rowCount = 5;//揶쏆뮇�땾
	int pageCount = 5;//占쎈읂占쎌뵠筌욑옙燁삳똻�뒲占쎈뱜
	
	@RequestMapping("/mypage/complainList_ajax.do")
	@ResponseBody
	public Map<String, Object> complainList_ajax(@RequestParam(value="pageNum",defaultValue="1")int currentPage,@RequestParam("user_email")String user_email){
		
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user_email", user_email);
		
		//�룯占� �뚮똾逾놅옙�쟿占쎌뵥 揶쏆뮇�땾
		int count = mypageService.selectRowCount(map);
		
		//start,end �뤃�뗫릭疫뀐옙
		PagingUtil page = new PagingUtil(currentPage, count, rowCount, pageCount, null);
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<MyPageCommand> list = null;
		if(count > 0) {
			list = mypageService.selectMypageList(map);
		}else {
			//null占쎌뵠 占쎌읈占쎈뼎占쎈┷筌욑옙 占쎈륫野껓옙 �뜮袁⑹뜖占쎄퐣 癰귣�沅→묾占�
			list = Collections.emptyList();
		}
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", 7);
		mapJson.put("list", list);
		
		return mapJson;
	}
	
	@RequestMapping(value="/mypage/mypageReservationListAjax.do", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> mypageReservationListAjax(HttpSession session, @RequestParam(value="pageNum",defaultValue="1")int currentPage) {
		String user_email = (String)session.getAttribute("user_email");
		
		int count = mypageService.selectReservationRowCount(user_email);
		
		PagingUtil pageUtil = new PagingUtil(currentPage, count, rowCount, pageCount, "mypageReservationList.do");
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("start", pageUtil.getStartCount());
		jsonMap.put("end", pageUtil.getEndCount());
		
		
		List<ReservationCommand> reservationList = null;
		if(count > 0) {
			reservationList = mypageService.selectReservationList(jsonMap);
		} else {
			reservationList = Collections.emptyList();
		}
		
		jsonMap.put("count", count);
		jsonMap.put("rowCount", rowCount);
		jsonMap.put("reservationList", reservationList);
		return jsonMap;
	}
	
	@RequestMapping("/mypage/complain_dialog2.do")
	@ResponseBody
	public Map<String, String> complainDialogInsert(@RequestParam("message_title")String message_title,
												@RequestParam("message_content")String message_content,
												@RequestParam("message_receiver")String message_receiver,
												@RequestParam("message_sender")String message_sender,
			HttpSession session,HttpServletRequest request) {
		
		
		
		Map<String,String> map = new HashMap<String, String>();
		String user_email = (String)session.getAttribute("user_email");
		
		Map<String,Object> mapper = new HashMap<String,Object>();
		mapper.put("message_title", message_title);
		mapper.put("message_content", message_content);
		mapper.put("message_sender", message_sender);
		mapper.put("message_receiver", message_receiver);
		
		
		if(user_email == null) {
			//濡쒓렇�씤�씠 �븞�릺�뼱 �엳�뒗 寃쎌슦
			map.put("result", "logout");
		}else{
			//履쎌� �쟾�넚
			map.put("result", "success");
			mypageService.insert_message1(mapper);
		}
		return map;
	}
}
