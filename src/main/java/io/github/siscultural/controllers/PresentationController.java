/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.Presentation;
import io.github.siscultural.services.ContractService;
import io.github.siscultural.services.PresentationService;
import io.github.siscultural.utils.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Natarajan
 */
@Controller
@SessionAttributes("presentation")
public class PresentationController {

    @Autowired
    PresentationService presentationService;
    @Autowired
    ContractService contractService;

    @GetMapping(value = "/apresentacao")
    public ModelAndView apresentacao(SessionStatus sessionStatus) {

        ModelAndView mav = new ModelAndView("apresentacao");
        sessionStatus.setComplete();
        List<Presentation> presentations = presentationService.findAll();

        mav.addObject("presentations", presentations);

        return mav;

    }

    @RequestMapping(value = "/apresentacao2")
    public ModelAndView apresentacao2(Pageable pageable) {

        ModelAndView mav = new ModelAndView("apresentacao");

        Page<Presentation> list = presentationService.findAllPagable(pageable);


        mav.addObject("presentations", list.getContent());
        mav.addObject("pagination", pageable.getPageNumber());


        return mav;

    }

    @RequestMapping(value = "/presentation/search")
    public ModelAndView search(@RequestParam("name") String name, Pageable pageable, HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("apresentacao");

        Pageable myPageable = new PageRequest(pageable.getPageNumber(), 10, new Sort(new Sort.Order(Sort.Direction.ASC, "name")));

        Page<Presentation> list = presentationService.findByName(name, myPageable);

        mav.addObject("name", name);
        mav.addObject("presentations", list);
        mav.addObject("pagination", pageable.getPageNumber());

        mav.addObject("path", request.getServletPath());


        return mav;

    }




    @RequestMapping(value = "/apresentacao_add", method = RequestMethod.GET)
    public ModelAndView apresentacaoAdd(Presentation presentation) {
        ModelAndView modelAndView = new ModelAndView("presentations/apresentacao_add");
        modelAndView.addObject("presentation", presentation);
        return modelAndView;
    }

    @RequestMapping(value = "/apresentacao_add", method = RequestMethod.POST)
    public ModelAndView cadastrarNovoTopico(@Validated @ModelAttribute("presentation") Presentation presentation, BindingResult result,
            RedirectAttributes redirectAttributes, Model model, SessionStatus sessionStatus) {

        String name = presentation.getName();
        String artist = presentation.getArtist();
        String releaseText = presentation.getReleaseText();
        int duration = presentation.getDuration();

        model.addAttribute("name", name);
        model.addAttribute("artist", artist);
        model.addAttribute("releaseText", releaseText);
        model.addAttribute("duration", duration);

        ModelAndView modelAndView = new ModelAndView("presentations/apresentacao_add");

        if (result.hasErrors()) {

//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + presentation, );
//            return new ModelAndView("presentations/apresentacao_add");
            return modelAndView;

        } else {

            Presentation presetation = presentationService.save(presentation);

            sessionStatus.setComplete();
            modelAndView.addObject("presentation", presentation);
            modelAndView = new ModelAndView("redirect:/apresentacao_details?id=" + presentation.getId());

//            sessionStatus.setComplete();
//            modelAndView = new ModelAndView("redirect:/apresentacao");

        }

        return modelAndView;
    }

    @RequestMapping(value = "/apresentacao_edit", method = RequestMethod.GET)
    public ModelAndView apresentacaoEdit(@RequestParam("id") String id) {
        Presentation presentation = presentationService.findById(Long.parseLong(id));
        ModelAndView modelAndView = new ModelAndView("presentations/apresentacao_add");
        modelAndView.addObject("presentation", presentation);
        return modelAndView;
    }


    @PostMapping(value = "/apresentacao/delete")
    @ResponseBody
    public ModelAndView deteleOrcamento(@RequestParam("id") String id) {

        Map<String, String> map = new HashMap<>();
        map.clear();
        System.out.println("entrei...");
        System.out.println(id);
        Presentation presentation = presentationService.findById(Long.parseLong(id));

        return JsonView.returnJsonFromMap(presentationService.delete(presentation));

    }

    @RequestMapping(value = "/apresentacao_details", method = RequestMethod.GET)
    public ModelAndView apresentacaoDetail2(@RequestParam("id") String id, SessionStatus sessionStatus) {
        sessionStatus.setComplete();

        ModelAndView modelAndView = new ModelAndView("presentations/apresentacao_details");

        if (id != null) {
            Presentation presentation = presentationService.findById(Long.parseLong(id));
            modelAndView.addObject("presentation", presentation);
            modelAndView.addObject("detailsContracts", contractService.findByPresentation(presentation));
        }

        return modelAndView;
    }



}
