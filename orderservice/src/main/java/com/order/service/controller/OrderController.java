package com.order.service.controller;

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

import com.order.service.dto.OrderDto;
import com.order.service.service.OrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="/orders",description="orders creation and retrieval")
@RestController
@RequestMapping(path="/orders")
@CrossOrigin(maxAge = 3600, origins = "*")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	
	@ApiOperation(value = "Save Order Items")
	@PostMapping("")
	public ResponseEntity<String> saveOrder(@RequestBody OrderDto orderDto) {
		ResponseEntity<String> response = orderService.saveOrder(orderDto);
		return response;
	}
	
	@ApiOperation(value = "Returns the results of order")
	@GetMapping("")
	public ResponseEntity<List<OrderDto>> getOrders() {
		List<OrderDto> orderList = orderService.getOrders();
		return new ResponseEntity<List<OrderDto>>(orderList, HttpStatus.OK);
	}

}
