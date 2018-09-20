package kr.spring.accom.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.spring.accom.domain.HotelDetailCommand;
import kr.spring.accom.domain.ImageCommand;
import kr.spring.accom.domain.PrivateDetailCommand;
import kr.spring.accom.domain.ReviewCommand;
import kr.spring.accom.service.AccomDetailService;

@Controller
public class AccomDetailController {
	
	private Logger log = Logger.getLogger(this.getClass());
	
	@Resource
	private AccomDetailService accomDetailService;
	
	//吏��슦湲�
	@RequestMapping(value="/accomDetail/insertImage.do",method=RequestMethod.GET)
	public String formImage() {
		return "accomDetail/insertImage";
	}
	
	@RequestMapping(value="/accomDetail/insertImage.do",method=RequestMethod.POST)
	public String insertImage(@ModelAttribute("image") ImageCommand image) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<imageCommand>> : "+image);
		}
		
		accomDetailService.insertImage(image);
		
		return "redirect:/main/main.do";
	}
	
	//�샇�뀛 �쟾�슜
	@RequestMapping("/accomDetail/imageView.do")
	public ModelAndView viewImage(@RequestParam("im_ac_num") int im_ac_num, @RequestParam("ro_room_num") int ro_room_num,@RequestParam("kind")String kind) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<im_ac_num>> : "+ im_ac_num);
			log.debug("<<ro_room_num>> : "+ ro_room_num);
			log.debug("<<kind>> : " + kind);
		}
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("im_ac_num", im_ac_num);
		map.put("ro_room_num", ro_room_num);
		
		HotelDetailCommand image = accomDetailService.selectHotelImage(map);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		
		if(kind.equals("im_cover")) {
			mav.addObject("imageFile", image.getIm_cover());//byte[] ���엯 �뜲�씠�꽣瑜� �꽔�뼱�빞�븿
			mav.addObject("filename", image.getIm_cover_name());
		}else if(kind.equals("im_image2")) {
			mav.addObject("imageFile", image.getIm_image2());
			mav.addObject("filename", image.getIm_image2_name());
		}else if(kind.equals("im_image3")) {
			mav.addObject("imageFile", image.getIm_image3());
			mav.addObject("filename", image.getIm_image3_name());
		}
		
		return mav;
	}
	
	//�봽�씪�씠鍮� �븯�슦�뒪 �쟾�슜
	@RequestMapping("/accomDetail/imageViewPrivate.do")
	public ModelAndView viewPrivateImage(@RequestParam("im_ac_num") int im_ac_num,@RequestParam("kind")String kind) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<im_ac_num>> : "+ im_ac_num);
			log.debug("<<kind>> : " + kind);
		}
		
		PrivateDetailCommand image = accomDetailService.selectPrivateImage(im_ac_num);
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("imageView");
		
		if(kind.equals("im_cover")) {
			mav.addObject("imageFile", image.getIm_cover());//byte[] ���엯 �뜲�씠�꽣瑜� �꽔�뼱�빞�븿
			mav.addObject("filename", image.getIm_cover_name());
		}else if(kind.equals("im_image2")) {
			mav.addObject("imageFile", image.getIm_image2());
			mav.addObject("filename", image.getIm_image2_name());
		}else if(kind.equals("im_image3")) {
			mav.addObject("imageFile", image.getIm_image3());
			mav.addObject("filename", image.getIm_image3_name());
		}
		
		return mav;
	}
	
	@RequestMapping("/accomDetail/accomDetail_hotel.do")
		public String accomDetailHotel(@RequestParam("im_ac_num") int im_ac_num,@RequestParam("check_in") String check_in,
                					   @RequestParam("check_out") String check_out,@RequestParam("people_count") int people_count,
                					   @RequestParam("search") String search,Model model) {
		
		if(log.isDebugEnabled()) {
			log.debug("<<im_ac_num>> : "+ im_ac_num);
			log.debug("<<check_in>> : "+ check_in);
			log.debug("<<check_out>> : "+ check_out);
			log.debug("<<people_count>> : "+ people_count);
			log.debug("<<search>> : "+ search);
		}
		
		//�쟾泥� �씠誘몄� 由ъ뒪�듃
		List<HotelDetailCommand> totalHotelImage = accomDetailService.selectHotelTotalImage(im_ac_num);
		model.addAttribute("totalHotelImage",totalHotelImage);
		
		//�샇�뀛 �긽�꽭 �젙蹂� 泥섎━
		HotelDetailCommand detailCommand = accomDetailService.selectHotelDetail(im_ac_num);
		model.addAttribute("hotelDetail",detailCommand);
		
		//�꽌鍮꾩뒪 諛곗뿴
		String serviceName = accomDetailService.selectService(im_ac_num);
		String[] serviceNameArray = serviceName.split(",");
		
		model.addAttribute("serviceNameArray",serviceNameArray);
		
		Random r = new Random();
		
		Set<String> serviceSet = new HashSet<String>();
		
		for(int i=0;i<=serviceNameArray.length;i++) {
			int num = r.nextInt(serviceNameArray.length);
			serviceSet.add(serviceNameArray[num]);
			
			if(i==4) break;
		}
		
		model.addAttribute("serviceSet",serviceSet);
		
		if(serviceName.contains("조식")) {
			int start = serviceName.indexOf("조식");
			String breakfast = serviceName.substring(start,start+2);
			model.addAttribute("breakfast",breakfast);
		}
		if(serviceName.contains("WIFI")) {
			int start = serviceName.indexOf("WIFI");
			String WIFI = serviceName.substring(start,start+4);
			model.addAttribute("WIFI",WIFI);
		}
		
		//�뒪�깲�떎�뱶猷�
		Map<String,Object> mapStandard = new HashMap<String,Object>();
		mapStandard.put("im_ac_num", im_ac_num);
		mapStandard.put("check_in", check_in);
		mapStandard.put("check_out", check_out);
		mapStandard.put("people_count", people_count);
		
		if(log.isDebugEnabled()) {
			log.debug("<<mapStandard>> : "+ mapStandard);
		}
		
		int countStandard = accomDetailService.selectStandardRoomCount(mapStandard);
		
		int more = 1;//�떎�쓬�럹�씠吏��엳�쓬
		if(1>=Math.ceil((double)countStandard/2)) {//�옄諛� �뒪�겕由쏀듃�� �떎瑜닿쾶 �옄諛붾뒗 臾댁“嫄� int �뿰�궛�� int�씠誘�濡� ceil�씠 �룞�옉�븯吏� �븡�쑝誘�濡� �븯�굹�쓽 媛믪쓣 double濡� 留뚮뱾�뼱�꽌 �궗�슜
			more = 0;//�떎�쓬�럹�씠吏��뾾�쓬
		}
		
		model.addAttribute("more",more);
		
		//�뵒�윮�뒪猷�
		Map<String,Object> mapDelux = new HashMap<String,Object>();
		mapDelux.put("im_ac_num", im_ac_num);
		mapDelux.put("check_in", check_in);
		mapDelux.put("check_out", check_out);
		mapDelux.put("people_count", people_count);
		int countDelux = accomDetailService.selectDeluxRoomCount(mapDelux);
		
		int more2 = 1;//�떎�쓬�럹�씠吏��엳�쓬
		if(1>=Math.ceil((double)countDelux/2)) {//�옄諛� �뒪�겕由쏀듃�� �떎瑜닿쾶 �옄諛붾뒗 臾댁“嫄� int �뿰�궛�� int�씠誘�濡� ceil�씠 �룞�옉�븯吏� �븡�쑝誘�濡� �븯�굹�쓽 媛믪쓣 double濡� 留뚮뱾�뼱�꽌 �궗�슜
			more2 = 0;//�떎�쓬�럹�씠吏��뾾�쓬
		}
		
		model.addAttribute("more2",more2);
		
		//�뒪�쐞�듃猷�
		Map<String,Object> mapSuite = new HashMap<String,Object>();
		mapSuite.put("im_ac_num", im_ac_num);
		mapSuite.put("check_in", check_in);
		mapSuite.put("check_out", check_out);
		mapSuite.put("people_count", people_count);
		int countSuite = accomDetailService.selectSuiteRoomCount(mapSuite);
		
		int more3 = 1;//�떎�쓬�럹�씠吏��엳�쓬
		if(1>=Math.ceil((double)countSuite/2)) {//�옄諛� �뒪�겕由쏀듃�� �떎瑜닿쾶 �옄諛붾뒗 臾댁“嫄� int �뿰�궛�� int�씠誘�濡� ceil�씠 �룞�옉�븯吏� �븡�쑝誘�濡� �븯�굹�쓽 媛믪쓣 double濡� 留뚮뱾�뼱�꽌 �궗�슜
			more3 = 0;//�떎�쓬�럹�씠吏��뾾�쓬
		}
		
		model.addAttribute("more3",more3);
		
		//由щ럭 
		Map<String,Object> mapReview = new HashMap<String,Object>();
		mapReview.put("im_ac_num", im_ac_num);
		int countReview = accomDetailService.selectReviewCount(mapReview);
		
		int more4 = 1;//�떎�쓬�럹�씠吏��엳�쓬
		if(1>=Math.ceil((double)countReview/4)) {//�옄諛� �뒪�겕由쏀듃�� �떎瑜닿쾶 �옄諛붾뒗 臾댁“嫄� int �뿰�궛�� int�씠誘�濡� ceil�씠 �룞�옉�븯吏� �븡�쑝誘�濡� �븯�굹�쓽 媛믪쓣 double濡� 留뚮뱾�뼱�꽌 �궗�슜
			more4 = 0;//�떎�쓬�럹�씠吏��뾾�쓬
		}
		
		model.addAttribute("more4",more4);
		
		//�룊�젏�씠 �넂�� �떎瑜� �닕�냼
		Map<String,Object> mapHotelGrade = new HashMap<String,Object>();
		mapHotelGrade.put("im_ac_num", im_ac_num);
		mapHotelGrade.put("check_in", check_in);
		mapHotelGrade.put("check_out", check_out);
		mapHotelGrade.put("search", search);
		List<HotelDetailCommand> hotelGrade = accomDetailService.selectHotelRecommendationList(mapHotelGrade);
		
		//check_in
		model.addAttribute("check_in",check_in);
		//check_out
		model.addAttribute("check_out",check_out);
		//people_count
		model.addAttribute("people_count",people_count);
		//search
		model.addAttribute("search",search);
		
		model.addAttribute("hotelGrade",hotelGrade);
		
		return "accomDetailHotel";//�떇蹂꾩옄
		}
	
		//=======�봽�씪�씠鍮� �븯�슦�뒪========
		@RequestMapping("/accomDetail/accomDetail_private.do")
		public String accomDetailPrivate(@RequestParam("im_ac_num") int im_ac_num,@RequestParam("check_in") String check_in,
				                         @RequestParam("check_out") String check_out,@RequestParam("people_count") int people_count,
				                         @RequestParam("search") String search,Model model) {
			
			if(log.isDebugEnabled()) {
				log.debug("<<im_ac_num>> : "+ im_ac_num);
				log.debug("<<check_in>> : "+ check_in);
				log.debug("<<check_out>> : "+ check_out);
				log.debug("<<people_count>> : "+ people_count);
				log.debug("<<search>> : "+ search);
			}
			
			//�봽�씪�씠鍮� �븯�슦�뒪 �씠誘몄�
			PrivateDetailCommand privateDetail = accomDetailService.selectPrivateImage(im_ac_num);
			
			//�봽�씪�씠鍮� �븯�슦�뒪 �긽�꽭
			privateDetail = accomDetailService.selectPrivateDetail(im_ac_num);
			model.addAttribute("privateDetail",privateDetail);
			
			//�꽌鍮꾩뒪 諛곗뿴
			String serviceName2 = accomDetailService.selectService(im_ac_num);
			String[] serviceNameArray2 = serviceName2.split(",");
			
			model.addAttribute("serviceNameArray2",serviceNameArray2);
			
			Random r = new Random();
			
			Set<String> serviceSet2 = new HashSet<String>();
			
			for(int i=0;i<=serviceNameArray2.length;i++) {
				int num = r.nextInt(serviceNameArray2.length);
				serviceSet2.add(serviceNameArray2[num]);
				
				if(i==4) break;
			}
			
			model.addAttribute("serviceSet2",serviceSet2);
			
			//由щ럭 
			Map<String,Object> mapReview = new HashMap<String,Object>();
			mapReview.put("im_ac_num", im_ac_num);
			int countReview = accomDetailService.selectReviewCount(mapReview);
			
			int more5 = 1;//�떎�쓬�럹�씠吏��엳�쓬
			if(1>=Math.ceil((double)countReview/4)) {//�옄諛� �뒪�겕由쏀듃�� �떎瑜닿쾶 �옄諛붾뒗 臾댁“嫄� int �뿰�궛�� int�씠誘�濡� ceil�씠 �룞�옉�븯吏� �븡�쑝誘�濡� �븯�굹�쓽 媛믪쓣 double濡� 留뚮뱾�뼱�꽌 �궗�슜
				more5 = 0;//�떎�쓬�럹�씠吏��뾾�쓬
			}
			
			model.addAttribute("more5",more5);
			
			//�룊�젏�씠 �넂�� �떎瑜� �닕�냼
			Map<String,Object> mapPrivateGrade = new HashMap<String,Object>();
			mapPrivateGrade.put("im_ac_num", im_ac_num);
			mapPrivateGrade.put("check_in", check_in);
			mapPrivateGrade.put("check_out", check_out);
			mapPrivateGrade.put("search", search);
			mapPrivateGrade.put("people_count", people_count);
			List<PrivateDetailCommand> privateGrade = accomDetailService.selectPrivateRecommendationList(mapPrivateGrade);
			
			//check_in
			model.addAttribute("check_in",check_in);
			//check_out
			model.addAttribute("check_out",check_out);
			//people_count
			model.addAttribute("people_count",people_count);
			//search
			model.addAttribute("search",search);
			
			model.addAttribute("privateGrade",privateGrade);
			
			//datepicker
			//�떆�옉�씪怨� 醫낅즺�씪
			PrivateDetailCommand privateDetail2 = accomDetailService.selectStartEndDate();
			model.addAttribute("privateDetail2", privateDetail2);
			
			//寃��깋�븳 �궇吏쒖뿉 �삁�빟�릺�뼱 �엳�쑝硫� �삁�빟踰꾪듉 disable
			Map<String,Object> disableMap = new HashMap<String,Object>();
			disableMap.put("im_ac_num", im_ac_num);
			disableMap.put("check_in", check_in);
			disableMap.put("check_out", check_out);
			int disableCount = accomDetailService.selectPrivateDisableCount(disableMap);
			
			if(log.isDebugEnabled()) {
				log.debug("<<disableCount>> : "+ disableCount);
			}
			
			model.addAttribute("disableCount",disableCount);
			
			return "accomDetailPrivate";
		}
		
}
