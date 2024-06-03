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
 * Servlet implementation class FindIdController
 */
@WebServlet("/findId.me")
public class FindIdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindIdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ffPn = request.getParameter("ffPn");
		
		Member findIdMember = new MemberService().findId(ffPn);
		
		HttpSession session = request.getSession();
		
		if(findIdMember==null) {

			session.setAttribute("alertMsg", "핸드폰번호가 올바르지 않습니다. 다시 적어주세요");
			RequestDispatcher view = request.getRequestDispatcher("views/member/fId.jsp");
		    view.forward(request, response);
			
		}else {
			session.setAttribute("findIdMember",findIdMember);
			String alertMsg = "비밀번호 찾기 성공. 회원님의 아이디는 " + findIdMember.getUserId() + "입니다.";
		    session.setAttribute("alertMsg", alertMsg);
		    response.sendRedirect(request.getContextPath() + "/views/member/login.jsp");
			
		}
		
		
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
