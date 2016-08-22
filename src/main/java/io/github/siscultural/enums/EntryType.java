/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.enums;

/**
 *
 * @author Natarajan Rodrigues
 */
public enum EntryType {
    CREDIT(1), DEBIT(2);
    
    int id;
    
    EntryType (int n) {
        id = n;
    }

    public int getId() {
        return id;
    }
    
}
