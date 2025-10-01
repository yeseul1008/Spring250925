package com.example.test1.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.test1.dao.AreaService;
import com.example.test1.mapper.AreaMapper;
import com.google.gson.Gson;

@Controller
public class AreaController {
	
	@Autowired
	AreaService areaService;
	
	@RequestMapping("/area/list.do") 
    public String arealist(Model model) throws Exception{ 
		
        return "/area-list";
    }
	
	@RequestMapping(value = "/area-list.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String areaList(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = areaService.getAreaList(map);
		
		return new Gson().toJson(resultMap);
	}
	
	@RequestMapping(value = "/area/si.dox", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String si(Model model, @RequestParam HashMap<String, Object> map) throws Exception {
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		resultMap = areaService.getSiList(map);
		
		return new Gson().toJson(resultMap);
	}
}
