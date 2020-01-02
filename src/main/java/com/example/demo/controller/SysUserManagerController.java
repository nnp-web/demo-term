package com.example.demo.controller;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Sysuser;
import com.example.demo.mapper.SysuserMapper;

@Controller
@RequestMapping("/sys/user")
//@PreAuthorize("hasAuthority('admin')")
public class SysUserManagerController {

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
	
	@RequestMapping("/add")
	@ResponseBody
	public void addUser(Sysuser user) throws UnsupportedEncodingException {
		if(Integer.valueOf(user.getGender()) == 1)
			user.setGender("男");
		user.setGender(new String("女".getBytes(),"utf-8"));
		user.setCreate_time(new Date(System.currentTimeMillis()));
		try {
			sysuserMapper.insertUser(user);
		}catch(SQLException e) {
			e.printStackTrace();
		}	
	}
}
