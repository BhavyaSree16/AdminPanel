package com.mock.hackathon.AdminPanel.pages;

import org.openqa.selenium.By;

import com.mock.hackathon.AdminPanel.base.BasePage;

public class FramePage extends BasePage {

    // Navigation
    private By pagesLink = By.id("m-pages");
    private By embeddedContentLink = By.id("m-pagesembedded-pages");
    private By iframeLink = By.xpath("//a[contains(@href,'/pages/frames/iframe-test.html') or text()='iFrames']");

    // Iframe
    private By iframe = By.id("alist");

    // Inside iframe
    private By iframeItem = By.id("iframe1");

    // Main page element (for validation after switching back)
    private By mainPageHeader = By.tagName("h1");

    // Navigation
    public void navigateToIFramePage() {

        waitUtils.waitForPageLoad();

        click(pagesLink);

        waitUtils.waitForVisibility(embeddedContentLink);
        click(embeddedContentLink);

        waitUtils.waitForVisibility(iframeLink);
        click(iframeLink);

        waitUtils.waitForPageLoad();
    }

    // Switch to iframe
    public void switchToIFrame() {
        waitUtils.waitForVisibility(iframe);
        driver.switchTo().frame(waitForElement(iframe));
    }

    // Switch back
    public void switchToDefault() {
        driver.switchTo().defaultContent();
    }

    // Validation inside iframe
    public boolean isIframeItemVisible() {
        return waitUtils.waitForVisibility(iframeItem).isDisplayed();
    }

    // Validation for main page
    public boolean isMainPageAccessible() {
        return waitUtils.waitForVisibility(mainPageHeader).isDisplayed();
    }
}