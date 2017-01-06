/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;

import io.github.siscultural.entities.Committe;
import io.github.siscultural.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author Natarajan Rodrigues
 */
public interface CommitteRepository extends JpaRepository<Committe, Long>{

    public Committe findById(Long id);

    public List<Committe> findAll();

    public Committe save(Committe committe);

    public void delete(Committe committe);

}
