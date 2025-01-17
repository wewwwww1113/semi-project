package com.kh.common.model.vo;

public class PageInfo {
	//게시판과 관련된 정보 모음
	private int listCount;//총 게시글 개수
	private int currentPage; //현재페이지
	private int pageLimit; //페이지 하단에 보여질 페이징바의 최대개수
	private int boardLimit; //한 페이지에 보여줄 게시글 개수	
	private int maxPage; //가장 마지막 페이징바가 몇번인가(총 페이지 개수)
	private int startPage; //페이지 하단에 보여질 페이징바의 시작 수
	private int endPage; //페이지 하단에 보여질 페이징바의 끝 수
	
	
	public PageInfo() {
		super();
	}
	
	
	public PageInfo(int currentPage, int boardLimit) {
		super();
		this.currentPage = currentPage;
		this.boardLimit = boardLimit;
	}



	public PageInfo(int listCount, int currentPage, int pageLimit, int boardLimit, int maxPage, int startPage,
			int endPage) {
		super();
		this.listCount = listCount;
		this.currentPage = currentPage;
		this.pageLimit = pageLimit;
		this.boardLimit = boardLimit;
		this.maxPage = maxPage;
		this.startPage = startPage;
		this.endPage = endPage;
	}
	
	@Override
	public String toString() {
		return "PageInfo [listCount=" + listCount + ", currentPage=" + currentPage + ", pageLimit=" + pageLimit
				+ ", boardLimit=" + boardLimit + ", maxPage=" + maxPage + ", startPage=" + startPage + ", endPage="
				+ endPage + "]";
	}
	public int getListCount() {
		return listCount;
	}
	public void setListCount(int listCount) {
		this.listCount = listCount;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageLimit() {
		return pageLimit;
	}
	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}
	public int getBoardLimit() {
		return boardLimit;
	}
	public void setBoardLimit(int boardLimit) {
		this.boardLimit = boardLimit;
	}
	public int getMaxPage() {
		return maxPage;
	}
	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	
	

}
