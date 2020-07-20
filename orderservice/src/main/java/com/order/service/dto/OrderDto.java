package com.order.service.dto;

import java.io.Serializable;
import java.util.Date;

public class OrderDto implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	
	private int orderId;
	private String customerName;
	private Date orderDate;
	private String shippingAddress;
	private double total;
	private int orderItemId;
	
	
	
	public OrderDto() {
		
	}
	
	



	public OrderDto(int orderId, String customerName, Date orderDate, String shippingAddress, double total) {
		super();
		this.orderId = orderId;
		this.customerName = customerName;
		this.orderDate = orderDate;
		this.shippingAddress = shippingAddress;
		this.total = total;
	}





	public int getOrderItemId() {
		return orderItemId;
	}




	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}




	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}


	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}
	
	

		
	
	
	
	

}
