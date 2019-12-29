package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface SysroleMenuMapper {

	@Select("select mr.role_id from menu_role mr where mr.menu_id=#{menuid}")
	List<Integer> findRoleIdByMenuId(int menuid);
}
