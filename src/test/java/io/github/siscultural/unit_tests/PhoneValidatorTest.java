/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.unit_tests;

import io.github.siscultural.entities.Locality;
import io.github.siscultural.entities.Phone;
import org.hibernate.validator.HibernateValidator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import java.util.Set;

/**
 *
 * @author Natarajan Rodrigues
 */
public class PhoneValidatorTest {

    private LocalValidatorFactoryBean localValidatorFactory;
    private Phone phone;


    @Before
    public void setup() {

        localValidatorFactory = new LocalValidatorFactoryBean();

        localValidatorFactory.setProviderClass(HibernateValidator.class);

        localValidatorFactory.afterPropertiesSet();

        phone = new Phone();

        phone.setAreaCode("83");
        phone.setPhoneNumber("993033144");

    }

    @Test
    public void noValidationError() {

        Set<ConstraintViolation<Phone>> constraintViolations = localValidatorFactory.validate(phone);

        Assert.assertTrue(constraintViolations.size() == 0);

    }

    @Test
    public void erroSetDuration(){

        phone.setPhoneNumber(null);
        Set<ConstraintViolation<Phone>> constraintViolations = localValidatorFactory.validate(phone);
        Assert.assertFalse(constraintViolations.size() == 0);

    }


}
