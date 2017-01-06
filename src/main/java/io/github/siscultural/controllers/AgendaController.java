/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.siscultural.entities.Accomplishment;
import io.github.siscultural.entities.Contract;
import io.github.siscultural.entities.Program;
import io.github.siscultural.repositories.AccomplishmentRepository;
import io.github.siscultural.repositories.PaymentProposalRepository;
import io.github.siscultural.repositories.PresentationRepository;
import io.github.siscultural.repositories.ProgramRepository;
import io.github.siscultural.services.*;
import io.github.siscultural.utils.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author Natarajan
 */
@RestController
public class AgendaController {

    @Autowired
    ContractService contractService;

    @Autowired
    AccomplishmentRepository accomplishmentRepository;


    @GetMapping(value = "/agenda")
    public ModelAndView contratos(SessionStatus sessionStatus) {

        ModelAndView mav = new ModelAndView("agenda/agenda");

        List<Contract> contracts = contractService.findAll();

        mav.addObject("contracts", contracts);

        Map<Program, Set<Contract>> result =
                contractService.findAll().stream().collect(
                        Collectors.groupingBy(Contract::getProgram, Collectors.toSet() )
                );

        Map<Program, Set<Contract>> test = new LinkedHashMap<>();

        result.entrySet().stream()
                .sorted((e1, e2) -> e1.getKey().getName().compareTo(e2.getKey().getName()))
                .forEachOrdered(x -> test.put(x.getKey(), x.getValue()));

        mav.addObject("teste", test);



        return mav;

    }

    @RequestMapping(value = "/agenda2", method=RequestMethod.GET)
    public List<?> agenda2() throws JsonProcessingException {
        return accomplishmentRepository.accomplishmentReports3();
    }

    @RequestMapping(value = "/agenda3", method=RequestMethod.GET)
    public List<Contract> agenda3() throws JsonProcessingException {
        return contractService.findAll();
    }


}
