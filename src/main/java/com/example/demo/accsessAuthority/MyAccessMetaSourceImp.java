package com.example.demo.accsessAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import com.example.demo.entity.SysMenu;
import com.example.demo.mapper.SysMenuMapper;
import com.example.demo.mapper.SysroleMenuMapper;
import com.example.demo.mapper.SysrolerMapper;

@Component
public class MyAccessMetaSourceImp implements FilterInvocationSecurityMetadataSource {

	private Map<RequestMatcher, Collection<ConfigAttribute>> map = new HashMap<RequestMatcher, Collection<ConfigAttribute>>();
	
	@Autowired
	private SysMenuMapper sysMenuMapper;
	
	@Autowired
	private SysroleMenuMapper sysroleMenuMapper;
	
	@Autowired
	private SysrolerMapper sysroleMapper;

	@PostConstruct
	public void loadSecurityMetaSource() {
		List<SysMenu> menuList = sysMenuMapper.findAll();
		Collection<ConfigAttribute> attribute = new ArrayList<ConfigAttribute>();
		
		for (SysMenu menu : menuList) {
			String url = menu .getUrl();
			String method = menu .getMethod();
			List<Integer> rolerId = sysroleMenuMapper.findRoleIdByMenuId(menu.getId());
			List<String>  roleNameList = new ArrayList<String>();
			for(int i: rolerId) {
			    String rolename = sysroleMapper.findNameByRoleId(i);
			    roleNameList.add(rolename);
			}
			AntPathRequestMatcher requestMather = new AntPathRequestMatcher(url, method);
			
			for (String roler : roleNameList) {
				attribute.add(new SecurityConfig(roler));
			}
			
			attribute.add(new SecurityConfig("@needAuth"));
			map.put(requestMather, attribute);
		}
	}

	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		if (map == null) {
			loadSecurityMetaSource();
		}
		
		HttpServletRequest request = ((FilterInvocation) object).getRequest();
		
		for (Entry<RequestMatcher, Collection<ConfigAttribute>> mather : map.entrySet()) {
			
			if (mather.getKey().matches(request)) {
				
				return mather.getValue();
			}
		}
		
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		return new ArrayList<>();
	}

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return true;
	}

}
