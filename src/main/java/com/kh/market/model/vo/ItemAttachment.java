package com.kh.market.model.vo;

import java.sql.Date;

public class ItemAttachment {
	private int fileCode;
	private String refCode;
	private String originName;
	private String changeName;
	private String filePath;
	private Date update;
	private int fileLev;
	private String status;
	public ItemAttachment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ItemAttachment(int fileCode, String refCode, String originName, String changeName, String filePath,
			Date update, int fileLev, String status) {
		super();
		this.fileCode = fileCode;
		this.refCode = refCode;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.update = update;
		this.fileLev = fileLev;
		this.status = status;
	}
	
	
	
	
	public ItemAttachment(int fileCode, String originName, String changeName, String filePath) {
		super();
		this.fileCode = fileCode;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
	}
	
	public int getFileCode() {
		return fileCode;
	}
	public void setFileCode(int fileCode) {
		this.fileCode = fileCode;
	}
	public String getRefCode() {
		return refCode;
	}
	public void setRefCode(String refCode) {
		this.refCode = refCode;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getChangeName() {
		return changeName;
	}
	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getUpdate() {
		return update;
	}
	public void setUpdate(Date update) {
		this.update = update;
	}
	public int getFileLev() {
		return fileLev;
	}
	public void setFileLev(int fileLev) {
		this.fileLev = fileLev;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ItemAttachment [fileCode=" + fileCode + ", refCode=" + refCode + ", originName=" + originName
				+ ", changeName=" + changeName + ", filePath=" + filePath + ", update=" + update + ", fileLev="
				+ fileLev + ", status=" + status + "]";
	}
	
	
	
}
