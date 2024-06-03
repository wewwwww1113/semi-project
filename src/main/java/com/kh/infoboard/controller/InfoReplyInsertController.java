package com.kh.infoboard.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.vo.Reply;
import com.kh.infoboard.model.service.InfoBoardService;

/**
 * Servlet implementation class InfoReplyInsertController
 */
@WebServlet("/infoinsertReply.bo")
public class InfoReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoReplyInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int bno = Integer.parseInt(request.getParameter("bno"));
		String uno = request.getParameter("uno");
		String content = request.getParameter("content");

		Reply re = new Reply(bno, uno, content);

		int result = new InfoBoardService().insertReply(re);
		String message = "";
		if (result > 0) {
			message = "댓글을 작성했습니다.";
		} else {
			message = "댓글 작성에 실패했습니다.";
		}

		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(message);
	}

}
