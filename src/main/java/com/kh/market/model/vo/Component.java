package com.kh.market.model.vo;

public class Component {
	private int itemCode;
	private double protin;
	private double carbo;
	private double calorie;
	private double salt;
	private double fat;
	private double chol;
	private double saturatedFat;
	private double sugar;
	private double transFat;
	public Component() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Component(int itemCode, double protin, double carbo, double calorie, double salt, double fat,
			double chol, double saturatedFat, double sugar, double transFat) {
		super();
		this.itemCode = itemCode;
		this.protin = protin;
		this.carbo = carbo;
		this.calorie = calorie;
		this.salt = salt;
		this.fat = fat;
		this.chol = chol;
		this.saturatedFat = saturatedFat;
		this.sugar = sugar;
		this.transFat = transFat;
	}
	public int getItemCode() {
		return itemCode;
	}
	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	public double getProtin() {
		return protin;
	}
	public void setProtin(double protin) {
		this.protin = protin;
	}
	public double getCarbo() {
		return carbo;
	}
	public void setCarbo(double carbo) {
		this.carbo = carbo;
	}
	public double getCalorie() {
		return calorie;
	}
	public void setCalorie(double calorie) {
		this.calorie = calorie;
	}
	public double getSalt() {
		return salt;
	}
	public void setSalt(double salt) {
		this.salt = salt;
	}
	public double getFat() {
		return fat;
	}
	public void setFat(double fat) {
		this.fat = fat;
	}
	public double getChol() {
		return chol;
	}
	public void setChol(double chol) {
		this.chol = chol;
	}
	public double getSaturatedFat() {
		return saturatedFat;
	}
	public void setSaturatedFat(double saturatedFat) {
		this.saturatedFat = saturatedFat;
	}
	public double getSugar() {
		return sugar;
	}
	public void setSugar(double sugar) {
		this.sugar = sugar;
	}
	public double getTransFat() {
		return transFat;
	}
	public void setTransFat(double transFat) {
		this.transFat = transFat;
	}
	
	
}
