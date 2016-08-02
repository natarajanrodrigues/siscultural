/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entity.validator;

import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 *
 * @author Natarajan Rodrigues
 */
@Ignore
public class CpfValidatorImplTest {

    private CpfValitadorImpl validator;
    
    @Before
    public void setUpTests(){
        validator = new CpfValitadorImpl();
    }
    
    @AfterClass
    public static void afterTests(){
        System.out.println("Testes de validador de CNPJ concluídos.");
    }
    
    @Test(expected = InvalidCpfException.class)
    public void testarObjetoNulo() throws InvalidCpfException {
        validator.isValid(null);
    }
    
    @Test
    public void validarTamanho() throws InvalidCpfException {
        assertEquals(false, validator.isValid("007980214"));
        assertEquals(false, validator.isValid(""));
        assertEquals(false, validator.isValid("007980214111"));
        assertEquals(true, validator.isValid("00798021411"));
        assertEquals(true, validator.isValid("007.980.214-11"));
    }
    
    @Test
    public void validarFormato() throws InvalidCpfException {
        
        assertEquals(true, validator.isValid("00798021411"));
        assertEquals(true, validator.isValid("007.980.214-11"));
        
    }
    
    @Test
    public void testarCPFsValidos() throws InvalidCpfException  {
        
        assertEquals(true, validator.isValid("007.980.214-11"));
        
        
    }
    
    
}
