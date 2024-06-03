package com.kh.exercise.model.vo;

public class Exercise {
	private int exerNo;				//운동고유번호 	: 게시글 고유 번호
	private String exerTitle;		//운동명			: 운동의 이름
	private String exerType;		//운동종류		: 유산소, 무기호흡(근력운동)
	private String exerInf;			//운동정보		: 운동에 대한 간단한 설명
	private String exerContent;		//운동내용		: 해당 운동의 정보 상세내용
	//private String exerPart;		
	private int categoryNo;			//운동부위		: 부위로 목, 어깨, 팔, 가슴, 복근, 허벅지, 종아리 
	
	private String titleImg;		//사진첨부
	private String categoryName;	//운동부위 스트링으로 가져올때
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getCategoryNo() {
		return categoryNo;
	}
	public void setCategoryNo(int categoryNo) {
		this.categoryNo = categoryNo;
	}
	public String getTitleImg() {
		return titleImg;
	}
	public void setTitleImg(String titleImg) {
		this.titleImg = titleImg;
	}
	public int getExerNo() {
		return exerNo;
	}
	public void setExerNo(int exerNo) {
		this.exerNo = exerNo;
	}
	public String getExerTitle() {
		return exerTitle;
	}
	public void setExerTitle(String exerTitle) {
		this.exerTitle = exerTitle;
	}
	public String getExerType() {
		return exerType;
	}
	public void setExerType(String exerType) {
		this.exerType = exerType;
	}
	public String getExerInf() {
		return exerInf;
	}
	public void setExerInf(String exerInf) {
		this.exerInf = exerInf;
	}
	public String getExerContent() {
		return exerContent;
	}
	public void setExerContent(String exerContent) {
		this.exerContent = exerContent;
	}
	
	//글 자세히보기
	public Exercise(int exerNo, String exerType, String exerTitle, String exerInf, int categoryNo, String exerContent) {
		this.exerNo = exerNo;
		this.exerType = exerType;
		this.exerTitle = exerTitle;
		this.exerInf = exerInf;
		this.categoryNo = categoryNo;
		this.exerContent = exerContent;
	}
	
	//사진리스(현재 사용중)
	public Exercise(int exerNo,String titleImg,String exerTitle,String exerInf,String exerContent){
		this.exerNo = exerNo;
		this.titleImg = titleImg;
		this.exerTitle = exerTitle;
		this.exerInf = exerInf;
		this.exerContent = exerContent;
	}

//	public Exercise(String exerTitle,String exerInf,String exerType,int categoryNo,String exerContent){
//		this.exerTitle=exerTitle;
//		this.exerInf=exerInf;
//		this.exerType=exerType;
//		this.categoryNo=categoryNo;
//		this.exerContent=exerContent;
//		
//	}

	public Exercise(int exerNo,String exerTitle,String titleImg ,String exerInf,String exerType,String categoryName,String exerContent){
		this.exerNo=exerNo;
		this.exerTitle=exerTitle;
		this.titleImg=titleImg;
		this.exerInf=exerInf;
		this.exerType=exerType;
		this.categoryName=categoryName;
		this.exerContent=exerContent;
		
	}
	
	
	public Exercise() {
		super();
	}
	@Override
	public String toString() {
		return "Exercise [exerNo=" + exerNo + ", exerTitle=" + exerTitle + ", exerType=" + exerType + ", exerInf="
				+ exerInf + ", exerContent=" + exerContent + ", categoryNo=" + categoryNo + ", titleImg=" + titleImg
				  +", categoryName="+ categoryName+ "]";
	}
	
	
	
}
