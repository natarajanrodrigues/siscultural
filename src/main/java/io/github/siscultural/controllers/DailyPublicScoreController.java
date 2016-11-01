package io.github.siscultural.controllers;

import io.github.siscultural.entities.Contract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by natarajan on 01/11/16.
 */
@Controller
public class DailyPublicScoreController {

    @GetMapping(value = "/publico")
    public ModelAndView publico() {

        ModelAndView mav = new ModelAndView("publico");
//        sessionStatus.setComplete();
//        List<Contract> contracts = contractService.findAll();
//
//        mav.addObject("contracts", contracts);

        return mav;

    }
}
