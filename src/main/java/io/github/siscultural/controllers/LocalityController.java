/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.Locality;
import io.github.siscultural.services.LocalityService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Natarajan
 */
@Controller
@SessionAttributes("locality")
public class LocalityController {

    @Autowired
    LocalityService localityService;

    @GetMapping(value = "/localidades")
    public ModelAndView localidades (SessionStatus sessionStatus) {

        ModelAndView mav = new ModelAndView("localidades");
        sessionStatus.setComplete();
        List<Locality> localities = localityService.findAll();

        mav.addObject("localities", localities);

        return mav;

    }

    @RequestMapping(value = "/localidade_add", method=RequestMethod.GET)
    public ModelAndView localidadeAdd2(Locality locality) {
        ModelAndView modelAndView = new ModelAndView("localidades/localidade_add");
        modelAndView.addObject("locality", locality);
        return modelAndView;
    }

    @RequestMapping(value="/localidade_add", method=RequestMethod.POST)
    public ModelAndView localidadeAdd(@Validated @ModelAttribute ("locality") Locality locality, BindingResult result,
                                            RedirectAttributes redirectAttributes, Model model, SessionStatus sessionStatus) {

        String description = locality.getDescription();
        String address = locality.getAddress();
        String city = locality.getCity();
        String state = locality.getState();

        model.addAttribute("description", description);
        model.addAttribute("address", address);
        model.addAttribute("city", city);
        model.addAttribute("state", state);

        ModelAndView modelAndView = new ModelAndView("localidades/localidade_add");

        if (result.hasErrors()) {

            return new ModelAndView("localidades/localidade_add");

        } else {


            Locality locality2 = localityService.save(locality);

            if (locality2 != null) {

                sessionStatus.setComplete();
                modelAndView = new ModelAndView("redirect:/localidades");

            }

        }

        return modelAndView;
    }


    @RequestMapping(value = "/localidade_edit", method=RequestMethod.GET)
    public ModelAndView apresentacaoEdit(@RequestParam("id") String id) {
        Locality locality = localityService.findById(Long.parseLong(id));
        ModelAndView modelAndView = new ModelAndView("localidades/localidade_add");
        modelAndView.addObject("locality", locality);
        return modelAndView;
    }


    @PostMapping(value = "/localidade/delete")
    @ResponseBody
    public ModelAndView deteleLocalidade(@RequestParam("id") String id) {

        Map<String, String> map = new HashMap<>();
        map.clear();

        Locality locality = localityService.findById(Long.parseLong(id));

        return JsonView.returnJsonFromMap(localityService.delete(locality));

    }


}
