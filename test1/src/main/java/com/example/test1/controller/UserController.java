package com.example.test1.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test1.dao.UserService;
import com.google.gson.Gson;
//주소를　만드는　부분　（@Controller가　선언되있어야　그　파일이　주소　만드는　파일이란것을　인식함）
@Controller
public class UserController {

	@Autowired
	UserService userService; //userService객체 생성 (이렇게하면 계속 재사용 할 수 있음) 
	
	@RequestMapping("/login.do") 
    public String login(Model model) throws Exception{
        return "/login";
    }
	
//	요청을 하기 위한 주소
	@RequestMapping(value = "/login.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String login(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println(map); //입력값이 맵으로 담겨서 넘어옴
		resultMap = userService.userLogin(map); //userService클래스의 userLogin 호출
		
		return new Gson().toJson(resultMap);
	}
	
}
