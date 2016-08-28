/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.Budget;
import io.github.siscultural.entities.Rubric;
import io.github.siscultural.repositories.RubricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@Controller
public class RubricaController {

    @Autowired
    RubricRepository rubricRepository;

    @GetMapping(value = "/rubrica")
    public ModelAndView rubrica() {

        ModelAndView mav = new ModelAndView("rubrica");

        List<Rubric> rubrics = rubricRepository.findAll();

        mav.addObject("rubrics", rubrics);

        return mav;
    }

    @PostMapping(value = "/rubrica/add")
    public ModelAndView addOrcamento(@RequestParam("name") String name, @RequestParam("descricao") String descricao) {

        ModelAndView mav = new ModelAndView("redirect:/rubrica");

        Rubric newRubric = new Rubric();

        newRubric.setName(name);
        newRubric.setDescription(descricao);

        rubricRepository.save(newRubric);

//        List<Rubric> ru= rubricRepository.findAll();
//
//        mav.addObject("budgets", budgets);

        return mav;


    }




}
