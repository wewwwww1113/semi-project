package com.kh.market.model.vo;

public class ItemCatogory {
	
	private int categoryNo;
	private String categoryName;
	public ItemCatogory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItemCatogory(int categoryNO, String categoryName) {
		super();
		this.categoryNo = categoryNO;
		this.categoryName = categoryName;
	}
	public int getCategoryNO() {
		return categoryNo;
	}
	public void setCategoryNO(int categoryNO) {
		this.categoryNo = categoryNO;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	
}
