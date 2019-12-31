package com.example.demo.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.SysMenu;
import com.example.demo.mapper.SysMenuMapper;

@Controller
@RequestMapping("/sys/menu")
public class SysMenuManagerController {
	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@RequestMapping("/list")
	public String toMenu() {		
		return "System/menu";
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public List<SysMenu> obtainMenu(){	
		List<SysMenu> s = sysMenuMapper.findAll();
		return sysMenuMapper.findAll();
	}
	

}
