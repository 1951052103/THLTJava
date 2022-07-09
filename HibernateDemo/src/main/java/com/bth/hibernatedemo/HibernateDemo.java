/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.bth.hibernatedemo;

import com.bth.pojo.Product;
import com.bth.services.ProductServices;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.hibernate.Session;

/**
 *
 * @author admin
 */
public class HibernateDemo {

    public static void main(String[] args) {
        try (Session s = HibernateUtils.getFactory().openSession()) {
            Query q = s.createNamedQuery("Product.findAll", Product.class);
            List<Product> products = q.getResultList();
            products.forEach(p -> System.out.printf("%d - %s - %s\n", 
                p.getId(), p.getName(), p.getPrice()));
            
        }
        
//        ProductServices s = new ProductServices();
//        
//        Map<String, String> params = new HashMap<>();
//        params.put("kw", "iPhone");
//        params.put("fromPrice", "17000000");
//        
//        s.getProducts(params, 1).forEach(p -> System.out.printf("%d - %s - %.1f\n", 
//                p.getId(), p.getName(), p.getPrice()));
    }
}
