/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Natarajan Rodrigues && Victor Hugo
 */
@Entity
public class Contract implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    
    @ManyToOne(optional = true, fetch = FetchType.EAGER)
    private Presentation presentation;
    
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Accomplishment> accomplishments;
    
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<PaymentProposal> paymentProposals;

    @ManyToOne
    private Program program;
    
    private LocalDate contractDate;

    public Contract() {

        accomplishments = new ArrayList<>();
        paymentProposals = new ArrayList<>();

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Presentation getPresentation() {
        return presentation;
    }

    public void setPresentation(Presentation presentation) {
        this.presentation = presentation;
    }
    
    public boolean addAccomplishments(Accomplishment accomplishment) {
        return accomplishments.add(accomplishment);
    }

    public boolean removeAccomplishments(Accomplishment accomplishment) {
        return accomplishments.remove(accomplishment);
    }

    public List<Accomplishment> getAccomplishments() {
        return Collections.unmodifiableList(accomplishments);
    }

    public void setAccomplishments(List<Accomplishment> accomplishments) {
        this.accomplishments = accomplishments;
    }

    public boolean addPaymentProposal(PaymentProposal paymentProposal) {
        return paymentProposals.add(paymentProposal);
    }

    public boolean removePaymentProposal(PaymentProposal paymentProposal) {
        return paymentProposals.remove(paymentProposal);
    }

    public List<PaymentProposal> getPaymentProposals() {
        return Collections.unmodifiableList(paymentProposals);
    }

    public void setPaymentProposals(List<PaymentProposal> paymentProposals) {
        this.paymentProposals = paymentProposals;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public LocalDate getContractDate() {
        return contractDate;
    }

    public void setContractDate(LocalDate contractDate) {
        this.contractDate = contractDate;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final Contract other = (Contract) obj;

        return Objects.equals(this.id, other.id);
    }

}
