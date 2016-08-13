/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Natarajan Rodrigues && Victor Hugo
 */
@Entity
public class Rubric implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long        id;
    private String      name;
    private BigDecimal  totalBalance;

    public Rubric(BigDecimal total, String name) {
        
        this.id = id;
        this.totalBalance = total;
        this.name = name;
    }

    public Rubric() {
    }

    public void doDeposit(BigDecimal amountDeposited){
        
        totalBalance.add(amountDeposited);
    }
    
    public void doDraft(BigDecimal amountDrawee){
        
        totalBalance.subtract(amountDrawee);
    }

    public BigDecimal getCurrentBalance() {

        throw new UnsupportedOperationException();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(BigDecimal totalBalance) {
        this.totalBalance = totalBalance;
    }
    
}
