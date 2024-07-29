package com.flowerShop.ccsdProject.controller;

import com.flowerShop.ccsdProject.model.Product;
import com.flowerShop.ccsdProject.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String getProducts(@RequestParam(required = false) String name, Model model) {
        List<Product> products = (name != null && !name.isEmpty()) ? productService.searchProducts(name) : productService.findAll();
        model.addAttribute("products", products);
        return "productList";
    }
}
