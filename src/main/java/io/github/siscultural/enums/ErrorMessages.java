/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.enums;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public enum ErrorMessages {
    
    EMPTY_CPF("Campo CPF nulo ou vazio."),
    EMPTY_CNPJ("Campo CNPJ nulo ou vazio."),
    
    INVALID_CPF("O CPF informado é inválido."),
    INVALID_CNPJ("O CNPJ informado é inválido.");
    
    String message;

    private ErrorMessages(String msg) {
        
        message = msg;
    }
    
    public String getValue(){
        
        return message;
    }
    
}
