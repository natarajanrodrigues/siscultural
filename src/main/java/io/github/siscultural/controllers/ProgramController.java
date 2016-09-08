/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.Program;
import io.github.siscultural.entities.Rubric;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.repositories.ProgramRepository;
import io.github.siscultural.utils.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Natarajan
 */
@Controller
public class ProgramController {

    @Autowired
    ProgramRepository programRepository;

    @GetMapping(value = "/programa")
    public ModelAndView programa() {

        ModelAndView mav = new ModelAndView("programa");

        List<Program> programs = programRepository.findAll();

        mav.addObject("programs", programs);

        return mav;

    }

    @PostMapping(value = "/programa/add")
    @ResponseBody
    public ModelAndView addProgram(@RequestParam("name") String name) {

        Map<String, String> map = new HashMap<>();

        Program program = new Program();
        program.setName(name);

        Program newProgram = programRepository.save(program);

        if (newProgram != null) {
            map.put("error", ErrorMessages.INVALID_LOGIN.toString());
        } else {
            map.put("resultado", "Operação realizada com sucesso.");
        }
        return JsonView.returnJsonFromMap(map);

    }

    @RequestMapping(value = "/programa/edit", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView editUsuario(@ModelAttribute("id") String id, @ModelAttribute("name") String name) {

        Map<String, String> map = new HashMap<>();

        Program program = programRepository.findById(Long.parseLong(id));

        if (program != null) {

            program.setName(name);

            program = programRepository.save(program);

            if (program != null) {
                map.put("error", ErrorMessages.ERROR_OPERATION.toString());
            } else {
                map.put("resultado", "Operação realizada com sucesso.");
            }

        } else {
            map.put("error", "Programa não encontrado.");
        }


        return JsonView.returnJsonFromMap(map);

    }

    @PostMapping(value = "/programa/delete")
    @ResponseBody
    public ModelAndView deteleOrcamento(@RequestParam("id") String id) {

        Map<String, String> map = new HashMap<>();
        map.clear();

        Program program = programRepository.findById(Long.parseLong(id));


        if (program != null) {

            programRepository.delete(program);

            program = programRepository.findById(Long.parseLong(id));

            if (program != null) {
                map.put("erro", ErrorMessages.ERROR_OPERATION.toString());
            } else {
                map.put("resultado", "Exclusão realizada com sucesso.");
            }
        } else {
            map.put("error", "Programa não encontrado.");
        }

        return JsonView.returnJsonFromMap(map);

    }


}
