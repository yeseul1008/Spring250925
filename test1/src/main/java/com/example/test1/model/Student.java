package com.example.test1.model;

public class Student {
	private String stuNo;
	private String stuName;
	private String stuDept;
	private String stuGrade;
	private String stuGender;
	
	private double enrAvg;
	private String totalAvg;
	
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
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
	public String getStuGrade() {
		return stuGrade;
	}
	public void setStuGrade(String stuGrade) {
		this.stuGrade = stuGrade;
	}
	public String getStuGender() {
		return stuGender;
	}
	public void setStuGender(String stuGender) {
		this.stuGender = stuGender;
	}
	public double getEnrAvg() {
		return enrAvg;
	}
	public void setEnrAvg(double enrAvg) {
		this.enrAvg = enrAvg;
	}
	public String getTotalAvg() {
		return totalAvg;
	}
	public void setTotalAvg(String totalAvg) {
		this.totalAvg = totalAvg;
	}
}