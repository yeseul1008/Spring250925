package com.example.test1.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.test1.model.Area;

@Mapper
public interface AreaMapper {
	List<Area> areaList(HashMap<String, Object> map);
	
	int selectAreaCnt(HashMap<String, Object> map);
	// 시/도 리스트
	List<Area> selectSiList(HashMap<String, Object> map);
}
