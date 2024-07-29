package com.flowerShop.ccsdProject.controller;

import com.flowerShop.ccsdProject.model.Cart;
import com.flowerShop.ccsdProject.model.CartItem;
import com.flowerShop.ccsdProject.model.Product;
import com.flowerShop.ccsdProject.service.CartService;
import com.flowerShop.ccsdProject.service.CartItemService;
import com.flowerShop.ccsdProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartItemService cartItemService;

    @PostMapping("/add")
    public String addToCart(@RequestParam Long productId, @RequestParam int quantity, @RequestParam Long cartId) {
        Cart cart = cartService.findById(cartId);
        if (cart != null) {
            Product product = productService.findById(productId);
            if (product != null) {
                // Check if cart already has the product
                CartItem existingItem = cart.getItems().stream()
                        .filter(item -> item.getProduct().getId().equals(productId))
                        .findFirst().orElse(null);

                if (existingItem != null) {
                    // Update quantity if product already in cart
                    existingItem.setQuantity(existingItem.getQuantity() + quantity);
                    cartItemService.save(existingItem);
                } else {
                    // Create a new CartItem
                    CartItem cartItem = new CartItem();
                    cartItem.setProduct(product);
                    cartItem.setQuantity(quantity);
                    cartItem.setCart(cart);
                    cartItemService.save(cartItem);
                }
                cartService.save(cart);
            }
        }
        return "redirect:/cart/view?id=" + cartId;
    }

    @GetMapping("/view")
    public String viewCart(@RequestParam Long id, Model model) {
        Cart cart = cartService.findById(id);
        model.addAttribute("cart", cart);
        return "cartView";
    }

    @PostMapping("/update")
    public String updateCartItem(@RequestParam Long cartItemId, @RequestParam int quantity, @RequestParam Long cartId) {
        CartItem cartItem = cartItemService.findById(cartItemId);
        if (cartItem != null) {
            cartItem.setQuantity(quantity);
            cartItemService.save(cartItem);
        }
        return "redirect:/cart/view?id=" + cartId;
    }

    @PostMapping("/remove")
    public String removeCartItem(@RequestParam Long cartItemId, @RequestParam Long cartId) {
        cartItemService.deleteById(cartItemId);
        return "redirect:/cart/view?id=" + cartId;
    }
}
