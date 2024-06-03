package com.kh.member.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class MemberCheckControll
 */
@WebServlet("/idCheck.me")
public class MemberCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MemberCheckController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String inputId = request.getParameter("inputId");
//		
//		String responseData = "'"+inputId+"'은 이미 사용중인 아이디입니다.";
//		
//		response.setContentType("text/html;charset=UTF-8");
//		response.getWriter().print(responseData);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String inputId = request.getParameter("inputId");

		Boolean flag = new MemberService().checkId(inputId);

		String responseStr = "";

		if (flag) {
			responseStr = "NNNNN";
		} else {
			responseStr = "NNNNY";
		}
		
		

		response.getWriter().print(responseStr);
		
		
		
	}

}
