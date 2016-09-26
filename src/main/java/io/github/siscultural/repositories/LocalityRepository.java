/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;

import io.github.siscultural.entities.Locality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author Natarajan Rodrigues
 */
public interface LocalityRepository extends JpaRepository<Locality, Long>{
    
    public Locality findById(Long id);

    public List<Locality> findAll();

    public Locality save(Locality locality);

    public void delete(Locality locality);
}
