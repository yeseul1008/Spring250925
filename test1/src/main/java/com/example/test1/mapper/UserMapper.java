package com.example.test1.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import com.example.test1.model.User;

@Mapper
public interface UserMapper { // xml파일에서 구체화 함
	
	User userLogin(HashMap<String, Object> map); //userLogin 호출
	
}
