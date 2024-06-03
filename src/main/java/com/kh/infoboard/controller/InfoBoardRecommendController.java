package com.kh.infoboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.infoboard.model.service.InfoBoardService;

/**
 * Servlet implementation class InfoBoardRecommendController
 */
@WebServlet("/inforecommend.bo")
public class InfoBoardRecommendController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoBoardRecommendController() {
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
		
		switch(new InfoBoardService().checkRecommend(uno,bno)) {
		
		case "RCYYY": //추천기록이 없는경우
			int result=new InfoBoardService().increaseRecommend(uno,bno);
			
			if(result>0) {
				message="이 글을 추천했습니다.";
			} else {
				message="추천에 실패했습니다.";
			}
			break;
			
		case "RCNNN": //추천기록이 있는경우
			message="이미 이 글을 추천했습니다.";
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
