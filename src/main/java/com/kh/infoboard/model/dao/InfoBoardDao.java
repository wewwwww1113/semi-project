package com.kh.infoboard.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;
import com.kh.infoboard.model.vo.InfoBoard;
import com.kh.infoboard.model.vo.InfoCategory;
import com.kh.infoboard.model.vo.Reply;

public class InfoBoardDao {
	
	private Properties prop = new Properties();
	
	public InfoBoardDao() {
		
			try {
				
				String filePath =InfoBoardDao.class.getResource("/resources/sql/info-mapper.xml").getPath();
				prop.loadFromXML(new FileInputStream(filePath));
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	

	public int listCount(Connection conn) {
		
		ResultSet rset = null;
		Statement stmt = null;
		
		String sql = prop.getProperty("listCount");
		int result = 0;
		
		try {
			stmt=conn.createStatement();
			rset=stmt.executeQuery(sql);
			
			if(rset.next()) {
				result=rset.getInt("Count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
	
		return result;
	}

	public int listCount(Connection conn, String category) {

		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("listCountByCategory");
		
		int result = 0;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,category);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				result = rset.getInt("Count");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return result;
		
		
	}

	
	
	public ArrayList<InfoBoard> selectList(Connection conn, PageInfo pi, String sort) {
		
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = "";
		switch(sort) {
		case "latest":
			sql=prop.getProperty("selectList");
			break;
		case "view":
			sql=prop.getProperty("selectListSortbyView");
			break;
		case "recommend":
			sql= prop.getProperty("selectListSortbyRecommend");
			break;
		}
		
		String sql2 = prop.getProperty("selectNickName");
		ArrayList<InfoBoard> fList = new ArrayList<>();
		
		int startRow = (pi.getCurrentPage()-1)*pi.getBoardLimit()+1;
		int endRow = pi.getCurrentPage()*pi.getBoardLimit();
		//보여줄 게시글 끝 =현재페이지 * 페이지당 글 개수 제한
		//BOARD_NO,CATEGORY_NAME,BOARD_TITLE,USER_ID,COUNT,RECOMMEND,UPLOAD_DATE
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1,startRow);
			pstmt.setInt(2,endRow);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				fList.add(new InfoBoard(
						  rset.getInt("BOARD_NO"),
						  rset.getString("CATEGORY_NAME"),
						  rset.getString("BOARD_TITLE"),
						  rset.getString("USER_ID"),
						  rset.getInt("COUNT"),
						  rset.getInt("RECOMMEND"),
						  rset.getDate("UPLOAD_DATE")));
		}
//	    for(InfoBoard ib : fList) {
//	    	
//	    	pstmt=conn.prepareStatement(sql2);
//	    	pstmt.setString(1,ib.getBoardWriter());
//	    	
//	    	rset=pstmt.executeQuery();
//	    	
//	    	if(rset.next()) {
//	    		if(rset.getString("NICKNAME")!=null) {
//	    			ib.setBoardWriter(rset.getString("NICKNAME"));
//	    		}
//	    	}
//	    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return fList;
	}

	public ArrayList<InfoBoard> selectList(Connection conn, PageInfo pi, String category, String sort) {

		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = "";
		switch (sort) {
		case "latest":
			sql = prop.getProperty("selectListByCategory");
			break;
		case "view":
			sql = prop.getProperty("selectListByCategorySortbyView");
			break;
		case "recommend" : 
			sql=prop.getProperty("selectListByCategorySortbyRecommend");
			break;

		}
		String sql2 = prop.getProperty("selectNickName");
		ArrayList<InfoBoard> fList = new ArrayList<>();
		
		int startRow=(pi.getCurrentPage()-1)*pi.getBoardLimit()+1; 
		int endRow=pi.getCurrentPage()*pi.getBoardLimit(); 
		
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, category);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, endRow);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				fList.add(new InfoBoard(
						rset.getInt("BOARD_NO"),
						rset.getString("CATEGORY_NAME"),
					    rset.getString("BOARD_TITLE"),
						rset.getString("USER_ID"), //boardWriter
						rset.getInt("COUNT"),
						rset.getInt("RECOMMEND"),
						rset.getDate("UPLOAD_DATE")));
			}
			for(InfoBoard ib : fList) {
				pstmt=conn.prepareStatement(sql2);
				
				pstmt.setString(1, ib.getBoardWriter());
				rset=pstmt.executeQuery();
				if(rset.next()) {
					if(rset.getString("NICKNAME")!=null) {
						ib.setBoardWriter(rset.getString("NICKNAME"));
					}
				}
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
		}
		
		return fList;
	}



	
	
	
	public ArrayList<InfoCategory> selectCategory(Connection conn) {
		
		ResultSet rset = null;
		Statement stmt = null;
		
		String sql = prop.getProperty("selectCategory");
		ArrayList<InfoCategory> ftList = new ArrayList<>();
		
		try {
			
			stmt=conn.createStatement();
			rset=stmt.executeQuery(sql);
			
			while(rset.next()) {
				ftList.add(new InfoCategory(rset.getInt("CATEGORY_NO"),
						                rset.getString("CATEGORY_NAME")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		return ftList;
	}



		
	public InfoBoard selectInfoBoard(Connection conn, int bno) {
		
		ResultSet rset =null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectBoard");
		
		InfoBoard ib = null;
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				ib = new InfoBoard(rset.getInt("BOARD_NO"),
						         rset.getString("USER_ID"),
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
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
			
		}
		return ib;
		
		
	
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
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}



	
	public int increaseRecommend(Connection conn, int uno, int bno) {
		
		int result =0;
		int result2 = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("increaseRecommend");
		String sql2 = prop.getProperty("saveRecommend");
		
		
		try {
			//g해당 게시글의 추천수 늘리기
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1,uno);
			result = pstmt.executeUpdate();
			
			//해당 유저가 이 게시글을 추천했다는 기록 입력
			pstmt = conn.prepareStatement(sql2);
			pstmt.setInt(1, uno);
			pstmt.setInt(2, bno);
			result2 = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally { 
			JDBCTemplate.close(pstmt);
			
		}
		
		return result*result2;
	}




	
	
	public String checkRecommend(Connection conn, int uno, int bno) {
		
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("checkRecommend");
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
		String sql2=prop.getProperty("selectNickName");
		
		ArrayList<Reply> rList=new ArrayList<>();
		
		try {
			
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, bno);
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				
				//REPLY_NO,USER_ID,REPLY_CONTENT,CREATE_DATE
				rList.add(new Reply(rset.getInt("REPLY_NO"),
									rset.getString("USER_ID"),
									rset.getString("REPLY_CONTENT"),
									rset.getDate("CREATE_DATE")));
			}
			//닉네임이 있으면 아이디 대신 닉네임 출력
			for(Reply r : rList) {
				pstmt=conn.prepareStatement(sql2);
				pstmt.setString(1, r.getReplyWriter());
				rset=pstmt.executeQuery();
				if(rset.next()) {
					if(rset.getString("NICKNAME")!=null) {
					r.setReplyWriter(rset.getString("NICKNAME"));
					}
				}
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



	
	public int insertInfoBoard(Connection conn, InfoBoard ib) {
		
		int result=0;
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("insertBoard");
		
		//System.out.println(ib);
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, ib.getBoardWriter());
			pstmt.setString(2, ib.getBoardTitle());
			pstmt.setString(3, ib.getBoardContent());
			pstmt.setString(4, ib.getCategory());
			
			result=pstmt.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}



	public int updateInfoBoard(Connection conn, InfoBoard ib) {
		
		int result=0;
		PreparedStatement pstmt=null;
		
		String sql=prop.getProperty("updateBoard");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, ib.getBoardTitle());
			pstmt.setString(2, ib.getBoardContent());
			pstmt.setString(3, ib.getCategory());
			pstmt.setInt(4, ib.getBoardNo());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}




	public int deleteInfoBoard(Connection conn, int bno) {

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




	public ArrayList<InfoBoard> searchInfoBoard(Connection conn, String keyword, String category) {
		
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
		}
		
		ArrayList<InfoBoard> fList=new ArrayList<>();
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, "%"+keyword+"%");
			
			rset=pstmt.executeQuery();
			
			while(rset.next()) {
				//BOARD_NO,USER_ID,BOARD_TITLE,BOARD_CONTENT,COUNT,RECOMMEND,REVISE_DATE,CATEGORY_NAME
				fList.add(new InfoBoard(rset.getInt("BOARD_NO"),
									rset.getString("CATEGORY_NAME"), 
									rset.getString("BOARD_TITLE"), 
									rset.getString("USER_ID"), 
									rset.getInt("COUNT"), 
									rset.getInt("RECOMMEND"), 
									rset.getDate("REVISE_DATE")));
			}
			String sql2=prop.getProperty("selectNickName");
			for(InfoBoard f : fList) {
				pstmt=conn.prepareStatement(sql2);
				pstmt.setString(1, f.getBoardWriter());
				rset=pstmt.executeQuery();
				if(rset.next()) {
					if(rset.getString("NICKNAME")!=null) {
					f.setBoardWriter(rset.getString("NICKNAME"));
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return fList;
	}



	

	
	
}
