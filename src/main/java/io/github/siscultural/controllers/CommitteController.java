/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.Committe;
import io.github.siscultural.entities.Contract;
import io.github.siscultural.entities.Presentation;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.services.CommitteService;
import io.github.siscultural.services.ContractService;
import io.github.siscultural.services.PresentationService;
import io.github.siscultural.utils.JsonView;
import io.github.siscultural.utils.LocalDateToStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Natarajan
 */
@Controller
@SessionAttributes("committe")
public class CommitteController {


    @Autowired
    CommitteService committeService;

    @Autowired
    ContractService contractService;

    @GetMapping(value = "/comite")
    public ModelAndView comite(SessionStatus sessionStatus) {

        ModelAndView mav = new ModelAndView("comite");
        sessionStatus.setComplete();

        List<Committe> committes = committeService.findAll();

        mav.addObject("committes", committes);

        return mav;

    }



    @RequestMapping(value = "/comite_add", method = RequestMethod.GET)
    public ModelAndView comiteAdd(Committe committe) {
        ModelAndView modelAndView = new ModelAndView("comite/comite_add");
        modelAndView.addObject("committe", committe);
        return modelAndView;
    }

    @RequestMapping(value = "/comite_add", method = RequestMethod.POST)
    public ModelAndView comiteAdd(@Validated @ModelAttribute("committe") Committe committe, BindingResult result,
            RedirectAttributes redirectAttributes, Model model, SessionStatus sessionStatus) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        String description = committe.getDescription();
        LocalDate dateCommitte = committe.getDate();

        String date = "";
        if (dateCommitte != null)
            date = committe.getDate().format(formatter);

        System.out.println(date);


        model.addAttribute("description", description);
        model.addAttribute("date", date);

        ModelAndView modelAndView = new ModelAndView("comite/comite_add");

        if (result.hasErrors()) {

            return modelAndView;

        } else {

            committe.setDate(LocalDate.parse(date, formatter));
            committe.setDescription(description);

            Committe newCommitte = committeService.save(committe);

//            sessionStatus.setComplete();

            modelAndView = new ModelAndView("redirect:/comite");

        }

        return modelAndView;
    }

//    @RequestMapping(value = "/apresentacao_edit", method = RequestMethod.GET)
//    public ModelAndView apresentacaoEdit(@RequestParam("id") String id) {
//        Presentation presentation = presentationService.findById(Long.parseLong(id));
//        ModelAndView modelAndView = new ModelAndView("presentations/apresentacao_add");
//        modelAndView.addObject("presentation", presentation);
//        return modelAndView;
//    }
//
//
    @PostMapping(value = "/comite/delete")
    @ResponseBody
    public ModelAndView deteleOrcamento(@RequestParam("id") String id) {

        Map<String, String> map = new HashMap<>();
        map.clear();
        Committe committe = committeService.findById(Long.parseLong(id));

        return JsonView.returnJsonFromMap(committeService.delete(committe));

    }



}
