package com.example.ccsd.Controller;

import com.example.ccsd.Model.CartItem;
import com.example.ccsd.Model.Product;
import com.example.ccsd.Model.User;
import com.example.ccsd.Service.CartService;
import com.example.ccsd.Service.ProductService;
import com.example.ccsd.Service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService; // Service to manage cart operations

    @Autowired
    private ProductService productService; // Service to manage products

    @Autowired
    private UserService userService; // Service to manage users

    // Display the current user's cart
    @GetMapping("/view")
    public String viewCart(Model model, Authentication authentication) {
        // Get current user
        User user = getCurrentUser(authentication);

        if (user == null) {
            // Redirect to login if no user is authenticated
            return "redirect:/login?sessionExpired";
        }

        // Fetch cart items for the current user and add them to the model
        List<CartItem> cartItems = cartService.getCartItems(user);
        model.addAttribute("cartItems", cartItems);

        return "cart"; // Return the view name for the cart page
    }

    // Add a product to the user's cart
    @RequestMapping("/add/{id}")
    public String addToCart(@PathVariable("id") Long id, Authentication authentication) {
        // Retrieve the currently authenticated user
        User user = getCurrentUser(authentication);

        // Find the product by ID or throw an exception if the ID is invalid
        Product product = productService.findProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));

        // Add the product to the cart
        cartService.addToCart(product, user);
        return "redirect:/cart/view"; // Redirect to the cart view page
    }

    // Update the quantity of a product in the cart
    @PostMapping("/update/{id}")
    public String updateQuantity(@PathVariable("id") Long id, @RequestParam("action") String action) {
        // Update the quantity of the product based on the action (e.g., increase/decrease)
        cartService.updateQuantity(id, action);
        return "redirect:/cart/view";
    }

    // Remove a product from the cart
    @PostMapping("/remove/{id}")
    public String removeFromCart(@PathVariable("id") Long id) {
        cartService.removeFromCart(id);
        return "redirect:/cart/view";
    }

    // Helper method to retrieve the current user from the authentication object
    private User getCurrentUser(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }

        // Get user details and find the user from the database
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return userService.findByUsername(userDetails.getUsername());
    }
}