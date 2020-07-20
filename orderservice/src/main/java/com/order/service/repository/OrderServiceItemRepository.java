package com.order.service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.order.service.entity.OrderServiceItem;

@Repository
public interface OrderServiceItemRepository extends JpaRepository<OrderServiceItem, Integer> {
	
}
