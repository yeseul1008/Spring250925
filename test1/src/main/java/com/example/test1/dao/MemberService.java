package com.example.test1.dao;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test1.mapper.MemberMapper;
import com.example.test1.model.Member;

@Service
public class MemberService {

	@Autowired
	MemberMapper memberMapper;
	// @Autowired: mapper에 있는 함수들을 바로 호출하기 위해 필요 
	
	@Autowired
	HttpSession session; // 세션 구현하려면 필수적으로 있어야하는 코드
	
	// 받은 여러개의 변수를 보내기 위해 맵 형태로 만듬
	public HashMap<String, Object> login(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); // 맵을 리턴하기 위해 빈 맵을 미리 만들어둠
		// Member : 함수에 대한 리턴타입
		Member member = memberMapper.memberLogin(map);
		String message = member != null ? "로그인 성공" : "로그인 실패";
		String result = member != null ? "success" : "fail";
		
		if(member != null) {
			session.setAttribute("sessionId", member.getUserId()); // 사용자의 아이디 얻어옴
			session.setAttribute("sessionName", member.getName());
			session.setAttribute("sessionStatus", member.getStatus());
		}
		
		//만들어둔 resultMap에대가 결과 넣기
		resultMap.put("msg", message);
		resultMap.put("result", result);
		return resultMap;
	}
	
	public HashMap<String, Object> check(HashMap<String, Object> map){
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); // 맵을 리턴하기 위해 빈 맵을 미리 만들어둠
		// Member : 함수에 대한 리턴타입
		Member member = memberMapper.memberCheck(map);
		String message = member != null ? "중복된 아이디" : "사용가능한 아이디";
		String result = member != null ? "success" : "fail";
		
		//만들어둔 resultMap에대가 결과 넣기
		resultMap.put("msg", message);
		resultMap.put("result", result);
		return resultMap;
	}

	public HashMap<String, Object> logout(HashMap<String, Object> map) {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		// 세션에 있는 정보 삭제하는방법은 1개씩 키 값을 이용해 삭제하거나 전체를 한번에 삭제하는 방법

		String message = session.getAttribute("sessionName") + "님 로그아웃 되었습니다.";
		resultMap.put("msg", message);
		
//		session.removeAttribute("sessionId"); // 1개씩 삭제 -> 정보가 많아지면 번거로움
		
		session.invalidate(); // 세션정보 전체 삭제 -> 더 효율적
		return resultMap;
	}
}
