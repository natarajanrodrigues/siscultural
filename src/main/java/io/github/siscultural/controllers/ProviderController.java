/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.Budget;
import io.github.siscultural.entities.CompanyProvider;
import io.github.siscultural.entities.IndividualProvider;
import io.github.siscultural.entities.Locality;
import io.github.siscultural.entities.Provider;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.services.ProviderService;
import io.github.siscultural.utils.JsonView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@Controller
@RequestScope
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @GetMapping(value = "/fornecedores")
    public ModelAndView providers() {

        ModelAndView mav = new ModelAndView("fornecedores");

        mav.addObject("ips", providerService.findAllIndividualProvider());
        mav.addObject("cps", providerService.findAllCompanyProvider());

        return mav;

    }

    @PostMapping(value = "/fornecedores/add")
    @ResponseBody
    public ModelAndView addProvider(Provider provider) {

        ModelAndView mav = new ModelAndView("redirect:/fornecedores");

        providerService.save(provider);

        return mav;

    }

    @RequestMapping(value = "/fjuridico_add", method = RequestMethod.POST)
    public ModelAndView companyProviderAdd(@Validated @ModelAttribute("companyProvider") CompanyProvider companyProvider, BindingResult result,
            RedirectAttributes redirectAttributes, Model model, SessionStatus sessionStatus) {

        providerService.save(companyProvider);

        return new ModelAndView("redirect:/fornecedores");
    }

    @RequestMapping(value = "/ffisico_add", method = RequestMethod.POST)
    public ModelAndView individualProviderAdd(@Validated @ModelAttribute("companyProvider") IndividualProvider individualProvider, BindingResult result,
            RedirectAttributes redirectAttributes, Model model, SessionStatus sessionStatus) {

        providerService.save(individualProvider);

        return new ModelAndView("redirect:/fornecedores");
    }

    @PostMapping(value = "/provider_delete")
    @ResponseBody
    public ModelAndView deteleOrcamento(@RequestParam("id") String id) {

        Map<String, String> map = new HashMap<>();
        map.clear();

        providerService.remove(Long.parseLong(id));

        map.put("resultado", "Exclusão realizada com sucesso.");

        return JsonView.returnJsonFromMap(map);

    }
//    
//    
//    public ModelAndView addOrcamento(@RequestParam("name") String name, @RequestParam("descricao") String descricao) {
//
//        ModelAndView mav = new ModelAndView("redirect:/orcamento");
//        Map<String, String> map = new HashMap<>();
//
//
//        Budget newBudget = new Budget();
//        newBudget.setName(name);
//        newBudget.setDescription(descricao);
//        Budget b = budgetDao.save(newBudget);
//
//        if (b != null) {
//            map.put("error", ErrorMessages.INVALID_LOGIN.toString());
//            return JsonView.returnJsonFromMap(map);
//        }
//
//        return mav;
//
//
//    }

}
