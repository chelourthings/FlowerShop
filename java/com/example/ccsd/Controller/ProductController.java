package com.example.ccsd.Controller;

import com.example.ccsd.Model.Product;
import com.example.ccsd.Repository.ProductRepository;
import com.example.ccsd.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //simple page direct
    @GetMapping
    public String viewProducts(Model model) {
        model.addAttribute("products", productService.findAllProducts());
        return "products";
    }

    @GetMapping("/view/{id}")
    public String viewProductDetails(@PathVariable("id") Long id, Model model) {
        Product product = productService.findProductById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product Id: " + id));
        model.addAttribute("product", product);
        return "product";
    }
}



