/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entities;

import javax.persistence.*;

/**
 * Classe que representa uma fornecedor do tipo Pessoa Jurídica.
 * @author Natarajan Rodrigues && Victor Hugo
 */
@Entity
public class CompanyProvider extends Provider{
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(unique=true)
    private String  cnpj;
    private String  tradingName; // Nome fantasia

    public CompanyProvider() {
    }

    @Override
    public String getCode() {
        return this.cnpj;
    }

    public CompanyProvider(String cnpj, String tradingName, String name, String address, String city, String state, Phone phone) {
    
        super(name, address, city, state, phone);
        this.cnpj = cnpj;
        this.tradingName = tradingName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }

    @Override
    public String toString() {
        return "CompanyProvider{" + "cnpj=" + cnpj + ", tradingName=" + tradingName + '}';
    }
    
}
