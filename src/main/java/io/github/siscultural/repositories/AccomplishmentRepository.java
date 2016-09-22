/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;

import io.github.siscultural.entities.Accomplishment;
import io.github.siscultural.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author Natarajan Rodrigues
 */
public interface AccomplishmentRepository extends JpaRepository<Accomplishment, Long>{
    public Accomplishment findById(Long id);

    public List<Accomplishment> findAll();

    public List<Accomplishment> findByContract(Contract contract);

    public Accomplishment save(Accomplishment accomplishment);

    public void delete(Accomplishment accomplishment);
}
