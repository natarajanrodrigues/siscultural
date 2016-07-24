/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controller;

import io.github.siscultural.entity.User;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.repository.UserDao;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public class LoginController {
    
    UserDao userDao;
    
    public LoginController(){
        
         userDao = new UserDao();
    }
    
    public User login(String email, String password) throws IllegalArgumentException{
        
        Map<String,String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);
        
        List<User> users = userDao.searchByAttributes(map);
        
        if(users.isEmpty()){
            throw new IllegalArgumentException(ErrorMessages.INVALID_LOGIN.getValue());
        }
        
        User user = users.get(0);
        
        return user;
    }
    
}
