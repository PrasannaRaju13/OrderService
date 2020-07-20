package com.order.service.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.order.service.dto.OrderDto;

public interface OrderService {
	
	ResponseEntity<String> saveOrder(OrderDto orderDto);
	List<OrderDto> getOrders();
}
