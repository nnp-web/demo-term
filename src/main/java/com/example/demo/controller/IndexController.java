package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	    /**
	     * 主页
	     * @return
	     */
	    @RequestMapping("/indexs")
        public String  toIndex() {
        	return "indexs";
        }
	    /**
	     * 自定义登录页面
	     */
	    @GetMapping("/logins")
        public String login() {	    	
        	return "login";
        }
	    @RequestMapping("/login/error")
	    @ResponseBody
	    public String renpsonseErroMessage(HttpServletRequest req) {
	    	AuthenticationException ex =  (AuthenticationException) req.getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
			return "error";
	    }
	    
}
