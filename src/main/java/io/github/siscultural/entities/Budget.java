    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Comparator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Representa um determinado orçamento contendo as rubricas para pagamentos. 
 * @author Natarajan Rodrigues && Victor Hugo
 */
@Entity
public class Budget implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
//    private LocalDateTime creationDateTime;
    

    public Budget() {

    }

    public Budget(String name, LocalDateTime dateTime) {
        this.name = name;
//        this.creationDateTime = dateTime;
    }
    
    public Budget(String name) {
        
        this.name = name;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class Comparators {
        public static final Comparator<Budget> ID_COMPARE = (Budget b1, Budget b2) -> Long.compare(b1.getId(), b2.getId());
    }
}
