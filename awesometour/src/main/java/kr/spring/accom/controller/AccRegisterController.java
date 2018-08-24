package kr.spring.accom.controller;

import org.springframework.web.bind.annotation.RequestMapping;

public class AccRegisterController {
	@RequestMapping("/accregister/accregisterMain.do")
	public String form() {
		return "accregisterMain";
}

}
