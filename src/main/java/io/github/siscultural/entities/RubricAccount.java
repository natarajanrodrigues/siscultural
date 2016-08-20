/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entities;

import io.github.siscultural.enums.EntryType;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.exceptions.NotEnoughBalanceRubricException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Natarajan Rodrigues && Victor Hugo
 */
@Entity
public class RubricAccount implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Rubric rubric;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Budget budget;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY)
    private List<Entry> entries;

    public RubricAccount() {
    }

    public RubricAccount(Rubric rubric, Budget budget) {
        
        this.rubric = rubric;
        this.budget = budget;
        this.entries = new ArrayList<>();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Rubric getRubric() {
        return rubric;
    }

    public void setRubric(Rubric rubric) {
        this.rubric = rubric;
    }

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }

    public boolean addEntry(Entry entry) throws NotEnoughBalanceRubricException {

        BigDecimal currentBalance = getBalance();

        if (entry.getEntryType().equals(EntryType.DEBIT)) {

            if (currentBalance.compareTo(entry.getAmount()) < 0) {
                throw new NotEnoughBalanceRubricException("Não há saldo para registrar esse débito: " + entry.toString() + "\nSaldo: " + currentBalance);
            }

        }

        if (!this.entries.contains(entry)) {
            return this.entries.add(entry);
        }

        return false;
    }

    public boolean removeEntry(Entry entry) throws NotEnoughBalanceRubricException {

        if (entry.getEntryType().equals(EntryType.CREDIT)
                && entry.getAmount().compareTo(getBalance()) > 0) {

            throw new NotEnoughBalanceRubricException(ErrorMessages.NOT_ENOUGH_RUBRIC_BALANCE.toString());
        }

        if (this.entries.contains(entry)) {

            return entries.remove(entry);
        }

        return false;
    }

    public BigDecimal getBalance() {

        BigDecimal result = new BigDecimal(0);

        for (Entry entry : getEntries()) {
            BigDecimal entryValue = entry.getAmount();
            if (entry.getEntryType().equals(EntryType.DEBIT)) {
                entryValue = entryValue.negate();
            }
            result.add(entryValue);
        }

        return result;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.id);
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
        final RubricAccount other = (RubricAccount) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }

        return Objects.equals(this.rubric, other.rubric);
    }

}
