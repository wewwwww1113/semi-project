package com.kh.market.model.vo;

import java.sql.Date;

public class Order {

	private String orderNo;
	private String name;
	private Date shippingDate;
	private String postNum;
	private String address;
	
	private String cartList;
	private int price;
	private int userNo;
	
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	public Order(String orderNo, String name, Date shippingDate, String postNum, String address, String cartList,
			int price, int userNo) {
		super();
		this.orderNo = orderNo;
		this.name = name;
		this.shippingDate = shippingDate;
		this.postNum = postNum;
		this.address = address;
		this.cartList = cartList;
		this.price = price;
		this.userNo = userNo;
	}




	public int getUserNo() {
		return userNo;
	}



	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}



	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getShippingDate() {
		return shippingDate;
	}

	public void setShippingDate(Date shippingDate) {
		this.shippingDate = shippingDate;
	}

	public String getPostNum() {
		return postNum;
	}

	public void setPostNum(String postNum) {
		this.postNum = postNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCartList() {
		return cartList;
	}



	public void setCartList(String cartList) {
		this.cartList = cartList;
	}



	public int getPrice() {
		return price;
	}



	public void setPrice(int price) {
		this.price = price;
	}



	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", name=" + name + ", shippingDate=" + shippingDate + ", postNum="
				+ postNum + ", address=" + address + ", cartList=" + cartList + ", price=" + price + "]";
	}



	
	
	
	
}
