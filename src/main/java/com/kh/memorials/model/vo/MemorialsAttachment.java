package com.kh.memorials.model.vo;

import java.sql.Date;

public class MemorialsAttachment {
	
	private int fileNo; // FILE_NO NUMBER
	private int refMno;// REF_BNO NUMBER
	private String originName;// ORIGIN_NAME VARCHAR2(255 BYTE)
	private String changeName;// CHANGE_NAME VARCHAR2(255 BYTE)
	private String filePath;// FILE_PATH VARCHAR2(1000 BYTE)
	private Date uploadDate;// UPLOAD_DATE DATE
	private String status;// STATUS VARCHAR2(1 BYTE)

	public MemorialsAttachment() {
		super();
	}

	public MemorialsAttachment(int fileNo, int refMno, String originName, String changeName, String filePath,
			Date uploadDate, String status) {
		super();
		this.fileNo = fileNo;
		this.refMno = refMno;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.status = status;
	}

	public MemorialsAttachment(int fileNo, String originName, String changeName, String filePath) {
		super();
		this.fileNo = fileNo;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
	}

	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public int getRefMno() {
		return refMno;
	}

	public void setRefMno(int refMno) {
		this.refMno = refMno;
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

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "MemorialsAttachment [fileNo=" + fileNo + ", refMno=" + refMno + ", originName=" + originName
				+ ", changeName=" + changeName + ", filePath=" + filePath + ", uploadDate=" + uploadDate + ", status="
				+ status + "]";
	}
	
	


}
