package com.kh.infoboard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



import com.kh.infoboard.model.service.InfoBoardService;
import com.kh.infoboard.model.vo.InfoBoard;
import com.kh.infoboard.model.vo.InfoCategory;

/**
 * Servlet implementation class InfoBoardInsertController
 */
@WebServlet("/infoinsert.bo")
public class InfoBoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoBoardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<InfoCategory> ftList= new InfoBoardService().selectCategory();
		request.setAttribute("ftList", ftList);
		
		request.getRequestDispatcher("/views/infoboard/infoBoardInsertView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userNo = request.getParameter("userNo");
		String title = request.getParameter("infoboardTitle");
		String content = request.getParameter("boardContent");
		String category = request.getParameter("category");
		
		//System.out.println(title);
		
		InfoBoard ib = new InfoBoard(userNo,title,content,category);
		
		
		
		int result = new InfoBoardService().insertInfoBoard(ib);
		
		HttpSession session = request.getSession();
		
		if(result>0) {
			session.setAttribute("alertMsg", "작성완료.");
		}else {
			session.setAttribute("alertMsg", "작성실패.");
		}
		response.sendRedirect(request.getContextPath()+"/Infoboard.bo?currentPage=1&category=0&sort=latest");
	}

}
