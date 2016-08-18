/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entities;

import java.io.Serializable;
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

/**
 *
 * @author Natarajan Rodrigues && Victor Hugo
 */
@Entity
public class Activity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Presentation> presentations;
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<PaymentProposal> paymentProposals;

    public Activity() {
        
        presentations = new ArrayList<>();
        paymentProposals = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public boolean addPresentation(Presentation presentation){
        
        return presentations.add(presentation);
    }
    
    public boolean removePresentation(Presentation presentation){
        
        return presentations.remove(presentation);
    }

    public List<Presentation> getPresentations() {
        return Collections.unmodifiableList(presentations);
    }

    public void setPresentations(List<Presentation> presentations) {
        this.presentations = presentations;
    }
    
    public boolean addPaymentProposal(PaymentProposal paymentProposal){
        return paymentProposals.add(paymentProposal);
    }
    
    public boolean removePaymentProposal(PaymentProposal paymentProposal){
        return paymentProposals.remove(paymentProposal);
    }

    public List<PaymentProposal> getPaymentProposals() {
        return Collections.unmodifiableList(paymentProposals);
    }

    public void setPaymentProposals(List<PaymentProposal> paymentProposals) {
        this.paymentProposals = paymentProposals;
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
        final Activity other = (Activity) obj;

        return Objects.equals(this.id, other.id);
    }

}
