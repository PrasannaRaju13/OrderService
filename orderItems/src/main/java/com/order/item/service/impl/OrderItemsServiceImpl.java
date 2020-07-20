package com.order.item.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order.item.constants.ExceptionConstants;
import com.order.item.entity.OrderItem;
import com.order.item.exceptions.BusinessException;
import com.order.item.exceptions.OrderNotFoundException;
import com.order.item.model.OrderItemsDto;
import com.order.item.repository.OrderItemsRepository;
import com.order.item.service.OrderItemsService;

@Service
public class OrderItemsServiceImpl implements OrderItemsService {

	@Autowired
	private OrderItemsRepository orderItemsRepository;

	@Override
	public List<OrderItem> getOrderedItems() {
		List<OrderItem> orderItemsList = orderItemsRepository.findAll();
		if (orderItemsList.isEmpty()) {
			throw new OrderNotFoundException("Order Not Found");
		}
		return orderItemsList;
	}

	@Override
	@Transactional
	public String addOrderItem(OrderItemsDto orderItemsDto) {
		OrderItem orderItems = new OrderItem();
		if (Objects.nonNull(orderItemsDto)) {			
			orderItems.setProductCode(orderItemsDto.getProductCode());
			orderItems.setProductName(orderItemsDto.getProductName());
			orderItems.setQuantity(orderItemsDto.getQuantity());
			orderItems = orderItemsRepository.save(orderItems);
		} else {
			throw new BusinessException(ExceptionConstants.ORDER_ITEM_SAVE, ExceptionConstants.ORDER_ITEM_MANAGMENT, "Failed to save order Items");
		}
		return orderItems.getProductCode();
	}

}
