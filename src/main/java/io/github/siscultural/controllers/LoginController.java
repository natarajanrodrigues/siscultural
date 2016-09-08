/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.utils.JsonView;
import io.github.siscultural.entities.Functionary;
import io.github.siscultural.enums.ErrorMessages;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import io.github.siscultural.repositories.FunctionaryRepository;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
//@Controller
public class    LoginController {

//    @Autowired
//    FunctionaryRepository functionaryDao;
//    @Autowired
//    HttpSession httpSession;
//
//    @PostMapping(value = "/login")
//    @ResponseBody
//    public ModelAndView login(String email, String password) {
//
//        List<Functionary> users = functionaryDao.findByEmailAndPassword(email, password);
//
//        Map<String, String> map = new HashMap<>();
//
//        ModelAndView mav;
//
//        if (users.isEmpty()) {
//
////            map.put("error", ErrorMessages.INVALID_LOGIN.toString());
//            mav = new ModelAndView("login");
//            mav.addObject("error", ErrorMessages.INVALID_LOGIN.toString());
////            return JsonView.returnJsonFromMap(map);
//
//        } else {
//            
//            Functionary functionary = users.get(0);
//            
//            httpSession.setAttribute("functionary", functionary);
//
//            mav = new ModelAndView("redirect:/home");
////            map.put("redirect", "home");
//        }
//
//        return mav;
//
//    }
//    
//    @GetMapping(value = "/logout")
//    public ModelAndView logout(){
//        
//        httpSession.invalidate();
//        
//        return new ModelAndView("redirect:/");
//    }

}
