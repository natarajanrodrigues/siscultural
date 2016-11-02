package io.github.siscultural.services;

import io.github.siscultural.entities.DailyPublicScore;
import io.github.siscultural.repositories.DailyPublicScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

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


}
