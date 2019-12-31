package com.example.demo.controller;




import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SysIndexController {

	@RequestMapping("/index")
	public String enterIndex() {
		return "System/indexs";
	}
	
	@RequestMapping("/login")
	public String enterLoginPage() {
		return "System/login";
	}
	
}
