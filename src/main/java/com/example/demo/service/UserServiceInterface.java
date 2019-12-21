package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.TermUser;


@Service
public interface UserServiceInterface {
   TermUser findByName(String userName);
}
