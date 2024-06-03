package com.kh.infoboard.model.vo;

import java.sql.Date;

public class Reply {
	private int replyNo;//REPLY_NO	NUMBER
	private int refBno;//REF_BNO	NUMBER
	private String replyWriter;//REPLY_WRITER	NUMBER(회원번호 -> 아이디)
	private String replyContent;//REPLY_CONTENT	VARCHAR2(200 BYTE)
	private Date createDate;//CREATE_DATE	DATE
	private String status;//STATUS	CHAR(1 BYTE)
	
	public Reply() {
		super();
	}
	
	
	
	public Reply(int replyNo, String replyWriter, String replyContent, Date createDate) {
		super();
		this.replyNo = replyNo;
		this.replyWriter = replyWriter;
		this.replyContent = replyContent;
		this.createDate = createDate;
	}



	public Reply(int refBno, String replyWriter, String replyContent) {
		super();
		this.refBno = refBno;
		this.replyWriter = replyWriter;
		this.replyContent = replyContent;
	}


	public Reply(int replyNo, int refBno, String replyWriter, String replyContent, Date createDate, String status) {
		super();
		this.replyNo = replyNo;
		this.refBno = refBno;
		this.replyWriter = replyWriter;
		this.replyContent = replyContent;
		this.createDate = createDate;
		this.status = status;
	}
	@Override
	public String toString() {
		return "Reply [replyNo=" + replyNo + ", refBno=" + refBno + ", replyWriter=" + replyWriter + ", replyContent="
				+ replyContent + ", createDate=" + createDate + ", status=" + status + "]";
	}
	public int getReplyNo() {
		return replyNo;
	}
	public void setReplyNo(int replyNo) {
		this.replyNo = replyNo;
	}
	public int getRefBno() {
		return refBno;
	}
	public void setRefBno(int refBno) {
		this.refBno = refBno;
	}
	public String getReplyWriter() {
		return replyWriter;
	}
	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
