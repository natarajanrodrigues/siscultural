/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.utils.JsonView;
import io.github.siscultural.entities.Functionary;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.enums.UserType;
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
@Controller
public class FunctionaryAddController {

    @Autowired
    FunctionaryRepository functionaryDao;
    @Autowired
    HttpSession httpSession;

    @PostMapping(value = "/functionary/add")
    @ResponseBody
    public ModelAndView addFunctionary(String nome, String email, String password) {

        List<Functionary> users = functionaryDao.findByEmailAndPassword(email, password);

        Map<String, String> map = new HashMap<>();
        
        
        if (users.isEmpty()) {

            functionaryDao.save(new Functionary(nome, password, email, UserType.COLABORADOR));
            

        } else {
            
            map.put("error", ErrorMessages.EXISTENTE_USER.toString());
        }

        return JsonView.returnJsonFromMap(map);

    }
    

}
