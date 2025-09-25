package com.example.test1.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.test1.model.Student;

@Mapper
public interface StuMapper {
	
	Student stuInfo(HashMap<String, Object> map); // 리턴되는 값이 하나면(맵1개) 가능
	List<Student> stuList(HashMap<String, Object> map); //리턴되는 값이 여러개면 맵을 담는 리스트로 감싸줘야함
}
