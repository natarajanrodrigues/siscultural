/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.CompanyProvider;
import io.github.siscultural.services.ProviderService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView apresentacao() {

        ModelAndView mav = new ModelAndView("fornecedores");

        mav.addObject("ips", providerService.findAllIndividualProvider());
        mav.addObject("cps", providerService.findAllCompanyProvider());

        return mav;

    }
    
}
