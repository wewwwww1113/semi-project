package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class PwdUpdateController
 */
@WebServlet("/updatePwd.me")
public class PwdUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PwdUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String updatePwd = request.getParameter("updatePwd");

		int result = new MemberService().updatePwd(userId,userPwd,updatePwd);
		
		HttpSession session = request.getSession();
		if(result>0) {
			session.setAttribute("alertMsg", "비밀번호가 변경되었습니다. 다시 로그인해주세요");
			session.removeAttribute("loginUser");

			response.sendRedirect(request.getContextPath());
			
		}else {
			session.setAttribute("alertMsg", "비밀번호 변경실패");
			
			 response.sendRedirect(request.getRequestURI());
			
		}
		
	
	}

}
