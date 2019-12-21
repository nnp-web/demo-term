package com.example.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.TermUser;


@Mapper
public interface UserMapper {
	    @Select( "select u.userName, u.id, u.password from user u where u.userName=#{name}" )
        TermUser findByName(String name);
}
