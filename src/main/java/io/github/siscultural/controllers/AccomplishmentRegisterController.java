/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.Accomplishment;
import io.github.siscultural.entities.Contract;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Natarajan
 */
@Controller
public class AccomplishmentRegisterController {

    @Autowired
    ContractService contractService;

    @Autowired
    AccomplishmentRepository accomplishmentRepository;


    @RequestMapping(value = "/audiencia", method=RequestMethod.GET)
    public ModelAndView accomplishmentAudience(@RequestParam("id") String id) {

        Accomplishment accomplishment = accomplishmentRepository.findById(Long.parseLong(id));
        Contract contract = accomplishment.getContract();

        ModelAndView modelAndView = new ModelAndView("presentations/apresentacao_publico");

        modelAndView.addObject("contract", contract);
        modelAndView.addObject("accomplishment", accomplishment);

//        redirectAttributes.addFlashAttribute("accomplishment", accomplishment);

        return modelAndView;

    }

    @RequestMapping(value = "/audiencia/add", method=RequestMethod.POST)
    public ModelAndView accomplishmentAudience(@RequestParam("id") String id, @RequestParam("audience") String audience) {

        Accomplishment accomplishment = accomplishmentRepository.findById(Long.parseLong(id));
        Contract contract = accomplishment.getContract();

        accomplishment.setAudience(Integer.parseInt(audience));
        accomplishmentRepository.save(accomplishment);
        ModelAndView modelAndView = new ModelAndView("redirect:/apresentacao_details?id=" + accomplishment.getContract().getPresentation().getId());
        return modelAndView;

    }


}
