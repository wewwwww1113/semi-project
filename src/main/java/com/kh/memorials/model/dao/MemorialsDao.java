package com.kh.memorials.model.dao;

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
import com.kh.memorials.model.vo.Memorials;
import com.kh.memorials.model.vo.MemorialsAttachment;

public class MemorialsDao {


	private Properties prop = new Properties();
	
	
	public MemorialsDao() {
	String filePath = Memorials.class
			.getResource("/resources/sql/memorial-mapper.xml").getPath();
	
	
	try {
		prop.loadFromXML(new FileInputStream(filePath));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

	


	public int selectMemorialsNo(Connection conn) {
	    int memorialsNo = 0;
	    ResultSet rset = null;
	    Statement stmt = null;
	    
	    String sql = prop.getProperty("selectmemorials");
	    try {
	        stmt = conn.createStatement();
	        
	        rset = stmt.executeQuery(sql);
	        
	        if(rset.next()) {
	            memorialsNo = rset.getInt("MNO"); 
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();	
	    } finally {
	        JDBCTemplate.close(rset);
	        JDBCTemplate.close(stmt);
	    }
	    
	    return memorialsNo;
	}
	// 게시글 등록 메소드
	public int insertMemorials(Connection conn, Memorials m) {

		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMemorials");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemorialsDate());
			
			pstmt.setString(2, m.getMemorialsTime());
			
			pstmt.setString(3, String.join(",", m.getMemorialsParts()));
			pstmt.setString(4, m.getMemorialsContent());
			pstmt.setInt(5, m.getMemorialsSelfScore());
			pstmt.setString(6, m.getmUserId());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}


	public int insertMemorialsAttachment(Connection conn, MemorialsAttachment at) {
	    int result = 0;
	    PreparedStatement pstmt = null;
	    String sql = prop.getProperty("memorialsInsertAttachment");
	    
	    try {
	        pstmt = conn.prepareStatement(sql);
	        pstmt.setInt(1, at.getRefMno());
	        pstmt.setString(2, at.getOriginName());
	        pstmt.setString(3, at.getChangeName());
	        pstmt.setString(4, at.getFilePath());
	        
	        result = pstmt.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        JDBCTemplate.close(pstmt);
	    }
	    return result;
	}




	public ArrayList<Memorials> MemorialsList(Connection conn, String UserId) {

		ResultSet rset = null;
		PreparedStatement pstmt = null;
		ArrayList<Memorials> list = new ArrayList<>();
		
		String sql = prop.getProperty("memorialsList");
		
		
		
		
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, UserId);
			
			rset = pstmt.executeQuery();
			
			
			while(rset.next()) {
				
				list.add(new Memorials(rset.getInt("MEMORIALS_NO")
							  			,rset.getString("MEMORIALS_DATE")
							  			,rset.getString("MEMORIALS_TIME")
							  			,rset.getString("MEMORIALS_PARTS").split(",")
							  			,rset.getString("MEMORIALS_CONTENT")
							  			,rset.getInt("MEMORIALS_SELFSCORE")
							  			));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}




	public ArrayList<MemorialsAttachment> MemorialsListAttachment(Connection conn, int meNo) {
		// TODO Auto-generated method stub
		ResultSet rset = null;
		ArrayList<MemorialsAttachment> atlist = new ArrayList<>();
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("selectAttachment");
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, meNo);
			
			rset = pstmt.executeQuery();
			
		while(rset.next()) {
			
			atlist.add(new MemorialsAttachment(rset.getInt("FILE_NO")
						  			,rset.getString("ORIGIN_NAME")
						  			,rset.getString("CHANGE_NAME")
						  			,rset.getString("FILE_PATH")));
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
	}
	return atlist;
}




	public int deleteMemorials(Connection conn, int mNo) {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMemorials");
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, mNo);
			
			result= pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}




	public int deleteMemorialsAttachment(Connection conn, int mNo) {
		// TODO Auto-generated method stub
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteMemorialsAttachment");
		
		try {
			pstmt= conn.prepareStatement(sql);
			pstmt.setInt(1, mNo+1);
			
			result= pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
		
	}




	public int UpdateMemorials(Connection conn, Memorials m) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMemorials");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getMemorialsDate());
			pstmt.setString(2, m.getMemorialsTime());
			pstmt.setString(3, String.join(",", m.getMemorialsParts()));
			pstmt.setString(4, m.getMemorialsContent());
			pstmt.setInt(5,m.getMemorialsSelfScore());
			pstmt.setInt(6, m.getMemorialsNo());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		
		return result;
	}




	public int updateMemorialsAttachment(Connection conn, MemorialsAttachment at) {
		
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMemorialsAttachment");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileNo());
			
			result=pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}




	public Memorials selectMemorials(Connection conn, int memorialsNo) {

		
		ResultSet rset = null; 
		PreparedStatement pstmt = null;
		Memorials me = null; 
		String sql = prop.getProperty("selectMemorials");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, memorialsNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				me = new Memorials(rset.getInt("MEMORIALS_NO")
							      ,rset.getString("MEMORIALS_DATE")
							      ,rset.getString("MEMORIALS_TIME")
							      ,rset.getString("MEMORIALS_PARTS").split(",")
							      ,rset.getString("MEMORIALS_CONTENT")
							      ,rset.getInt("MEMORIALS_SELFSCORE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return me;
	}




	public MemorialsAttachment selectMemorialsAttachment(Connection conn, int memorialsNo) {
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		MemorialsAttachment at = null; 
		String sql = prop.getProperty("selectMemorialsAttachment");
		
		try {
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setInt(1, memorialsNo+1);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				at = new MemorialsAttachment(rset.getInt("FILE_NO")
											,rset.getString("ORIGIN_NAME")
											,rset.getString("CHANGE_NAME")
											,rset.getString("FILE_PATH"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return at;
	}
	

}
