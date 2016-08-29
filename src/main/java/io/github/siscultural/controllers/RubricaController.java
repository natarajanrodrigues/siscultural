/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.Budget;
import io.github.siscultural.entities.Program;
import io.github.siscultural.entities.Rubric;
import io.github.siscultural.repositories.ProgramRepository;
import io.github.siscultural.repositories.RubricRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 *
 * @author Natarajan
 */
@Controller
public class RubricaController {

    @Autowired
    private RubricRepository rubricRepository;
    @Autowired
    private ProgramRepository programRepository;

    @GetMapping(value = "/rubrica")
    public ModelAndView rubrica() {

        ModelAndView mav = new ModelAndView("rubrica");

        List<Rubric> rubrics = rubricRepository.findAll();

        mav.addObject("rubrics", rubrics);
        mav.addObject("programs", programRepository.findAll());

        return mav;
    }

    @PostMapping(value = "/rubrica/add")
    public ModelAndView addRubric(@RequestParam("name") String name, @RequestParam("descricao") String descricao, @RequestParam("programa") String programa) {

        ModelAndView mav = new ModelAndView("redirect:/rubrica");


        Program program = programRepository.findById(Long.parseLong(programa));
        Rubric newRubric = new Rubric();

        newRubric.setName(name);
        newRubric.setDescription(descricao);
        program.addRubric(newRubric);

        programRepository.save(program);
//        rubricRepository.save(newRubric);

        return mav;


    }




}
