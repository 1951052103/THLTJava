/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bth.hibernatedemo;

//import com.bth.pojo.Category;
//import com.bth.pojo.OrderDetail;
//import com.bth.pojo.Product;
//import com.bth.pojo.SaleOrder;
//import com.bth.pojo.User;

import com.bth.pojo.Product;
import com.bth.pojo.Category;
import com.bth.pojo.OrderDetail;
import com.bth.pojo.SaleOrder;
import com.bth.pojo.User;
import java.util.Properties;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;


/**
 *
 * @author admin
 */
public class HibernateUtils {
    private static final SessionFactory factory;
    
    static {
        Configuration conf = new Configuration();
        Properties props = new Properties();
        props.setProperty(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
        props.setProperty(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        props.setProperty(Environment.URL, "jdbc:mysql://localhost/saledb");
        props.setProperty(Environment.USER, "root");
        props.setProperty(Environment.PASS, "12345678");
        props.setProperty(Environment.SHOW_SQL, "true");
        conf.setProperties(props);
        
        conf.addAnnotatedClass(Category.class);
        conf.addAnnotatedClass(Product.class);
        conf.addAnnotatedClass(User.class);
        conf.addAnnotatedClass(SaleOrder.class);
        conf.addAnnotatedClass(OrderDetail.class);
        
        ServiceRegistry registry = new StandardServiceRegistryBuilder().applySettings(conf.getProperties()).build();
        
        factory = conf.buildSessionFactory(registry);
        
    }

    /**
     * @return the factory
     */
    public static SessionFactory getFactory() {
        return factory;
    }
}
