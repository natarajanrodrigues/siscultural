/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@Entity
public class PaymentProposal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private boolean isApproved;
    private BigDecimal amount;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private RubricAccount rubricAccount;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Entry> payments;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Functionary functionary;

    public PaymentProposal() {
    }

    public PaymentProposal(boolean isApproved, BigDecimal amount, RubricAccount rubricAccount, Functionary functionary) {

        this.isApproved = isApproved;
        this.amount = amount;
        this.rubricAccount = rubricAccount;
        this.payments = new ArrayList<>();
        this.functionary = functionary;
    }

    public boolean addEntry(Entry entry){
        return payments.add(entry);
    }
            
    public boolean removeEntry(Entry entry){
        return payments.remove(entry);
    }

    public Functionary getFunctionary() {
        return functionary;
    }

    public void setFunctionary(Functionary functionary) {
        this.functionary = functionary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isIsApproved() {
        return isApproved;
    }

    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public RubricAccount getRubricAccount() {
        return rubricAccount;
    }

    public void setRubricAccount(RubricAccount rubricAccount) {
        this.rubricAccount = rubricAccount;
    }

    public List<Entry> getPayments() {
        return Collections.unmodifiableList(payments);
    }

    public void setPayments(List<Entry> payments) {
        this.payments = payments;
    }

    public BigDecimal stillHaveToPay() {

        BigDecimal payed = new BigDecimal(0);

        payments.stream().forEach((entry) -> {

            payed.add(entry.getAmount());
        });

        return amount.subtract(payed);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.amount);
        hash = 43 * hash + Objects.hashCode(this.functionary);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PaymentProposal other = (PaymentProposal) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.amount, other.amount)) {
            return false;
        }

        return Objects.equals(this.functionary, other.functionary);
    }

}
