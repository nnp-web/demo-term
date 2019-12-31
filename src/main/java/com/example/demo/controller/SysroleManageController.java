package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/sys/role")
public class SysroleManageController {
	@RequestMapping("/list")
	public String touser() {
		return "System/role_index";
	}

}
