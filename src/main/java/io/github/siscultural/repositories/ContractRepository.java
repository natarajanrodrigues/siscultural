/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;

import io.github.siscultural.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Natarajan Rodrigues
 */
public interface ContractRepository extends JpaRepository<Contract, Long>{
    
}
