package kr.spring.accom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MapController {
	@RequestMapping("/awesomeMenu/map.do")
	public String form() {
		return "map";
	}
	
}
