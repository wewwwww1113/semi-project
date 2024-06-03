package com.kh.memorials.model.vo;

import java.util.Date;

public class Memorials {
	
	private int memorialsNo;
	private String memorialsDate; //사용자 지정 날짜
	private String memorialsTime;
	private String[] memorialsParts;
	private String memorialsContent;
	private int memorialsSelfScore;
	private Date createDate; // 서버에 올린 날짜
	private String mUserId;
	private String status;
	
	public Memorials() {
		super();
	}

	public Memorials(int memorialsNo, String memorialsDate, String memorialsTime, String[] memorialsParts,
			String memorialsContent, int memorialsSelfScore, Date createDate, String mUserId, String status) {
		super();
		this.memorialsNo = memorialsNo;
		this.memorialsDate = memorialsDate;
		this.memorialsTime = memorialsTime;
		this.memorialsParts = memorialsParts;
		this.memorialsContent = memorialsContent;
		this.memorialsSelfScore = memorialsSelfScore;
		this.createDate = createDate;
		this.mUserId = mUserId;
		this.status = status;
	}

	public Memorials(int memorialsNo, String memorialsDate, String memorialsTime, String[] memorialsParts,
			String memorialsContent, int memorialsSelfScore) {
		super();
		this.memorialsNo = memorialsNo;
		this.memorialsDate = memorialsDate;
		this.memorialsTime = memorialsTime;
		this.memorialsParts = memorialsParts;
		this.memorialsContent = memorialsContent;
		this.memorialsSelfScore = memorialsSelfScore;
	}

	public int getMemorialsNo() {
		return memorialsNo;
	}

	public void setMemorialsNo(int memorialsNo) {
		this.memorialsNo = memorialsNo;
	}

	public String getMemorialsDate() {
		return memorialsDate;
	}

	public void setMemorialsDate(String memorialsDate) {
		this.memorialsDate = memorialsDate;
	}

	public String getMemorialsTime() {
		return memorialsTime;
	}

	public void setMemorialsTime(String memorialsTime) {
		this.memorialsTime = memorialsTime;
	}

	public String[] getMemorialsParts() {
		return memorialsParts;
	}

	public void setMemorialsParts(String[] memorialsParts) {
		this.memorialsParts = memorialsParts;
	}

	public String getMemorialsContent() {
		return memorialsContent;
	}

	public void setMemorialsContent(String memorialsContent) {
		this.memorialsContent = memorialsContent;
	}

	public int getMemorialsSelfScore() {
		return memorialsSelfScore;
	}

	public void setMemorialsSelfScore(int memorialsSelfScore) {
		this.memorialsSelfScore = memorialsSelfScore;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getmUserId() {
		return mUserId;
	}

	public void setmUserId(String mUserId) {
		this.mUserId = mUserId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Memorials [memorialsNo=" + memorialsNo + ", memorialsDate=" + memorialsDate + ", memorialsTime="
				+ memorialsTime + ", memorialsParts=" + memorialsParts + ", memorialsContent=" + memorialsContent
				+ ", memorialsSelfScore=" + memorialsSelfScore + ", createDate=" + createDate + ", mUserId=" + mUserId
				+ ", status=" + status + "]";
	}

	
	
	

	

}
