package com.order.item.service;

import java.util.List;

import com.order.item.entity.OrderItem;
import com.order.item.model.OrderItemsDto;

public interface OrderItemsService {
	
	List<OrderItem> getOrderedItems();

	String addOrderItem(OrderItemsDto orderItemsDto);
}
