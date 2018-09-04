package kr.spring.accom.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.spring.accom.domain.HotelDetailCommand;
import kr.spring.accom.domain.ReviewCommand;
import kr.spring.accom.service.AccomDetailService;
import kr.spring.util.PagingUtil;

@Controller
public class AccomDetailAjaxController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private AccomDetailService accomDetailService;
	
	int rowCount = 2;
	int rowCount2 = 4;
	int pageCount = 1;
	
	@RequestMapping("/accomDetail/hotelRoomDetail_standard.do")
	@ResponseBody
	public Map<String,Object> viewRoomDetailStandard(@RequestParam(value="pageNum",defaultValue="1") int currentPage,@RequestParam("im_ac_num") int im_ac_num,@RequestParam("check_in") String check_in,@RequestParam("check_out") String check_out){
		
		if(log.isDebugEnabled()) {
			log.debug("<<currentPage>> : "+currentPage);
			log.debug("<<im_ac_num>> : " + im_ac_num);
			log.debug("<<check_in>> : " + check_in);
			log.debug("<<check_out>> : " + check_out);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("im_ac_num", im_ac_num);
		map.put("check_in", check_in);
		map.put("check_out", check_out);
		
		//총 스탠다드룸의 개수
		int count = accomDetailService.selectStandardRoomCount(map);
		
		//startRowNum,endRowNum을 구하기 위해 사용
		PagingUtil page = new PagingUtil(currentPage,count,rowCount,pageCount,null);//호출 url은 ajax 방식이므로 필요없음 
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<HotelDetailCommand> list = null;
		if(count > 0) {
			list = accomDetailService.selectStandardRoom(map);//map 데이터를 넘김(num,start,end 필요)
		}else {
			//null이 전달되지 않도록 list를 비워서 보내기
			list = Collections.emptyList();
		}
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);
		
		return mapJson;
	}
	@RequestMapping("/accomDetail/hotelRoomDetail_delux.do")
	@ResponseBody
	public Map<String,Object> viewRoomDetailDelux(@RequestParam(value="pageNum",defaultValue="1") int currentPage,@RequestParam("im_ac_num") int im_ac_num,@RequestParam("check_in") String check_in,@RequestParam("check_out") String check_out){
		
		if(log.isDebugEnabled()) {
			log.debug("<<currentPage>> : "+currentPage);
			log.debug("<<im_ac_num>> : " + im_ac_num);
			log.debug("<<check_in>> : " + check_in);
			log.debug("<<check_out>> : " + check_out);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("im_ac_num", im_ac_num);
		map.put("check_in", check_in);
		map.put("check_out", check_out);
		
		//총 스탠다드룸의 개수
		int count = accomDetailService.selectDeluxRoomCount(map);
		
		//startRowNum,endRowNum을 구하기 위해 사용
		PagingUtil page = new PagingUtil(currentPage,count,rowCount,pageCount,null);//호출 url은 ajax 방식이므로 필요없음 
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<HotelDetailCommand> list = null;
		if(count > 0) {
			list = accomDetailService.selectDeluxRoom(map);//map 데이터를 넘김(num,start,end 필요)
		}else {
			//null이 전달되지 않도록 list를 비워서 보내기
			list = Collections.emptyList();
		}
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);
		
		return mapJson;
	}
	@RequestMapping("/accomDetail/hotelRoomDetail_suite.do")
	@ResponseBody
	public Map<String,Object> viewRoomDetailSuite(@RequestParam(value="pageNum",defaultValue="1") int currentPage,@RequestParam("im_ac_num") int im_ac_num,@RequestParam("check_in") String check_in,@RequestParam("check_out") String check_out){
		
		if(log.isDebugEnabled()) {
			log.debug("<<currentPage>> : "+currentPage);
			log.debug("<<im_ac_num>> : " + im_ac_num);
			log.debug("<<check_in>> : " + check_in);
			log.debug("<<check_out>> : " + check_out);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("im_ac_num", im_ac_num);
		map.put("check_in", check_in);
		map.put("check_out", check_out);
		
		//총 스탠다드룸의 개수
		int count = accomDetailService.selectSuiteRoomCount(map);
		
		//startRowNum,endRowNum을 구하기 위해 사용
		PagingUtil page = new PagingUtil(currentPage,count,rowCount,pageCount,null);//호출 url은 ajax 방식이므로 필요없음 
		map.put("start", page.getStartCount());
		map.put("end", page.getEndCount());
		
		List<HotelDetailCommand> list = null;
		if(count > 0) {
			list = accomDetailService.selectSuiteRoom(map);//map 데이터를 넘김(num,start,end 필요)
		}else {
			//null이 전달되지 않도록 list를 비워서 보내기
			list = Collections.emptyList();
		}
		
		Map<String,Object> mapJson = new HashMap<String,Object>();
		mapJson.put("count", count);
		mapJson.put("rowCount", rowCount);
		mapJson.put("list", list);
		
		return mapJson;
	}
	
		//후기 작성
		@RequestMapping("/accomDetail/writeReview.do")
		@ResponseBody//JSON 문자열 생성
		public Map<String, String> writeReview(ReviewCommand reviewCommand,HttpSession session, HttpServletRequest request){
			
			if(log.isDebugEnabled()) {
				log.debug("<<re_acc_num>> : "+ reviewCommand.getRe_acc_num());
				log.debug("<<re_email>> : "+ reviewCommand.getRe_email());
				log.debug("<<re_content>> : "+ reviewCommand.getRe_content());
			}
			
			//map으로 반환(객체 형태로 만들어짐)
			Map<String, String> map = new HashMap<String, String>();
			String user_email = (String)session.getAttribute("user_email");
			
			Map<String,Object> mapRv = new HashMap<String,Object>();
			mapRv.put("re_acc_num", reviewCommand.getRe_acc_num());
			mapRv.put("re_email", reviewCommand.getRe_email());
			
			HotelDetailCommand rvReview = accomDetailService.selectRvReivew(mapRv);
			
			if(user_email == null) {
				//로그아웃
				map.put("result", "logout");
			}else if(user_email != null && rvReview != null){//로그인되어 있고, 해당 호텔을 현재 날짜 이전에 예약한 경우
				//로그인

				//IP 저장
				String re_ip = request.getRemoteAddr();
				
				mapRv.put("re_content", reviewCommand.getRe_content());
				mapRv.put("re_ip", re_ip);
				//댓글 등록
				accomDetailService.insertReview(mapRv);

				map.put("result", "success");
			}else {
				map.put("result", "not");
			}
			return map;
		}
		
		//후기 목록
		@RequestMapping("/accomDetail/listReview.do")
		@ResponseBody
		public Map<String,Object> getList(@RequestParam(value="pageNum",defaultValue="1")int currentPage,@RequestParam("im_ac_num") int im_ac_num){
			
			if(log.isDebugEnabled()) {
				log.debug("<<currentPage>> : "+currentPage);
				log.debug("<<im_ac_num>> : " + im_ac_num);
			}
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("im_ac_num", im_ac_num);
			
			//총 글의 갯수
			int count = accomDetailService.selectReviewCount(map);//map 데이터를 넘김(num의 정보 필요)
			
			//startRowNum,endRowNum을 구하기 위해 사용
			PagingUtil page = new PagingUtil(currentPage,count,rowCount2,pageCount,null);//호출 url은 ajax 방식이므로 필요없음 
			map.put("start", page.getStartCount());
			map.put("end", page.getEndCount());
			
			
			List<ReviewCommand> list = null;
			if(count > 0) {
				list = accomDetailService.selectListReview(map);//map 데이터를 넘김(num,start,end 필요)
			}else {
				//null이 전달되지 않도록 list를 비워서 보내기
				list = Collections.emptyList();
			}
			
			Map<String,Object> mapJson = new HashMap<String,Object>();
			mapJson.put("count", count);
			mapJson.put("rowCount", rowCount2);
			mapJson.put("list", list);
			
			return mapJson;
		}
		
		//후기 삭제
		@RequestMapping("/accomDetail/deleteReview.do")
		@ResponseBody
		public Map<String,String> deleteReview(@RequestParam("re_num") int re_num,@RequestParam("re_email") String re_email,HttpSession session){
			
			if(log.isDebugEnabled()) {
				log.debug("<<re_num>> : "+re_num);
				log.debug("<<re_email>> : " + re_email);
			}
			
			Map<String,String> map = new HashMap<String,String>();
			
			String user_email = (String)session.getAttribute("user_email");
			if(user_email == null) {
				//로그인이 되어 있지 않음
				map.put("result", "logout");
			}else if(user_email != null && user_email.equals(re_email)) {
				//로그인이 되어 있고 로그인한 아이디와 작성자 아이디 일치
				accomDetailService.deleteReview(re_num);
				map.put("result", "success");
			}else {//로그인 아이디와 작성자 아이디 
				map.put("result", "wrongAccess");
			}
			
			return map;
		}
		//후기 수정
		@RequestMapping("/accomDetail/updateReview.do")
		@ResponseBody
		public Map<String,String> modifyReview(@ModelAttribute("reviewCommand") ReviewCommand reviewCommand,HttpSession session,HttpServletRequest request){
			
			if(log.isDebugEnabled()) {
				log.debug("<<reviewCommand>> : "+reviewCommand);
			}
			
			Map<String,String> map = new HashMap<String,String>();
			
			String user_email = (String)session.getAttribute("user_email");
			
			if(user_email == null) {
				//로그인이 안되어 있는 경우
				map.put("result", "logout");
			}else if(user_email != null && user_email.equals(reviewCommand.getRe_email())) {
				//로그인 아이디와 작성자 아이디 일치
				//ip 등록
				reviewCommand.setRe_ip(request.getRemoteAddr());
				
				//댓글 수정
				accomDetailService.updateReview(reviewCommand);
				map.put("result", "success");
			}else { 
				//로그인 아이디와 작성자 아이디 불일치
				map.put("result", "wrongAccess");
			}
			
			return map;
		}
		
		//별점 등록
		@RequestMapping("/accomDetail/starReview.do")
		@ResponseBody
		public Map<String,String> starReview(@RequestParam("ag_grade") String ag_grade,@RequestParam("ag_acc_num") int ag_acc_num,@RequestParam("ag_email") String ag_email,HttpSession session){
			
			if(log.isDebugEnabled()) {
				log.debug("<<ag_grade>> : "+ag_grade);
				log.debug("<<ag_acc_num>> : " + ag_acc_num);
				log.debug("<<ag_email>> : " + ag_email);
			}
			
			Map<String,String> map = new HashMap<String,String>();
			
			String user_email = (String)session.getAttribute("user_email");
			
			if(user_email == null) {
				map.put("result", "logout");
			}else if(user_email != null && ag_grade != null) {
				
				Map<String,Object> mapGrade = new HashMap<String,Object>();
				mapGrade.put("ag_acc_num", ag_acc_num);
				mapGrade.put("ag_email", ag_email);
				
				ReviewCommand reviewCommand = accomDetailService.selectStarGrade(mapGrade);
				
				mapGrade.put("ag_grade", ag_grade);
				
				if(reviewCommand != null) {
					accomDetailService.updateStarGrade(mapGrade);
				}else {
					accomDetailService.insertStarGrade(mapGrade);
				}
				
				map.put("result", "success");
			}else { 
				map.put("result", "failed");
			}
			
			return map;
		}
		
		//좋아요 등록
		@RequestMapping("/accomDetail/likeReview.do")
		@ResponseBody
		public Map<String,String> likeReview(@RequestParam("al_re_num") int al_re_num,@RequestParam("al_acc_num") int al_acc_num,@RequestParam("al_email") String al_email,@RequestParam("re_email") String re_email,HttpSession session,Model model){
			
			if(log.isDebugEnabled()) {
				log.debug("<<al_re_num>> : "+al_re_num);
				log.debug("<<al_acc_num>> : " + al_acc_num);
				log.debug("<<al_email>> : " + al_email);
				log.debug("<<re_email>> : " + re_email);
			}
			
			Map<String,String> map = new HashMap<String,String>();
			
			String user_email = (String)session.getAttribute("user_email");
			
			if(user_email == null) {
				map.put("result", "logout");
			}else if(al_email.equals(re_email)) {
				map.put("result", "unable");
			}else if(user_email != null && al_email != null) {
				
				Map<String,Object> mapLike = new HashMap<String,Object>();
				
				mapLike.put("al_re_num", al_re_num);
				mapLike.put("al_acc_num", al_acc_num);
				mapLike.put("al_email", al_email);
				
				ReviewCommand reviewCommand = accomDetailService.selectLikeList(mapLike);
				
				if(reviewCommand != null) {
					accomDetailService.deleteLike(mapLike);
					map.put("result", "cancel");
					
				}else {
					accomDetailService.insertLike(mapLike);
					map.put("result", "success");
				}
				
			}else { 
				map.put("result", "failed");
			}
			
			return map;
		}
		
		@RequestMapping("/accomDetail/datePicker.do")
		@ResponseBody
		public Object datePicker(@RequestParam("im_ac_num") int im_ac_num){
			List<String> startList = accomDetailService.selectRvDateStart(im_ac_num);
			List<String> endList = accomDetailService.selectRvDateEnd(im_ac_num);
			
			if(log.isDebugEnabled()) {
				log.debug("<<im_ac_num>> : "+im_ac_num);
				log.debug("<<startList>> : "+startList);
				log.debug("<<endList>> : " + endList);
			}
			
			Map<String,Object> result = new HashMap<String,Object>();
			result.put("startList", startList);
			result.put("endList",endList);
			
			return result;
		}
}
