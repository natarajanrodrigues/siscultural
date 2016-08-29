/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;

import io.github.siscultural.entities.Program;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author Natarajan Rodrigues
 */
public interface ProgramRepository extends JpaRepository<Program, Long>{
    
    public Program findById(long id);

    public List<Program> findAll();

    public Program save(Program program);

    public void delete(Program program);
}
