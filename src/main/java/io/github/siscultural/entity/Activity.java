/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entity;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Natarajan Rodrigues
 */
public class Activity {
    
    private int id;
    private List<ActivityPresentation> presentations;
    private Proposal proposal;
    private Program program;

    public Activity() {
        this.presentations = new ArrayList<>();
    }

    public Activity(int id, Proposal proposal, Program program) {
        this.id = id;
        this.proposal = proposal;
        this.program = program;
        this.presentations = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public void setProposal(Proposal proposal) {
        this.proposal = proposal;
    }

    public Program getProgram() {
        return program;
    }

    public void setProgram(Program program) {
        this.program = program;
    }
    
    public boolean addPresentation(ActivityPresentation presentation) {
        return this.presentations.add(presentation);
    }
    
    public boolean removePresentation(ActivityPresentation presentation) {
        return this.presentations.remove(presentation);
    }

    public List<ActivityPresentation> getPresentations() {
        return presentations;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + this.id;
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
