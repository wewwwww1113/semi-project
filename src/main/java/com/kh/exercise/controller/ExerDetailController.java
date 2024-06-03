package com.kh.exercise.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.exercise.model.service.ExerService;
import com.kh.exercise.model.vo.Exercise;
import com.kh.exercise.model.vo.Photo;

/**
 * Servlet implementation class ExerDetailController
 */
@WebServlet("/detail.ex")
public class ExerDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExerDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int eno=Integer.parseInt(request.getParameter("eno"));	
		ExerService es=new ExerService();
		
		Exercise ex=es.selectExer(eno);
		ArrayList<Photo> ap=es.selectPhotoArr(eno);
		
		request.setAttribute("es", es);
		request.setAttribute("ex", ex);
		request.setAttribute("ap", ap);
		
		request.getRequestDispatcher("views/exercise/exerDetailView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
