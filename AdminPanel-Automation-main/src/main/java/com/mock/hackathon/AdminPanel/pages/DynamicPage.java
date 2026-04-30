package com.mock.hackathon.AdminPanel.pages;

import org.openqa.selenium.By;

import com.mock.hackathon.AdminPanel.base.BasePage;

public class DynamicPage extends BasePage {

    // Navigation
    private By challengesLink = By.id("m-challenges");
    private By syncLink = By.xpath("//a[@href='/challenges/synchronization/']");
    private By dynamicButtonsLink = By.xpath("//a[@href='/challenges/synchronization/dynamic-buttons-01/']");

    // Buttons
    private By startBtn = By.id("button00");
    private By buttonOne = By.id("button01");
    private By buttonTwo = By.id("button02");
    private By buttonThree = By.id("button03");

    // Result
    private By resultText = By.id("buttonmessage");

    // Navigation
    public void navigateToDynamicButtons() {

        waitUtils.waitForPageLoad();

        click(challengesLink);

        waitUtils.waitForVisibility(syncLink);
        click(syncLink);

        waitUtils.waitForVisibility(dynamicButtonsLink);
        click(dynamicButtonsLink);

        waitUtils.waitForPageLoad();
    }

    // ✅ NEW: Only trigger start
    public void clickStart() {
        click(startBtn);
    }

    // ✅ NEW: Check dynamic visibility
    public boolean isButtonOneVisible() {
        return waitUtils.waitForVisibility(buttonOne).isDisplayed();
    }

    // ✅ NEW: Step-by-step interaction (optional for clarity)
    public void clickButtonOne() {
        waitUtils.waitForVisibility(buttonOne);
        click(buttonOne);
    }

    public void clickButtonTwo() {
        waitUtils.waitForVisibility(buttonTwo);
        click(buttonTwo);
    }

    public void clickButtonThree() {
        waitUtils.waitForVisibility(buttonThree);
        click(buttonThree);
    }

    // Existing flow (kept as-is ✅)
    public void clickAllDynamicButtons() {

        click(startBtn);

        waitUtils.waitForVisibility(buttonOne);
        click(buttonOne);

        waitUtils.waitForVisibility(buttonTwo);
        click(buttonTwo);

        waitUtils.waitForVisibility(buttonThree);
        click(buttonThree);
    }

    // Validation
    public boolean isAllButtonsClicked() {
        return getText(resultText).contains("All Buttons Clicked");
    }
}