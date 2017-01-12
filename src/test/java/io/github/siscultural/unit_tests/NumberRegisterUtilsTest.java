/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.unit_tests;

import io.github.siscultural.entities.Entry;
import io.github.siscultural.exceptions.PaymentExceedsApprovedValueException;
import io.github.siscultural.utils.NumberRegisterUtils;
import org.junit.*;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Natarajan Rodrigues
 */
public class NumberRegisterUtilsTest {

    public NumberRegisterUtilsTest() {
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


    @Test
    public void formatCPF() {

        String unformattedCPF = "00798021411";
        String result = NumberRegisterUtils.formatCode(unformattedCPF);
        System.out.println(result);

        Assert.assertEquals("007.980.214-11", result);


    }

    @Test
    public void formatCNPJ() {

        String unformattedCNPJ = "07237373000120";
        String result = NumberRegisterUtils.formatCode(unformattedCNPJ);
        System.out.println(result);

        Assert.assertEquals("07.237.373/0001-20", result);


    }

}
