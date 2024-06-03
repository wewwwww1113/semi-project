package com.kh.market.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.market.model.service.MarketService;

/**
 * Servlet implementation class MarketDeleteController
 */
@WebServlet("/delete.mk")
public class MarketDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int itno=Integer.parseInt(request.getParameter("itno"));
		
		int result=new MarketService().deleteItem(itno);
		
		HttpSession session = request.getSession();
		
		if(result>0) {
			session.setAttribute("alertMsg", "상품게시 중지됨");
			response.sendRedirect(request.getContextPath()+"/list.mk?sort=topSal");
		}else {
			session.setAttribute("alertMsg", "작업 실패 관리자에게 문의하십시오");
			response.sendRedirect(request.getContextPath()+"detail.mk?itno="+itno);
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
