package com.yedam.app.deal;

import java.sql.Date;

public class DealInfo {
	private Date dealDate;
	private int productId;
	private String productName;
	private int productAmount;
		
	public Date getDealDate() {
		return dealDate;
	}
	public void setDealDate(Date dealDate) {
		this.dealDate = dealDate;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductAmount() {
		return productAmount;
	}
	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}
	@Override
	public String toString() {
		String result = "거래일자 : " + dealDate
				+ ", 제품번호 : " + productId;
		
		if(productName != null) {
			result += ", 제품이름 : " + productName;
		}
		
		result +=", 수량 : " + productAmount; 
		
		return result;
	}
	
}
