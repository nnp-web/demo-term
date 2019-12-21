package com.example.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.filter.ImageCodeAuhtenticationFilter;
import com.sun.net.httpserver.Filter;

@Configuration
public class TermSecurityAuthenticationAdapet extends WebSecurityConfigurerAdapter {
    //@Autowired
    private AuthenticationSuccess successHandler;
   // @Autowired
    private FailuerSecurityHandler failuerHandler;
    @Autowired
    private ImageCodeAuhtenticationFilter imageCodeAuhtenticationFilter;
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
   /**
    *  @Bean
    * @return
    
    public AuthenticationFailureHandler failureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }*/
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.addFilterBefore(imageCodeAuhtenticationFilter, UsernamePasswordAuthenticationFilter.class)
	        .formLogin()
		    .loginPage("/logins")
		    .loginProcessingUrl("/login")
		    .usernameParameter("username")
		    .passwordParameter("password")//successHandler(successHandler)
		    //.failureForwardUrl("/errors")
		    .defaultSuccessUrl("/indexs")//successUrl
		    .failureForwardUrl("/login/error")//
		    .and()
		    .authorizeRequests()
			.antMatchers("/logins","/createCode").permitAll()  
			.anyRequest()
			.authenticated()
			.and().csrf().disable();
	}

}
