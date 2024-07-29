package com.flowerShop.ccsdProject.service;

import com.flowerShop.ccsdProject.model.Cart;
import com.flowerShop.ccsdProject.model.CartItem;
import com.flowerShop.ccsdProject.repository.CartRepository;
import com.flowerShop.ccsdProject.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    public Cart findById(Long id) {
        return cartRepository.findById(id).orElse(null);
    }

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public CartItem saveCartItem(CartItem cartItem) {
        return cartItemRepository.save(cartItem);
    }
}
