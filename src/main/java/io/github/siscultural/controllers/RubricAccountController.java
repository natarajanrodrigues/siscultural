/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.Budget;
import io.github.siscultural.entities.Rubric;
import io.github.siscultural.entities.RubricAccount;
import io.github.siscultural.repositories.OrcamentoRepository;
import io.github.siscultural.repositories.RubricAccountRepository;
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
 * @author Natarajan
 */
@Controller
public class RubricAccountController {

    @Autowired
    private RubricAccountRepository rubricAccountRepository;
    @Autowired
    private OrcamentoRepository orcamentoRepository;
    @Autowired
    private RubricRepository rubricRepository;



    @GetMapping(value = "/conta")
    public ModelAndView conta() {

        ModelAndView mav = new ModelAndView("conta");

        List<RubricAccount> rubricAccounts = rubricAccountRepository.findAll();

        mav.addObject("rubricAccounts", rubricAccounts);
        mav.addObject("budgets", orcamentoRepository.findAll());
        mav.addObject("rubrics", rubricRepository.findAll());


        return mav;
    }

    @PostMapping(value = "/conta/add")
    public ModelAndView addRubricAccount(@RequestParam("orcamento") long orcamento, @RequestParam("rubrica") long rubrica) {

        ModelAndView mav = new ModelAndView("redirect:/conta");


        Budget budget = orcamentoRepository.findById(orcamento);
        Rubric rubric = rubricRepository.findById(rubrica);


        RubricAccount existingRubricAccount = rubricAccountRepository.findByBudgetAndRubric(budget, rubric);

        if (existingRubricAccount == null) {
            RubricAccount ra = new RubricAccount();
            ra.setBudget(budget);
            ra.setRubric(rubric);
            rubricAccountRepository.save(ra);
        }


        return mav;


    }




}
