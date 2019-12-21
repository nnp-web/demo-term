package com.example.demo.entity;

import java.io.Serializable;

public class Sysroler implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6725062995763822778L;
	
	private int role_id;
	
	private String roleName;
	
	private int user_id;

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	@Override
	public String toString() {
		return "Sysroler [role_id=" + role_id + ", roleName=" + roleName + ", user_id=" + user_id + "]";
	}
	
	
	

}
