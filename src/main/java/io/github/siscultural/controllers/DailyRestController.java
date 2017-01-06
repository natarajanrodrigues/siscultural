package io.github.siscultural.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.siscultural.entities.DailyPublicScore;
import io.github.siscultural.services.DailyPublicScoreService;
import io.github.siscultural.utils.ContractUtils;
import io.github.siscultural.utils.DailyDTO;
import io.github.siscultural.utils.LocalDateToStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Created by natarajan on 02/11/16.
 */
@RestController
public class DailyRestController {

    @Autowired
    private DailyPublicScoreService dailyPublicScoreService;

    @RequestMapping("/getAllJson")
    public List<DailyDTO> greeting() throws JsonProcessingException {
        return dailyPublicScoreService.allDailyToJson();
    }

    @RequestMapping("/getAll")
    public List<?> greeting2() throws JsonProcessingException {
        return dailyPublicScoreService.findAll();
    }

    @RequestMapping("/getMonth")
    public List<?> greeting3(@RequestParam("inicialDate") String inicialDate, @RequestParam("finalDate") String finalDate) throws JsonProcessingException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        LocalDate inicialDateConverted = LocalDate.parse(inicialDate, formatter);
        LocalDate finalDateConverted = LocalDate.parse(finalDate, formatter);
//        return dailyPublicScoreService.getMonthScore(LocalDate.of(2016, 11, 1), LocalDate.of(2016, 11, 30));
        return dailyPublicScoreService.getMonthScore(inicialDateConverted, finalDateConverted);
    }

    @RequestMapping("/scorePerDate")
    public int getScoreByDate(@RequestParam("date") String date) {
        DailyPublicScore dps = dailyPublicScoreService.findByDate(LocalDate.parse(date, LocalDateToStringConverter.formatter));
        return dps.getMainPublicScore();
    }

    @RequestMapping("/scorePerYear")
    public List<?> getScoreByYear(@RequestParam("year") String year) throws JsonProcessingException {
        try {
            return dailyPublicScoreService.getYearScore(year);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
