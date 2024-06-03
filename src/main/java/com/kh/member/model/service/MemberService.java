package com.kh.member.model.service;

import java.sql.Connection;
import java.util.Date;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.vo.Member;

public class MemberService {
	
	
	
public Member loginMember(String userId, String userPwd) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new MemberDao().loginMember(conn,userId,userPwd);

		JDBCTemplate.close(conn);
		
		return m;
	}

	public int insertMember(Member m) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().insertMember(conn,m);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
			
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
		
		
		
	}

	// 아이디 중복 체크 메소드
	public Boolean checkId(String inputId) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Boolean flag = new MemberDao().checkId(conn,inputId);
		
		JDBCTemplate.close(conn);
		
		return flag;
		
	}
	
	// 닉네임 중복 체크 메소드
	public Boolean CheckNickName(String inputNickName) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		Boolean flag = new MemberDao().checkNickName(conn,inputNickName);
		
		JDBCTemplate.close(conn);
		
		return flag;
	}
	
	
	

	public Member findPwd(String ffId, String ffEmail) {
		// TODO Auto-generated method stub
		
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new MemberDao().findPwd(conn,ffId,ffEmail);
//		System.out.println(m);
		JDBCTemplate.close(conn);
		
		return m;
		
	}

	public Member findId(String ffPn) {
		// TODO Auto-generated method stub
		Connection conn = JDBCTemplate.getConnection();
		
		Member m = new MemberDao().findId(conn,ffPn);
		JDBCTemplate.close(conn);
		
		return m;
	}

	public int updatePwd(String userId,String userPwd,String updatePwd) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new MemberDao().updatePwd(conn,userId,userPwd,updatePwd);
		
		
		if(result>0) {
			JDBCTemplate.commit(conn); 
		}else {
			JDBCTemplate.rollback(conn);
		}
	
		
		JDBCTemplate.close(conn); 
		
		return result;
	}
	
	
	public int deleteMember(String userId) {
		Connection conn = JDBCTemplate.getConnection();
		
		
		int result = new MemberDao().deleteMember(conn,userId);
		
		if(result>0) {
			JDBCTemplate.commit(conn);
		}else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public Member updateMember(Member m) {
		
		Connection conn = JDBCTemplate.getConnection();
		int result = new MemberDao().updateMember(conn, m);
		
		Member updateMember = null;
		if (result > 0) {
			JDBCTemplate.commit(conn);
			updateMember = new MemberDao().selectMember(conn, m.getUserId());
		} else {
			JDBCTemplate.rollback(conn);
		}

		JDBCTemplate.close(conn);

		return updateMember;

	}

	
}
