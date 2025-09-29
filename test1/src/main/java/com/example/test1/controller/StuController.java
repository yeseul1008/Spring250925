package com.example.test1.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

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
	@RequestMapping("/stu-view.do") //파라미터 받기 (이 방식으로 하면 파라미터를 한번에 여러개를 보내줄 수 있음)
    public String view(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		System.out.println(map);
		request.setAttribute("stuNo", map.get("stuNo")); // 페이지를 띄우면서 맵 보내주기
		return "/stu-view";
    }
	@RequestMapping("/stu-edit.do") //파라미터 받기 (이 방식으로 하면 파라미터를 한번에 여러개를 보내줄 수 있음)
    public String edit(HttpServletRequest request, Model model, @RequestParam HashMap<String, Object> map) throws Exception{
		System.out.println(map);
		request.setAttribute("stuNo", map.get("stuNo")); // 페이지를 띄우면서 맵 보내주기
		return "/stu-edit";
    }
	
	
//	요청을 하기 위한 주소
	@RequestMapping(value = "/stu-info.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String login(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println(map); //입력값이 맵으로 담겨서 넘어옴
		resultMap = stuService.stuInfo(map);
		
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/stu-list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String stuList(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = stuService.GetStuList(map);
		
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/stu-delete.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String stuDelete(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = stuService.removeStu(map);
		
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/stu-view.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String stuView(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = stuService.GetStu(map);
		
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/stu-eidt.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String stuEdit(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = stuService.editStu(map);
		
		return new Gson().toJson(resultMap);
	}
}
