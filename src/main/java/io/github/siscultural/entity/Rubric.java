/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entity;

import io.github.siscultural.entity.payment.exception.NotEnoughBalanceRubricException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author susanneferraz
 */
class Rubric {
    
    private int id;
    private BigDecimal total;
    private BigDecimal applied; //valor gasto. total dos valores do pagamentos
    private BigDecimal balance; //saldo = total - applied
    
    private String name;
    
    private Program program;
    
    private List<Payment> payments;

    public Rubric(int id, BigDecimal total, String name, Program program) {
        this.id = id;
        this.total = total;
        this.name = name;
        this.program = program;
        this.payments = new ArrayList<>();
    }

    public Rubric() {
        this.payments = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }
    
    
    public BigDecimal getApllied() {
        BigDecimal totalApplied = new BigDecimal(0);
        
        for (Payment p : getPayments()){
            totalApplied.add(p.getValue());
        }
        return totalApplied;
    }

    public BigDecimal getBalance() {
        return this.total.subtract(getApllied());
    }    

    public boolean addPayment(Payment p) throws NotEnoughBalanceRubricException{
        boolean result = false;
        
        if ( getBalance().equals(new BigDecimal(0)) || p.getValue().compareTo(getBalance()) == 1 ) {
        
            throw new NotEnoughBalanceRubricException("A rubrica especificada não contém saldo para registrar este pagamento.");
        
        } else {
            
            result = this.payments.add(p);
            
        }
        
        return result;
    }
    
    public boolean removePayment(Payment p) {
        
        return this.payments.remove(p);
        
    }
    
    
     
}
