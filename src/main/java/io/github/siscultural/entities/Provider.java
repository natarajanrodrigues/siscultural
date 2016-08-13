/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entities;

import io.github.siscultural.interfaces.Payable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Natarajan Rodrigues && Victor Hugo
 */
@MappedSuperclass
public abstract class Provider implements Serializable, Payable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String address;
    private String city;
    @Column(name = "home_state")
    private String state;
    @OneToOne(cascade = CascadeType.ALL)
    private Phone phone;
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Expense> expenses;

    public Provider() {

        expenses = new ArrayList<>();
    }

    public Provider(String name, String address, String city, String state, Phone phone) {

        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.phone = phone;
        expenses = new ArrayList<>();
    }

    @Override
    public List<Expense> getExpenses() {

        return Collections.unmodifiableList(expenses);
    }

    public void setExpenses(List<Expense> expenses) {
        this.expenses = expenses;
    }

    public boolean addExpense(Expense expense) {

        return expenses.add(expense);
    }

    public boolean removeExpense(Expense expense) {

        return expenses.remove(expense);
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

}
