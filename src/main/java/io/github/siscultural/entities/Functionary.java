/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entities;

import io.github.siscultural.enums.AdministrationUnit;
import io.github.siscultural.enums.AdministrationUnit2;
import io.github.siscultural.enums.UserType;
import java.io.Serializable;
import java.util.Comparator;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Natarajan Rodrigues && Victor Hugo
 */
@Entity
public class Functionary implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long        id;
    private String      name;
    private String      password;
    private String      email;
    @Enumerated(EnumType.STRING)
    private UserType    userType;

    @Column(name = "unit_id")
    private int unit;

    public Functionary() {
    }

    public Functionary(String name, String password, String email, UserType userType) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.userType = userType;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public AdministrationUnit2 getUnit() {
        return AdministrationUnit2.parse(this.unit);
    }

    public void setUnit(AdministrationUnit2 administrationUnit) {
        this.unit = administrationUnit.getId();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + Objects.hashCode(this.id);
        hash = 61 * hash + Objects.hashCode(this.name);
        hash = 61 * hash + Objects.hashCode(this.email);
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
        final Functionary other = (Functionary) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        
        return Objects.equals(this.id, other.id);
    }

    public static class Comparators {
        public static final Comparator<Functionary> NAME = (Functionary f1, Functionary f2) -> f1.getName().compareTo(f2.getName());
    }

}
