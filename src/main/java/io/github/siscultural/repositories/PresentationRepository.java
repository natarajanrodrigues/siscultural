/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;

import io.github.siscultural.entities.Presentation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author Natarajan Rodrigues
 */
public interface PresentationRepository extends JpaRepository<Presentation, Long>{
    
    public Presentation findById(long id);

    public Page<Presentation> findAll(Pageable pageble);

    public Page<Presentation> findByNameContainingIgnoreCase(String name, Pageable pageable);
    
}
