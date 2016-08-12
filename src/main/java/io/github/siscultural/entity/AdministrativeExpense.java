/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entity;

import io.github.siscultural.interfaces.Payable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@Entity
public class AdministrativeExpense extends Expense implements Payable, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Expense> expenses;

    public AdministrativeExpense() {
        
        super();
        expenses = new ArrayList<>();
    }

    public AdministrativeExpense(String description, LocalDateTime expenseDate, BigDecimal cost) {
        
        super(description, expenseDate, cost);
    }
    
    public boolean addExpense(Expense expense) {

        return expenses.add(expense);
    }

    public boolean removeExpense(Expense expense) {

        return expenses.remove(expense);
    }

    @Override
    public List<Expense> getExpenses() {

        return Collections.unmodifiableList(expenses);
    }

    public void setExpenses(List<Expense> expenses) {
        
        this.expenses = expenses;
    }

}
