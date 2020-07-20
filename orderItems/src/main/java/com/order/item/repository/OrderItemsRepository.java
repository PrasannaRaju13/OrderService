package com.order.item.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.order.item.entity.OrderItem;
import com.order.item.model.OrderItemsDto;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItem, Integer>{
	
	@Query(value="select new com.order.item.model.OrderItemsDto(orderItem.orderItemId,orderItem.productCode,orderItem.productName,orderItem.quantity) from OrderItem orderItem ")
	 List<OrderItemsDto> getOrderedItems();
}
