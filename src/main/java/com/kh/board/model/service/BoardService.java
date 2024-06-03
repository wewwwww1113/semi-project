package com.kh.board.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;
import com.kh.board.model.vo.Reply;
import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;

public class BoardService {

	public int listCount() {
		Connection conn=JDBCTemplate.getConnection();
		int result=new BoardDao().listCount(conn);
		
		JDBCTemplate.close(conn);
		return result;
	}

	//선택한 카테고리인 게시판 개수 출력(페이징바 조절용)
	public int listCount(String category) {
		Connection conn=JDBCTemplate.getConnection();
		int result=new BoardDao().listCount(conn,category);
		
		JDBCTemplate.close(conn);
		return result;
	}
	
	//게시판 출력
	public ArrayList<Board> selectList(PageInfo pi, String sort) {
		Connection conn=JDBCTemplate.getConnection();
		ArrayList<Board> bList=new BoardDao().selectList(conn,pi,sort);
		
		JDBCTemplate.close(conn);
		return bList;
	}

	//카테고리별 게시판 출력
	public ArrayList<Board> selectList(PageInfo pi, String category, String sort) {
		Connection conn=JDBCTemplate.getConnection();
		ArrayList<Board> bList=new BoardDao().selectList(conn,pi,category,sort);
		
		JDBCTemplate.close(conn);
		return bList;
	}

	public ArrayList<Category> selectCategory() {
		Connection conn=JDBCTemplate.getConnection();
		ArrayList<Category> ctList=new BoardDao().selectCategory(conn);
		
		JDBCTemplate.close(conn);
		return ctList;
	}

	public Board selectBoard(int bno) {
		Connection conn=JDBCTemplate.getConnection();
		Board b=new BoardDao().selectBoard(conn,bno);
		
		JDBCTemplate.close(conn);
		return b;
	}

	public int increaseCount(int bno) {
		Connection conn=JDBCTemplate.getConnection();
		int result=new BoardDao().increaseCount(conn,bno);
		if(result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int increaseRecommend(int uno,int bno) {
		Connection conn=JDBCTemplate.getConnection();
		int result=new BoardDao().increaseRecommend(conn,uno,bno);
		if(result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public String checkRecommend(int uno, int bno) {
		Connection conn=JDBCTemplate.getConnection();
		String result=new BoardDao().checkRecommend(conn,uno,bno);
		
		JDBCTemplate.close(conn);
		return result;
	}

	public int insertReply(Reply re) {
		Connection conn=JDBCTemplate.getConnection();
		int result=new BoardDao().insertReply(conn,re);
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
		ArrayList<Reply> rList=new BoardDao().selectReply(conn,bno);
		
		JDBCTemplate.close(conn);
		return rList;
	}

	public int insertBoard(Board b) {
		Connection conn=JDBCTemplate.getConnection();
		int result=new BoardDao().insertBoard(conn,b);
		if(result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int updateBoard(Board b) {
		Connection conn=JDBCTemplate.getConnection();
		int result=new BoardDao().updateBoard(conn,b);
		if(result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public int deleteBoard(int bno) {
		Connection conn=JDBCTemplate.getConnection();
		int result=new BoardDao().deleteBoard(conn,bno);
		if(result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}

	public ArrayList<Board> searchBoard(String keyword, String category, PageInfo pi) {
		Connection conn=JDBCTemplate.getConnection();
		ArrayList<Board> bList=new BoardDao().searchBoard(conn,keyword,category,pi);
		
		JDBCTemplate.close(conn);
		return bList;
	}

	public int deleteReply(int rNo) {
		Connection conn=JDBCTemplate.getConnection();
		int result=new BoardDao().deleteReply(conn,rNo);
		if(result>0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		return result;
	}

}
