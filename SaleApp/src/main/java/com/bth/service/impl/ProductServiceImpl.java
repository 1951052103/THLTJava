/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bth.service.impl;

import com.bth.pojo.Product;
import com.bth.repository.ProductRepository;
import com.bth.service.ProductService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author admin
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts(Map<String, String> params, int page) {
        return this.productRepository.getProducts(params, page);
    }

    @Override
    public int countProuct() {
        return this.productRepository.countProuct();
    }

    @Override
    public boolean addProduct(Product p) {
        p.setImage("https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248652/dkeolz3ghc0eino87iec.jpg");

        return this.productRepository.addProduct(p);
    }

    @Override
    public boolean deleteProduct(int id) {
        return this.productRepository.deleteProduct(id);
    }

    @Override
    public List<Object[]> cateStats() {
        return this.productRepository.cateStats();
    }

    @Override
    public List<Object[]> revenueStats() {
        return this.productRepository.revenueStats();
    }
}
