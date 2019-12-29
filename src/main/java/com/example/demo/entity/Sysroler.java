package com.example.demo.entity;

import java.io.Serializable;

public class Sysroler implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6725062995763822778L;
	
	private int role_id;
	
	private String roleName;
	
	
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

	@Override
	public String toString() {
		return "Sysroler [role_id=" + role_id + ", roleName=" + roleName + "]";
	}


	
	
	

}
