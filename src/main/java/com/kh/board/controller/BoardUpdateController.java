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
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.bo")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bno=Integer.parseInt(request.getParameter("bno"));
		Board b=new BoardService().selectBoard(bno);
		
		request.setAttribute("b", b);
		ArrayList<Category> ctList=new BoardService().selectCategory();
		request.setAttribute("ctList", ctList);
		
		request.getRequestDispatcher("/views/board/boardUpdateView.jsp").forward(request, response);
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
		
		Board b=new Board(userNo,title,content,category);
		b.setBoardNo(bno);
		
		int result=new BoardService().updateBoard(b);
		
		HttpSession session=request.getSession();
		
		if(result>0) {
			session.setAttribute("alertMsg", "수정이 완료되었습니다.");
		} else {
			session.setAttribute("alertMsg", "수정에 실패했습니다.");
		}
		response.sendRedirect("detail.bo?bno="+bno);
		
	}

}
