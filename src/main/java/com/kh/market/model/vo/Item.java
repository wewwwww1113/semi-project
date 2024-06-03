package com.kh.market.model.vo;

public class Item {

	private int itemCode;
	private int category;
	private int price;
	private int discount;
	private int salesVol;
	private String storageMethod;
	private String itemName;
	
	
	private String categoryName; //카테고리 네임 join 구문으로 가져오는 용도
	private String thumbnailImg;
	private int quantity;
	private int totalPrice;
	private double score;
	private String status;
	
	public Item() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Item(int itemCode, int category, int price, int salesVol, String storageMethod, String itemName) {
		super();
		this.itemCode = itemCode;
		this.category = category;
		this.price = price;
		this.salesVol = salesVol;
		this.storageMethod = storageMethod;
		this.itemName = itemName;
	}
	
	
	
	public Item(int itemCode, String categoryName, int price,int discount, String itemName,int salesVol,double score, String thumbnailImg) {
		super();
		this.itemCode = itemCode;
		this.categoryName = categoryName;
		this.price = price;
		this.discount=discount;
		this.itemName = itemName;
		this.salesVol=salesVol;
		this.score=score;
		this.thumbnailImg = thumbnailImg;
	}
	
	
	
	public Item(String categoryName,int category,  int price,int discount, String itemName,int salesVol, String storageMethod,int itemCode, String status) {
		super();
		this.categoryName = categoryName;
		this.category=category;
		this.price = price;
		this.discount=discount;
		this.itemName = itemName;
		this.salesVol=salesVol;
		this.storageMethod = storageMethod;
		this.itemCode = itemCode;
		this.status = status;
	}
	
	
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getItemCode() {
		return itemCode;
	}
	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getSalesVol() {
		return salesVol;
	}
	public void setSalesVol(int salesVol) {
		this.salesVol = salesVol;
	}
	public String getStorageMethod() {
		return storageMethod;
	}
	public void setStorageMethod(String itemDetail) {
		this.storageMethod = itemDetail;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getThumbnailImg() {
		return thumbnailImg;
	}
	public void setThumbnailImg(String thumbnailImg) {
		this.thumbnailImg = thumbnailImg;
	}
	
	public int getTotalPrice() {
	
		int tp=(int)(Math.round(price*(1-discount/(double)100)/(double)10)*(double)10);
		this.totalPrice=tp;
		
		return  totalPrice;
	}
	
}
