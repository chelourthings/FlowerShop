package com.flowerShop.ccsdProject.repository;

import com.flowerShop.ccsdProject.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
