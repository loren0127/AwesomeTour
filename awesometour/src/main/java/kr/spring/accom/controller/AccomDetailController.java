package kr.spring.accom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccomDetailController {
	@RequestMapping("/accomList/accomList.do")
	public String form() {
		return "accomList";
	}
	
	/*@RequestMapping("/accomDetail/accomDetail_hotel.do")
	public String form() {
		return "accomDetailHotel";
	}*/
}
