/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;

import io.github.siscultural.entities.AdministrationUnity;
import io.github.siscultural.entities.Locality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author Natarajan Rodrigues
 */
public interface AdministrationUnityRepository extends JpaRepository<AdministrationUnity, Long>{
    
    AdministrationUnity findById(Long id);

    List<AdministrationUnity> findAll();

    List<AdministrationUnity> findByNameContainsIgnoreCase(String name);

}
