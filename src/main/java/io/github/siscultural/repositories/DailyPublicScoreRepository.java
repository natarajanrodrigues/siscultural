package io.github.siscultural.repositories;

import io.github.siscultural.entities.DailyPublicScore;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by natarajan on 01/11/16.
 */
public interface DailyPublicScoreRepository extends JpaRepository<DailyPublicScore, Long> {

    public DailyPublicScore findById(Long id);

    public DailyPublicScore findByDate(LocalDate date);

    public DailyPublicScore save(DailyPublicScore dps);

    public void delete(DailyPublicScore dps);

    public List<DailyPublicScore> findAll();

}
