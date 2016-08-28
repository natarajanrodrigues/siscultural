/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;

import io.github.siscultural.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author Natarajan
 */

public interface OrcamentoRepository extends JpaRepository<Budget, Long>{

    public List<Budget> findAll();

    public Budget save(Budget budget);

//    public Budget update(Budget budget);
//
//    public void remove(Budget budget);
        
}
