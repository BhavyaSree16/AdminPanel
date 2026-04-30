package com.mock.hackathon.AdminPanel.base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import com.mock.hackathon.AdminPanel.factory.DriverFactory;
import com.mock.hackathon.AdminPanel.utils.ConfigReader;

public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        DriverFactory.initDriver();
        driver = DriverFactory.getDriver();
        driver.get(ConfigReader.getProperty("baseUrl"));
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}