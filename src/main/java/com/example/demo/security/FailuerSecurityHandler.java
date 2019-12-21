package com.example.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//@Configuration
public class FailuerSecurityHandler implements AuthenticationFailureHandler {
	@Autowired
	private RedirectStrategy redirectStrategy;   
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		    request.setAttribute("message", exception.getMessage());
		    request.getRequestDispatcher("/login.html").forward(request, response);
		    redirectStrategy.sendRedirect(request, response, "/errors");
		
	}

}
