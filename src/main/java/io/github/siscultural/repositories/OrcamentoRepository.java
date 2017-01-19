/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;

import io.github.siscultural.entities.Budget;
import io.github.siscultural.enums.AdministrationUnit2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 *
 * @author Natarajan
 */

public interface OrcamentoRepository extends JpaRepository<Budget, Long>{

    public Budget findById(Long id);

    public List<Budget> findAll();

    public Budget save(Budget budget);

    public Budget saveAndFlush(Budget budget);

    public void delete(Budget budget);

    public List<Budget> findByCurrent(boolean current);

    List<Budget> findByUnit(int unit);

    List<Budget> findByCurrentAndUnit(boolean current, int unit);
        
}
