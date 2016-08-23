/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.unit_tests;

import io.github.siscultural.entities.Contract;
import io.github.siscultural.entities.PaymentProposal;
import io.github.siscultural.entities.Program;
import io.github.siscultural.entities.Rubric;
import io.github.siscultural.entities.RubricAccount;
import io.github.siscultural.model.ContractManager;
import static io.github.siscultural.model.ContractManager.addPaymentProposal;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

/**
 *
 * @author Natarajan Rodrigues
 */
public class ContractManagerTest {
    
    private ContractManager cm;
    
    @Mock private Contract contract;
    @Mock private Program program;
    @Mock private Rubric rubric;
    @Mock private RubricAccount rubricAccount;
    @Mock private PaymentProposal paymentProposal;
    
    
    public ContractManagerTest() {
        
    }
    
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        
    }
    
    /**
     * Test of addPaymentProposal method, of class ContractManager.
     */
    @Test
    public void testAddPaymentProposal() throws Exception {
                
        paymentProposal.setRubricAccount(rubricAccount);
        
        when(rubricAccount.getRubric()).thenReturn(rubric);
        when(rubricAccount.getBalance()).thenReturn(new BigDecimal(1000)); 
        when(contract.getProgram()).thenReturn(program);
        when(paymentProposal.getAmount()).thenReturn(new BigDecimal(500));
        addPaymentProposal(contract, paymentProposal);
        
    }
    
}
