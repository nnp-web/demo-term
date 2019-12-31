package com.example.demo.accsessAuthority;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

@Component
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {
	
	private static final Logger log = LoggerFactory.getLogger(MyFilterSecurityInterceptor.class);
			
	private final MyAccessMetaSourceImp securityMetadataSource;
	
	private final MyAccessDecisionManagerImp accessDecisionManagerImp;
	
	private static final String FILTER_APPLIED = "__spring_security_filterSecurityInterceptor_filterApplied";
	
	
	@Autowired
	public MyFilterSecurityInterceptor(MyAccessMetaSourceImp securityMetadataSource, MyAccessDecisionManagerImp accessDecisionManagerImp) {
		log.info("注入==========================================自定义鉴权管理器和自定义权限映射数据源");
		this.accessDecisionManagerImp  = accessDecisionManagerImp;
		this.securityMetadataSource = securityMetadataSource;
	}
	
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@PostConstruct
	public void initSetMessage() {
		log.info("设置==========================================鉴权决策管理器");
		super.setAccessDecisionManager(accessDecisionManagerImp);
	}
	
	public void destroy() {
	}

	@Override
	public Class<?> getSecureObjectClass() {
		
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		log.info("获取==========================================自定义权限映射数据源");
		return this.securityMetadataSource;
	}
	
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(request, response, chain);
		invoke(fi);
	}
	
	public void invoke(FilterInvocation fi) throws IOException, ServletException {
		if ((fi.getRequest() != null)
				&& (fi.getRequest().getAttribute(FILTER_APPLIED) != null)
				) {
			// filter already applied to this request and user wants us to observe
			// once-per-request handling, so don't re-do security checking
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		}
		else {
			// first time this request being called, so perform security checking
			if (fi.getRequest() != null) {
				fi.getRequest().setAttribute(FILTER_APPLIED, Boolean.TRUE);
			}

			InterceptorStatusToken token = super.beforeInvocation(fi);

			try {
				fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
			}
			finally {
				super.finallyInvocation(token);
			}

			super.afterInvocation(token, null);
		}
	}

}
