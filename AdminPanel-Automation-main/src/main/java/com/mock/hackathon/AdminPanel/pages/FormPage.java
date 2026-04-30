package com.mock.hackathon.AdminPanel.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.mock.hackathon.AdminPanel.base.BasePage;

public class FormPage extends BasePage {

    // Navigation
    private By pagesLink = By.xpath("//span[text()='Pages']");
    private By formsLink = By.xpath("//a[@href='/pages/forms/']");
    private By htmlFormLink = By.xpath("//a[@href='/pages/forms/html-form/']");

    // Form Fields
    private By username = By.name("username");
    private By password = By.name("password");
    private By comments = By.name("comments");

    private By checkbox1 = By.xpath("//input[@value='cb1']");
    private By radio1 = By.xpath("//input[@value='rd1']");

    private By dropdown = By.name("dropdown");
    private By submitBtn = By.xpath("//input[@value='submit']");

    // Result Locators
    private By selectedRadio = By.id("_valueradioval");
    private By selectedDropdown = By.id("_valuedropdown");
    private By resultContainer = By.tagName("body");
    private By noUsernameText = By.id("_username");
    private By selectedCheckbox = By.id("_valuecheckboxes0");
    private By commentsResult = By.id("_valuecomments");

    // Navigation
    public void navigateToHtmlForm() {

        waitUtils.waitForPageLoad();

        click(pagesLink);

        waitUtils.waitForVisibility(formsLink);
        click(formsLink);

        waitUtils.waitForVisibility(htmlFormLink);
        click(htmlFormLink);

        waitUtils.waitForPageLoad();
    }

    // Actions
    public void fillForm(String user, String pass, String comment, String dropdownValue) {

        type(username, user);
        type(password, pass);
        type(comments, comment);

        selectCheckbox(checkbox1);
        selectRadio(radio1);

        selectDropdown(dropdownValue);
    }

    public void submitForm() {

        WebElement element = waitUtils.waitForClickable(submitBtn);

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", element);

        waitUtils.waitForPageLoad();
    }

    // Checkbox (JS-safe)
    private void selectCheckbox(By locator) {

        WebElement element = waitUtils.waitForClickable(locator);

        if (!element.isSelected()) {
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
    }

    // Radio (JS-safe)
    private void selectRadio(By locator) {

        WebElement element = waitUtils.waitForClickable(locator);

        ((org.openqa.selenium.JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", element);

        if (!element.isSelected()) {
            ((org.openqa.selenium.JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
    }

    public void selectDropdown(String value) {
        Select select = new Select(waitUtils.waitForVisibility(dropdown));
        select.selectByValue(value);
    }

    // Validations
    public boolean isUsernameDisplayed(String user) {
        return waitUtils.waitForText(resultContainer, user);
    }

    public boolean isCorrectRadioSelected(String expected) {
        return getText(selectedRadio).equals(expected);
    }

    public boolean isCorrectDropdownSelected(String expected) {
        return getText(selectedDropdown).equals(expected);
    }

    public boolean isEmptyFormHandled() {
        return getText(noUsernameText).contains("No Value");
    }
    
 // Checkbox Validation
    public boolean isCheckboxSelected(String expected) {
        return getText(selectedCheckbox).equals(expected);
    }

    // TextArea / Comments Validation
    public boolean isCommentDisplayed(String expected) {
        return getText(commentsResult).equals(expected);
    }
}