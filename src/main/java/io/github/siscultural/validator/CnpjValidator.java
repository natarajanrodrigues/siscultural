/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.validator;

import io.github.siscultural.exceptions.InvalidCnpjException;

/**
 *
 * @author Natarajan Rodrigues
 */
public interface CnpjValidator {
        
        Boolean isValid(String cnpj) throws InvalidCnpjException;
    
}
