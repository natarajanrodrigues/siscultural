/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.unit_tests;

import io.github.siscultural.entities.Locality;
import io.github.siscultural.validator.PresentationValidator;
import org.hibernate.validator.HibernateValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.parsing.Location;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 *
 * @author Natarajan Rodrigues
 */
public class LocationValidatorTest {

    private LocalValidatorFactoryBean localValidatorFactory;
    private Locality locality;


    @Before
    public void setup() {

        localValidatorFactory = new LocalValidatorFactoryBean();

        localValidatorFactory.setProviderClass(HibernateValidator.class);

        localValidatorFactory.afterPropertiesSet();

        locality = new Locality();

        locality.setAddress("Rua Cel José Gomes de Sá, 7, Centro");
        locality.setCity("Sousa");
        locality.setDescription("CCBNB-Sousa");
        locality.setState("PB");

    }

    @Test
    public void noValidationError() {

        Set<ConstraintViolation<Locality>> constraintViolations = localValidatorFactory.validate(locality);

        Assert.assertTrue(constraintViolations.size() == 0);

    }

    @Test
    public void erroSetDuration(){

        locality.setState(null);
        Set<ConstraintViolation<Locality>> constraintViolations = localValidatorFactory.validate(locality);
        Assert.assertFalse(constraintViolations.size() == 0);

    }


}
