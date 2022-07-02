/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bth.englishapp;

import javax.sql.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author admin
 */
public class JdbcTemplateUtils {
    private final static JdbcTemplate t;
    static {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
        DataSource d = (DataSource) ctx.getBean("dataSource");
        t = new JdbcTemplate(d);
    }

    /**
     * @return the t
     */
    public static JdbcTemplate getT() {
        return t;
    }
}
