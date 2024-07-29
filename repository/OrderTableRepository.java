package com.flowerShop.ccsdProject.repository;

import com.flowerShop.ccsdProject.model.OrderTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderTableRepository extends JpaRepository<OrderTable, Long> {
}
