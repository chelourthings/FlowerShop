package com.example.ccsd.Controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.ccsd.Model.CartItem;
import com.example.ccsd.Model.User;
import com.example.ccsd.security.CustomUserDetails;
import com.example.ccsd.Service.CartService;
import com.example.ccsd.Service.PurchaseService;
import com.example.ccsd.Service.UserService;

import java.util.List;

@Controller
public class CheckoutController {

    private final CartService cartService;
    private final PurchaseService purchaseService;
    private final UserService userService;

    // Constructor injection
    public CheckoutController(CartService cartService, PurchaseService purchaseService, UserService userService) {
        this.cartService = cartService;
        this.purchaseService = purchaseService;
        this.userService = userService;
    }

    // Method to handle GET requests for the checkout page
    @GetMapping("/checkout")
    public String checkoutPage(Model model, @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        // Retrieve the ID of the currently authenticated user
        Long userId = customUserDetails.getId();
        // Retrieve user information from the UserService
        User user = userService.findById(userId);
        // Retrieve the list of cart items for the user
        List<CartItem> cartItems = cartService.getCartItems(user);
        // Calculate the total price of the items in the cart
        double totalPrice = cartService.calculateTotalPrice(user);

        // Add user and cart details to the model to be used in the view
        model.addAttribute("user", user);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("totalPrice", totalPrice);

        // Add user address information to the model
        model.addAttribute("addressLine1", user.getAddressLine1());
        model.addAttribute("addressLine2", user.getAddressLine2());
        model.addAttribute("city", user.getCity());
        model.addAttribute("state", user.getState());
        model.addAttribute("postalCode", user.getPostalCode());

        return "checkout";
    }

    // Method to handle POST requests for placing an order
    @PostMapping("/placeOrder")
    public String placeOrder(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                             @RequestParam("paymentMethod") String paymentMethod,
                             @RequestParam("shippingAddress") String shippingAddress) {
        // Retrieve the ID of the currently authenticated user
        Long userId = customUserDetails.getId();
        // Retrieve user information from the UserService
        User user = userService.findById(userId);
        // Retrieve the list of cart items for the user
        List<CartItem> cartItems = cartService.getCartItems(user);
        // Calculate the total price of the items in the cart
        double totalPrice = cartService.calculateTotalPrice(user);

        // Create a purchase record with the given details
        purchaseService.createPurchase(user, cartItems, paymentMethod, shippingAddress);

        // Clear the user's cart after the order is placed
        cartService.clearCart(user);

        return "orderConfirmation";
    }
}

