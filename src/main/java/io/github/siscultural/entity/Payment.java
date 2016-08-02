/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entity;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * 
 * @author Natarajan Rodrigues
 */
public class Payment {
    
    private int id; //pensar em mudar de int para outra representação, pois o número de pagamentos aumenta muito rápido
    private SpecifiedProvider provider; //fornecedor indicado
    private BigDecimal value;           
    private String[] notes;             // módulo de prestação de contas ou anotações???
    private String description;
    private LocalDate date;
    
    private Rubric rubric;             
    
    private Activity activity;  
    //um pagamento pode não estar ligado a uma activity. Ou seja, activity pode ser null
    //P.ex.: pagamentos administrativos, 
    //que não estão ligados a nenhuma apresentação. 

    
    //default constructor
    public Payment() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SpecifiedProvider getProvider() {
        return provider;
    }

    public void setProvider(SpecifiedProvider provider) {
        this.provider = provider;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String[] getNotes() {
        return notes;
    }

    public void setNotes(String[] notes) {
        this.notes = notes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Rubric getRubrica() {
        return rubric;
    }

    public void setRubrica(Rubric rubrica) {
        this.rubric = rubrica;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
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
        final Payment other = (Payment) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }    
    
}
