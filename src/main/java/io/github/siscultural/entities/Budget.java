    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String description;
    private LocalDateTime creationDateTime;
    

    public Budget() {
        
    }

    public Budget(String name, LocalDateTime dateTime) {
        this.name = name;
        this.creationDateTime = dateTime;
    }
    
    public Budget(String name) {
        
        this.name = name;
        
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
    
}
