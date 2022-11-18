package com.yedam.app.products;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yedam.app.common.DAO;

public class ProductsDAO extends DAO{
	//싱글톤
	private static ProductsDAO productsDAO = null;
	private ProductsDAO() {}
	public static ProductsDAO getInstance() {
		if(productsDAO == null) {
			productsDAO = new ProductsDAO();
		}
		return productsDAO;
	}
	//등록
	public void insert(Product product) {
		try {
			connect();
			String sql = "INSERT INTO products (product_name, product_price) "
						+"VALUES ( ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getProductName());
			pstmt.setInt(2, product.getProductPrice());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("정상적으로 등록되었습니다.");
			}else {
				System.out.println("정상적으로 등록되지 않았습니다.");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	//수정 - 재고
	public void updateStock(Product product) {
		try {
			connect();
			String sql = "UPDATE products "
						+"SET product_stock = "+ product.getProductStock()
						+"WHERE product_id = " + product.getProductId();
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			if(result > 0) {
				System.out.println("정상적으로 수정되었습니다.");
			}else {
				System.out.println("정상적으로 수정되지 않았습니다.");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	
	//수정 - 이름, 가격
	public void updateInfo(Product product) {
		try {
			connect();
			String sql = "UPDATE products "
						+"SET product_name = ?,"
						+" product_price = ? "
						+"WHERE product_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, product.getProductName());
			pstmt.setInt(2, product.getProductPrice());
			pstmt.setInt(3, product.getProductId());
			
			int result = pstmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("정상적으로 수정되었습니다.");
			}else {
				System.out.println("정상적으로 수정되지 않았습니다.");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	
	
	//삭제
	public void delete(int productId) {
		try {
			connect();
			String sql = "DELETE FROM products "
						+"WHERE product_id = " + productId;
			stmt = conn.createStatement();
			int result = stmt.executeUpdate(sql);
			if(result > 0) {
				System.out.println("정상적으로 삭제되었습니다.");
			}else {
				System.out.println("정상적으로 삭제되지 않았습니다.");
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
	}
	//단건조회 - 재고이름
	public Product selectOne(String productName) {
		Product product = null;
		try {
			connect();
			String sql = "SELECT * FROM products "
						+"WHERE product_name = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, productName);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductPrice(rs.getInt("product_price"));
				product.setProductStock(rs.getInt("product_stock"));			
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}		
		return product;
	}
	//전체조회
	public List<Product> selectAll(){
		List<Product> list = new ArrayList<>();
		try {
			connect();
			String sql = "SELECT * FROM products "
						+"ORDER BY product_id";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Product product = new Product();
				product.setProductId(rs.getInt("product_id"));
				product.setProductName(rs.getString("product_name"));
				product.setProductPrice(rs.getInt("product_price"));
				product.setProductStock(rs.getInt("product_stock"));
				list.add(product);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		return list;
	}

}
