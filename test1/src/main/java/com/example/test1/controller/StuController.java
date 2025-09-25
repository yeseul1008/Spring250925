package com.example.test1.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test1.dao.StuService;
import com.google.gson.Gson;
// 주소 만들땐 @컨트롤러 이 표시가 꼭 필요함
@Controller
public class StuController {
	
	@Autowired
	StuService stuService; // Stuservice 클래스를 담은 stuService객체 생성 (이렇게하면 계속 재사용 할 수 있음) 
	
	@RequestMapping("/stu-list.do") 
    public String login(Model model) throws Exception{
    
		return "/stu-list";
    }
//	요청을 하기 위한 주소
	@RequestMapping(value = "/stu-list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String login(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println(map); //입력값이 맵으로 담겨서 넘어옴
		resultMap = stuService.stuInfo(map);
		
		return new Gson().toJson(resultMap);
	}
}
