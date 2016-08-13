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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Representa um determinado orçamento contendo as rubricas para pagamentos. 
 * @author Natarajan Rodrigues && Victor Hugo
 */
@Entity
public class Budget implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long             id;
    private String          name;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH} )
    private List<Program>   programs;

    public Budget() {
        
        programs = new ArrayList<>();
    }

    public Budget(String name) {
        
        this.name = name;
        programs = new ArrayList<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public boolean addProgram (Program p) {
        
        return programs.add(p);
    }
    
    public boolean removePrograma(Program p){
        
        return programs.remove(p);
    }
    
    public List<Program> getPrograms(){
        
        return Collections.unmodifiableList(programs);
    }
    
    
}
