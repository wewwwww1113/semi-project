package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		String test = request.getParameter("test");
		String pass = request.getParameter("pass");
		
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String gender = request.getParameter("gender");
		String nickName = request.getParameter("nickName");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		if(test.equals(pass)) {
		Member m = new Member(userId,userName,gender,nickName,email,phone);
		
		Member uMember = new MemberService().updateMember(m);
		
		if(uMember == null ) {
			request.setAttribute("alertMsg", "정보수정실패");
			response.sendRedirect(request.getContextPath()+"/myPage.me");
			
		}else {
			HttpSession session = request.getSession();
			
			session.setAttribute("alertMsg", "정보수정성공");
			session.setAttribute("loginUser",uMember);
			
			response.sendRedirect(request.getContextPath()+"/myPage.me");
			
		}
	}else {
		request.getSession().setAttribute("alertMsg", "비밀번호가 일치하지않습니다. 다시 시도해주세요.");
		response.sendRedirect(request.getContextPath()+"/myPage.me");
	}
	}
}
