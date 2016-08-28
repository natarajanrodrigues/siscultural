/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;


import io.github.siscultural.entities.Budget;
import io.github.siscultural.entities.Rubric;
import io.github.siscultural.entities.RubricAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author Natarajan Rodrigues
 */
public interface RubricAccountRepository extends JpaRepository<RubricAccount, Long>{
    
    public RubricAccount findById(long id);

    public RubricAccount findByBudgetAndRubric(Budget budget, Rubric rubric);

    public List<RubricAccount> findAll();

    public RubricAccount save(RubricAccount rubricAccount);

    
}
