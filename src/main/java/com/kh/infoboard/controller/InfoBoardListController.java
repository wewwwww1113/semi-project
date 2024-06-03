package com.kh.infoboard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.kh.common.model.vo.PageInfo;
import com.kh.infoboard.model.service.InfoBoardService;
import com.kh.infoboard.model.vo.InfoBoard;
import com.kh.infoboard.model.vo.InfoCategory;


/**
 * Servlet implementation class InfoBoardListController
 */
@WebServlet("/infoboard.bo")
public class InfoBoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoBoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시판 세부 설정
		int listCount;//총 게시글 개수
		int currentPage; //현재페이지
		int pageLimit;
		int boardLimit;
		
		int maxPage;//가장 마지막 페이징바
		int startPage;//페이지 하단에 보여줄 페이징바의 첫 수
		int endPage;//페이지 하단에 보여질 페이징바의 끝수
		
		
		String category = request.getParameter("category");
		//카테고리 선택에 따라 현재 게시글 개수 조회하기 (0이면 구분없이 , 0이 아니면 해당하는 카테고리가 보일수 있게)
		if(category.equals("0")) {
			listCount=new InfoBoardService().listCount();
		} else {
			listCount=new InfoBoardService().listCount(category);
		}
	    //currentPage 현재페이지 정보
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		//페이지 하단에 보여질 페이징바의 최대 개수
		pageLimit=10;
		//한페이지에 보여줄 게시글 개수
		boardLimit =20;
		
		maxPage=(int)(Math.ceil((double)listCount/boardLimit));
		
		startPage = (currentPage-1)/pageLimit*pageLimit+1;
		
		//endPage : PageLimit가 10일때 startpage가 1이면 10,11이면 20...
		endPage = startPage+pageLimit-1;
		//만약 현재 페이징 바 처리에 endpage가 maxpage보다 더 많을경우 endpage값을 maxpage값에 맞춰두기
		
		if(maxPage<endPage){
			endPage=maxPage;
		}
	
		
		//페이지 관련 데이터들을 객체에 담기
		PageInfo pi = new PageInfo(listCount,currentPage,pageLimit,boardLimit,maxPage,startPage,endPage);
		
		String sort = request.getParameter("sort");
		
		ArrayList<InfoBoard> fList = new ArrayList<>();	
		
		if(category.equals("0")) {
			fList = new InfoBoardService().selectList(pi,sort);
			
		}else {
			fList = new InfoBoardService().selectList(pi,category,sort);
		}
		
		ArrayList<InfoCategory> ftList = new InfoBoardService().selectCategory();
		// 정보게시판으로 넘기기
		request.setAttribute("fList", fList);
		request.setAttribute("ftList", ftList);
		request.setAttribute("category", category);
		request.setAttribute("sort", sort);
		request.setAttribute("pi", pi);
		
		request.getRequestDispatcher("/views/infoboard/infoBoardListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
