/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bth.repository;

import com.bth.pojo.User;

/**
 *
 * @author admin
 */
public interface UserRepository {
    User getUserByUsername(String username);
    boolean register(User user);
}
