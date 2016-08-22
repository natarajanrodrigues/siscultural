/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.utils;

import java.util.Map;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public class JsonView {

    public static ModelAndView returnJsonFromMap(Map<String, String> modelMap) {

        MappingJackson2JsonView jsonConverter = new MappingJackson2JsonView();

        ModelAndView mav = new ModelAndView(jsonConverter);
        mav.addAllObjects(modelMap);

        return mav;
    }
}
