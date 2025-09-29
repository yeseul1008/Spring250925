package com.example.test1.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.test1.model.Student;

@Mapper
public interface StuMapper {
	
	//게시글 목록
	List<Student> stuList(HashMap<String, Object> map); //리턴되는 값이 여러개면 맵을 담는 리스트로 감싸줘야함
	
	//게시글 삭제
	int deleteStu(HashMap<String, Object> map);
	// xml에서 deleteStu 실행
	
	//게시글 하나 조회
	Student stuInfo(HashMap<String, Object> map);
	
	//게시글 수정
	int editStu(HashMap<String, Object> map);
}
