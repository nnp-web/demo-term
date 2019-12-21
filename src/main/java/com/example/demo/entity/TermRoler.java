package com.example.demo.entity;

import java.io.Serializable;

import org.springframework.security.core.GrantedAuthority;

//角色实体类
public class TermRoler implements Serializable,GrantedAuthority{
    /**
	 * 
	 */
	private static final long serialVersionUID = -5771089883348027837L;
	private int role_id;
    private String roleName;
    private int user_id;
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	public String getRole_name() {
		return roleName;
	}
	public void setRole_name(String role_name) {
		this.roleName = role_name;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "TermRoler [role_id=" + role_id + ", role_name=" + roleName + ", user_id=" + user_id + "]";
	}
	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.roleName;
	}
    
 
}
