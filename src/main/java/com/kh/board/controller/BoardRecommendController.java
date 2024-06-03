package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;

/**
 * Servlet implementation class BoardRecommendController
 */
@WebServlet("/recommend.bo")
public class BoardRecommendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardRecommendController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int uno=Integer.parseInt(request.getParameter("uno"));
		int bno=Integer.parseInt(request.getParameter("bno"));
		
		String message="";
		
		switch(new BoardService().checkRecommend(uno,bno)) {
		case "RCYYY": //추천기록이 없는경우
			int result=new BoardService().increaseRecommend(uno,bno);
			if(result>0) {
				message="RCYYY";
			} else {
				message="RCYYN";
			}
			break;
		case "RCNNN": //추천기록이 있는경우
			message="RCNNN";
			break;
		}
		
		
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(message);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
