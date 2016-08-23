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
    INVALID_CNPJ("O CNPJ informado é inválido."),
    INVALID_LOGIN("Usuário não encontrado no sistema, verifique o e-mail e senha e tente novamente."),
    
    NOT_ENOUGH_RUBRIC_BALANCE("Não é possível excluir este crédito. Exclusão resultaria em saldo negativo"),
    NEGATIVE_ENTRY_AMOUNT("Registro com valor inválido."),
    
    EXISTENTE_USER("Já existe um usuário cadastrado com o email informado");
    
    String message;

    private ErrorMessages(String msg) {
        
        message = msg;
    }
    
    @Override
    public String toString(){
        
        return message;
    }
    
}
