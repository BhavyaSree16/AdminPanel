package com.mock.hackathon.AdminPanel.pages;

import org.openqa.selenium.By;

import com.mock.hackathon.AdminPanel.base.BasePage;

public class AlertPage extends BasePage {

    // Navigation
    private By pagesLink = By.xpath("//span[text()='Pages']");
    private By basicsLink = By.xpath("//a[@href='/pages/basics/']");
    private By alertsLink = By.xpath("//a[@href='/pages/basics/alerts-javascript/']");

    // Buttons
    private By alertBtn = By.id("alertexamples");
    private By confirmBtn = By.id("confirmexample");
    private By promptBtn = By.id("promptexample");

    // Result Elements
    private By alertText = By.id("alertexplanation");
    private By confirmText = By.id("confirmexplanation");
    private By promptText = By.id("promptexplanation");
    private By promptResult = By.id("promptreturn");

    // Navigation
    public void navigateToAlertsPage() {

        waitUtils.waitForPageLoad();

        click(pagesLink);

        waitUtils.waitForVisibility(basicsLink);
        click(basicsLink);

        waitUtils.waitForVisibility(alertsLink);
        click(alertsLink);

        waitUtils.waitForPageLoad();
    }

    // Alert Actions
    public void triggerAlertAndAccept() {
        click(alertBtn);
        waitUtils.waitForAlert().accept();
    }

    public void triggerConfirmAccept() {
        click(confirmBtn);
        waitUtils.waitForAlert().accept();
    }

    public void triggerConfirmDismiss() {
        click(confirmBtn);
        waitUtils.waitForAlert().dismiss();
    }

    public void triggerPromptAndEnterText(String text) {
        click(promptBtn);
        waitUtils.waitForAlert().sendKeys(text);
        waitUtils.waitForAlert().accept();
    }

    // Validations
    public boolean isAlertHandled() {
        return getText(alertText).contains("handled");
    }

    public boolean isConfirmAccepted() {
        return getText(confirmText).contains("true");
    }

    public boolean isConfirmCancelled() {
        return getText(confirmText).contains("false");
    }

    public boolean isPromptValueDisplayed(String expected) {
        return getText(promptResult).equals(expected);
    }
}