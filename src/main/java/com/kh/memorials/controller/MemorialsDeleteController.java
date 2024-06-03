package com.kh.memorials.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.memorials.model.service.MemorialsService;
import com.kh.memorials.model.vo.Memorials;

/**
 * Servlet implementation class MemorialsDelete
 */
@WebServlet("/memorialsDelete.me")
public class MemorialsDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemorialsDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int mNo = Integer.parseInt(request.getParameter("mNo"));
		
		int result = new MemorialsService().deleteMemorials(mNo);
		
		HttpSession session = request.getSession();
		if(result>0) {
			session.setAttribute("alertMsg", "삭제 성공");
			response.sendRedirect(request.getContextPath()/* +"/views/memorials/memorials.jsp" */);
		}else {
			session.setAttribute("alertMsg", "삭제 실패");
			response.sendRedirect(request.getContextPath()/* +"/views/memorials/memorials.jsp" */);
		}
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
