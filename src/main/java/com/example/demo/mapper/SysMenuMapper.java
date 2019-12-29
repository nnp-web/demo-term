package com.example.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.entity.SysMenu;

@Mapper
public interface SysMenuMapper {

	@Select("select m.id, m.menuname, m.parent_id, m.url, m.method from menu m")
	List<SysMenu> findAll();
}
