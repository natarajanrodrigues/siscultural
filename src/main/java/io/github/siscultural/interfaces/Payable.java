/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.interfaces;

import java.util.List;
import io.github.siscultural.entities.Expense;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public interface Payable {
    
    List<Expense> getExpenses();
    
}
