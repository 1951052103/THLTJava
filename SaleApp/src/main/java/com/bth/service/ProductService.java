/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.bth.service;

import com.bth.pojo.Comments;
import com.bth.pojo.Product;
import java.util.List;
import java.util.Map;

/**
 *
 * @author admin
 */
public interface ProductService {
    List<Product> getProducts(Map<String, String> params, int page);
    int countProuct();
    boolean addProduct(Product p);
    boolean deleteProduct(int id);
    List<Object[]> cateStats();
    List<Object[]> revenueStats();
    List<Comments> getComments(int productId);
    Product getProductById(int id);
    Comments addComment(String content, int productId);
}
