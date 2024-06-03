package com.kh.board.controller;

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
import com.kh.board.model.vo.Category;

/**
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/insert.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Category> ctList=new BoardService().selectCategory();
		
		request.setAttribute("ctList", ctList);
		
		request.getRequestDispatcher("/views/board/boardInsertView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userNo=request.getParameter("userNo");
		String category=request.getParameter("category");
		String title=request.getParameter("boardTitle");
		String content=request.getParameter("boardContent");
		Board b=new Board(userNo,title,content,category);
		int result=new BoardService().insertBoard(b);
		
		HttpSession session=request.getSession();
		if(result>0) {
			session.setAttribute("alertMsg", "작성이 완료되었습니다.");
		} else {
			session.setAttribute("alertMsg","작성에 실패했습니다.");
		}
		response.sendRedirect(request.getContextPath()+"/board.bo?currentPage=1&category=0&sort=latest");
		
		
	}

}
