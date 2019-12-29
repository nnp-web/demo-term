package com.example.demo.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Sysroler;

@Mapper
public interface SysrolerMapper {

	@Select("select r.role_id, r.roleName from role r where r.id=#{roleId}")
	Sysroler findByRoleId(int roleId);
	@Select("select r.roleName from role r where r.id=#{roleId}")
	String findNameByRoleId(int roleId);
}
