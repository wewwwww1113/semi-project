package com.kh.exercise.model.vo;

public class ExerCategory {
	private int categoryNo;
	private String categoryName;
	
	
	
	public ExerCategory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public ExerCategory(int categoryNo, String categoryName) {
		super();
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
	}
	public String toString() {
		return "ExerCategory [categoryNo=" + categoryNo + ", categoryName=" + categoryName + "]";
	}
	
	
}
