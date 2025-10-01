package com.example.test1.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.test1.model.Board;
import com.example.test1.model.Comment;

@Mapper
public interface BoardMapper {
	
	// select할때 아니면 int로 한다.
	
	// 게시글 목록
	List<Board> selectBoardList(HashMap<String, Object> map);
	
	// 게시글 전체 개수
	int selectBoardCnt(HashMap<String, Object> map);
	
	// 게시글 삭제
	int deleteBoard(HashMap<String, Object> map);
	
	// 게시글 등록
	int insertBoard(HashMap<String, Object> map);
	
	// 게시글 상세보기
	Board selectBoard(HashMap<String, Object> map);
	
	// 댓글 목록
	List<Comment> selectCommentList(HashMap<String, Object> map);
	
	// 댓글 등록
	int insertComment(HashMap<String, Object> map);
	
	// 조회수 증가
	int updateCnt(HashMap<String, Object> map);
}






