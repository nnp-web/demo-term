package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TermUser;
import com.example.demo.mapper.UserMapper;

@Service
public class UserService implements UserServiceInterface{
    @Autowired
    private UserMapper userMapper;
	@Override
	public TermUser findByName(String userName) {
		// TODO Auto-generated method stub
		TermUser user = userMapper.findByName(userName);
		return user;
	}

}
