package com.example.test1.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.test1.model.Board;

@Mapper
public interface BoardMapper {
	//게시글 목록
	List<Board> boardList(HashMap<String, Object> map); //리턴되는 값이 여러개면 맵을 담는 리스트로 감싸줘야함
	//개발자가 직접 만든 모델 클래스 <Board>
	// 게시글 삭제
	int deleteBoard(HashMap<String, Object> map);
	
	// 게시글 추가
	int addBoard(HashMap<String, Object> map);
	
	// 게시글 하나 조회
	Board selectBoard(HashMap<String, Object> map);
}
