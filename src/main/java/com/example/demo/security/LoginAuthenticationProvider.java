package com.example.demo.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.entity.TermUser;
import com.example.demo.service.UserServiceInterface;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {
	private final CustmizeAuthenticationService userDetailService; 
	private PasswordEncoder passwordEncode;
	@Autowired
	public LoginAuthenticationProvider(CustmizeAuthenticationService userService, PasswordEncoder passwordEncode) {
		this.userDetailService = userService;
		this.passwordEncode = passwordEncode;
	}
    //providerManager会遍历authenticaitonManager中实例auticationProvider选择支持token认证的provider
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		// TODO Auto-generated method stub
		//获取UsernamePasswordAuthenticationFilter包装的UsernamePasswordAuthenticationToken对象 extends Authentication（密码没有加密）
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		//调用userDetailService接口返回userDetail对象
		UserDetails use = userDetailService.loadUserByUsername(username);
		if(use == null) {
			//BadCredentialsException继承AuthenticationException
			throw new BadCredentialsException("用户名错误！");
		}
		//password字符，use.getPassword()encodePassword比较
		if(!passwordEncode.matches(password, passwordEncode.encode(use.getPassword()))) {
			throw new BadCredentialsException("密码错误！");
		}
		//获取authority一个角色名字符串集合
		Collection<? extends GrantedAuthority> authority = use.getAuthorities();
		//重新封装成UsernamePasswordAuthenticationToken
		return new UsernamePasswordAuthenticationToken(use, password, authority);
	}

	@Override
	//判断该provider是否支持该token的认证
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
