package kr.spring.accom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GroupController {
	@RequestMapping("/group.do")
	public String form() {
		return "group";
	}
	
}
