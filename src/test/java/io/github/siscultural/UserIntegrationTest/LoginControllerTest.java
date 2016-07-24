/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.UserIntegrationTest;

import io.github.siscultural.controller.LoginController;
import io.github.siscultural.entity.User;
import io.github.siscultural.entity.validator.CnpjValidatorImpl;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public class LoginControllerTest extends GenericDatabaseTestCase{
    
    private LoginController loginController;
    
    public LoginControllerTest() {
        
        
    }
    
    @Before
    public void setUpTests() {
        
        loginController = new LoginController();
    }

    @Test
    public void doLogin(){
        
        User user = loginController.login("email@email.com", "123");
        
        assertNotNull(user);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void doFailLogin(){
        
        User user = loginController.login("email@email.com", "321");
    }

    @Override
    public String getDataSetFile() {
        
        return "src/test/resources/user_test.xml";
    }
}
