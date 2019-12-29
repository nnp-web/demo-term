package com.example.demo.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.mapper.SysrolerMapper;

public class SysMenu {
	
    
	private int id;
	
	private String method;
	
	private String url;
	
	private int parent_id;
	
	private String menuname;
	
	private List<String> rolelist;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}


	public int getParent_id() {
		return parent_id;
	}

	public void setParent_id(int parent_id) {
		this.parent_id = parent_id;
	}

	public String getMenuname() {
		return menuname;
	}

	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}

	public List<String> getRolelist() {
		return rolelist;
	}

	public void setRolelist(List<String> rolelist) {
		this.rolelist = rolelist;
	}
	
	
}
