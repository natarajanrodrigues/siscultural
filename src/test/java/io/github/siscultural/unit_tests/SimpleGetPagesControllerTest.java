/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.integration_tests;

import io.github.siscultural.controllers.SimpleGetPagesController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext
public class SimpleGetPagesControllerTest {

    @Autowired
    private SimpleGetPagesController simpleGetPagesController;

    private MockMvc mvc;

    @Before
    public void setUp() throws Exception {

        mvc = MockMvcBuilders.standaloneSetup(
                simpleGetPagesController)
                .build();
    }

    /**
     * Test of index method, of class SimpleGetPagesController.
     */
    @Test
    public void testIndex() throws Exception {

        ModelAndView mav;
        mav = mvc.perform(get("/")).andExpect(status().isOk())
                                   .andReturn().getModelAndView();
        
        
        ModelAndViewAssert.assertViewName(mav, "login");
    }

    /**
     * Test of home method, of class SimpleGetPagesController.
     */
    @Test
    public void testHome() throws Exception {

        ModelAndView mav;
        mav = mvc.perform(get("/home")).andExpect(status().isOk())
                                   .andReturn().getModelAndView();
        
        ModelAndViewAssert.assertViewName(mav, "main_menu");
    }

}
