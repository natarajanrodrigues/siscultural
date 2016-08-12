/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entity;

/**
 * Classe que representa uma fornecedor do tipo Pessoa Jurídica.
 * @author Natarajan Rodrigues &&
 */
public class CorporationProvider extends SpecifiedProvider{
    
    private int     id;
    private String  cnpj;
    private String  companyName; //Razão Social
    private String  tradingName; // Nome fantasia

    public CorporationProvider() {
    }

    public CorporationProvider(String cnpj, String companyName, String tradingName) {
        this.cnpj = cnpj;
        this.companyName = companyName;
        this.tradingName = tradingName;
    }
    
}
