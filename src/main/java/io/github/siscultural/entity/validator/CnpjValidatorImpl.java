/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entity.validator;

import br.com.caelum.stella.ValidationMessage;
import br.com.caelum.stella.validation.CNPJValidator;
import java.util.List;

/**
 *
 * @author Natarajan Rodrigues
 */
public class CnpjValidatorImpl implements CnpjValidator{

    private CNPJValidator validator;

    public CnpjValidatorImpl() {
        this.validator = new CNPJValidator();
    }
    

    @Override
    public Boolean isValid(String cnpj) throws InvalidCnpjException {
        
        if (cnpj == null) {
            throw new InvalidCnpjException("Número de CNPJ nulo ou não informado");
        }
        
        List<ValidationMessage> validaCNPJ = validator.invalidMessagesFor(cnpj);
        
        if (validaCNPJ.isEmpty()) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }
    
}
