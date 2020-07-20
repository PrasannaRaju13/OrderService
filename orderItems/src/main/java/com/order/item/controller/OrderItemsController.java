package com.order.item.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.item.constants.ExceptionConstants;
import com.order.item.entity.OrderItem;
import com.order.item.exceptions.BusinessException;
import com.order.item.model.OrderItemsDto;
import com.order.item.service.OrderItemsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="/orderItem",description="orderItems creation and retrieval")
@RestController
@RequestMapping(path="/orderItem")
@CrossOrigin(maxAge = 3600, origins = "*")
public class OrderItemsController {
	
	@Autowired
	private OrderItemsService orderItemsService;
	
	
	@ApiOperation(value = "Returns the results of order items")
	@GetMapping("")
	public List<OrderItem> getOrderedItems() {
		List<OrderItem> orderItemsList = orderItemsService.getOrderedItems();
		return orderItemsList;
	}
	
	@ApiOperation(value = "Save Order Items")
	@PostMapping("")
	public ResponseEntity<String> addOrderItem(@Valid @RequestBody OrderItemsDto orderItemsDto) {
		String productCode=orderItemsService.addOrderItem(orderItemsDto);
		if(productCode.isEmpty()) {		
			throw new BusinessException(ExceptionConstants.ORDER_ITEM_SAVE, ExceptionConstants.ORDER_ITEM_MANAGMENT, "Failed to save order Item"); 
		} else {
			return new ResponseEntity<String>("Order proccessed successfully for " + productCode, HttpStatus.OK);
		}
	}
}




