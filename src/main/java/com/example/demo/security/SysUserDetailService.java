package com.example.demo.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.entity.Sysroler;
import com.example.demo.entity.Sysuser;
import com.example.demo.mapper.SysrolerMapper;
import com.example.demo.mapper.SysuserMapper;


@Component
public class SysUserDetailService implements UserDetailsService {
	@Autowired
	private SysuserMapper sysuserMapper;
	
	@Autowired
	private SysrolerMapper sysrolerMapper;
	
	@Autowired	
	private PasswordEncoder passwordEncode;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Sysuser sysuser = sysuserMapper.findByUserName(username);
		if(sysuser == null) {
			throw new UsernameNotFoundException("找不到用户信息");
		}
		String newPassword = passwordEncode.encode(sysuser.getPassword());
		List<Sysroler> roleList = sysrolerMapper.findByUserId(sysuser.getId());
		sysuser.setSysRolers(roleList);
		sysuser.setPassword(newPassword);
		return sysuser;
	}

}
