package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

import com.example.demo.accsessAuthority.MyAccessDeniedHandler;
import com.example.demo.accsessAuthority.MyFilterSecurityInterceptor;

@EnableWebSecurity
public class SecurityConfigAdapt extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private  MyFilterSecurityInterceptor myFilterSecurityInterceptor;
	
	@Autowired
	private MyAccessDeniedHandler myAccessDeniedHandler;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	


	@Override
	protected void configure(HttpSecurity http) throws Exception {	
		http.exceptionHandling().accessDeniedPage("/denied.html");//没有权限提示页面
		http.csrf().disable().addFilterAt(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
		    .authorizeRequests().antMatchers("/login", "/img/**", "/js/**", "/css/**").permitAll()//免验证url
	        .anyRequest()//其他请求都要验证
	        .authenticated()
	        .and()
		    .formLogin()//表单登录验证
		    .loginPage("/login")
		    .loginProcessingUrl("/index")
		    .usernameParameter("username")//.failureHandler(failuerHandler)
		    .successForwardUrl("/index")//使用defualtSuccsessUrl重定向有时失败？
		    .failureUrl("/login?error=true");//不知道为什么重定向其他url 不起作用
		

		    

		    
	}

}
