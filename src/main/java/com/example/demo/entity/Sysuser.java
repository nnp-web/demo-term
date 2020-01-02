package com.example.demo.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
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
    
    
    private String[] sysRolers;
    
    private String gender;
    
    private Date create_time;
    
	public int getId() {
		return id;
	}
	
	public Sysuser() {
		
	}
	
    public Sysuser(String username, String password) {
		this.username = username;
		this.password = password;
	}


	@Override
	public String toString() {
		return "Sysuser [username=" + username + ", password=" + password + ", gender=" + gender + "]";
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String[] getSysRolers() {
		return sysRolers;
	}
	
	public void setSysRolers(String[] sysRolers) {
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
		if(this.sysRolers == null) {
			
			return AuthorityUtils.commaSeparatedStringToAuthorityList("");
		}
		
		return AuthorityUtils.createAuthorityList(sysRolers);

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


	

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

}
