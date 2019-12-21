package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfigAdapt extends WebSecurityConfigurerAdapter {
	//@Autowired
	private CustmizeAutenTicationFailuer failuerHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder ();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.formLogin()
		    .loginPage("/login")
		    .loginProcessingUrl("/login")
		    .defaultSuccessUrl("/index")
		    .usernameParameter("username")//.failureHandler(failuerHandler)
		    .failureUrl("/login?error=true")//不知道为什么重定向其他url 不起作用
		    .and()
		    .authorizeRequests()
		    .antMatchers("/login", "/create/code").permitAll()
		    .anyRequest()
		    .authenticated()
		    .and().csrf().disable();
		    
	}
	
	

}
