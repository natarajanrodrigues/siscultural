/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entities;

import io.github.siscultural.enums.EntryType;
import io.github.siscultural.enums.ErrorMessages;
import io.github.siscultural.enums.NatureAccounting;
import io.github.siscultural.exceptions.PaymentExceedsApprovedValueException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Natarajan Rodrigues && Victor Hugo
 */
@Entity
public class Entry implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private LocalDateTime dateTime;
    private BigDecimal amount;
    private String description;
    @Enumerated(EnumType.STRING)
    private EntryType entryType;
    @Enumerated(EnumType.STRING)
    private NatureAccounting natureAccouting;
    @OneToOne(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Functionary responsibleFunctionary;

    public Entry() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return dateTime;
    }

    public void setDate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) throws PaymentExceedsApprovedValueException {

        if (amount.signum() < 0) {
            throw new PaymentExceedsApprovedValueException(ErrorMessages.NEGATIVE_ENTRY_AMOUNT.toString());
        }

        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public EntryType getEntryType() {
        return entryType;
    }

    public void setEntryType(EntryType entryType) {
        this.entryType = entryType;
    }

    public NatureAccounting getNatureAccouting() {
        return natureAccouting;
    }

    public void setNatureAccouting(NatureAccounting natureAccouting) {
        this.natureAccouting = natureAccouting;
    }

    public Functionary getResponsibleFunctionary() {
        return responsibleFunctionary;
    }

    public void setResponsibleFunctionary(Functionary responsibleFunctionary) {
        this.responsibleFunctionary = responsibleFunctionary;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.dateTime);
        hash = 79 * hash + Objects.hashCode(this.description);
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
        final Entry other = (Entry) obj;
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.dateTime, other.dateTime)) {
            return false;
        }
        
        return Objects.equals(this.amount, other.amount);
    }

    @Override
    public String toString() {
        return "Entry{" + "id=" + id + ", date=" + dateTime + ", value=" + amount + ", description=" + description + ", entryType=" + entryType + ", natureAccouting=" + natureAccouting + ", responsibleFunctionary=" + responsibleFunctionary + '}';
    }

}
