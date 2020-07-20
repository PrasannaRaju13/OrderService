package com.order.service.service.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.order.service.constants.ExceptionConstants;
import com.order.service.dto.OrderDto;
import com.order.service.dto.OrderItemsDto;
import com.order.service.entity.Order;
import com.order.service.entity.OrderServiceItem;
import com.order.service.exceptions.BaseException;
import com.order.service.feignclient.OrderItemsServiceFeignClient;
import com.order.service.repository.OrderRepository;
import com.order.service.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired(required=true)
	private OrderItemsServiceFeignClient orderItemsServiceFeignClient;
	
	@Override
	@Transactional
	public ResponseEntity<String> saveOrder(OrderDto orderDto) {
		
		List<OrderItemsDto> itemsDtos = orderItemsServiceFeignClient.getOrderedItems();
		
		List<OrderItemsDto> orderItemsDto = new ArrayList<OrderItemsDto>();
		
		/*
		 * for(OrderItemsDto oid:orderedItems.getBody()) orderItemsDto.add(oid);
		 */
		
		String response="";
		Order order=new Order();
		if(Objects.nonNull(orderDto))
		{
			order.setCustomerName(orderDto.getCustomerName());
			order.setOrderDate(orderDto.getOrderDate());
			order.setShippingAddress(orderDto.getShippingAddress());
			order.setTotal(orderDto.getTotal());
			order=orderRepository.save(order);
			
		    OrderServiceItem orderServiceItem=new OrderServiceItem();
		    orderServiceItem.setOrderItemId(itemsDtos.iterator().next().getOrderItemId());
		    orderServiceItem.setOrderId(order.getOrderId());
		    response = "Order processed successfully for "+order.getOrderId().toString();
		}
		else {
			throw new BaseException(ExceptionConstants.ORDER_SAVE,ExceptionConstants.ORDER_SERVICE_MANAGMENT); 
		}
		
		if(response.isEmpty()) {
			return new ResponseEntity<String>("Order not processed", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<String>("Order processed successfully for "+ order.getOrderId().toString(), HttpStatus.CREATED);
		}
	}

	@Override
	public List<OrderDto> getOrders() {
		
		List<OrderDto> orders=new ArrayList<OrderDto>();
		
		orders=orderRepository.Orders();
		
		if(orders.isEmpty()) {
			throw new BaseException(ExceptionConstants.ORDERS_NOT_FOUND,ExceptionConstants.ORDER_SERVICE_MANAGMENT); 
		} else {
			return orders;
		}
	}		    

	
	
	
	
	
	
	

}
