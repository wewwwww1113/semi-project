package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;
import com.kh.common.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/board.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시판 세부설정
		int listCount;//총 게시글 개수
		int currentPage; //현재페이지
		int pageLimit; //페이지 하단에 보여질 페이징바의 최대개수
		int boardLimit; //한 페이지에 보여줄 게시글 개수
		
		int maxPage; //가장 마지막 페이징바(총 페이지 개수)
		int startPage; //페이지 하단에 보여질 페이징바의 시작 수
		int endPage; //페이지 하단에 보여질 페이징바의 끝 수
		
		String category=request.getParameter("category");
		//카테고리 선택에 따라 현재 게시글 개수 조회하기(0이면 구분없이,0이 아니면 해당하는 카테고리만)
		if(category.equals("0")) {
			listCount=new BoardService().listCount();
		} else {
			listCount=new BoardService().listCount(category);
		}
		
		
		//currentPage 현재페이지 정보
		currentPage=Integer.parseInt(request.getParameter("currentPage"));
		

		
		//페이지 하단에 보여질 페이징바 최대개수
		pageLimit=10;
		//한페이지에서 보여줄 게시글 개수
		boardLimit=20;
		
		maxPage=(int)(Math.ceil((double)listCount/boardLimit));
		//listCount,boardLimit 둘다 int이기 때문에 소수점을 살리기 위해서는 둘중 하나에 double형변환을 건다.
		//나온 결과를 Math.ceil()을 통해 올림처리
		//올림 처리를 한후 double형이기 때문에 int로 형변환
		
		startPage=(currentPage-1)/pageLimit * pageLimit+1;

		//endPage :pageLimit가 10일때 startPage가 1이면 10,11이면 20,...
		endPage=startPage+pageLimit-1;

		//만약 현재 페이징바 처리에 endPage가 maxPage보다 더 많을경우 endPage값을 maxPage값으로 변경하기
		if(maxPage<endPage) {
			endPage=maxPage;
		}
		
		//페이지 관련 데이터들을 객체에 담기
		PageInfo pi=new PageInfo(listCount,currentPage,pageLimit,boardLimit,maxPage,startPage,endPage);
		
		String sort=request.getParameter("sort");
		
		ArrayList<Board> bList=new ArrayList<>();
		if(category.equals("0")) {
			bList=new BoardService().selectList(pi,sort);
		} else {
			bList=new BoardService().selectList(pi,category,sort);
		}
		
		ArrayList<Category> ctList=new BoardService().selectCategory();


		//자유게시판으로 넘기기
		request.setAttribute("bList", bList);
		request.setAttribute("ctList", ctList);
		request.setAttribute("category", category);
		request.setAttribute("sort", sort);
		request.setAttribute("pi", pi);
		request.getRequestDispatcher("/views/board/boardListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
