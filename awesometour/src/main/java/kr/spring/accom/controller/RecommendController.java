package kr.spring.accom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecommendController {
	@RequestMapping("/awesomeMenu/recommend.do")
	public String form() {
		return "recommend";
	}
	
}
