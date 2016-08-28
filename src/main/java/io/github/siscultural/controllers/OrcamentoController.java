/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.Budget;
import io.github.siscultural.entities.Functionary;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.repositories.FunctionaryRepository;
import io.github.siscultural.repositories.OrcamentoRepository;
import io.github.siscultural.utils.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@Controller
public class OrcamentoController {

    @Autowired
    OrcamentoRepository budgetDao;

    @GetMapping(value = "/orcamento")
    public ModelAndView orcamento() {

        ModelAndView mav = new ModelAndView("orcamento");

        List<Budget> budgets = budgetDao.findAll();
        Collections.sort(budgets, Budget.Comparators.ID_COMPARE);

        mav.addObject("budgets", budgets);

        return mav;
    }

    @PostMapping(value = "/orcamento/add")
    @ResponseBody
    public ModelAndView addOrcamento(@RequestParam("name") String name, @RequestParam("descricao") String descricao) {

        ModelAndView mav = new ModelAndView("redirect:/orcamento");
        Map<String, String> map = new HashMap<>();


        Budget newBudget = new Budget();
        newBudget.setName(name);
        newBudget.setDescription(descricao);
        Budget b = budgetDao.save(newBudget);

        if (b != null) {
            map.put("error", ErrorMessages.INVALID_LOGIN.toString());
            return JsonView.returnJsonFromMap(map);
        }

        return mav;


    }

    @PostMapping(value = "/orcamento/edit")
    @ResponseBody
    public ModelAndView editOrcamento(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("descricao") String descricao) {

//        ModelAndView mav = new ModelAndView("redirect:/orcamento");
        Map<String, String> map = new HashMap<>();
        map.clear();

        Budget budget = budgetDao.findById(Long.parseLong(id));


        if (budget != null) {

            budget.setName(name);
            budget.setDescription(descricao);

            Budget b = budgetDao.save(budget);

            if (b == null) {
                map.put("erro", ErrorMessages.ERROR_OPERATION.toString());

            } else {
                map.put("resultado", "Atualização realizada com sucesso.");
//                return JsonView.returnJsonFromMap(map);
            }


        }
        return JsonView.returnJsonFromMap(map);
//        return mav;


    }




}
