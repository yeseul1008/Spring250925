package com.example.test1.model;

public class Student {
	private String stuNo;
	private String stuName;
	private String stuDept;

	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo; // 언더바(_)를 카멜표기법으로 쓸 수 있음
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuDept() {
		return stuDept;
	}
	public void setStuDept(String stuDept) {
		this.stuDept = stuDept;
	}

	
}