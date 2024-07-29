package com.flowerShop.ccsdProject.repository;

import com.flowerShop.ccsdProject.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
