/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@Entity
public class Expense implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String description;
    private LocalDateTime expenseDate;
    private BigDecimal cost;
    private boolean itsPaid;

    public Expense() {
        
        itsPaid = false;
    }

    public Expense(String description, LocalDateTime expenseDate, BigDecimal cost, boolean itsPaid) {
        
        this.description = description;
        this.expenseDate = expenseDate;
        this.cost = cost;
        this.itsPaid = itsPaid;
    }

    public boolean isItsPaid() {
        return itsPaid;
    }

    public void setItsPaid(boolean itsPaid) {
        this.itsPaid = itsPaid;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getExpenseDate() {
        return expenseDate;
    }

    public void setExpenseDate(LocalDateTime expenseDate) {
        this.expenseDate = expenseDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }
    
}
