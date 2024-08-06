package com.example.ccsd.Service;

import com.example.ccsd.Model.CartItem;
import com.example.ccsd.Model.Product;
import com.example.ccsd.Model.User;
import com.example.ccsd.Repository.CartItemRepository;
import com.example.ccsd.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addToCart(Product product, User user) {
        Optional<CartItem> existingCartItem = cartItemRepository.findAll().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(product.getId()) && cartItem.getUser().equals(user))
                .findFirst();

        if (existingCartItem.isPresent()) {
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
            cartItem.setTotalPrice(cartItem.getQuantity() * product.getPrice());  // Update totalPrice
            cartItemRepository.save(cartItem);
        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(1); // default quantity for new items
            cartItem.setUser(user);  // Set the user
            cartItem.setTotalPrice(product.getPrice());  // Set initial totalPrice
            cartItemRepository.save(cartItem);
        }
    }

    public List<CartItem> getCartItems(User user) {
        return cartItemRepository.findAll().stream()
                .filter(cartItem -> cartItem.getUser().equals(user))
                .toList();
    }

    public void updateQuantity(Long id, String action) {
        CartItem cartItem = cartItemRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid cart item Id:" + id));
        Product product = productRepository.findById(cartItem.getProduct().getId()).orElse(null);
        int updateQuantity;

        if(action.equalsIgnoreCase("decrease")){
            updateQuantity = cartItem.getQuantity()-1;

            if(updateQuantity <= 0){
                cartItemRepository.delete(cartItem);
            }else{
                cartItem.setQuantity(updateQuantity);
                cartItem.setTotalPrice(updateQuantity * cartItem.getProduct().getPrice());
                cartItemRepository.save(cartItem);
            }
        }else{
            updateQuantity = cartItem.getQuantity()+1;
            cartItem.setQuantity(updateQuantity);
            cartItem.setTotalPrice(updateQuantity * cartItem.getProduct().getPrice());
            cartItemRepository.save(cartItem);
        }
    }

    public void removeFromCart(Long id) {
        cartItemRepository.deleteById(id);
    }

    public void clearCart(User user) {
        // Extract the user ID from the User object
        Long userId = user.getId();

        // Retrieve all cart items for the given user ID
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);

        // Delete all the retrieved cart items
        cartItemRepository.deleteAll(cartItems);
    }

    public double calculateTotalPrice(User user) {
        List<CartItem> cartItems = cartItemRepository.findAll().stream()
                .filter(cartItem -> cartItem.getUser().equals(user))
                .toList();

        return cartItems.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }
}
