/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.unit_tests;

import io.github.siscultural.entities.Entry;
import io.github.siscultural.exceptions.PaymentExceedsApprovedValueException;
import java.math.BigDecimal;
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
public class EntryTest {
    
    public EntryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    
    /**
     * Test of setValue method, of class Entry.
     * @throws io.github.siscultural.core.exceptions.PaymentExceedsApprovedValueException
     */
    @Test(expected = PaymentExceedsApprovedValueException.class)
    public void testSetNegativeValue() throws PaymentExceedsApprovedValueException {
        System.out.println("setValue");
        BigDecimal value = new BigDecimal(-1000);
        Entry instance = new Entry();
        instance.setAmount(value);
        
    }
    
    /**
     * Test of setValue method, of class Entry.
     * @throws io.github.siscultural.core.exceptions.PaymentExceedsApprovedValueException
     */
    @Test
    public void testSetPositiveValue() throws PaymentExceedsApprovedValueException {
        System.out.println("setValue");
        BigDecimal value = new BigDecimal(2000);
        Entry instance = new Entry();
        instance.setAmount(value);
        assertEquals(instance.getAmount(), new BigDecimal("2000"));
        
    }

    
    
}
