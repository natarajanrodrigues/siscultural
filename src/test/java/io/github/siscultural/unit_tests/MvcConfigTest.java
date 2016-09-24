/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.unit_tests;

import io.github.siscultural.MvcConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@EnableWebMvc
@WebAppConfiguration
@DirtiesContext
@ContextConfiguration(classes = MvcConfig.class)
public class MvcConfigTest {

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {

        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }

    /**
     * Test of index method, of class SimpleGetPagesController.
     */
    @Test
    public void testIndex() throws Exception {

        mvc.perform(get("/")).andExpect(status().isOk())
                .andReturn().getModelAndView();

    }

    /**
     * Test of home method, of class SimpleGetPagesController.
     */
    @Test
    public void testHome() throws Exception {

        mvc.perform(get("/home")).andExpect(status().isOk())
                .andReturn().getModelAndView();

    }

}
