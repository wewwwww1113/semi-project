package com.kh.infoboard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.infoboard.model.service.InfoBoardService;
import com.kh.infoboard.model.vo.Category;
import com.kh.infoboard.model.vo.InfoBoard;

/**
 * Servlet implementation class InfoBoardUpdateController
 */
@WebServlet("/infoupdate.bo")
public class InfoBoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoBoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		InfoBoard ib = new InfoBoardService().selectInfoBoard(bno);
		
		request.setAttribute("ib",ib);
		ArrayList<Category> ftList = new InfoBoardService().selectCategory();
		
		request.getRequestDispatcher("/views/infoboard/infoboardUpdateView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userNo=request.getParameter("userNo");
		String title=request.getParameter("boardTitle");
		String content=request.getParameter("boardContent");
		String category=request.getParameter("category");
		
		int bno=Integer.parseInt(request.getParameter("boardNo"));
		
		InfoBoard ib=new InfoBoard(userNo,title,content,category);
		ib.setBoardNo(bno);
		
		int result=new InfoBoardService().updateInfoBoard(ib);
		
		HttpSession session = request.getSession();
		
		if(result>0) {
			session.setAttribute("alertMsg","수정완료");
		}else {
			session.setAttribute("alertMsg", "수정에 실패했습니다.");
		}
		response.sendRedirect("infodetail.bo?bno="+bno);
	}

}
