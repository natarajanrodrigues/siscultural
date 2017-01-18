/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.FunctionaryUser;
import io.github.siscultural.entities.Budget;
import io.github.siscultural.enums.AdministrationUnit2;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.enums.UserType;
import io.github.siscultural.repositories.FunctionaryRepository;
import io.github.siscultural.repositories.OrcamentoRepository;
import io.github.siscultural.services.BudgetService;
import io.github.siscultural.utils.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
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

    @Autowired
    BudgetService budgetService;

    @GetMapping(value = "/orcamento")
    public ModelAndView orcamento(Authentication authentication) {

        ModelAndView mav = new ModelAndView("orcamento");

        List<Budget> budgets = budgetDao.findAll();
        Collections.sort(budgets, Budget.Comparators.ID_COMPARE);

        mav.addObject("budgets", budgets);
        mav.addObject("units", AdministrationUnit2.values());

        FunctionaryUser user = (FunctionaryUser) authentication.getPrincipal();

        if (!user.getUserType().equals(UserType.ADMINISTRADOR) ) {
            mav.addObject("myUnits", user.getUnit());
        } else {
            mav.addObject("myUnits", AdministrationUnit2.values());
        }

//

        return mav;
    }

    @PostMapping(value = "/orcamento/add")
    @ResponseBody
    public ModelAndView addOrcamento(@RequestParam("name") String name,
                                     @RequestParam("descricao") String descricao,
                                     @RequestParam("unit") String unit) {

        ModelAndView mav = new ModelAndView("redirect:/orcamento");
        Map<String, String> map = new HashMap<>();


        Budget newBudget = new Budget();
        newBudget.setName(name);
        newBudget.setDescription(descricao);
        newBudget.setUnit(AdministrationUnit2.valueOf(unit));
        Budget b = budgetDao.save(newBudget);

        if (b != null) {
            map.put("error", ErrorMessages.INVALID_LOGIN.toString());
            return JsonView.returnJsonFromMap(map);
        }

        return mav;


    }

    @PostMapping(value = "/orcamento/search_budgets")
    @ResponseBody
    public ModelAndView selectUnit(@RequestParam("unit") String unit) {

        ModelAndView mav = new ModelAndView("orcamento/selectbudget");

        List<Budget> budgets = budgetService.findByUnit(AdministrationUnit2.valueOf(unit).getId());

        mav.addObject("budgets", budgets);

        return mav;

    }

    @PostMapping(value = "/orcamento/escolher")
    @ResponseBody
    public ModelAndView chooseCurrentBudget(@RequestParam("orcamento") String orcamentoId) {

        ModelAndView mav = new ModelAndView("redirect:/orcamento");

        budgetService.setCurrent(Long.parseLong(orcamentoId));

        return mav;

    }



    @PostMapping(value = "/orcamento/edit")
    @ResponseBody
    public ModelAndView editOrcamento(@RequestParam("id") String id, @RequestParam("name") String name,
                                      @RequestParam("descricao") String descricao,
                                      @RequestParam("unit") String unit) {

        Map<String, String> map = new HashMap<>();
        map.clear();

        Budget budget = budgetDao.findById(Long.parseLong(id));


        if (budget != null) {

            budget.setName(name);
            budget.setDescription(descricao);
            budget.setUnit(AdministrationUnit2.valueOf(unit));
            Budget b = budgetDao.save(budget);

            if (b == null) {
                map.put("erro", ErrorMessages.ERROR_OPERATION.toString());
            } else {
                map.put("resultado", "Atualização realizada com sucesso.");
            }
        }
        return JsonView.returnJsonFromMap(map);


    }





    @PostMapping(value = "/orcamento/delete")
    @ResponseBody
    public ModelAndView deteleOrcamento(@RequestParam("id") String id) {

        Map<String, String> map = new HashMap<>();
        map.clear();

        Budget budget = budgetDao.findById(Long.parseLong(id));


        if (budget != null) {

            budgetDao.delete(budget);

            budget = budgetDao.findById(Long.parseLong(id));

            if (budget != null) {
                map.put("erro", ErrorMessages.ERROR_OPERATION.toString());
            } else {
                map.put("resultado", "Exclusão realizada com sucesso.");
            }
        }
        return JsonView.returnJsonFromMap(map);


    }



}
