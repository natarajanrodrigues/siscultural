/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.AdministrationUnity;
import io.github.siscultural.entities.Locality;
import io.github.siscultural.services.AdministrationUnityService;
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
@SessionAttributes("unity")
public class UnityController {

    @Autowired
    AdministrationUnityService administrationUnityService;

    @GetMapping(value = "/unity")
    public ModelAndView localidades (SessionStatus sessionStatus) {

        ModelAndView mav = new ModelAndView("unity/unity");
        sessionStatus.setComplete();
        List<AdministrationUnity> administrationUnities = administrationUnityService.findAll();

        mav.addObject("unities", administrationUnities);

        return mav;

    }

    @RequestMapping(value = "/unity/add", method=RequestMethod.GET)
    public ModelAndView unityAddGet(AdministrationUnity unity) {
        ModelAndView modelAndView = new ModelAndView("unity/unity_add");
        modelAndView.addObject("unity", unity);
        return modelAndView;
    }

    @RequestMapping(value="/unity/add", method=RequestMethod.POST)
    public ModelAndView unityAddPost(@Validated @ModelAttribute ("unity") AdministrationUnity unity, BindingResult result,
                                            RedirectAttributes redirectAttributes, Model model, SessionStatus sessionStatus) {

        String name = unity.getName();

        model.addAttribute("name", name);

        ModelAndView modelAndView = new ModelAndView("unity/unity_add");

        if (!result.hasErrors()) {

            AdministrationUnity unity1 = administrationUnityService.save(unity);

            if (unity!= null) {

                sessionStatus.setComplete();
                modelAndView = new ModelAndView("redirect:/unity");

            }

        }

        return modelAndView;
    }

//
//    @RequestMapping(value = "/localidade_edit", method=RequestMethod.GET)
//    public ModelAndView apresentacaoEdit(@RequestParam("id") String id) {
//        Locality locality = localityService.findById(Long.parseLong(id));
//        ModelAndView modelAndView = new ModelAndView("localidades/localidade_add");
//        modelAndView.addObject("locality", locality);
//        return modelAndView;
//    }
//
//
//    @PostMapping(value = "/localidade/delete")
//    @ResponseBody
//    public ModelAndView deteleLocalidade(@RequestParam("id") String id) {
//
//        Map<String, String> map = new HashMap<>();
//        map.clear();
//
//        Locality locality = localityService.findById(Long.parseLong(id));
//
//        return JsonView.returnJsonFromMap(localityService.delete(locality));
//
//    }


}
