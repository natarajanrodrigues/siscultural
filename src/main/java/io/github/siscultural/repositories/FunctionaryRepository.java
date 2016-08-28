/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;

import io.github.siscultural.entities.Functionary;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */

public interface FunctionaryRepository extends JpaRepository<Functionary, Long>{

    public Functionary findByEmail(String email);

    public Functionary findById(Long id);

    public List<Functionary> findByEmailAndPassword(String email, String password);

    public List<Functionary> findAll();

    public Functionary save(Functionary functionary);

    public Functionary saveAndFlush(Functionary functionary);

    public void delete(Functionary functionary);
        
}
