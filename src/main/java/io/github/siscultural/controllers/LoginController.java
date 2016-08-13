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

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@Controller
public class LoginController {

    @Autowired
    FunctionaryRepository functionaryDao;

    @PostMapping(value = "/login")
    public ModelAndView login(String email, String password) {

        List<Functionary> users = functionaryDao.findByEmailAndPassword(email, password);

        if (users.isEmpty()) {

            Map<String, String> map = new HashMap<>();

            map.clear();
            map.put("error", ErrorMessages.INVALID_LOGIN.getValue());

            return JsonView.returnJsonFromMap(map);

        } else {

            return new ModelAndView("home");
        }
    }

}
