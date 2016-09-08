/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.Functionary;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.enums.UserType;
import io.github.siscultural.repositories.FunctionaryRepository;
import io.github.siscultural.utils.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@Controller
public class UsuarioController {

    @Autowired
    FunctionaryRepository functionaryRepository;

    @GetMapping(value = "/usuarios")
    public ModelAndView usuario() {

        ModelAndView mav = new ModelAndView("usuarios");

        List<Functionary> functionaries = functionaryRepository.findAll();
        Collections.sort(functionaries, Functionary.Comparators.NAME);
        UserType[] types = UserType.values();

        mav.addObject("functionaries", functionaries);
        mav.addObject("types", types);

        return mav;
    }

    @PostMapping(value = "/usuarios/add")
    @ResponseBody
    public ModelAndView addUsuario(@RequestParam("name") String name, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("type") String type) {

        Map<String, String> map = new HashMap<>();

        Functionary functionary = new Functionary();
        functionary.setName(name);
        functionary.setEmail(email);
        functionary.setPassword(password);
        functionary.setUserType(UserType.valueOf(type));

        Functionary f = functionaryRepository.save(functionary);

        if (f != null) {
            map.put("error", ErrorMessages.INVALID_LOGIN.toString());
        } else {
            map.put("resultado", "Operação realizada com sucesso.");
        }
        return JsonView.returnJsonFromMap(map);

    }

    @RequestMapping(value = "/usuarios/edit", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView editUsuario(@ModelAttribute("id") String id, @ModelAttribute("name") String name, @ModelAttribute("email") String email, @ModelAttribute("type") String type) {

        Map<String, String> map = new HashMap<>();


        Functionary functionary = functionaryRepository.findByEmail(email);

        if (functionary != null) {


            functionary.setName(name);
            functionary.setEmail(email);
            functionary.setUserType(UserType.valueOf(type));

            Functionary f = functionaryRepository.save(functionary);

            if (f != null) {
                map.put("error", ErrorMessages.ERROR_OPERATION.toString());
            } else {
                map.put("resultado", "Operação realizada com sucesso.");
            }

        } else {
            map.put("error", "Usuário não encontrado.");
        }


        return JsonView.returnJsonFromMap(map);

    }

    @PostMapping(value = "/usuarios/delete")
    @ResponseBody
    public ModelAndView deteleOrcamento(@RequestParam("id") String id) {

        Map<String, String> map = new HashMap<>();
        map.clear();

        Functionary functionary = functionaryRepository.findById(Long.parseLong(id));


        if (functionary != null) {

            functionaryRepository.delete(functionary);

            functionary = functionaryRepository.findById(Long.parseLong(id));

            if (functionary != null) {
                map.put("erro", ErrorMessages.ERROR_OPERATION.toString());
            } else {
                map.put("resultado", "Exclusão realizada com sucesso.");
            }
        } else {
            map.put("error", "Usuário não encontrado.");
        }

        return JsonView.returnJsonFromMap(map);


    }



}
