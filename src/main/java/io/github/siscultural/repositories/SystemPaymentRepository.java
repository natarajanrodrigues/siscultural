/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;


import io.github.siscultural.entities.Rubric;
import io.github.siscultural.entities.SystemPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author Natarajan Rodrigues
 */
public interface SystemPaymentRepository extends JpaRepository<SystemPayment, BigInteger>{
    
    public SystemPayment findByNumPg(BigInteger numPg);

    public List<SystemPayment> findAll();

    public SystemPayment save(SystemPayment systemPayment);

    @Query (value = "select sum(sp.valor)  " +
            "from SystemPayment sp " +
            "where sp.cpfCnpj = ?1 ")
    public BigDecimal sumProvider(String cpfCnpj);

    
}
