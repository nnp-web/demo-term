package com.example.demo.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.alibaba.druid.util.StringUtils;
import com.example.demo.entity.ImageCode;

@Component
public class ImageCodeAuhtenticationFilter extends OncePerRequestFilter {
	@Autowired(required=false)
	private AuthenticationFailureHandler failureHandler;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(request.getMethod().equalsIgnoreCase("post") && request.getRequestURI().equalsIgnoreCase("/login")) {
			try {
				authentizeCode(request);
			}catch(BadCredentialsException e) {
				failureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		filterChain.doFilter(request, response);
		
	}
	
	private void authentizeCode(HttpServletRequest request) throws BadCredentialsException{
		String  imageCode = request.getParameter("imageCode").trim();
		ImageCode sessionCode = (ImageCode) request.getSession().getAttribute("code");
		if(!StringUtils.equals(imageCode, sessionCode.getCode())) {
			throw new BadCredentialsException("验证码输入错误!");
		}
	}

}
