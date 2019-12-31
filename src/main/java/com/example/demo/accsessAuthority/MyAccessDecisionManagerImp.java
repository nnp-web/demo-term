package com.example.demo.accsessAuthority;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MyAccessDecisionManagerImp implements AccessDecisionManager {

	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {
		// TODO Auto-generated method stub
		Iterator<ConfigAttribute> it = configAttributes.iterator();
		
		while(it.hasNext()) {
			ConfigAttribute resourceAttr = it.next();
			String resourceRole =  resourceAttr.getAttribute();
			
			for(GrantedAuthority userAuth : authentication.getAuthorities()) {
				
				if(userAuth.getAuthority().trim().equals(resourceRole.trim())) {
					
					return;
				}
			}
		}
		
		throw new AccessDeniedException("权限不足");
		
		
	}

	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
