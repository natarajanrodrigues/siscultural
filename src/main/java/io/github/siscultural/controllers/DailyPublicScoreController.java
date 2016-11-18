package io.github.siscultural.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.siscultural.entities.Contract;
import io.github.siscultural.entities.DailyPublicScore;
import io.github.siscultural.services.DailyPublicScoreService;
import io.github.siscultural.utils.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.swing.text.DateFormatter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by natarajan on 01/11/16.
 */
@Controller
public class DailyPublicScoreController {

    @Autowired
    private DailyPublicScoreService dailyPublicScoreService;
    
    @GetMapping(value = "/publico")
    public ModelAndView publico() {

        ModelAndView mav = new ModelAndView("publico");
//        sessionStatus.setComplete();
//        List<Contract> contracts = contractService.findAll();
//
//        mav.addObject("contracts", contracts);

        return mav;

    }

    @RequestMapping(value = "/publico/populate", method= RequestMethod.POST)
    public ModelAndView popoulateCalendar(@RequestParam("initialDate") String initialDate, @RequestParam("finalDate") String finalDate, @RequestParam("text") String text) {

        Map<String, String> map = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        dailyPublicScoreService.populateDates(LocalDate.parse(initialDate, formatter), LocalDate.parse(finalDate, formatter), text);

        map.put("resultado", "Operação realizada com sucesso.");

        return JsonView.returnJsonFromMap(map);
    }

    @RequestMapping(value = "/publico/register", method= RequestMethod.POST)
    public ModelAndView popoulateRegister(@RequestParam("date") String date,
                                          @RequestParam(value = "closed", required = false) String closed,
                                          @RequestParam("text") String text) {

        Map<String, String> map = new HashMap<>();


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        DailyPublicScore dps = new DailyPublicScore();
        dps.setDate(LocalDate.parse(date, formatter));

        if (closed == null)
            dps.setOpenToVisitors(true);
        else
            dps.setOpenToVisitors(false);


        dailyPublicScoreService.save(dps);


        map.put("resultado", "Operação realizada com sucesso.");

        return JsonView.returnJsonFromMap(map);
    }


    @RequestMapping(value = "/publico/main_score", method= RequestMethod.POST)
    public ModelAndView addMainScore(@RequestParam("date") String date,
                                     @RequestParam("score") String score) {

        Map<String, String> map = new HashMap<>();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        DailyPublicScore dps = dailyPublicScoreService.findByDate(LocalDate.parse(date, formatter));

        if (dps == null)
            map.put("erro", "Data não encontrada.");
        else {
            dps.setMainPublicScore(Integer.parseInt(score));
        }

        dailyPublicScoreService.save(dps);

        map.put("resultado", "Operação realizada com sucesso.");

        return JsonView.returnJsonFromMap(map);
    }

}
