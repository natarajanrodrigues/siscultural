/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entity;

import java.time.LocalDateTime;

/**
 * 
 * @author Natarajan Rodrigues
 */
public class ActivityPresentation {
    
    private int id; //int ou uma representação de maior cardinalidade
    private LocalDateTime dateTime;
    private int audienceCount; //contagem de público na atividade. Inicial igual a 0
    private Locality locality;

    public ActivityPresentation() {
    }

    public ActivityPresentation(int id, LocalDateTime dateTime, Locality locality) {
        this.id = id;
        this.dateTime = dateTime;
        this.audienceCount = 0;
        this.locality = locality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getAudienceCount() {
        return audienceCount;
    }

    public void setAudienceCount(int audienceCount) {
        this.audienceCount = audienceCount;
    }

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
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
        final ActivityPresentation other = (ActivityPresentation) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    
    
}
