/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.unit_tests;

import io.github.siscultural.entities.Accomplishment;
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
public class AccomplishmentTest {
    
    public AccomplishmentTest() {
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
     * Test of setAudience method, of class Accomplishment.
     */
    @Test (expected = IllegalArgumentException.class)
    public void testSetAudienceNegative() {
        System.out.println("setAudience");
        int audience = -1;
        Accomplishment instance = new Accomplishment();
        instance.setAudience(audience);

    }
    
}
