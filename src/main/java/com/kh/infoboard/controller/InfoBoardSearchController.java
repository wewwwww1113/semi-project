package com.kh.infoboard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.infoboard.model.service.InfoBoardService;
import com.kh.infoboard.model.vo.InfoBoard;

/**
 * Servlet implementation class InfoBoardSearchController
 */
@WebServlet("/infosearch.bo")
public class InfoBoardSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InfoBoardSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String keyword = request.getParameter("search");
		String category = request.getParameter("searchCategory");
		
		ArrayList<InfoBoard> fList = new ArrayList<>();
		InfoBoardService ibs = new InfoBoardService();
		
		/*switch(category) {
		case "title":
			bList=bs.searchBoardByTitle(keyword);
			break;
		case "content":
			bList=bs.searchBoardByContent(keyword);
			break;
		case "writer":
			bList=bs.searchBoardByWriter(keyword);
			break;
		}*/
		
		fList=ibs.searchInfoBoard(keyword,category);
		
		request.setAttribute("fList", fList);
		request.setAttribute("keyword", keyword);
		

		
		request.getRequestDispatcher("/views/infoboard/infoBoardSearchView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
