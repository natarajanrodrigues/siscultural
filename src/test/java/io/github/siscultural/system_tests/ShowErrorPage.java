/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.system_tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
public class ShowErrorPage {
    
    @Test
    public void showErrorPage() throws Exception {

        WebDriver driver = new FirefoxDriver();

        driver.get("http://localhost:8080/istoeoacrenomecxiste");

        String text = driver.findElements(By.tagName("h1")).get(0).getText();

        new WebDriverWait(driver, 500) {};

        Assert.assertEquals("Whitelabel Error Page", text);

        driver.close();
    }
    
}
