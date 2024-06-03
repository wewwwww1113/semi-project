package com.kh.board.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.board.model.vo.Board;
import com.kh.board.model.vo.Category;
import com.kh.board.model.vo.Reply;
import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;

public class BoardDao {
	
	private Properties prop=new Properties();
	
	public BoardDao() {
		
		try {
			String filePath=BoardDao.class.getResource("/resources/sql/board-mapper.xml").getPath();
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int listCount(Connection conn) {
		ResultSet rset=null;
		Statement stmt=null;
		
		String sql=prop.getProperty("listCount");
		int result=0;
		try {
			stmt=conn.createStatement();
			rset=stmt.executeQuery(sql);
			if(rset.next()) {
				result=rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return result;
	}

	public int listCount(Connection conn, String category) {
		ResultSet rset=null;
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("listCountByCategory");
		int result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, category);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getInt("COUNT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Board> selectList(Connection conn, PageInfo pi, String sort) {
		ResultSet rset=null;
		PreparedStatement pstmt=null;
		
		String sql="";
		switch(sort) {
		case "latest":
			sql=prop.getProperty("selectList");
			break;
		case "view":
			sql=prop.getProperty("selectListSortbyView");
			break;
		case "recommend":
			sql=prop.getProperty("selectListSortbyRecommend");
			break;
		}
		
		String sql2=prop.getProperty("selectNickName");
		ArrayList<Board> bList=new ArrayList<>();
		
		int startRow=(pi.getCurrentPage()-1)*pi.getBoardLimit()+1; //보여줄 게시글 시작=(현재페이지-1)*페이지당 글개수제한+1
		int endRow=pi.getCurrentPage()*pi.getBoardLimit(); //보여줄 게시글 끝=현재페이지*페이지당 글개수제한
		//BOARD_NO,CATEGORY_NAME,BOARD_TITLE,USER_ID,COUNT,RECOMMEND,UPLOAD_DATE
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				bList.add(new Board(rset.getInt("BOARD_NO"),
									rset.getString("CATEGORY_NAME"),
									rset.getString("BOARD_TITLE"),
									rset.getString("NICKNAME"),
									rset.getInt("COUNT"),
									rset.getInt("RECOMMEND"),
									rset.getDate("UPLOAD_DATE")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return bList;
	}

	public ArrayList<Board> selectList(Connection conn, PageInfo pi, String category, String sort) {
		ResultSet rset=null;
		PreparedStatement pstmt=null;
		
		String sql="";
		switch(sort) {
		case "latest":
			sql=prop.getProperty("selectListByCategory");
			break;
		case "view":
			sql=prop.getProperty("selectListByCategorySortbyView");
			break;
		case "recommend":
			sql=prop.getProperty("selectListByCategorySortbyRecommend");
			break;
		}
		String sql2=prop.getProperty("selectNickName");
		ArrayList<Board> bList=new ArrayList<>();
		
		int startRow=(pi.getCurrentPage()-1)*pi.getBoardLimit()+1; 
		int endRow=pi.getCurrentPage()*pi.getBoardLimit(); 
		//BOARD_NO,CATEGORY_NAME,BOARD_TITLE,USER_ID,COUNT,RECOMMEND,UPLOAD_DATE
		//조건에 CATEGORY_NO 추가됨
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, category);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				bList.add(new Board(rset.getInt("BOARD_NO"),
									rset.getString("CATEGORY_NAME"),
									rset.getString("BOARD_TITLE"),
									rset.getString("NICKNAME"), //boardWriter
									rset.getInt("COUNT"),
									rset.getInt("RECOMMEND"),
									rset.getDate("UPLOAD_DATE")));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return bList;
	}

	public ArrayList<Category> selectCategory(Connection conn) {
		ResultSet rset=null;
		Statement stmt=null;
		
		String sql=prop.getProperty("selectCategory");
		ArrayList<Category> ctList=new ArrayList<>();
		try {
			stmt=conn.createStatement();
			rset=stmt.executeQuery(sql);
			while(rset.next()) {
				ctList.add(new Category(rset.getInt("CATEGORY_NO"),
										rset.getString("CATEGORY_NAME")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return ctList;
	}

	public Board selectBoard(Connection conn, int bno) {
		ResultSet rset=null;
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("selectBoard");
		Board b=null;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				b=new Board(rset.getInt("BOARD_NO"),
							rset.getString("NICKNAME"),
							rset.getString("BOARD_TITLE"),
							rset.getString("BOARD_CONTENT"),
							rset.getInt("COUNT"),
							rset.getInt("RECOMMEND"),
							rset.getDate("REVISE_DATE"),
							rset.getString("CATEGORY_NAME"));
				
			}

				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return b;
	}

	public int increaseCount(Connection conn, int bno) {
		int result=0;
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("increaseCount");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}

	public int increaseRecommend(Connection conn,int uno, int bno) {
		int result=0;
		int result2=0;
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("increaseRecommend");
		String sql2=prop.getProperty("saveRecommend");
		try {
			//해당 게시글의 추천수 늘리기
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			result=pstmt.executeUpdate();
			
			//해당 유저가 이 게시글을 추천했다는 기록 입력
			pstmt=conn.prepareStatement(sql2);
			pstmt.setInt(1, uno);
			pstmt.setInt(2, bno);
			result2=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result*result2;
	}

	public String checkRecommend(Connection conn, int uno, int bno) {
		ResultSet rset=null;
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("checkRecommend");
		String result="RC";
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, uno);
			pstmt.setInt(2, bno);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				//추천 기록이 있는경우
				result +="NNN";
			} else {
				//추천 기록이 없는경우
				result +="YYY";
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int insertReply(Connection conn, Reply re) {
		int result=0;
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("insertReply");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, re.getRefBno());
			pstmt.setInt(2, Integer.parseInt(re.getReplyWriter()));
			pstmt.setString(3, re.getReplyContent());
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Reply> selectReply(Connection conn, int bno) {
		ResultSet rset=null;
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("selectReply");
		ArrayList<Reply> rList=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				//REPLY_NO,USER_ID,REPLY_CONTENT,CREATE_DATE
				rList.add(new Reply(rset.getInt("REPLY_NO"),
									rset.getString("NICKNAME"),
									rset.getString("REPLY_CONTENT"),
									rset.getDate("CREATE_DATE")));
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return rList;
	}

	public int insertBoard(Connection conn, Board b) {
		int result=0;
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("insertBoard");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardWriter());
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
			pstmt.setString(4, b.getCategory());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateBoard(Connection conn, Board b) {
		int result=0;
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("updateBoard");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setString(3, b.getCategory());
			pstmt.setInt(4, b.getBoardNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteBoard(Connection conn, int bno) {
		int result=0;
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("deleteBoard");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public ArrayList<Board> searchBoard(Connection conn, String keyword, String category,PageInfo pi) {
		ResultSet rset=null;
		PreparedStatement pstmt=null;
		String sql=null;
		
		switch(category) {
		case "title":
			sql=prop.getProperty("searchBoardByTitle");
			break;
		case "content":
			sql=prop.getProperty("searchBoardByContent");
			break;
		case "writer":
			sql=prop.getProperty("searchBoardByWriter");
		}
		
		int startRow=(pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
		int endRow=pi.getCurrentPage()*pi.getBoardLimit();
		
		ArrayList<Board> bList=new ArrayList<>();
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				//BOARD_NO,USER_ID,BOARD_TITLE,BOARD_CONTENT,COUNT,RECOMMEND,REVISE_DATE,CATEGORY_NAME
				bList.add(new Board(rset.getInt("BOARD_NO"),
									rset.getString("CATEGORY_NAME"), 
									rset.getString("BOARD_TITLE"), 
									rset.getString("NICKNAME"), 
									rset.getInt("COUNT"), 
									rset.getInt("RECOMMEND"), 
									rset.getDate("REVISE_DATE")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return bList;
	}

	public int deleteReply(Connection conn, int rNo) {
		int result=0;
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("deleteReply");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, rNo);
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	

}
