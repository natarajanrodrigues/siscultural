/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entity;

import javax.persistence.Entity;

/**
 * Classe que representa uma fornecedor do tipo Pessoa Jurídica.
 * @author Natarajan Rodrigues && Victor Hugo
 */
@Entity
public class CompanyProvider extends Provider{
    
    private String  cnpj;
    private String  companyName; //Razão Social
    private String  tradingName; // Nome fantasia

    public CompanyProvider() {
    }

    public CompanyProvider(String cnpj, String companyName, String tradingName, String name, String address, String city, String state, Phone phone) {
    
        super(name, address, city, state, phone);
        this.cnpj = cnpj;
        this.companyName = companyName;
        this.tradingName = tradingName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTradingName() {
        return tradingName;
    }

    public void setTradingName(String tradingName) {
        this.tradingName = tradingName;
    }
    
}
