/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@Controller
public class SimpleGetPagesController {

    @Autowired
    private HttpSession httpSession;

    @GetMapping("/")
    public ModelAndView index() {

        if (httpSession.getAttribute("functionary") == null) {

            return new ModelAndView("login");
        } else {

            return new ModelAndView("redirect:/home");
        }

    }

    @GetMapping("/home")
    public ModelAndView home() {

        return new ModelAndView("main_menu");
    }

    @GetMapping("/menu_orcamento")
    public ModelAndView menu_orcamento() {

        return new ModelAndView("menu_orcamento");
    }


}
