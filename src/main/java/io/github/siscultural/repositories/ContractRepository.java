/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;

import io.github.siscultural.entities.Accomplishment;
import io.github.siscultural.entities.Committe;
import io.github.siscultural.entities.Contract;
import io.github.siscultural.entities.Presentation;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 * @author Natarajan Rodrigues
 */
public interface ContractRepository extends JpaRepository<Contract, Long>{

//    @Query("SELECT c FROM Contract c JOIN FETCH c.accomplishments WHERE c.id = (:id)")
//    public Contract findById(@Param("id") Long id);

    public Contract findById(Long id);

    public List<Contract> findAll();

    public Contract save(Contract contract);

    public void delete(Contract contract);

    public List<Contract> findByPresentation(Presentation presentation);

    public List<Contract> findByCommitte(Committe committe);

}
