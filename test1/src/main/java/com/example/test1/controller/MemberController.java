package com.example.test1.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test1.dao.MemberService;
import com.google.gson.Gson;

@Controller
public class MemberController {

	@Autowired
	MemberService memberService; // 객체 일일이 만들지 않고 바로 불러올수 있음
	
	@RequestMapping("/member/login.do") 
    public String login(Model model) throws Exception{
    
		return "/member/member-login";
    }
	
	@RequestMapping("/member/join.do") 
    public String join(Model model) throws Exception{
    
		return "/member/member-join";
    }
	
	@RequestMapping("/addr.do") 
    public String addr(Model model) throws Exception{
    
		return "/jusoPopup";
    }
	@RequestMapping(value = "/member/login.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String boardList(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); // 미리 빈 맵 만들어두기
		resultMap = memberService.login(map); // 서비스에서 만든 resultMap 맵 호출 (resultMap 안에는 "msg": message이 담겨있다)
		
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/member/check.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String checkList(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); // 미리 빈 맵 만들어두기
		resultMap = memberService.check(map); // 서비스에서 만든 resultMap 맵 호출 (resultMap 안에는 "msg": message이 담겨있다)
		
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/member/logout.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String logout(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>(); // 미리 빈 맵 만들어두기
		resultMap = memberService.logout(map); // 서비스에서 만든 resultMap 맵 호출 (resultMap 안에는 "msg": message이 담겨있다)
		
		return new Gson().toJson(resultMap);
	}
	
}
