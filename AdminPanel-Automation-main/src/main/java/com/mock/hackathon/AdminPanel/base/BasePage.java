package com.mock.hackathon.AdminPanel.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.mock.hackathon.AdminPanel.factory.DriverFactory;
import com.mock.hackathon.AdminPanel.utils.ConfigReader;
import com.mock.hackathon.AdminPanel.utils.WaitUtils;

public class BasePage {

    protected WebDriver driver;
    protected WaitUtils waitUtils;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
        this.waitUtils = new WaitUtils(driver,
                Integer.parseInt(ConfigReader.getProperty("timeout")));
    }

    public WebElement waitForElement(By locator) {
        return waitUtils.waitForVisibility(locator);
    }

    public void click(By locator) {

        WebElement element = waitUtils.waitForClickable(locator);

        // Scroll to center (better than true)
        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

        try {
            element.click();
        } catch (Exception e) {
            // Fallback JS click
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
    }

    public void type(By locator, String value) {
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    public String getText(By locator) {
        return waitForElement(locator).getText();
    }
}