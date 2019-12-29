package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysuserRoleMapper {

	@Select("select ur.role_id from user_role ur where ur.user_id=#{userid}")
	int[] findRoleIdByUserId(int userid);
}
