/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import br.com.caelum.stella.inwords.FormatoDeInteiro;
import br.com.caelum.stella.inwords.FormatoDeReal;
import br.com.caelum.stella.inwords.InteiroSemFormato;
import br.com.caelum.stella.inwords.NumericToWordsConverter;
import io.github.siscultural.entities.Accomplishment;
import io.github.siscultural.entities.Contract;
import io.github.siscultural.entities.PaymentProposal;
import io.github.siscultural.entities.Provider;
import io.github.siscultural.repositories.AccomplishmentRepository;
import io.github.siscultural.repositories.PaymentProposalRepository;
import io.github.siscultural.repositories.PresentationRepository;
import io.github.siscultural.repositories.ProgramRepository;
import io.github.siscultural.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Natarajan
 */
@Controller
@SessionAttributes("contract")
public class PressContractController {

    @Autowired
    ContractService contractService;

    @Autowired
    ProviderService providerService;


    @RequestMapping(value = "/press_area", method=RequestMethod.GET)
    public ModelAndView contratoEdit(@RequestParam("id") String id, SessionStatus sessionStatus) {

        Contract contract = contractService.findById(Long.parseLong(id));

        Set<Provider> providerList = new HashSet<>();

        for (PaymentProposal p : contract.getPaymentProposals()) {
            providerList.add(p.getProvider());
        }

        ModelAndView modelAndView = new ModelAndView("press/config");

        modelAndView.addObject("contract", contract);
        modelAndView.addObject("providers", providerList);

        return modelAndView;
    }

    @RequestMapping(value = "/press", method=RequestMethod.POST)
    public ModelAndView imprimir(@ModelAttribute("contract") Contract contract, @RequestParam("provider") String provider) {

        Contract c = contractService.findById(contract.getId());
        Provider choosedProvider = providerService.findbyId(Long.parseLong(provider));

        NumericToWordsConverter converter;
        converter = new NumericToWordsConverter(new InteiroSemFormato());

        NumericToWordsConverter converterReal = new NumericToWordsConverter(new FormatoDeReal());


        double total = 0;
        for (PaymentProposal p : contract.getPaymentProposals()) {
            if (p.getProvider().equals(choosedProvider))
                total += p.getAmount().doubleValue();
        }

        ModelAndView modelAndView = new ModelAndView("press/contract");

        modelAndView.addObject("contract", contract);
        modelAndView.addObject("accomplisments", contract.getAccomplishments());
        modelAndView.addObject("provider", choosedProvider);
        modelAndView.addObject("total", total);
        modelAndView.addObject("converter", converter);
        modelAndView.addObject("converterReal", converterReal);
        modelAndView.addObject("num", new Double(contract.getAccomplishments().size()).doubleValue());

        return modelAndView;
    }





}
