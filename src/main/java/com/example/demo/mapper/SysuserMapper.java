package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.Sysuser;

@Mapper
public interface SysuserMapper {
    @Select("select u.id, u.username, u.password  from user u where u.username=#{name}")
	Sysuser findByUserName(String username);
    @Insert("insert into user(username, password)values(#{username}, #{password})")
    void insertUser(Sysuser user);
    @Select("select u.id, u.username, u.password, u.gender,u.create_time from user u ")
   	List<Sysuser> findAll();
}
