/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa um determinado orçamento contendo as rubricas para pagamentos. 
 * @author susanneferraz
 */
public class Budget {
    private int id;
    private String name;
    private List<Program> programs;

    public Budget() {
        programs = new ArrayList<>();
    }

    public Budget(int id, String name) {
        this.id = id;
        this.name = name;
        programs = new ArrayList<>();
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
    
    public boolean addProgram (Program p) {
        return programs.add(p);
    }
    
    public boolean removePrograma(Program p){
        return programs.remove(p);
    }
    
    
}
