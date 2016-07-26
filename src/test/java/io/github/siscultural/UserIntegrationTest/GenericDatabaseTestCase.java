/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.UserIntegrationTest;

import java.io.FileInputStream;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@Ignore
public abstract class GenericDatabaseTestCase {

    private IDatabaseTester databaseTester;

    @Before
    public void defaultSetUp() throws Exception {
        IDataSet dataSet = new FlatXmlDataSetBuilder().build(new FileInputStream((getDataSetFile())));
        databaseTester = new JdbcDatabaseTester("org.postgresql.Driver",
                "jdbc:postgresql://localhost:5432/Siscultural", "postgres", "123456");
        databaseTester.setDataSet(dataSet);
        databaseTester.setSetUpOperation(DatabaseOperation.INSERT);
        databaseTester.setTearDownOperation(DatabaseOperation.DELETE);
        databaseTester.onSetup();
    }

    public abstract String getDataSetFile();

    @After
    public void defaultTearDown() throws Exception {
        databaseTester.onTearDown();
    }

}
