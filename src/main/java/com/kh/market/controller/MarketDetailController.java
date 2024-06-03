package com.kh.market.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.board.model.vo.Category;
import com.kh.market.model.service.MarketService;
import com.kh.market.model.vo.Component;
import com.kh.market.model.vo.Item;
import com.kh.market.model.vo.ItemAttachment;

/**
 * Servlet implementation class MarketDetailController
 */
@WebServlet("/detail.mk")
public class MarketDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int itemNo=Integer.parseInt(request.getParameter("itno"));
		
		MarketService ms = new MarketService();
		
		Item i = ms.selectItem(itemNo);
		ArrayList<ItemAttachment> itList = ms.selectAttachmentList(itemNo);
		Component c = ms.selectComponent(itemNo);
		ArrayList<Category> cList =new MarketService().selectCategory();
		
		Category[] category = cList.toArray(new Category[cList.size()]);
		
		request.setAttribute("cList", category);
		request.setAttribute("i", i);
		request.setAttribute("itList", itList);
		request.setAttribute("c", c);
		request.setAttribute("listLength", itList.size());
		
		request.getRequestDispatcher("views/market/marketDetailView.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
