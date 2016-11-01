package io.github.siscultural.services;

import io.github.siscultural.entities.DailyPublicScore;
import io.github.siscultural.repositories.DailyPublicScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by natarajan on 01/11/16.
 */
@Service
@Transactional
public class DailyPublicScoreService {

    @Autowired
    private static DailyPublicScoreRepository dailyPublicScoreRepository;

    @Autowired
    public DailyPublicScoreService(DailyPublicScoreRepository dailyPublicScoreRepository) {
        this.dailyPublicScoreRepository = dailyPublicScoreRepository;
    }

    public DailyPublicScore findById(Long id) { return dailyPublicScoreRepository.findById(id); }

    public DailyPublicScore save(DailyPublicScore dps) { return dailyPublicScoreRepository.save(dps);}

    public void delete (DailyPublicScore dps){ dailyPublicScoreRepository.delete(dps); }



}
