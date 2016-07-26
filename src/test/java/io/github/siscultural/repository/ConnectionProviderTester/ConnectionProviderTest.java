package io.github.siscultural.repository.ConnectionProviderTester;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import io.github.siscultural.repository.ConnectionProvider;
import java.sql.Connection;
import java.sql.SQLException;
import javax.validation.constraints.AssertTrue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public class ConnectionProviderTest {
    
    public ConnectionProviderTest() {
    }
    
    @Ignore
    @Test
    public void testNullConnection() throws SQLException{
        
        ConnectionProvider connectionProvider = ConnectionProvider.getInstance();
        
        Connection connection = connectionProvider.getConnection();
        
        assertFalse(connection.isClosed());
    }
    
}
