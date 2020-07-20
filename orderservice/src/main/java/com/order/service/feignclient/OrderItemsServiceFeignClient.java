package com.order.service.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import com.order.service.config.FeignClientConfig;
import com.order.service.dto.OrderItemsDto;

import feign.RequestLine;

@FeignClient(name="orderItem-management",url="localhost:8083", configuration = FeignClientConfig.class)
public interface OrderItemsServiceFeignClient {
	
	//@GetMapping("/orderItem")
	@RequestLine("GET /orderItem")
	public List<OrderItemsDto> getOrderedItems();
	
}