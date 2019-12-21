package com.example.demo.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.entity.TermRoler;
import com.example.demo.entity.TermUser;
import com.example.demo.mapper.RolerMapper;
import com.example.demo.service.UserServiceInterface;

@Component
public class CustmizeAuthenticationService implements UserDetailsService {
	@Autowired
	private UserServiceInterface userService;
	@Autowired
    private RolerMapper rolerMapper;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		TermUser users =  userService.findByName(username);
		if(users == null) {
			throw new UsernameNotFoundException("用户找不到！");
		}
		List<TermRoler> rolerList = rolerMapper.findByUserId(users.getId());
		users.setRoleList(rolerList);
		return users ;
	}

	//@Override
	/*public UserDetails loadUserByUsername(String username) {
		// TODO Auto-generated method stub
		TermUser users =  userService.findByName(username);
		if(users == null) {
			throw new UsernameNotFoundException("用户找不到");
		}
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities = AuthorityUtils.commaSeparatedStringToAuthorityList("admin");
		return new User(users.getUsername(), users.getPassword(), authorities);
		//return users;
	}*/

}
