package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.example.demo.accsessAuthority.MyAccessDecisionManagerImp;
import com.example.demo.accsessAuthority.MyFilterSecurityInterceptor;

@Configuration
public class SecurityConfigAdapt extends WebSecurityConfigurerAdapter {
	//@Autowired
	private CustmizeAutenTicationFailuer failuerHandler;
	@Autowired
	private MyAccessDecisionManagerImp accessDecisionManager;
	@Autowired
	private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder ();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		http.csrf().disable()
		    .formLogin()
		    .loginPage("/login")
		    .loginProcessingUrl("/login")
		    .usernameParameter("username")//.failureHandler(failuerHandler)
		    .successForwardUrl("/tomain")//使用defualtSuccsessUrl重定向有时失败？
		    .failureUrl("/login?error=true")//不知道为什么重定向其他url 不起作用
		    .and().addFilterAfter(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
		    .authorizeRequests()
		    .accessDecisionManager(accessDecisionManager)
		    .antMatchers("/login", "/create/code").permitAll()
		    .anyRequest()
		    .authenticated();

		    
	}
	
	

}
