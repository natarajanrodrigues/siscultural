/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.integration_tests;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import io.github.siscultural.repositories.FunctionaryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 *
 * @author Natarajan Rodrigues
 */
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
  TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DatabaseSetup(FunctionaryRepositoryIT.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { FunctionaryRepositoryIT.DATASET })
@DirtiesContext
public class FunctionaryRepositoryIT {
    
    protected static final String DATASET = "classpath:functionary_test.xml";
    
    @Autowired
    private FunctionaryRepository functionaryDao;
    
    @Test
    public void findUserByEmailAndPassword() {

        Assert.assertEquals(1, functionaryDao.findByEmailAndPassword("email@email.com", "123").size());
        
    }
    
    @Test
    public void NoUserWithInvalidPassword() {

        Assert.assertEquals(0, functionaryDao.findByEmailAndPassword("email@email.com", "").size());
        
    }
    
}
