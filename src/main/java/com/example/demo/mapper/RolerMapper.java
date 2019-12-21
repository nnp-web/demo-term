package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.TermRoler;

@Mapper
public interface RolerMapper {
      @Select("select r.role_id,r.roleName,r.user_id from role r where r.user_id=#{userId}")
      List<TermRoler> findByUserId(int UserId);
}
