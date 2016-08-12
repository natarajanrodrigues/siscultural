/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entity;

import io.github.siscultural.interfaces.Payable;
import java.io.Serializable;
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
 * @author Natarajan Rodrigues && Victor Hugo
 */
@Entity
public class Activity implements Serializable, Payable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Proposal> proposals;

    public Activity() {
        
        proposals = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean addPresentation(Proposal proposal) {

        return proposals.add(proposal);
    }

    public boolean removePresentation(Proposal proposal) {
        
        return this.proposals.remove(proposal);
    }

    public List<Proposal> getPresentations() {
        
        return Collections.unmodifiableList(proposals);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + new Long(id).hashCode();
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
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

}
