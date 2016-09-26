/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.repositories;

import io.github.siscultural.entities.Contract;
import io.github.siscultural.entities.PaymentProposal;
import io.github.siscultural.entities.Presentation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 *
 * @author Natarajan Rodrigues
 */
public interface PaymentProposalRepository extends JpaRepository<PaymentProposal, Long>{
    
    public PaymentProposal findById(Long id);

    public PaymentProposal save(PaymentProposal paymentProposal);

    public List<PaymentProposal> findAll();

    public List<PaymentProposal> findByContract(Contract contract);

    public void delete(PaymentProposal paymentProposal);
    
}
