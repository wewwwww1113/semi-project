package com.kh.memorials.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.memorials.model.dao.MemorialsDao;
import com.kh.memorials.model.vo.Memorials;
import com.kh.memorials.model.vo.MemorialsAttachment;

public class MemorialsService {

	public int insertMemorials(Memorials m, MemorialsAttachment at) {
	    	
		Connection conn = JDBCTemplate.getConnection();
	    
	    int memorialsNo = new MemorialsDao().selectMemorialsNo(conn);
	       
	    int result = new MemorialsDao().insertMemorials(conn, m);
	    
	    int result2 = new MemorialsDao().insertMemorialsAttachment(conn, at);
	            
        if (result * result2 > 0) {
            JDBCTemplate.commit(conn);
        } else {
            JDBCTemplate.rollback(conn);
        }
        
	    JDBCTemplate.close(conn);
	    
	    return result*result2; 
	}
	
	

	public ArrayList<Memorials> MemorialsList(String UserId) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Memorials> list =new MemorialsDao().MemorialsList(conn,UserId);
		
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
	public ArrayList<MemorialsAttachment> MemorialsListAttachment(ArrayList<Memorials> list) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<MemorialsAttachment> atlist = new ArrayList<>();

		for (Memorials memorial : list) {
		    int meNo = (memorial.getMemorialsNo())+1;
		    ArrayList<MemorialsAttachment> tempList = new MemorialsDao().MemorialsListAttachment(conn, meNo);
		    atlist.addAll(tempList);
		}
		JDBCTemplate.close(conn);
		
		return atlist;
	}

	public int deleteMemorials(int mNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemorialsDao().deleteMemorials(conn,mNo);
		
		if(result >0) {
		result = new MemorialsDao().deleteMemorialsAttachment(conn, mNo);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int UpdateMemorials(Memorials m, MemorialsAttachment at) {
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemorialsDao().UpdateMemorials(conn,m);
		
		int result2 = 1;
		
		if(at!=null) {
			
			if(at.getFileNo()!=0) { 
				result2 = new MemorialsDao().updateMemorialsAttachment(conn,at);
			}else { 
				result2 = new MemorialsDao().insertMemorialsAttachment(conn, at);
			}
		}
		if(result*result2>0) { 
			JDBCTemplate.commit(conn);
		}else { 
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		
		return result*result2;
		
	
	}


	public Memorials selectMemorials(int memorialsNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Memorials me = new MemorialsDao().selectMemorials(conn,memorialsNo);
		
		JDBCTemplate.close(conn);
		
		return me;
	}


	public MemorialsAttachment selectAttachment(int memorialsNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		MemorialsAttachment at = new MemorialsDao().selectMemorialsAttachment(conn,memorialsNo);
		
		JDBCTemplate.close(conn);
		
		return at;
	}
}


