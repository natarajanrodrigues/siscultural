package io.github.siscultural.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.siscultural.entities.DailyPublicScore;
import io.github.siscultural.services.DailyPublicScoreService;
import io.github.siscultural.utils.DailyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
