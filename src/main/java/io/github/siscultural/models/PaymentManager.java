/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.models;

import io.github.siscultural.entities.Expense;
import io.github.siscultural.entities.Functionary;
import io.github.siscultural.entities.Payment;
import io.github.siscultural.entities.Rubric;
import io.github.siscultural.repositories.Dao;
import java.util.List;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public class PaymentManager {

    private Dao<Rubric> rubricDao;  
    
    public Payment pay(List<Rubric> rubrics, Functionary functionary, Expense expense){
        
        
        
        return null;
    }
    
}
