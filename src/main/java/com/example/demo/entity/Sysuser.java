package com.example.demo.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class Sysuser implements UserDetails, Serializable{
      /**
	 * 
	 */
	private static final long serialVersionUID = 8446139896538726885L;
	private int id;
	
    private String username;
    
    private String password;
    
    private List<Sysroler> sysRolers;
    
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "Sysuser [id=" + id + ", username=" + username + "]";
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public List<Sysroler> getSysRolers() {
		return sysRolers;
	}
	
	public void setSysRolers(List<Sysroler> sysRolers) {
		this.sysRolers = sysRolers;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		StringBuilder builder = new StringBuilder();
		if(this.sysRolers == null) {
			return AuthorityUtils.commaSeparatedStringToAuthorityList("");
		}
		for(Sysroler sysroler : this.getSysRolers()) {
			builder.append(sysroler.getRoleName()).append(",");
		}
		return AuthorityUtils.commaSeparatedStringToAuthorityList(builder.substring(0, builder.length()-1));

	}
	
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
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
