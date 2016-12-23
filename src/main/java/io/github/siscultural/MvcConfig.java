/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@Configuration
@Controller
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/").setViewName("main_menu");
        registry.addViewController("/home").setViewName("main_menu");
        registry.addViewController("/menu_atividades").setViewName("menu_atividades");
        registry.addViewController("/menu_orcamento").setViewName("menu_orcamento");
        registry.addViewController("/fornecedores/fornecedor_fisico").setViewName("fornecedores/fornecedor_fisico");
        registry.addViewController("/fornecedores/fornecedor_juridico").setViewName("fornecedores/fornecedor_juridico");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler("/resources/**")
                .addResourceLocations("/resources/")
                .setCachePeriod(31556926);
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {

        ModelAndView mav = new ModelAndView("login");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.isAuthenticated()
                && !(authentication instanceof AnonymousAuthenticationToken)) {

            mav.setViewName("redirect:/home");
        }

        return mav;
    }

}
