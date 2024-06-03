package com.kh.market.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Reply;
import com.kh.market.model.service.MarketService;
import com.kh.market.model.vo.Review;

/**
 * Servlet implementation class ReviewInsertController
 */
@WebServlet("/insertR.mk")
public class ReviewInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int itno=Integer.parseInt(request.getParameter("itno"));
		int uno=Integer.parseInt(request.getParameter("uno"));
		String content=request.getParameter("content");
		double score=Double.parseDouble(request.getParameter("checkedVal"));
		
		Review re=new Review(itno,uno,content,score);
		
		int result=new MarketService().insertReview(re);
		String message="";
		if(result>0) {
			message="리뷰를 작성했습니다.";
		} else {
			message="리뷰 작성에 실패했습니다.";
		}
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(message);
	
	
	}

}
