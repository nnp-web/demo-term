package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Sysuser;

@Mapper
public interface SysuserMapper {
    @Select("select u.id, u.username, u.password from user u where u.username=#{name}")
	Sysuser findByUserName(String username);
}
