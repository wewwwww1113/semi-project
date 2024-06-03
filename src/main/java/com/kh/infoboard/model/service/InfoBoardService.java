package com.kh.infoboard.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;
import com.kh.infoboard.model.dao.InfoBoardDao;
import com.kh.infoboard.model.vo.InfoBoard;
import com.kh.infoboard.model.vo.InfoCategory;
import com.kh.infoboard.model.vo.Reply;

public class InfoBoardService {

	
	
	public int listCount() {
		
		Connection conn =JDBCTemplate.getConnection();
		int result = new InfoBoardDao().listCount(conn);
		
		JDBCTemplate.close(conn);
		return result;

	}
	//선택한 카테고리인 게시판 개수 출력(페이징바 조절용)
	public int listCount(String category) {
		Connection conn=JDBCTemplate.getConnection();
		int result= new InfoBoardDao().listCount(conn,category);
		
		JDBCTemplate.close(conn);
		return result;
	}

	
	
	public ArrayList<InfoBoard> selectList(PageInfo pi, String sort) {

		Connection conn = JDBCTemplate.getConnection();
		ArrayList<InfoBoard> fList = new InfoBoardDao().selectList(conn,pi,sort);
		
		JDBCTemplate.close(conn);
		
		return fList;
	}
	
	public ArrayList<InfoBoard> selectList(PageInfo pi, String category, String sort) {
		
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<InfoBoard> fList = new InfoBoardDao().selectList(conn, pi,category, sort);
		
		JDBCTemplate.close(conn);
		
		return fList;
	}
	
	
	
	public ArrayList<InfoCategory> selectCategory() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<InfoCategory> ftList = new InfoBoardDao().selectCategory(conn);
		
		JDBCTemplate.close(conn);
		
		return ftList;
	}
	
	public InfoBoard selectInfoBoard(int bno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		InfoBoard ib = new InfoBoardDao().selectInfoBoard(conn, bno);
		
		JDBCTemplate.close(conn);
		
		return ib;
		
	}

	
	
	public int increaseCount(int bno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new InfoBoardDao().increaseCount(conn,bno);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
		
	}

	public int increaseRecommend(int uno,int bno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new InfoBoardDao().increaseRecommend(conn, uno,bno);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
		
		}

	
	
	public String checkRecommend(int uno ,  int bno) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		String result = new InfoBoardDao().checkRecommend(conn,uno,bno);
		
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertReply(Reply re) {
		
		Connection conn=JDBCTemplate.getConnection();
		int result= new InfoBoardDao().insertReply(conn,re);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result;
		
	}


	public ArrayList<Reply> selectReply(int bno) {
		
		Connection conn=JDBCTemplate.getConnection();
		ArrayList<Reply> rList = new InfoBoardDao().selectReply(conn,bno);
		
		JDBCTemplate.close(conn);
		return rList;
	}

	public int insertInfoBoard(InfoBoard ib) {
		
		Connection conn=JDBCTemplate.getConnection();
		int result=new InfoBoardDao().insertInfoBoard(conn,ib);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;

	}
	
	
	public int updateInfoBoard(InfoBoard ib) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result=new InfoBoardDao().updateInfoBoard(conn,ib);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
		
	}

	public int deleteInfoBoard(int bno) {
		
		Connection conn=JDBCTemplate.getConnection();
		int result=new InfoBoardDao().deleteInfoBoard(conn,bno);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	

	public ArrayList<InfoBoard> searchInfoBoard(String keyword, String category) {
		
		Connection conn=JDBCTemplate.getConnection();
		ArrayList<InfoBoard> fList=new InfoBoardDao().searchInfoBoard(conn,keyword,category);
		
		JDBCTemplate.close(conn);
		return fList;
	}
	

	
}

