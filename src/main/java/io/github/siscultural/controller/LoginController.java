/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controller;

import io.github.siscultural.entity.SystemUser;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.repository.Dao;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@Controller
public class LoginController {

    @Autowired
    Dao<SystemUser> userDao;

    @PostMapping(value = "/login")
    @ResponseBody
    public ModelAndView login(@ModelAttribute("email") String email, @ModelAttribute("password") String password) throws IOException {

        Map<String, String> map = new HashMap<>();
        map.put("email", email);
        map.put("password", password);

        List<SystemUser> users = userDao.findByAttributes(SystemUser.class, map);

        if (users.isEmpty()) {

            map.clear();
            map.put("error", ErrorMessages.INVALID_LOGIN.getValue());

            return JsonView.returnJsonFromMap(map);

        } else {

            System.out.println(users.get(0).getClass());

            return new ModelAndView("/");
        }
    }

}
