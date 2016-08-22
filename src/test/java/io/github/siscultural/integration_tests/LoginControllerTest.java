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
import io.github.siscultural.controllers.LoginController;
import io.github.siscultural.enums.ErrorMessages;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
    TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DatabaseSetup(FunctionaryRepositoryIT.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = {FunctionaryRepositoryIT.DATASET})
@DirtiesContext
@ActiveProfiles("scratch")
//@Ignore
public class LoginControllerTest {

    @Autowired
    private LoginController loginController;
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.standaloneSetup(
                loginController)
                .build();
    }

    @Test
    public void getLogin() throws Exception {
        mvc.perform(post("/login").accept(MediaType.APPLICATION_JSON).param("email", "email@email.com").param("password", "123"))
                .andExpect(status().isOk());
    }
    
    @Test
    public void completeLogin() throws Exception{
        mvc.perform(post("/login").accept(MediaType.APPLICATION_JSON).param("email", "email@email.com").param("password", "123"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"redirect\":\"home\"}"));
    }

    @Test
    public void failLogin() throws Exception{
        mvc.perform(post("/login").accept(MediaType.APPLICATION_JSON).param("email", "email@email.com").param("password", "arebaba"))
                .andExpect(status().isOk())
                .andExpect(content().json("{\"error\":\""+ErrorMessages.INVALID_LOGIN+"\"}"));
    }
    
}
