package com.kh.memorials.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.memorials.model.dao.MemorialsDao;
import com.kh.memorials.model.service.MemorialsService;
import com.kh.memorials.model.vo.Memorials;
import com.kh.memorials.model.vo.MemorialsAttachment;

/**
 * Servlet implementation class MemberIndividualRecordControlloer
 */
@WebServlet("/MemorialsMyPage.me")
public class MemorialsMyPageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemorialsMyPageController() {
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
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		String UserId = request.getParameter("UserId");
		
		ArrayList<Memorials> list = new MemorialsService().MemorialsList(UserId);
		
		ArrayList<MemorialsAttachment> atlist = new MemorialsService().MemorialsListAttachment(list);
		
		HttpSession session = request.getSession();
		if(list.isEmpty() ) {
			session.setAttribute("alertMsg", "기록이 없습니다.기록 후 조회해 주세요.");
			response.sendRedirect(request.getContextPath()+"/Memorials.me");
		}else {
            request.setAttribute("list", list);
            request.setAttribute("atlist", atlist);
	        request.getRequestDispatcher("views/memorials/memorialsMyPage.jsp").forward(request, response);
	    }
	}

}
