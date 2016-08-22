/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entities;

import javax.persistence.Entity;

/**
 * Classe que representa fornecedores do tipo Pessoa Física
 * @author Natarajan Rodrigues && Victor Hugo
 */
@Entity
public class IndividualProvider extends Provider{
    
    private String cpf;
    private String nisNit;

    public IndividualProvider() {
    }

    public IndividualProvider(String cpf, String nisNit, String name, String address, String city, String state, Phone phone) {
        
        super(name, address, city, state, phone);
        this.cpf = cpf;
        this.nisNit = nisNit;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNisNit() {
        return nisNit;
    }

    public void setNisNit(String nisNit) {
        this.nisNit = nisNit;
    }
    
}
