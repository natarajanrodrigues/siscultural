/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.unit_tests;

import io.github.siscultural.entities.Entry;
import io.github.siscultural.entities.RubricAccount;
import io.github.siscultural.enums.EntryType;
import io.github.siscultural.enums.NatureAccounting;
import io.github.siscultural.exceptions.NotEnoughBalanceRubricException;
import io.github.siscultural.exceptions.PaymentExceedsApprovedValueException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Natarajan Rodrigues
 */
public class RubricAccountTest {

    private RubricAccount ra1;
    private Entry entryCredit500;
    private Entry entryPayment400;
    private Entry entryPayment500;

    public RubricAccountTest() {
        
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws PaymentExceedsApprovedValueException {
        ra1 = new RubricAccount();
        
        //creating aport entry with value 500
        entryCredit500 = new Entry();
        entryCredit500.setId(1);
        entryCredit500.setAmount(new BigDecimal("500"));
        entryCredit500.setDate(LocalDateTime.now());
        entryCredit500.setNatureAccouting(NatureAccounting.APORTE);
        entryCredit500.setEntryType(EntryType.CREDIT);
        
        //creating payment entry with value 600
        entryPayment400 = new Entry();
        entryPayment400.setId(2);
        entryPayment400.setAmount(new BigDecimal(400));
        entryPayment400.setDate(LocalDateTime.now());
        entryPayment400.setNatureAccouting(NatureAccounting.PAGAMENTO);
        entryPayment400.setEntryType(EntryType.DEBIT);
        
        //creating payment entry with value 500
        entryPayment500 = new Entry();
        entryPayment500.setId(3);
        entryPayment500.setAmount(new BigDecimal(500));
        entryPayment500.setDate(LocalDateTime.now());
        entryPayment500.setNatureAccouting(NatureAccounting.PAGAMENTO);
        entryPayment500.setEntryType(EntryType.DEBIT);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void allowAddingEntryWithEnoughBalance() throws PaymentExceedsApprovedValueException, NotEnoughBalanceRubricException {
        ra1.addEntry(entryCredit500);
        assertEquals(new BigDecimal(500), ra1.getBalance());
    }
    
    @Test
    public void allowAddingPaymentEntryWithEnoughBalance() throws PaymentExceedsApprovedValueException, NotEnoughBalanceRubricException {
        ra1.addEntry(entryCredit500);
        ra1.addEntry(entryPayment400);
    }
    
    
    @Test (expected = NotEnoughBalanceRubricException.class)
    public void preventNegativeBalanceByRemoveEntryCauses() throws PaymentExceedsApprovedValueException, NotEnoughBalanceRubricException {
        ra1.addEntry(entryCredit500);
        ra1.addEntry(entryPayment400);
        ra1.removeEntry(entryCredit500);
    }
    
    @Test (expected = NotEnoughBalanceRubricException.class)
    public void preventAddingEntryWithouEnoughBalance() throws PaymentExceedsApprovedValueException, NotEnoughBalanceRubricException {    
        ra1.addEntry(entryCredit500);
        ra1.addEntry(entryPayment500);
        ra1.addEntry(entryPayment400);
    }
    
}
