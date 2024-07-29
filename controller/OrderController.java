package com.flowerShop.ccsdProject.controller;

import com.flowerShop.ccsdProject.model.Cart;
import com.flowerShop.ccsdProject.model.CartItem;
import com.flowerShop.ccsdProject.model.OrderTable;
import com.flowerShop.ccsdProject.model.OrderItem;
import com.flowerShop.ccsdProject.service.CartService;
import com.flowerShop.ccsdProject.service.CartItemService;
import com.flowerShop.ccsdProject.service.OrderTableService;
import com.flowerShop.ccsdProject.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/checkout")
public class OrderController {

    @Autowired
    private CartService cartService;

    @Autowired
    private CartItemService cartItemService;

    @Autowired
    private OrderTableService orderTableService;

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public String checkout(@RequestParam Long id, Model model) {
        Cart cart = cartService.findById(id);
        if (cart != null) {
            OrderTable order = new OrderTable();
            order.setTotalAmount(cart.getItems().stream()
                    .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                    .sum());
            order.setOrderDate(java.time.LocalDateTime.now().toString());
            order.setStatus("pending");
            order = orderTableService.save(order);

            for (CartItem cartItem : cart.getItems()) {
                OrderItem orderItem = new OrderItem();
                orderItem.setProduct(cartItem.getProduct());
                orderItem.setQuantity(cartItem.getQuantity());
                orderItem.setPrice(cartItem.getProduct().getPrice());
                orderItem.setOrder(order);
                orderItemService.save(orderItem);
            }

            cart.getItems().clear();
            cartService.save(cart);

            model.addAttribute("order", order);
            return "orderConfirmation";
        }
        return "redirect:/cart/view?id=" + id;
    }
}
