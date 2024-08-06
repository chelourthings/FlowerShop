package com.example.ccsd.Service;

import com.example.ccsd.Model.CartItem;
import com.example.ccsd.Model.Purchase;
import com.example.ccsd.Model.PurchaseItem;
import com.example.ccsd.Model.User;
import com.example.ccsd.Repository.PurchaseItemRepository;
import com.example.ccsd.Repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseService {

    @Autowired
    private CartService cartService;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private PurchaseItemRepository purchaseItemRepository;

    public double calculateTotalPrice(List<CartItem> cartItems) {
        return cartItems.stream()
                .mapToDouble(CartItem::getTotalPrice)
                .sum();
    }

//    public Purchase createPurchase(User user, List<CartItem> cartItems, String paymentMethod, String shippingAddress) {
//        double totalPrice = calculateTotalPrice(cartItems);
//
//        Purchase purchase = new Purchase();
//        purchase.setUser(user);
//        purchase.setPaymentMethod(paymentMethod);
//        purchase.setShippingAddress(shippingAddress);
//        purchase.setTotalPrice(totalPrice);
//
//        purchase = purchaseRepository.save(purchase);
//
//        for (CartItem cartItem : cartItems) {
//            PurchaseItem purchaseItem = new PurchaseItem();
//            purchaseItem.setPurchase(purchase);
//            purchaseItem.setProduct(cartItem.getProduct());
//            purchaseItem.setQuantity(cartItem.getQuantity());
//            purchaseItem.setTotalPrice(cartItem.getTotalPrice());
//            purchaseItemRepository.save(purchaseItem);
//        }
//        return purchase; // Return the saved purchase
//    }

    public void createPurchase(User user, List<CartItem> cartItems, String paymentMethod, String shippingAddress) {
        double totalPrice = calculateTotalPrice(cartItems);

        Purchase purchase = new Purchase();
        purchase.setUser(user);
        purchase.setPaymentMethod(paymentMethod);
        purchase.setShippingAddress(shippingAddress);
        purchase.setTotalPrice(totalPrice);

        purchase = purchaseRepository.save(purchase);

        for (CartItem cartItem : cartItems) {
            PurchaseItem purchaseItem = new PurchaseItem();
            purchaseItem.setPurchase(purchase);
            purchaseItem.setProduct(cartItem.getProduct());
            purchaseItem.setQuantity(cartItem.getQuantity());
            purchaseItem.setTotalPrice(cartItem.getTotalPrice());
            purchaseItemRepository.save(purchaseItem);
        }
    }
}

