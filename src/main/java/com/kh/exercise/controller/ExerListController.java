package com.kh.exercise.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.kh.exercise.model.service.ExerService;
import com.kh.exercise.model.vo.*;

/**
 * Servlet implementation class ExerListController
 */
@WebServlet("/list.ex")
public class ExerListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExerListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<ExerCategory> ec=new ExerService().selectCategory();
		
		ArrayList<Exercise> elist=new ArrayList<>();
		if(request.getParameter("category")!=null) {
			int categoryNo= Integer.parseInt(request.getParameter("category"));
			elist=new ExerService().selectexList(categoryNo);
			
			
		}else {
			
			elist=new ExerService().selectexList();
		}
		
		
		
		request.setAttribute("elist",elist);
		request.setAttribute("ec", ec);
		
		
		request.getRequestDispatcher("views/exercise/exerListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
