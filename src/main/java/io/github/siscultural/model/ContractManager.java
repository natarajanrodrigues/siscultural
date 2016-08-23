/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.model;

import io.github.siscultural.entities.Contract;
import io.github.siscultural.entities.PaymentProposal;
import io.github.siscultural.entities.Program;
import io.github.siscultural.entities.RubricAccount;
import io.github.siscultural.exceptions.NotEnoughBalanceRubricException;
import java.math.BigDecimal;
//import io.github.siscultural.repositories.ContractRepository;
//import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Natarajan Rodrigues
 */
public class ContractManager {
    
    
    public static void addPaymentProposal(Contract contract, PaymentProposal paymentProposal) throws NotEnoughBalanceRubricException {
        
        Program programContract = contract.getProgram();
        
        RubricAccount rubricAccountProposed = paymentProposal.getRubricAccount();
        
        if (programContract != null && rubricAccountProposed != null) {
            if (programContract.containRubric(rubricAccountProposed.getRubric()) 
                    && rubricAccountProposed.getBalance().compareTo(paymentProposal.getAmount()) >= 0) {
                contract.addPaymentProposal(paymentProposal);
            } else {
                throw new NotEnoughBalanceRubricException("Não existe saldo insuficente para inserir esta proposta de pagamento.");
            }
        } 
    }
    
    
    
}
