package com.kh.infoboard.model.vo;

public class InfoCategory {
	private int categoryNo;
	private String categoryName;
	
	public InfoCategory() {
		super();
	}
	
	public InfoCategory(int categoryNo, String categoryName) {
		super();
		this.categoryNo = categoryNo;
		this.categoryName = categoryName;
	}

	
	@Override
	public String toString() {
		return "InfoCategory [categoryNo=" + categoryNo + ", categoryName=" + categoryName + "]";
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
	
	

}
