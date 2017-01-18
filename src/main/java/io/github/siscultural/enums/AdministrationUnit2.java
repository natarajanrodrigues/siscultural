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
public enum AdministrationUnit2 {

    SOUSA(1, "CCBNB-Sousa"),

    CARIRI(2, "CCBNB-Cariri"),

    FORTALEZA(3, "CCBNB-Fortaleza"),

    CELULA(4, "Célula de Cultura"),

    AMBIENTE (5, "Ambiente de Marketing");

    String name;
    int id;

    AdministrationUnit2(int unitId, String unitName) {
        id = unitId;
        name = unitName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public static AdministrationUnit2 parse(int id) {

        for (AdministrationUnit2 u : AdministrationUnit2.values()) {
            if (u.getId() == id)
                return u;
        }
        return null;
    }
}
