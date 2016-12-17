package io.github.siscultural.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.siscultural.model.AccomplishReport;
//import io.github.siscultural.repositories.AccomplishmentReportRepository;
import io.github.siscultural.repositories.AccomplishmentRepository;
import io.github.siscultural.services.DailyPublicScoreService;
import io.github.siscultural.utils.DailyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by natarajan on 15/03/16.
 */
@RestController
public class ReportRestController {

//    @Autowired
//    private AccomplishmentReportRepository accomplishmentReportRepository;

    @Autowired
    private AccomplishmentRepository accomplishmentRepository;

    @RequestMapping("/report")
    public List<?> report() throws JsonProcessingException {
        return accomplishmentRepository.accomplishmentReports();
//        return accomplishmentRepository.accomplishmentReports2(LocalDateTime.of(2016,12,13,00,00), LocalDateTime.of(2016,12,17,00,00));
    }

    @RequestMapping("/report2")
    public List<?> report2(@RequestParam("inicialDate") String inicialDate, @RequestParam("finalDate") String finalDate) throws JsonProcessingException {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        LocalDateTime inicialDateConverted = LocalDateTime.parse(inicialDate, formatter);
        LocalDateTime finalDateConverted = LocalDateTime.parse(finalDate, formatter);

        return accomplishmentRepository.accomplishmentReports2(inicialDateConverted, finalDateConverted);
    }



}
