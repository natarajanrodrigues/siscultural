/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Natarajan Rodrigues
 */
public enum NatureAccounting {
    
    PAGAMENTO(1, new ArrayList<>(Arrays.asList(EntryType.DEBIT))),
    
    TRANSFERÊNCIA(2, new ArrayList<>(Arrays.asList(EntryType.DEBIT, EntryType.CREDIT))), 
    
    APORTE(30, new ArrayList<>(Arrays.asList(EntryType.CREDIT)));
    
    int id;
    List<EntryType> entryTypes;
    
    NatureAccounting (int n, List<EntryType> types) {
        id = n;
        entryTypes = types;
    }

    public int getId() {
        return id;
    }
    
    public List<EntryType> getEntryTypes(){
        return entryTypes;
    }
    
    
}
