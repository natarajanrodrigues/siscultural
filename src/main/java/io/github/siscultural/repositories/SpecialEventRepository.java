/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;

import io.github.siscultural.entities.SpecialEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author Natarajan
 */

public interface SpecialEventRepository extends JpaRepository<SpecialEvent, Long>{

    public SpecialEvent findById(Long id);

    public List<SpecialEvent> findAll();

    public SpecialEvent save(SpecialEvent specialEvent);

    public void delete(SpecialEvent specialEvent);
        
}
