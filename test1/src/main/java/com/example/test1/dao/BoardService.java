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
	
	public HashMap<String, Object> removeBoard(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int cnt = boardMapper.deleteBoard(map);
		
		resultMap.put("result", "success");
		return resultMap;
		
	}

	//게시글 추가
	public HashMap<String, Object> addBoard(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int cnt = boardMapper.addBoard(map);
		
		resultMap.put("result", "success");
		return resultMap;
	}
	
	//게시글 하나 조회
	public HashMap<String, Object> GetBoard(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Board board = boardMapper.selectBoard(map); //stuMapper 호출해서 맵이 담긴 리스트 만듬
		
		resultMap.put("info", board); // stu에 담긴거 맵에 넣기
		resultMap.put("result", "success");
		return resultMap;
	}
}
