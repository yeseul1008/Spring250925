package com.example.test1.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test1.mapper.StuMapper;
import com.example.test1.model.Student;

@Service
public class StuService {
	
	@Autowired
	StuMapper stuMapper;

	
	public HashMap<String, Object> stuInfo(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		System.out.println("service =>" + map);
		Student stu = stuMapper.stuInfo(map); //stuMapper 호출해서 맵을 만듬
		if(stu != null) {
			System.out.println(stu.getStuNo());
			System.out.println(stu.getStuName());
			System.out.println(stu.getStuDept());
		}
		resultMap.put("info", stu); // stu에 담긴거 맵에 넣기
		resultMap.put("result", "success");
		return resultMap;
	}
	// 리스트 불러오기
	public HashMap<String, Object> GetStuList(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		List<Student> list = stuMapper.stuList(map); //stuMapper 호출해서 맵이 담긴 리스트 만듬
		
		resultMap.put("list", list); // stu에 담긴거 맵에 넣기
		resultMap.put("result", "success");
		return resultMap;
	}
	
	//게시글 삭제
	public HashMap<String, Object> removeStu(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int cnt = stuMapper.deleteStu(map); //리스트 전체가 아닌 삭제할 것 하나를 호출해야하기때문에(몇개에 영향줬는지 cnt로 받음)
		
		resultMap.put("result", "success");
		return resultMap;
		
	}
	// 게시글 하나 불러오기
	public HashMap<String, Object> GetStu(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		Student stu = stuMapper.stuInfo(map); //stuMapper 호출해서 맵이 담긴 리스트 만듬
				
		resultMap.put("info", stu); // stu에 담긴거 맵에 넣기
		resultMap.put("result", "success");
		return resultMap;
	}
	
	//게시글 수정
	public HashMap<String, Object> editStu(HashMap<String, Object> map) {
		// TODO Auto-generated method stub
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		int cnt = stuMapper.editStu(map); //리스트 전체가 아닌 삭제할 것 하나를 호출해야하기때문에(몇개에 영향줬는지 cnt로 받음)
		
		resultMap.put("result", "success");
		return resultMap;
	}
}
