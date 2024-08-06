package com.example.ccsd.Repository;

import com.example.ccsd.Model.CartItem;
import com.example.ccsd.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUserId(Long userId);
}
