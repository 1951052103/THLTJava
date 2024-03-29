/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bth.controllers;

import com.bth.service.CategoryService;
import com.bth.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author admin
 */
@Controller
@ControllerAdvice
public class IndexController {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;
    
    @ModelAttribute
    public void commonAttr(Model model) {
        model.addAttribute("categories", this.categoryService.getCategories());
    }
    
    @RequestMapping("/")
    public String index(Model model, @RequestParam Map<String, String> params) {     
        int page = Integer.parseInt(params.getOrDefault("page", "1"));
        model.addAttribute("products", this.productService.getProducts(params, page));
        model.addAttribute("productCounter", this.productService.countProuct());
        
        return "index";
    }
    
    @GetMapping("/products/{productId}")
    public String productDetails(Model model, 
            @PathVariable(value = "productId") int id) {
        model.addAttribute("product", this.productService.getProductById(id));
        
        return "product-details";
    }
}