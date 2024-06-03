package com.kh.market.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.board.model.vo.Category;
import com.kh.common.JDBCTemplate;
import com.kh.market.model.vo.Component;
import com.kh.market.model.vo.Item;
import com.kh.market.model.vo.ItemAttachment;
import com.kh.market.model.vo.Order;
import com.kh.market.model.vo.Review;

public class MarketDao {

	private Properties prop = new Properties();
	
	public MarketDao() {
		String filePath=MarketDao.class.getResource("/resources/sql/market-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Item> selectItemList(Connection conn, String sort) {
		ResultSet rset=null;
		Statement stmt=null;
		ArrayList<Item> list= new ArrayList<>();
		String sql=null;
		if(sort.equals("topSal")) {
		sql=prop.getProperty("selectItemListOrderBySal") ;
		}
		if(sort.equals("latest")) {
			sql=prop.getProperty("selectItemListOrderBydate") ;
			}
		if(sort.equals("topSco")) {
			sql=prop.getProperty("selectItemListOrderBysco") ;
			}
		if(sort.equals("N")) {
			sql=prop.getProperty("selectItemListN");
		}
		try {
			stmt=conn.createStatement();
			rset=stmt.executeQuery(sql);
			while(rset.next()) {
				list.add(new Item(rset.getInt("ITEM_CODE"),
						rset.getString("CATEGORY_NAME"),
						rset.getInt("PRICE"),
						rset.getInt("DISCOUNT"),
						rset.getString("ITEM_NAME"),
						rset.getInt("SALES_VOL"),
						rset.getDouble("SCORE"),
						rset.getString("THUMBNAIL")
						));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(stmt);
		}
		
		return list;
	}
	
	public ArrayList<Item> selectItemList(Connection conn, String sort, int categoryNo) {
		ResultSet rset=null;
		PreparedStatement pstmt=null;
		ArrayList<Item> list= new ArrayList<>();
		String sql=null ;
		if(sort.equals("topSal")) {
			sql=prop.getProperty("selectItemListOrderBySalSort") ;
			}
			if(sort.equals("latest")) {
				sql=prop.getProperty("selectItemListOrderByDateSoty") ;
				}
			if(sort.equals("topSco")) {
				sql=prop.getProperty("selectItemListOrderByscoSort") ;
				}
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, categoryNo);
			rset=pstmt.executeQuery();
			while(rset.next()) {
				list.add(new Item(rset.getInt("ITEM_CODE"),
						rset.getString("CATEGORY_NAME"),
						rset.getInt("PRICE"),
						rset.getInt("DISCOUNT"),
						rset.getString("ITEM_NAME"),
						rset.getInt("SALES_VOL"),
						rset.getDouble("SCORE"),
						rset.getString("THUMBNAIL")
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

	public Item selectItem(int itemNo, Connection conn) {
		ResultSet rset= null;
		PreparedStatement pstmt=null;
		Item i=null;
		String sql=prop.getProperty("selectItem");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, itemNo);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				i=(new Item(
						rset.getString("CATEGORY_NAME"),
						rset.getInt("CATEGORY_NO"),
						rset.getInt("PRICE"),
						rset.getInt("discount"),
						rset.getString("ITEM_NAME"),
						rset.getInt("SALES_VOL"),
						rset.getString("STORAGE_METHOD"),
						rset.getInt("ITEM_CODE"),
						rset.getString("STATUS")));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return i;
	}

	
		


	public ArrayList<Category> selectCategory(Connection conn) {
		ResultSet rset=null;
		Statement stmt=null;
		ArrayList<Category> cList=new ArrayList<>();
		String sql = prop.getProperty("selectCategory");
		
		try {
			stmt=conn.createStatement();
			rset=stmt.executeQuery(sql);
			while(rset.next()) {
				cList.add(
						new Category(
								rset.getInt("CATEGORY_NO")
								,rset.getString("CATEGORY_NAME")));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cList;
	}

	public int insertItem(Item i, Connection conn) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql = prop.getProperty("insertItem");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, i.getCategory());
			pstmt.setString(2, i.getItemName());
			pstmt.setInt(3, i.getPrice());
			pstmt.setInt(4, i.getDiscount());
			pstmt.setString(5, i.getStorageMethod());
			
			result= pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertItemAttachment(Connection conn, ArrayList<ItemAttachment> itList) {
		int result=1;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("insertItemAttachment");
		try {
		
			for(ItemAttachment it : itList) {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, it.getOriginName());
				pstmt.setString(2, it.getChangeName());
				pstmt.setString(3, it.getFilePath());
				pstmt.setInt(4, it.getFileLev());
				
				
				result*=pstmt.executeUpdate();
				
			
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<ItemAttachment> selectAttachmentList(Connection conn, int itemNo) {
		ResultSet rset=null; 
		PreparedStatement pstmt=null;
		ArrayList<ItemAttachment> itList = new ArrayList<>();
		String sql = prop.getProperty("selectAttachmentList");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, itemNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				itList.add(new ItemAttachment(rset.getInt("FILE_CODE"),
											  rset.getString("ORIGIN_NAME"),
											  rset.getString("CHANGE_NAME"),
											  rset.getString("FILE_PATH")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		
		return itList;
	}

	public int insertComponent(int category, Connection conn, Component c) {
		int result = 0;
		PreparedStatement pstmt = null; 
		String sql = prop.getProperty("insertComponent");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setDouble(1, c.getCalorie());
			pstmt.setDouble(2, c.getProtin());
			pstmt.setDouble(3, c.getSalt());
			pstmt.setDouble(4, c.getCarbo());
			pstmt.setDouble(5, c.getFat());
			pstmt.setDouble(6, c.getTransFat());
			pstmt.setDouble(7, c.getSaturatedFat());
			pstmt.setDouble(8, c.getChol());
			pstmt.setDouble(9, c.getSugar());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public Component selectComponent(Connection conn, int itemNo) {
		ResultSet rset=null;
		PreparedStatement pstmt =null;
		Component c=null;
		String sql = prop.getProperty("selectComponent");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, itemNo);
			
			rset=pstmt.executeQuery();
			
			if(rset.next()) {
				c=new Component(
						rset.getInt("ITEM_CODE"),
						rset.getDouble("PROTIN"),
						rset.getDouble("CARBO"),
						rset.getDouble("CALORIE"),
						rset.getDouble("SALT"),
						rset.getDouble("FAT"),
						rset.getDouble("CHOL"),
						rset.getDouble("SATURATED_FAT"),
						rset.getDouble("SUGAR"),
						rset.getDouble("TRANS_FAT"));
						
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		return c;
	}

	public int deleteItem(Connection conn, int itno) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("deactivateItem");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, itno);
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertOrder(Connection conn, Order o) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql =prop.getProperty("insertOrder");
		
			try {
				pstmt=conn.prepareStatement(sql);
				pstmt.setString(1, o.getOrderNo());
				pstmt.setString(2, o.getName());
				pstmt.setDate(3, o.getShippingDate());
				pstmt.setString(4, o.getPostNum());
				pstmt.setString(5, o.getAddress());
				pstmt.setInt(6, o.getPrice());
				pstmt.setString(7, o.getCartList());
				pstmt.setInt(8, o.getUserNo());
				result=pstmt.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally {
				JDBCTemplate.close(conn);
			}
		
		return result;
	}

	public int updateItem(Item i, Connection conn, int itCode) {
		int result =0;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("updateItem");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, i.getCategory());
			pstmt.setString(2, i.getItemName());
			pstmt.setInt(3, i.getPrice());
			pstmt.setInt(4, i.getDiscount());
			pstmt.setString(5, i.getStorageMethod());
			pstmt.setInt(6, itCode);
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateItemAttachment(Connection conn, ArrayList<ItemAttachment> itList, int itCode) {
		int result=1;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("updateItemAttachment");
		try {
		
			for(ItemAttachment it : itList) {
				pstmt=conn.prepareStatement(sql);
				pstmt.setInt(1, itCode);
				pstmt.setString(2, it.getOriginName());
				pstmt.setString(3, it.getChangeName());
				pstmt.setString(4, it.getFilePath());
				pstmt.setInt(5, it.getFileLev());
				
				
				result*=pstmt.executeUpdate();
				
			
		}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	

	public int updateComponent(int category, Connection conn, Component c, int itCode) {
		int result =0;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("updateComponent");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setDouble(1, c.getCalorie());
			pstmt.setDouble(2, c.getProtin());
			pstmt.setDouble(3, c.getSalt());
			pstmt.setDouble(4, c.getCarbo());
			pstmt.setDouble(5, c.getFat());
			pstmt.setDouble(6, c.getTransFat());
			pstmt.setDouble(7, c.getSaturatedFat());
			pstmt.setDouble(8, c.getChol());
			pstmt.setDouble(9, c.getSugar());
			pstmt.setInt(10,itCode);
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteItemAttachment(Connection conn, int fileLev, int itCode) {
		// TODO Auto-generated method stub
		int result=0;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("deleteItemAttachment");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, fileLev);
			pstmt.setInt(2, itCode);
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Order> selectOrder(Connection conn, int userNo) {
		ResultSet rset=null;
		PreparedStatement pstmt=null;
		ArrayList<Order> ol=new ArrayList<>();
		String sql = prop.getProperty("selectOrder");
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, userNo);
			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				ol.add(new Order(
						rset.getString("ORDER_NUMBER"),
						rset.getString("CUSTOMER_NAME"),
						rset.getDate("SHIPPING_DATE"),
						rset.getString("POST_NUMBER"),
						rset.getString("ADDRESS"),
						rset.getString("CART_LIST"),
						rset.getInt("PRICE"),
						rset.getInt("USER_NO")));
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return ol;
	}

	public int updateSalVol(Item item, Connection conn) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("updateSalVol");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, item.getSalesVol());
			pstmt.setInt(2, item.getItemCode());
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int insertReview(Connection conn, Review re) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("insertReview");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, re.getRefItemNo());
			pstmt.setString(2, re.getContent());
			pstmt.setDouble(3, re.getScore());
			pstmt.setInt(4, re.getReviewWriter());
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public ArrayList<Review> selectReview(Connection conn, int itno) {
		ResultSet rset=null;
		PreparedStatement pstmt=null;
		ArrayList<Review> rList=new ArrayList<>();
		String sql = prop.getProperty("selectReview");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, itno);
			
			rset=pstmt.executeQuery();
			while(rset.next()) {
				rList.add(new Review(
						rset.getInt("REVIEW_NO"),
						rset.getInt("REF_ITEMNO"),
						rset.getString("CONTENT"),
						rset.getDouble("SCORE"),
						rset.getInt("REVIEW_WRITER"),
						rset.getString("NICKNAME"),
						rset.getDate("UP_DATE")));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return rList;
	}

	public double selectScore(Connection conn, int itemCode) {
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String sql=prop.getProperty("selectScore");
		
		double result=0;
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, itemCode);
			rset=pstmt.executeQuery();
			if(rset.next()) {
				result=rset.getDouble("result");
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


	public int updateScore(Connection conn, int itemCode, Double result2) {
		PreparedStatement pstmt=null;
		int result=0;
		String sql=prop.getProperty("updateScore");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setDouble(1, result2);
			pstmt.setInt(2,itemCode);
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int reinsertItem(Connection conn, int itno) {
		int result=0;
		PreparedStatement pstmt=null;
		String sql=prop.getProperty("reactivateItem");
		
		try {
			pstmt=conn.prepareStatement(sql);
			pstmt.setInt(1, itno);
			
			result=pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	

}
