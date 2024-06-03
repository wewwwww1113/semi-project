package com.kh.market.model.vo;

import java.sql.Date;

public class Review {
	private int reviewNo;
	private int refItemNo;
	private String content;
	private double score;
	private int reviewWriter;
	private Date update;
	private String nickName;
	
	
	public Review() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Review(int reviewNo, int refItemNo, String content, double score, int reviewWriter,String nickName, Date update) {
		super();
		this.reviewNo = reviewNo;
		this.refItemNo = refItemNo;
		this.content = content;
		this.score = score;
		this.reviewWriter = reviewWriter;
		this.nickName=nickName;
		this.update = update;
	}

	
	
	public Review(int refItemNo, int reviewWriter, String content, double score) {
		super();
		this.refItemNo = refItemNo;
		this.content = content;
		this.score = score;
		this.reviewWriter = reviewWriter;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getReviewNo() {
		return reviewNo;
	}

	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}

	public int getRefItemNo() {
		return refItemNo;
	}

	public void setRefItemNo(int refItemNo) {
		this.refItemNo = refItemNo;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getReviewWriter() {
		return reviewWriter;
	}

	public void setReviewWriter(int reviewWriter) {
		this.reviewWriter = reviewWriter;
	}

	public Date getUpdate() {
		return update;
	}

	public void setUpdate(Date update) {
		this.update = update;
	}
	
	
}
