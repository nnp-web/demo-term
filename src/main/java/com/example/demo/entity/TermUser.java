package com.example.demo.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
//用户实体类
public class TermUser implements Serializable,UserDetails{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5525575795854027211L;
	private int id;
	private String username;
	private String password;
	private List<TermRoler> roleList;
	public List<TermRoler> getRoleList() {
		return roleList;
	}
	public void setRoleList(List<TermRoler> roleList) {
		this.roleList = roleList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "TermUser [user_id=" + id + ", user_name=" + username + ", password=" + password + "]";
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		if(this.roleList == null) {
			return AuthorityUtils.commaSeparatedStringToAuthorityList("");
		}
		StringBuilder builder = new StringBuilder();
		for(TermRoler sysrole: roleList) {
			builder.append(sysrole.getRole_name()).append(",");
		}
		List<GrantedAuthority> authorityList = AuthorityUtils.commaSeparatedStringToAuthorityList(builder.substring(0, builder.length()-1));
		return authorityList;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
}
