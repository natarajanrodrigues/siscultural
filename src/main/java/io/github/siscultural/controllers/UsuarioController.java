/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.Functionary;
import io.github.siscultural.enums.AdministrationUnit;
import io.github.siscultural.enums.AdministrationUnit2;
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
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

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
        AdministrationUnit2[] units = AdministrationUnit2.values();

        mav.addObject("functionaries", functionaries);
        mav.addObject("types", types);
        mav.addObject("units", units);

        return mav;
    }

    @PostMapping(value = "/usuarios/add")
    @ResponseBody
    public ModelAndView addUsuario(@RequestParam("name") String name, @RequestParam("email") String email,
                                   @RequestParam("password") String password, @RequestParam("type") String type,
                                   @RequestParam("unit") String unit) {

        Map<String, String> map = new HashMap<>();

        Functionary functionary = new Functionary();
        functionary.setName(name);
        functionary.setEmail(email);
        functionary.setPassword(password);
        functionary.setUserType(UserType.valueOf(type));
        functionary.setUnit(AdministrationUnit2.valueOf(unit));


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
    public ModelAndView editUsuario(@RequestParam("id") String id, @RequestParam("name") String name,
                                    @RequestParam("email") String email, @RequestParam("type") String type,
                                    @RequestParam("unit") String unit) {

        Map<String, String> map = new HashMap<>();

        Functionary functionary = functionaryRepository.findById(Long.parseLong(id));

        if (functionary != null) {

            functionary.setName(name);
            functionary.setEmail(email);
            functionary.setUserType(UserType.valueOf(type));
            functionary.setUnit(AdministrationUnit2.valueOf(unit));
//            System.out.println(functionary.getEmail());

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

    @GetMapping("/current")
    public String currentUserNameSimple() {

        String result = "";

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            result = authentication.getName();
        } catch (NullPointerException ex) {
            System.out.println("Deu null no nome");
        }

        return result;
    }

}
