package com.kh.exercise.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.common.MyFileRenamePolicy;
import com.kh.exercise.model.service.ExerService;
import com.kh.exercise.model.vo.ExerCategory;
import com.kh.exercise.model.vo.Exercise;
import com.kh.exercise.model.vo.Photo;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ExerInsertController
 */
@WebServlet("/insert.ex")
public class ExerInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExerInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<ExerCategory> ec=new ExerService().selectCategory();
		request.setAttribute("category", ec);
		request.getRequestDispatcher("views/exercise/exerInsertView.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		if(ServletFileUpload.isMultipartContent(request)) {
			int maxSize=10*1024*1024;
			String savePath=request.getSession().getServletContext().getRealPath("/resources/uploadFiles/");
			
			MultipartRequest multiRequest=new MultipartRequest(request, savePath, maxSize, "UTF-8",new MyFileRenamePolicy());
		
		String type, title, inf, content;
		int category;
		category=Integer.parseInt(multiRequest.getParameter("category"));
		title=multiRequest.getParameter("title");
		inf=multiRequest.getParameter("inf");
		type=multiRequest.getParameter("type");
		content=multiRequest.getParameter("content");
		
		// 구현할 것 전부 1시간 23분 40초 구간에 있음. (학습 동영상)
		// 이후 insertview에 input형식 추가 밸류 : 유저고유번호
		
		Exercise ex=new Exercise();
		ex.setCategoryNo(category);
		ex.setExerTitle(title);
		ex.setExerInf(inf);
		ex.setExerType(type);
		ex.setExerContent(content);
		//보드안에 해당 구현한 것들 setter
		//
		
		
		ArrayList<Photo> ptlist=new ArrayList<>();
		for(int i=1;i<=4;i++) {
			String key = "file"+i;
			if(multiRequest.getOriginalFileName(key)!=null) {
				Photo p=new Photo();
				p.setOriginName(multiRequest.getOriginalFileName(key));
				p.setChangeName(multiRequest.getFilesystemName(key));
				p.setFilePath("/resources/uploadFiles/");
				
				if(i==1) {
					p.setFileLevel(1);
				} else {
					p.setFileLevel(2);
				}
				ptlist.add(p);
			}
		}
		
		
		int result=new ExerService().insertExer(ex,ptlist);
		HttpSession session=request.getSession();
		
		String msg="";
		if(result>0) {
			msg="작성이 완료되었습니다.";

			response.sendRedirect(request.getContextPath()+"/list.ex");
		} else {
			msg="작성되지 않았습니다...";
			
			response.sendRedirect(request.getContextPath()+"/list.ex");
		}
		session.setAttribute("alertMsg", msg);
		//response.sendRedirect(request.getContextPath()+"/list.ex");
		}
	}

}
