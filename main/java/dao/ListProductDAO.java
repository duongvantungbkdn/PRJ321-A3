package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import context.DBContext;
import model.Product;
import model.ResultSQLProducts;

//ListProductDAO: lớp chứa các phương thức làm việc với bảng Product
public class ListProductDAO {

	//return the list of Product by name
	public ResultSQLProducts getProducts(int pageNumber, int pageSize) throws Exception {
		// create a list contain result
		List<Product> list = new ArrayList<>();
		
		// create connection to ShoppingDB
		DBContext db = new DBContext();
		Connection con = db.getConnection();
		
		int maxPage = 0;
		
		if(con == null) {
			System.out.println("connect to DB FAILED on getAllProducts of class ListProductDAO"); 
		} else {
			System.out.println("connect to DB SUCCESSED on getAllProducts of class ListProductDAO");
			String sqlTotalRows = "SELECT COUNT(*) AS total_rows FROM Products";
			String sql = "SELECT * FROM Products ORDER BY product_id OFFSET (?-1) * ? ROWS FETCH NEXT ? ROWS ONLY";
			
			con.setAutoCommit(false); // begin transaction
			
			// get total row
			Statement stmt1 = con.createStatement();
			ResultSet rs1 = stmt1.executeQuery(sqlTotalRows);
			int totalRows = 0;
			while(rs1.next()) {
				totalRows = rs1.getInt("total_rows");
			}	
			
			if(totalRows > 0) {
				// calculate maxPage
				if(totalRows % pageSize > 0) {maxPage = (totalRows/pageSize) + 1;
				} else {maxPage = (totalRows/pageSize);}
				
				// validate if pageNumber < 1 and pageNumber > maxPage
				if(pageNumber < 1) {pageNumber = 1;} 
				if(pageNumber > maxPage) {pageNumber = maxPage;}
				
				PreparedStatement stmt2 =con.prepareStatement(sql);
				stmt2.setInt(1, pageNumber);
				stmt2.setInt(2, pageSize);
				stmt2.setInt(3, pageSize);
				ResultSet rs2 = stmt2.executeQuery();
				
				//process result
				while(rs2.next()) {
					int id = rs2.getInt("product_id");
					String name = rs2.getString("product_name");
					String description = rs2.getString("product_des");
					String src = rs2.getString("product_img_source");
					String type = rs2.getString("product_type");
					String brand = rs2.getString("product_brand"); //product's category
					float price = rs2.getFloat("product_price");
					
					Product product = new Product(id,name,description,src,type,brand,1,price);
					list.add(product); 
				}
			}
			con.commit(); // end transaction
		}
		con.close(); // close connection
		
		ResultSQLProducts resultSQL = new ResultSQLProducts(list,maxPage,pageNumber);
		return resultSQL; // return result
	}
	
	//return the list of Product by name
	public ResultSQLProducts searchProduct(String search, String category, int pageNumber, int pageSize) throws Exception {
		// create a list contain result
		List<Product> list = new ArrayList<>();
		
		// create connection to ShoppingDB
		DBContext db = new DBContext();
		Connection con = db.getConnection();
		
		int maxPage = 0;
		
		if(con == null) {
			System.out.println("connect to DB FAILED on searchProduct of class ListProductDAO"); 
		} else {
			System.out.println("connect to DB SUCCESSED on searchProduct of class ListProductDAO");
			
			String sqlTotalRows = "SELECT COUNT(*) AS total_rows FROM Products" 
					+ " where product_brand like ? and (product_name like ? or product_des like ?)";
			String sql = "SELECT * FROM Products where product_brand like ? and (product_name like ? or product_des like ?)"
					+ " ORDER BY product_id OFFSET (?-1) * ? ROWS FETCH NEXT ? ROWS ONLY";
			
			con.setAutoCommit(false); // begin transaction
			
			// get total row
			PreparedStatement stmt1 = con.prepareStatement(sqlTotalRows);
			stmt1.setString(1, "%" + category + "%");
			stmt1.setString(2, "%" + search + "%");
			stmt1.setString(3, "%" + search + "%");
			ResultSet rs1 = stmt1.executeQuery();
			int totalRows = 0;
			while(rs1.next()) {
				totalRows = rs1.getInt("total_rows");
			}	
			
			if(totalRows > 0) {
				// if found product
				// calculate maxPage
				if(totalRows % pageSize > 0) {maxPage = (totalRows/pageSize) + 1;
				} else {maxPage = (totalRows/pageSize);}
				
				// validate if pageNumber < 1 and pageNumber > maxPage
				if(pageNumber < 1) {pageNumber = 1;} 
				if(pageNumber > maxPage) {pageNumber = maxPage;}

				PreparedStatement stmt2 = con.prepareStatement(sql);
				stmt2.setString(1, "%" + category + "%");
				stmt2.setString(2, "%" + search + "%");
				stmt2.setString(3, "%" + search + "%");
				stmt2.setInt(4, pageNumber);
				stmt2.setInt(5, pageSize);
				stmt2.setInt(6, pageSize);
				ResultSet rs2 = stmt2.executeQuery();
				
				//process result
				while(rs2.next()) {
					int id = rs2.getInt("product_id");
					String name = rs2.getString("product_name");
					String description = rs2.getString("product_des");
					String src = rs2.getString("product_img_source");
					String type = rs2.getString("product_type");
					String brand = rs2.getString("product_brand"); //product's category
					float price = rs2.getFloat("product_price");
					
					Product product = new Product(id,name,description,src,type,brand,1,price);
					list.add(product); 
				}
			} 
			con.commit(); // end transaction
		}
		con.close(); // close connection
		
		ResultSQLProducts resultSQL = new ResultSQLProducts(list,maxPage,pageNumber);
		return resultSQL; // return result
	}
	
	
	//get product by id
	public Product getProductById(int id) throws Exception {		
		// create connection to ShoppingDB
		DBContext db = new DBContext();
		Connection con = db.getConnection();
		
		if(con == null) {
			System.out.println("connect to DB FAILED on getProductById of class ListProductDAO"); 
		} else {
			System.out.println("connect to DB SUCCESSED on getProductById of class ListProductDAO");
			String sql = "select * from Products where product_id = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1,id);
			ResultSet rs = stmt.executeQuery();
			
			//process result
			while(rs.next()) {
				int id_rs = rs.getInt("product_id");
				String name = rs.getString("product_name");
				String description = rs.getString("product_des");
				String src = rs.getString("product_img_source");
				String type = rs.getString("product_type");
				String brand = rs.getString("product_brand"); //product's category
				float price = rs.getFloat("product_price");
				
				Product product = new Product(id_rs,name,description,src,type,brand,1,price);
				return product;
			}
		}
		
		con.close(); // close connection
		
		return null; // return null if not found
	}
}
