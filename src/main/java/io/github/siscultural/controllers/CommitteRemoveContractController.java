/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.Committe;
import io.github.siscultural.entities.Contract;
import io.github.siscultural.entities.Program;
import io.github.siscultural.services.CommitteService;
import io.github.siscultural.services.ContractService;
import io.github.siscultural.utils.LocalDateToStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

import static io.github.siscultural.entities.Contract.Comparators.PRESENTATION_NAME;

/**
 * @author Natarajan
 */
@Controller
@SessionAttributes("committe")
public class CommitteRemoveContractController {


    @Autowired
    CommitteService committeService;

    @Autowired
    ContractService contractService;


    @RequestMapping(value = "/committe_edit", method = RequestMethod.GET)
    public ModelAndView apresentacaoDetail2(@RequestParam("id") String id) {

        ModelAndView modelAndView = new ModelAndView("comite/comite_edit");

        List<Contract> availableContracts = contractService.findAvailables();
        modelAndView.addObject("availableContracts", availableContracts);


        if (id != null) {
            Committe committe = committeService.findById(Long.parseLong(id));

            if (committe != null) {
                modelAndView.addObject("committe", committe);
                modelAndView.addObject("date2", committe.getDate().format(LocalDateToStringConverter.formatter));

                Map<Program, Set<Contract>> test = committeService.contractsPerProgram(committe);

                modelAndView.addObject("teste", test);
            }

        }

        return modelAndView;
    }

    @RequestMapping(value = "/comite/contract/add", method = RequestMethod.POST)
    public ModelAndView apresentacaoDetail2(@ModelAttribute("committe") Committe committe, @RequestParam("contracts") String[] contracts) {

        committe = committeService.findById(committe.getId());

        for (String s : contracts) {

            Contract c = contractService.findById(Long.parseLong(s));

            c.setCommitte(committe);
            contractService.save(c);
            committe.addContract(c);


        }

        committeService.save(committe);
        ModelAndView modelAndView = new ModelAndView("redirect:/committe_edit?id=" + committe.getId());
        modelAndView.addObject("committe", committe);

        return modelAndView;
    }


    @RequestMapping(value = "/comite/contract/remove", method = RequestMethod.POST)
    public ModelAndView accomplishmentDelete(@ModelAttribute("committe") Committe committe,
                                             @RequestParam("contractId") String contractId) {

        ModelAndView modelAndView = new ModelAndView("redirect:/committe_edit?id=" + committe.getId());

        Contract c = contractService.findById(Long.parseLong(contractId));

        if (c != null) {

            c.setCommitte(null);
            c = contractService.save(c);

            boolean result = committe.removeContract(c);

            committeService.save(committe);

            modelAndView.addObject("committe", committe);

//            sessionStatus.setComplete();

        }

        return modelAndView;

    }


}
