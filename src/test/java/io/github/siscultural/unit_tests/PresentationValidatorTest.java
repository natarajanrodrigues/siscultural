/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.unit_tests;

import io.github.siscultural.entities.Accomplishment;
import io.github.siscultural.entities.Presentation;
import io.github.siscultural.validator.PresentationValidator;
import org.hibernate.validator.HibernateValidator;
import org.junit.*;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 *
 * @author Natarajan Rodrigues
 */
public class PresentationValidatorTest {

    private LocalValidatorFactoryBean localValidatorFactory;
    private PresentationValidator presentationValidator;


    @Before
    public void setup() {

        localValidatorFactory = new LocalValidatorFactoryBean();

        localValidatorFactory.setProviderClass(HibernateValidator.class);

        localValidatorFactory.afterPropertiesSet();

        presentationValidator = new PresentationValidator();

        presentationValidator.setName("Oh, terrinha boa!");
        presentationValidator.setDuration(60);
        presentationValidator.setArtist("Grupo Arcoíris");
        presentationValidator.setReleaseText("Release fictício...");

    }

    @Test
    public void noValidationError() {

        Set<ConstraintViolation<PresentationValidator>> constraintViolations = localValidatorFactory.validate(presentationValidator);

        Assert.assertTrue(constraintViolations.size() == 0);

    }

    @Test
    public void erroSetDuration(){

        presentationValidator.setDuration(0);
        Set<ConstraintViolation<PresentationValidator>> constraintViolations = localValidatorFactory.validate(presentationValidator);
        Assert.assertFalse(constraintViolations.size() == 0);

    }


}
