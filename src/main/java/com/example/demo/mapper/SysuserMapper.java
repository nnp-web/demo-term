package com.example.demo.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.entity.Sysuser;

@Mapper
public interface SysuserMapper {
    @Select("select u.id, u.username, u.password  from user u where u.username=#{name}")
	Sysuser findByUserName(String username);
    @Insert("insert into user(username, password,gender,create_time)values(#{username}, #{password}, #{gender}, DATE_FORMAT(SYSDATE(), '%Y-%m-%d %T') )")
    void insertUser(Sysuser user) throws SQLException;
    @Select("select u.id, u.username, u.password, u.gender,u.create_time from user u ")
   	List<Sysuser> findAll();

}
