/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;

import io.github.siscultural.entities.Accomplishment;
import io.github.siscultural.entities.Contract;
import io.github.siscultural.model.AccomplishReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Natarajan Rodrigues
 */
public interface AccomplishmentRepository extends JpaRepository<Accomplishment, Long>{
    public Accomplishment findById(Long id);

    public List<Accomplishment> findAll();

    public List<Accomplishment> findByContract(Contract contract);

    public Accomplishment saveAndFlush(Accomplishment accomplishment);

    public void delete(Accomplishment accomplishment);

//    @Query (value = "select p.name as programa, count(a) as quantidade, sum(a.audience) as público " +
//            "from contract c join program p on c.program_id = p.id join accomplishment a on a.contract_id = c.id " +
//            "group by p.name")
//    public List<?> accomplishmentReports();

//    @Query (value = "select new io.github.siscultural.model.AccomplishReport (p.name, count(a), sum(a.audience) )" +
    @Query (value = "select p.name, count(a), sum(a.audience) " +
//            "from Contract c join Program p on c.program_id = p.id join Accomplishment a on a.contract_id = c.id " +
//            "from Contract c join Program p join Accomplishment a  " +
//            "from Contract c join fetch c.program p join fetch a.contract a)  " +
            "from Accomplishment a join fetch a.contract c join fetch c.program p  " +
            "group by p.name")
    public List<?> accomplishmentReports();

    @Query (value = "select p.name, count(a), sum(a.audience) " +
            "from Accomplishment a join fetch a.contract c join fetch c.program p  " +
//            "where a.dateTime between :startDate AND :finalDate" +
            "where a.dateTime between ?1 AND ?2 " +
            "group by p.name")
    public List<?> accomplishmentReports2(LocalDateTime startDate, LocalDateTime finalDate);
}
