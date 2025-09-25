package com.example.test1.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.test1.model.Board;

@Mapper
public interface BoardMapper {
	
	List<Board> boardList(HashMap<String, Object> map); //리턴되는 값이 여러개면 맵을 담는 리스트로 감싸줘야함
	
	Board boardremove(HashMap<String, Object> map); // 리턴되는 값이 하나면(맵1개) 가능
}
