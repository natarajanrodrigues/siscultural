/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;


import io.github.siscultural.entities.Rubric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author Natarajan Rodrigues
 */
public interface RubricRepository extends JpaRepository<Rubric, Long>{
    
    public Rubric findById(long id);

    public List<Rubric> findAll();

    public Rubric save(Rubric rubric);

    
}
