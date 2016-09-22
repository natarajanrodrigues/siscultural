/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.Presentation;
import io.github.siscultural.entities.Program;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.repositories.ProgramRepository;
import io.github.siscultural.services.PresentationService;
import io.github.siscultural.utils.JsonView;
import io.github.siscultural.validator.PresentationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
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

    @GetMapping(value = "/apresentacao")
    public ModelAndView apresentacao(SessionStatus sessionStatus) {

        ModelAndView mav = new ModelAndView("apresentacao");
        sessionStatus.setComplete();
        List<Presentation> presentations = presentationService.findAll();

        mav.addObject("presentations", presentations);

        return mav;

    }

    @RequestMapping(value = "/apresentacao_add", method=RequestMethod.GET)
    public ModelAndView apresentacaoAdd(Presentation presentation) {
        ModelAndView modelAndView = new ModelAndView("presentations/apresentacao_add");
        modelAndView.addObject("presentation", presentation);
//        return "presentations/apresentacao_add";
        return modelAndView;
    }

    @RequestMapping(value="/apresentacao_add", method=RequestMethod.POST)
    public ModelAndView cadastrarNovoTopico(@Validated @ModelAttribute ("presentation") Presentation presentation, BindingResult result,
                                            RedirectAttributes redirectAttributes, Model model, SessionStatus sessionStatus) {

        String name = presentation.getName();
        String groupOrArtist = presentation.getGroupOrArtist();
        String releaseText = presentation.getReleaseText();
        int duration = presentation.getDuration();

        model.addAttribute("name", name);
        model.addAttribute("groupOrArtist", groupOrArtist);
        model.addAttribute("releaseText", releaseText);
        model.addAttribute("duration", duration);

        ModelAndView modelAndView = new ModelAndView("presentations/apresentacao_add");

        if (result.hasErrors()) {

//            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult." + presentation, );

            return new ModelAndView("presentations/apresentacao_add");
        } else {


            Presentation presetation = presentationService.save(presentation);

            if (presentation != null) {

//                modelAndView.getModel().put("succes", true);
                sessionStatus.setComplete();
                modelAndView = new ModelAndView("redirect:/apresentacao");
//                modelAndView.addObject("id", topic.getId());

            }

        }

        return modelAndView;
    }


    @RequestMapping(value = "/apresentacao_edit", method=RequestMethod.GET)
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

        Presentation presentation = presentationService.findById(Long.parseLong(id));

        return JsonView.returnJsonFromMap(presentationService.delete(presentation));

    }


}
