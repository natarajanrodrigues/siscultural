/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.FunctionaryUser;
import io.github.siscultural.entities.Budget;
import io.github.siscultural.entities.Rubric;
import io.github.siscultural.entities.RubricAccount;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.enums.UserType;
import io.github.siscultural.repositories.OrcamentoRepository;
import io.github.siscultural.repositories.RubricAccountRepository;
import io.github.siscultural.repositories.RubricRepository;
import io.github.siscultural.services.BudgetService;
import io.github.siscultural.utils.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Natarajan
 */
@Controller
public class RubricAccountController {

    @Autowired
    private RubricAccountRepository rubricAccountRepository;
    @Autowired
    private BudgetService budgetService;
    @Autowired
    private RubricRepository rubricRepository;
    @Autowired
    private OrcamentoRepository budgetRepository;



    @GetMapping(value = "/conta")
    public ModelAndView conta(Authentication authentication) {

        ModelAndView mav = new ModelAndView("conta");


        FunctionaryUser user = (FunctionaryUser) authentication.getPrincipal();

        if (!user.getUserType().equals(UserType.ADMINISTRADOR)) {

            //se for gerente, procurar o budget de sua unidade.
            //o mesmo as rubrics accoutns- procurar apenas as rubrics accounts do budget atual de sua unidade

            Budget currentBudget = budgetService.findByCurrentAndUnit(true, user.getUnit().getId());

//            Budget currentBudget = budgetService.getCurrentBudget();

            mav.addObject("budgets", budgetService.findByUnit(user.getUnit().getId()));
            mav.addObject("rubrics", rubricRepository.findAll());
            if (currentBudget != null)
                mav.addObject("rubricAccounts", rubricAccountRepository.findByBudget(currentBudget));

        } else {
            mav.addObject("rubricAccounts", rubricAccountRepository.findAll());
            mav.addObject("budgets", budgetService.findAll());
            mav.addObject("rubrics", rubricRepository.findAll());
        }





        return mav;
    }

    @PostMapping(value = "/conta/add")
    public ModelAndView addRubricAccount(@RequestParam("orcamento") long orcamento, @RequestParam("rubrica") long rubrica) {

        ModelAndView mav = new ModelAndView("redirect:/conta");


        Budget budget = budgetService.findById(orcamento);
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

    @RequestMapping(value = "/conta/edit", method = RequestMethod.POST)
    public ModelAndView contaEdit(@RequestParam("id") String id, @RequestParam("orcamento") String orcamento, @RequestParam("rubrica") String rubrica) {

        ModelAndView mav = new ModelAndView("redirect:/conta");

        RubricAccount account = rubricAccountRepository.findById(Long.parseLong(id));
        Budget budget = budgetRepository.findById(Long.parseLong(orcamento));
        Rubric rubric = rubricRepository.findById(Long.parseLong(rubrica));

        account.setBudget(budget);
        account.setRubric(rubric);

        rubricAccountRepository.save(account);

        return mav;
    }

    @RequestMapping(value = "/conta/delete", method=RequestMethod.POST)
    public ModelAndView contaDelete(@RequestParam("id") String id) {

        Map<String, String> map = new HashMap<>();
        map.clear();

        RubricAccount account = rubricAccountRepository.findById(Long.parseLong(id));


        if (account != null) {

//            account.getProgram().removeRubric(rubric);

            rubricAccountRepository.delete(account);

            account = rubricAccountRepository.findById(Long.parseLong(id));

            if (account != null) {
                map.put("erro", ErrorMessages.ERROR_OPERATION.toString());
            } else {
                map.put("resultado", "Exclusão realizada com sucesso.");
            }
        }
        return JsonView.returnJsonFromMap(map);

    }




}
