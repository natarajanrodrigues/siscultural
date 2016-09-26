/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.controllers;

import io.github.siscultural.entities.Budget;
import io.github.siscultural.entities.Program;
import io.github.siscultural.entities.Rubric;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.repositories.ProgramRepository;
import io.github.siscultural.repositories.RubricRepository;
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
public class RubricaController {

    @Autowired
    private RubricRepository rubricRepository;
    @Autowired
    private ProgramRepository programRepository;

    @GetMapping(value = "/rubrica")
    public ModelAndView rubrica() {

        ModelAndView mav = new ModelAndView("rubrica");

        List<Rubric> rubrics = rubricRepository.findAll();

        mav.addObject("rubrics", rubrics);
        mav.addObject("programs", programRepository.findAll());

        return mav;
    }

    @PostMapping(value = "/rubrica/add")
    public ModelAndView addRubric(@RequestParam("name") String name, @RequestParam("descricao") String descricao, @RequestParam("programa") String programa) {

        ModelAndView mav = new ModelAndView("redirect:/rubrica");


        Program program = programRepository.findById(Long.parseLong(programa));
        Rubric newRubric = new Rubric();

        newRubric.setName(name);
        newRubric.setDescription(descricao);
        program.addRubric(newRubric);

        programRepository.save(program);
//        rubricRepository.save(newRubric);

        return mav;

    }

    @RequestMapping(value = "/rubrica/edit", method= RequestMethod.POST)
    public ModelAndView rubricaEdit(@RequestParam("id") String id, @RequestParam("nome") String nome, @RequestParam("descricao") String descricao, @RequestParam("programa") String programaId) {

        ModelAndView mav = new ModelAndView("redirect:/rubrica");

        Rubric rubric = rubricRepository.findById(Long.parseLong(id));

        Program program = programRepository.findById(Long.parseLong(programaId));


        rubric.setName(nome);
        rubric.setDescription(descricao);
        rubric.getProgram().removeRubric(rubric);

        program.addRubric(rubric);

        programRepository.save(program);

        return mav;
    }

    @RequestMapping(value = "/rubrica/delete", method=RequestMethod.POST)
    public ModelAndView accomplishmentDelete(@RequestParam("id") String id) {

        Map<String, String> map = new HashMap<>();
        map.clear();

        Rubric rubric = rubricRepository.findById(Long.parseLong(id));


        if (rubric != null) {

            rubric.getProgram().removeRubric(rubric);

            rubricRepository.delete(rubric);

            rubric = rubricRepository.findById(Long.parseLong(id));

            if (rubric != null) {
                map.put("erro", ErrorMessages.ERROR_OPERATION.toString());
            } else {
                map.put("resultado", "Exclusão realizada com sucesso.");
            }
        }
        return JsonView.returnJsonFromMap(map);

    }


}
