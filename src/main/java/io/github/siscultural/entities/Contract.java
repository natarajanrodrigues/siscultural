/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.*;

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

    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch=FetchType.EAGER )
//    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch=FetchType.EAGER )
//    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "contract", fetch=FetchType.EAGER )
//    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE})
//    @OneToMany
    @JsonIgnore
    @JoinColumn(name="contract_id")
    private List<Accomplishment> accomplishments;


    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//    @PrivateOwned
    @JoinColumn(name="contract_id")
    private List<PaymentProposal> paymentProposals;

    @ManyToOne
    private Program program;

    @ManyToOne
    private SpecialEvent specialEvent;

    @ManyToOne
//    @JsonIgnore
//    @JoinColumn(name="committe_id")
    private Committe committe;
    
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

    public SpecialEvent getSpecialEvent() {
        return specialEvent;
    }

    public void setSpecialEvent(SpecialEvent specialEvent) {
        this.specialEvent = specialEvent;
    }

    public Committe getCommitte() {
        return committe;
    }

    public void setCommitte(Committe committe) {
        this.committe = committe;
    }

    public BigDecimal total (){
        BigDecimal soma = new BigDecimal(0);
        for (PaymentProposal p : paymentProposals) {
            soma = soma.add(p.getAmount());
        }

        return soma;
    }

//    @Override
//    public int hashCode() {
//        int hash = 5;
//        hash = 59 * hash + Objects.hashCode(this.id);
//        return hash;
//    }

//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj) {
//            return true;
//        }
//        if (obj == null) {
//            return false;
//        }
//        if (getClass() != obj.getClass()) {
//            return false;
//        }
//        final Contract other = (Contract) obj;
//
//        return Objects.equals(this.id, other.id);
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contract contract = (Contract) o;

        if (getId() != null ? !getId().equals(contract.getId()) : contract.getId() != null) return false;
        if (!getPresentation().equals(contract.getPresentation())) return false;
        if (getAccomplishments() != null ? !getAccomplishments().equals(contract.getAccomplishments()) : contract.getAccomplishments() != null)
            return false;
        if (getPaymentProposals() != null ? !getPaymentProposals().equals(contract.getPaymentProposals()) : contract.getPaymentProposals() != null)
            return false;
        if (getProgram() != null ? !getProgram().equals(contract.getProgram()) : contract.getProgram() != null)
            return false;
//        if (!getSpecialEvent().equals(contract.getSpecialEvent())) return false;
//        if (getCommitte() != null ? !getCommitte().equals(contract.getCommitte()) : contract.getCommitte() != null)
//            return false;
//        return getContractDate().equals(contract.getContractDate());

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getAccomplishments() != null ? getAccomplishments().hashCode() : 0);
        result = 31 * result + (getPaymentProposals() != null ? getPaymentProposals().hashCode() : 0);
        result = 31 * result + (getProgram() != null ? getProgram().hashCode() : 0);
        return result;
    }

    public static class Comparators {
        public static final Comparator<Contract> PRESENTATION_NAME = (Contract c1, Contract bc2) -> c1.getPresentation().getName().compareTo(bc2.getPresentation().getName());
        public static final Comparator<Contract> PRESENTATION_DATE = (Contract c1, Contract bc2) -> c1.getAccomplishments().get(0).getDateTime().compareTo(bc2.getAccomplishments().get(0).getDateTime());
    }
}
