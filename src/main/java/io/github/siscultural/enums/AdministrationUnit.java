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
public enum AdministrationUnit {

    SOUSA("CCBNB-Sousa"),

    CARIRI("CCBNB-Cariri"),

    FORTALEZA("CCBNB-Fortaleza"),

    CELULA("Célula de Cultura"),

    AMBIENTE ("Ambiente de Marketing");

    String name;

    AdministrationUnit(String unitName) {
        name = unitName;
    }

    public String getName(){
        return name;
    }
    
    
}
