package io.github.siscultural.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by natarajan on 05/01/17.
 */
@Entity
public class Committe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Size(min = 1, max = 1000, message = "Não pode ser nulo. Valor máximo - 1000 caracteres")
    @NotNull(message = "Insira a descrição do comitê")
    private String description;

    private LocalDate date;

//    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, mappedBy = "committe", fetch=FetchType.EAGER )
//    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch=FetchType.LAZY )
    @OneToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch=FetchType.EAGER )
    @JsonIgnore
    @JoinColumn(name="committe_id")
    private List<Contract> contracts;


    public Committe() {
        this.contracts = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void addContract(Contract contract) {

        contract.setCommitte(this);
        this.contracts.add(contract);

    }

    public boolean removeContract(Contract contract) {

//        contract.setCommitte(null);
        return this.contracts.remove(contract);

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Committe committe = (Committe) o;

        if (getId() != null ? !getId().equals(committe.getId()) : committe.getId() != null) return false;
        if (getDescription() != null ? !getDescription().equals(committe.getDescription()) : committe.getDescription() != null)
            return false;
        if (getDate() != null ? !getDate().equals(committe.getDate()) : committe.getDate() != null) return false;
        return getContracts() != null ? getContracts().equals(committe.getContracts()) : committe.getContracts() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 31 * result + (getContracts() != null ? getContracts().hashCode() : 0);
        return result;
    }
}
