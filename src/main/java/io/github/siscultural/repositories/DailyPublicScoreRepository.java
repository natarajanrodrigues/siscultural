package io.github.siscultural.repositories;

import io.github.siscultural.entities.DailyPublicScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Query (value=
            "select cast (cast (extract(month from d.date) as integer) as varchar) as mês, sum (d.mainPublicScore) " +
            "from DailyPublicScore d " +
            "where d.date between ?1 and ?2 " +
            "group by extract(month from d.date)" +
                    "order by mês asc")
    public List<?> getMonthPublicScore(LocalDate inicialDate, LocalDate finalDate);

}
