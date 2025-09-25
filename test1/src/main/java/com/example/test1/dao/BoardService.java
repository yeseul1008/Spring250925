package com.example.test1.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test1.mapper.BoardMapper;
import com.example.test1.model.Board;

@Service
public class BoardService {
	
	@Autowired
	BoardMapper boardMapper;

	
	public HashMap<String, Object> GetBoardList(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<Board> list = boardMapper.boardList(map); //stuMapper 호출해서 맵이 담긴 리스트 만듬
		
		resultMap.put("list", list); // stu에 담긴거 맵에 넣기
		resultMap.put("result", "success");
		return resultMap;
	}
	
	public HashMap<String, Object> boardRemove(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		Board board = boardMapper.boardRemove(map); 

		return resultMap;
	}
}
