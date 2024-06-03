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
import com.kh.market.model.vo.Item;
import com.kh.member.model.service.MemberService;

/**
 * Servlet implementation class MarketListController
 */
@WebServlet("/list.mk")
public class MarketListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarketListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Category> cList =new MarketService().selectCategory();
		
		Category[] category = cList.toArray(new Category[cList.size()]);
		
		request.setAttribute("cList", category);
		ArrayList<Item> list=null;
		int categoryNo=0;
		if(request.getParameter("category")!=null) {
			categoryNo=Integer.parseInt(request.getParameter("category"));
		}
		String sort=request.getParameter("sort");
		if(categoryNo==0) {
			
			 list = new MarketService().selectItemList(sort);
		}else {
			list = new MarketService().selectItemList(sort,categoryNo);
		}
		
		for (int i=0;i<list.size();i++) {
			
			int itemCode=list.get(i).getItemCode();
			
			Double result=(Double) new MarketService().selectScore(itemCode);
			
			new MarketService().updateScore(itemCode, result);
				
			}
	
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("views/market/marketListView.jsp").forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
