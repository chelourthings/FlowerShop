package com.flowerShop.ccsdProject.service;

import com.flowerShop.ccsdProject.model.CartItem;
import com.flowerShop.ccsdProject.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public CartItem save(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }

    public void deleteById(Long id) {
        cartItemRepository.deleteById(id);
    }

    public CartItem findById(Long id) {
        return cartItemRepository.findById(id).orElse(null);
    }
}
