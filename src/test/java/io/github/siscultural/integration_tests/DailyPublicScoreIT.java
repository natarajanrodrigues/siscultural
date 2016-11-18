/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.integration_tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import io.github.siscultural.entities.DailyPublicScore;
import io.github.siscultural.entities.Entry;
import io.github.siscultural.exceptions.PaymentExceedsApprovedValueException;
import io.github.siscultural.services.DailyPublicScoreService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import javax.validation.constraints.AssertTrue;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.assertEquals;

/**
 *
 * @author Natarajan Rodrigues
 */
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class, DbUnitTestExecutionListener.class})
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
//@DatabaseSetup(DailyPublicScoreIT.DATASET)
//@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = { DailyPublicScoreIT.DATASET })
@DirtiesContext
//@ActiveProfiles("scratch")
public class DailyPublicScoreIT {

    protected static final String DATASET = "classpath:daily_test.xml";
    protected static final LocalDate diaDeLosMuertos = LocalDate.of(2016, Month.NOVEMBER, 2);

    @Autowired
    private DailyPublicScoreService dailyPublicScoreService;

    public DailyPublicScoreIT() {
    }

//    @Test
//    public void test() {
//
//        DailyPublicScore dailyPublicScore = dailyPublicScoreService.save(new DailyPublicScore(LocalDate.now(), false, 0, 0, null));
//        System.out.println(dailyPublicScore.isOpenToVisitors());
//        dailyPublicScore = dailyPublicScoreService.save(new DailyPublicScore(LocalDate.now(), true, 0, 0, null));
//        System.out.println(dailyPublicScore.isOpenToVisitors());
//
////        LocalDate now = LocalDate.now();
////        dailyPublicScoreService.populateDates(now, now.plusDays(2));
//
//    }

    @Test
    public void test() throws JsonProcessingException {

        System.out.println(dailyPublicScoreService.allDailyToJson());

    }

    @Test
    @DatabaseSetup(DailyPublicScoreIT.DATASET)
    public void testPopulateDates() {


        Assert.assertEquals(1, dailyPublicScoreService.findAll().size());
        dailyPublicScoreService.populateDates(diaDeLosMuertos.plusDays(1), diaDeLosMuertos.plusDays(3), "Centro Cultural");
        Assert.assertEquals(4, dailyPublicScoreService.findAll().size());

    }


//    @Test
//    @DatabaseSetup(DailyPublicScoreIT.DATASET)
//    public void noRepeatedDates() {
//        //carrega o dataset e confere se adicionou as informações - 1 (uma) linha
//        Assert.assertEquals(1, dailyPublicScoreService.findAll().size());
//
//        DailyPublicScore dailyPublicScore = dailyPublicScoreService.save(new DailyPublicScore(diaDeLosMuertos, false, 0, 0, null));
////        Assert.assertNull(dailyPublicScore);
//        Assert.assertEquals(1, dailyPublicScoreService.findAll().size());
//
////        Assert.assertEquals(2, dailyPublicScoreService.findAll().size());
//
//    }

    @Test
    @DatabaseSetup(DailyPublicScoreIT.DATASET)
    public void testFindByDate() {

        DailyPublicScore dailyPublicScore = dailyPublicScoreService.findByDate(diaDeLosMuertos);
        Assert.assertNotNull(dailyPublicScore);

    }

    @Test (expected = IllegalArgumentException.class)
    @DatabaseSetup(DailyPublicScoreIT.DATASET)
    public void testSetLibraryScore() {

        DailyPublicScore dailyPublicScore = dailyPublicScoreService.findByDate(diaDeLosMuertos);
        dailyPublicScore.setLibraryPublicScore(-1);


    }

    @Test (expected = IllegalArgumentException.class)
    @DatabaseSetup(DailyPublicScoreIT.DATASET)
    public void testSetMainScore() {

        DailyPublicScore dailyPublicScore = dailyPublicScoreService.findByDate(diaDeLosMuertos);
        dailyPublicScore.setMainPublicScore(-1);


    }


    
}
