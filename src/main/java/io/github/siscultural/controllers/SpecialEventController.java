/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.SpecialEvent;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.services.SpecialEventService;
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

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Natarajan Rodrigues em 18/11/2016
 */
@Controller
@SessionAttributes("specialEvent")
public class SpecialEventController {

    @Autowired
    SpecialEventService specialEventService;

    @GetMapping(value = "/special")
    public ModelAndView special() {

        ModelAndView mav = new ModelAndView("special");

        List<SpecialEvent> specialEvents = specialEventService.findAll();

        mav.addObject("specialEvents", specialEvents);

        return mav;
    }

//    @PostMapping(value = "/special/add")
//    @ResponseBody
//    public ModelAndView addSpecial(@RequestParam("name") String name, @RequestParam("descricao") String descricao) {
//
//        ModelAndView mav = new ModelAndView("redirect:/special");
//        Map<String, String> map = new HashMap<>();
//
//
//        SpecialEvent newSpecialEvent = new SpecialEvent();
//        newSpecialEvent.setName(name);
//        newSpecialEvent.setDescription(descricao);
//        SpecialEvent b = specialEventService.save(newSpecialEvent);
//
//        if (b != null) {
//            map.put("error", ErrorMessages.INVALID_LOGIN.toString());
//            return JsonView.returnJsonFromMap(map);
//        }
//
//        return mav;
//
//    }


    @RequestMapping(value = "/special_add", method=RequestMethod.GET)
    public ModelAndView specialAdd2(SpecialEvent specialEvent) {
        ModelAndView modelAndView = new ModelAndView("special/special_add");
        modelAndView.addObject("specialEvent", specialEvent);
        return modelAndView;
    }

    @RequestMapping(value="/special_add", method=RequestMethod.POST)
    public ModelAndView specialAdd(@Validated @ModelAttribute ("specialEvent") SpecialEvent specialEvent, BindingResult result,
                                   RedirectAttributes redirectAttributes, Model model, SessionStatus sessionStatus) {

        String name = specialEvent.getName();
        String description = specialEvent.getDescription();

        model.addAttribute("name", name);
        model.addAttribute("description", description);

        ModelAndView modelAndView = new ModelAndView("special/special_add");

        if (result.hasErrors()) {

            return new ModelAndView("special/special_add");

        } else {


            SpecialEvent se = specialEventService.save(specialEvent);

            if (se != null) {

                sessionStatus.setComplete();
                modelAndView = new ModelAndView("redirect:/special");

            }

        }

        return modelAndView;
    }


    @RequestMapping(value = "/special_edit", method=RequestMethod.GET)
    public ModelAndView specialEdit(@RequestParam("id") String id) {

        SpecialEvent specialEvent = specialEventService.findById(Long.parseLong(id));
        ModelAndView modelAndView = new ModelAndView("special/special_add");
        modelAndView.addObject("specialEvent", specialEvent);
        return modelAndView;
    }


    @PostMapping(value = "/special/delete")
    @ResponseBody
    public ModelAndView deteleSpecial(@RequestParam("id") String id) {

        Map<String, String> map = new HashMap<>();
        map.clear();

        SpecialEvent specialEvent = specialEventService.findById(Long.parseLong(id));

        return JsonView.returnJsonFromMap(specialEventService.delete(specialEvent));

    }

//    @PostMapping(value = "/special/edit")
//    @ResponseBody
//    public ModelAndView editOrcamento(@RequestParam("id") String id, @RequestParam("name") String name, @RequestParam("descricao") String descricao) {
//
//        Map<String, String> map = new HashMap<>();
//        map.clear();
//
//        SpecialEvent SpecialEvent = SpecialEventDao.findById(Long.parseLong(id));
//
//
//        if (SpecialEvent != null) {
//
//            SpecialEvent.setName(name);
//            SpecialEvent.setDescription(descricao);
//
//            SpecialEvent b = SpecialEventDao.save(SpecialEvent);
//
//            if (b == null) {
//                map.put("erro", ErrorMessages.ERROR_OPERATION.toString());
//            } else {
//                map.put("resultado", "Atualização realizada com sucesso.");
//            }
//        }
//        return JsonView.returnJsonFromMap(map);
//
//
//    }
//
//
//
//
//
//    @PostMapping(value = "/orcamento/delete")
//    @ResponseBody
//    public ModelAndView deteleOrcamento(@RequestParam("id") String id) {
//
//        Map<String, String> map = new HashMap<>();
//        map.clear();
//
//        SpecialEvent SpecialEvent = SpecialEventDao.findById(Long.parseLong(id));
//
//
//        if (SpecialEvent != null) {
//
//            SpecialEventDao.delete(SpecialEvent);
//
//            SpecialEvent = SpecialEventDao.findById(Long.parseLong(id));
//
//            if (SpecialEvent != null) {
//                map.put("erro", ErrorMessages.ERROR_OPERATION.toString());
//            } else {
//                map.put("resultado", "Exclusão realizada com sucesso.");
//            }
//        }
//        return JsonView.returnJsonFromMap(map);
//
//
//    }



}
