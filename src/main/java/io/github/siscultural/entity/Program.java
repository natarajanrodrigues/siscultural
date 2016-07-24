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
 * @author susanneferraz
 */
class Program {
    
    private int id;
    private String name;
    private List<Rubric> rubrics;

    public Program() {
        this.rubrics = new ArrayList<>();        
    }

    public Program(int id, String name) {
        this.id = id;
        this.name = name;
        this.rubrics = new ArrayList<>();        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Rubric> getRubrics() {
        return rubrics;
    }
    
    public boolean addRubric(Rubric r){
        return this.rubrics.add(r);
    }
    
    public boolean removeRubric(Rubric r){
        return this.rubrics.remove(r);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + this.id;
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
        final Program other = (Program) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
            
}
