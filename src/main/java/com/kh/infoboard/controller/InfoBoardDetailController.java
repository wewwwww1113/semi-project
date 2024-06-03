package com.kh.infoboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.infoboard.model.service.InfoBoardService;
import com.kh.infoboard.model.vo.InfoBoard;

/**
 * Servlet implementation class InfoBoardDetailController
 */
@WebServlet("/infodetail.bo")
public class InfoBoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoBoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int bno = Integer.parseInt(request.getParameter("bno"));

		int result = new InfoBoardService().increaseCount(bno);
		if (result > 0) {
			InfoBoard ib = new InfoBoardService().selectInfoBoard(bno);
			
			request.setAttribute("ib", ib);
		} else {
			request.getSession().setAttribute("alertMsg", "게시판 접근에 실패했습니다.");
			response.sendRedirect(request.getHeader("referer"));
		}

		request.getRequestDispatcher("/views/infoboard/infoBoardDetailView.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
