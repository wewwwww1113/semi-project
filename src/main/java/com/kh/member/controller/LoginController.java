package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login.me")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		

		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		

		Cookie cookie = null;

		String saveId = request.getParameter("saveId");
		

		if(saveId != null) {

			cookie = new Cookie("userId",userId);

			cookie.setMaxAge(60*60*24);

			response.addCookie(cookie);
		}else { 
			cookie = new Cookie("userId",null);
			cookie.setMaxAge(0); 
			response.addCookie(cookie);
			
		}
		
		Member loginUser = new MemberService().loginMember(userId,userPwd);
		
		
		HttpSession session = request.getSession();
		
		if(loginUser==null) {

			session.setAttribute("alertMsg", "아이디 또는 비밀번호가 올바르지 않습니다.");
			RequestDispatcher view = request.getRequestDispatcher("views/member/login.jsp");
		    view.forward(request, response);
			
		}else {
			session.setAttribute("loginUser",loginUser);
			session.setAttribute("alertMsg", "로그인 성공");
			response.sendRedirect(request.getContextPath());
			
		}
	}
}