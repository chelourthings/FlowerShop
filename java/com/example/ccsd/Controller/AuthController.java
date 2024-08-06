//handles HTTP requests related to user registration and login

package com.example.ccsd.Controller;

import com.example.ccsd.Model.Product;
import com.example.ccsd.Model.User;
import com.example.ccsd.Service.ProductService;
import com.example.ccsd.Service.UserService;
import com.example.ccsd.Dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthController {

    @Autowired
    private UserDetailsService userDetailsService;

    private UserService userService;
    @Autowired
    private ProductService productService;


    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Handles requests to the root URL ("/") and displays the homepage
    @GetMapping( "/")
    public String homepage(Model model, Principal principal) {
        // Fetch all products and add them to the model
        List<Product> product = productService.findAllProducts();
        model.addAttribute("products", product);

        // Check if the user is authenticated
        if(principal !=null) {
            // Load user details and add them to the model
            UserDetails userDetails = userDetailsService.loadUserByUsername(principal.getName());
            model.addAttribute("userdetail", userDetails);
        } else {
            model.addAttribute("userdetail", null);
        }
        // Add authentication status to the model
        model.addAttribute("isAuthenticated", principal != null);
        return "index";
    }

    // Handles GET requests to "/login" and displays the login page
    @GetMapping("/login")
    public String login(Model model, UserDto userDto) {
        model.addAttribute("user", userDto);
        return "login";
    }

    //handle method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model, UserDto userDto) {
        model.addAttribute("user", userDto);
        return "register";
    }

    //handle method to handle user registration form submit request
    @PostMapping("/register")
    public String registerSava(@ModelAttribute("user") UserDto userDto, Model model) {
        // Check if a user with the same username already exists
        User user = userService.findByUsername(userDto.getUsername());
        // Check if a user with the same email already exists
        User user2 = userService.findByEmail(userDto.getEmail());

        // If a user with the same username exists, add an error message and return to the registration page
        if (user != null) {
            model.addAttribute("Userexist", user);
            return "register";
        }

        // If a user with the same email exists, add an error message and return to the registration page
        if (user2 != null) {
            model.addAttribute("Emailexist", user2);
            return "register";
        }
        userService.save(userDto);
        return "redirect:/register?success";
    }

    // Handles GET requests to "/search" to search for products by name
    @GetMapping("/search")
    public String searchProduct(@RequestParam(name="name", required = false) String name, Model model) {
        if (name == null || name.trim().isEmpty()) {
            model.addAttribute("searchResults", new ArrayList<>());
            model.addAttribute("searchname", name);
            return "searchResult";
        }

        // Perform the search and add the results to the model
        List<Product> searchResults = productService.searchProducts(name);
        model.addAttribute("searchResults", searchResults);
        model.addAttribute("searchname", name);
        return "searchResult";
    }
}
