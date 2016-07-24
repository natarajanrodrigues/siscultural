    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.entity;

/**
 * Enum que representa os tipos de usuários do sistema
 * @author Natarajan Rodrigues
 */
enum UserType {
    
    ADMINISTRADOR(1), GERENTE(2), COLABORADOR(3);

    int id;
    
    UserType (int n) {
        id = n;
    }

    public int getId() {
        return id;
    }
    
}
