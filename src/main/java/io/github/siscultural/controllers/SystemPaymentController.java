/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.Committe;
import io.github.siscultural.entities.Contract;
import io.github.siscultural.repositories.AccomplishmentRepository;
import io.github.siscultural.repositories.PaymentProposalRepository;
import io.github.siscultural.repositories.PresentationRepository;
import io.github.siscultural.repositories.ProgramRepository;
import io.github.siscultural.services.*;
import io.github.siscultural.utils.JsonView;
import io.github.siscultural.utils.SystemPaymentUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Natarajan
 */
@Controller
public class SystemPaymentController {


    @Autowired
    private SystemPaymentUtils systemPaymentUtils;


    @PostMapping("/upload_xls")
    @ResponseBody
    public String cadastrarNovoContrato(@RequestParam("file") MultipartFile multipartFile) {

//        ModelAndView mav = new ModelAndView("fornecedores");
        InputStream file = null;

        try {

            file = new ByteArrayInputStream(multipartFile.getBytes());

            systemPaymentUtils.updateTable(file);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }

        return "ok";
    }

    @RequestMapping("/status")
    @ResponseBody
    Map<String, String> cadastrarNovoContrato() {

        return Collections.singletonMap("total", new Double(SystemPaymentUtils.count()).toString());

    }




}
