package io.github.siscultural.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.*;
import io.github.siscultural.entities.DailyPublicScore;
import io.github.siscultural.repositories.DailyPublicScoreRepository;
import io.github.siscultural.utils.DailyDTO;
import io.github.siscultural.utils.JsonView;
import io.github.siscultural.utils.LocalDateSerializer;
import io.github.siscultural.utils.LocalDateToStringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by natarajan on 01/11/16.
 */
@Service
@Transactional
public class DailyPublicScoreService {

    @Autowired
    private DailyPublicScoreRepository dailyPublicScoreRepository;

    @Autowired
    public DailyPublicScoreService(DailyPublicScoreRepository dailyPublicScoreRepository) {
        this.dailyPublicScoreRepository = dailyPublicScoreRepository;
    }

    public DailyPublicScore findById(Long id) { return dailyPublicScoreRepository.findById(id); }

    public DailyPublicScore findByDate(LocalDate date) { return dailyPublicScoreRepository.findByDate(date); }

    public DailyPublicScore save(DailyPublicScore dps) { return dailyPublicScoreRepository.save(dps);}

    public Map<String, String> save2(DailyPublicScore dps, int score) {

        Map<String, String> map = new HashMap<>();

        if (dps == null)
            map.put("erro", "Data não encontrada.");
        else {
            if (!dps.isOpenToVisitors()) {
                map.put("erro", "Nesta data o Centro Cultural não está aberto ao público.");
            } else {
                dps.setMainPublicScore(score);
                dailyPublicScoreRepository.save(dps);
            }
        }

        map.put("resultado", "Operação realizada com sucesso.");

        return map;
    }

    public void delete (DailyPublicScore dps){ dailyPublicScoreRepository.delete(dps); }

    public List<DailyPublicScore> findAll(){ return dailyPublicScoreRepository.findAll(); }

    /**
     * Creates DailyPublicScores between the given dates (including them) axnd set the openingReason. Automatically sets sundays and mondays with OpentoVisitors = false;
     * Be careful: this function can OVERWRITE existing DailyPublicScores and all its fields on DB.
     * @param initialDate the first DailyPublicScore' date to create/overwrite.
     * @param finalDate the last DailyPublicScore' date to create/overwrite.
     * @param openingReason the openin reason text.
     */
    public void populateDates(LocalDate initialDate, LocalDate finalDate, String openingReason) {

        if (initialDate.isBefore(finalDate)) {

            for (LocalDate i = initialDate; i.isBefore(finalDate.plusDays(1)); i = i.plusDays(1)) {

                DailyPublicScore newDaily = new DailyPublicScore();

                if (i.getDayOfWeek().equals(DayOfWeek.SUNDAY) || i.getDayOfWeek().equals(DayOfWeek.MONDAY))
                    newDaily.setOpenToVisitors(false);
                else
                    newDaily.setOpenToVisitors(true);

                newDaily.setDate(i);
                newDaily.setOpeningReasonText(openingReason);

                dailyPublicScoreRepository.save(newDaily);

            }
        }
    }

    public List<DailyDTO> allDailyToJson() throws JsonProcessingException {
        List<DailyPublicScore> all = dailyPublicScoreRepository.findAll();

//        Gson gson = new GsonBuilder()
//                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
//                .setDateFormat(DateFormat.FULL)
//                .create();
//        return gson.toJson(all);

        List<DailyDTO> list = new LinkedList<>();
        for (DailyPublicScore d : all) {
            list.add(new DailyDTO(d));
        }

//        return list.toString();
        return list;

    }

    public List<?> getMonthScore(LocalDate inicialDate, LocalDate finalDate) throws JsonProcessingException {
        return dailyPublicScoreRepository.getMonthPublicScore(inicialDate, finalDate);
    }

    public List<?> getYearScore(String year) throws JsonProcessingException {
        LocalDate inicialDate = LocalDate.parse("01/01/"+ year, LocalDateToStringConverter.formatter);
        LocalDate finalDate =   LocalDate.parse("31/12/"+ year, LocalDateToStringConverter.formatter);
        return dailyPublicScoreRepository.getPublicScoresBetween(inicialDate, finalDate);
    }


}
