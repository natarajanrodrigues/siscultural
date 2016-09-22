/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;

import io.github.siscultural.entities.IndividualProvider;
import io.github.siscultural.entities.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@Repository
public interface IndividualProviderRepository  extends JpaRepository<IndividualProvider, Long>{
    
    public Presentation findById(long id);
    
}
