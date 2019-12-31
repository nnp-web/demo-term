package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entity.Sysuser;
import com.example.demo.mapper.SysuserMapper;

@Controller
@RequestMapping("/sys/user")
//@PreAuthorize("hasAuthority('admin')")
public class SysuserController {

	@Autowired
	private SysuserMapper sysuserMapper;
	@RequestMapping("/list")
	//@PreAuthorize("hasAuthority('admin')")
	public String touser() {
		return "System/user_index";
	}
	
	@RequestMapping("/findAll")
	@ResponseBody
	public List<Sysuser> obtainUser() {
		return sysuserMapper.findAll();

	}
}
