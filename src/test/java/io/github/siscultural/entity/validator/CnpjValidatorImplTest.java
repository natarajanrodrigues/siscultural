/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entity.validator;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Natarajan Rodrigues
 */
public class CnpjValidatorImplTest {

    private CnpjValidatorImpl validator;

    @Before
    public void setUpTests() {
        validator = new CnpjValidatorImpl();
    }

    @AfterClass
    public static void afterTests() {
        System.out.println("Testes de validador de CNPJ encerrados.");
    }

    @Test(expected = InvalidCnpjException.class)
    public void testarObjetoNulo() throws InvalidCnpjException {
        validator.isValid(null);
    }

    
    @Test
    public void validarTamanho() throws InvalidCnpjException {
        assertEquals(false, validator.isValid("152715276512"));
        assertEquals(false, validator.isValid(""));
        assertEquals(false, validator.isValid("9999999"));
        assertEquals(true, validator.isValid("07237373000120"));
        assertEquals(true, validator.isValid("07.237.373/0001-20"));
    }
    
    @Test
    public void validarFormato() throws InvalidCnpjException {
        
        assertEquals(true, validator.isValid("07237373000120"));
        assertEquals(true, validator.isValid("07.237.373/0001-20"));
        assertEquals(false, validator.isValid("07237373000-120"));
        assertEquals(false, validator.isValid("07.237373/0001-20"));
    }
    
    @Test
    public void testarCNPJsValidos() throws InvalidCnpjException {
        
        assertEquals(true, validator.isValid("07.237.373/0001-20"));
        assertEquals(true, validator.isValid("10.446.347/0001-16"));
        assertEquals(true, validator.isValid("09.634.753/0001-23"));
        assertEquals(true, validator.isValid("03.515.668/0001-60"));
        
    }

	
}
