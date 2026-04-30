package com.mock.hackathon.AdminPanel.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.mock.hackathon.AdminPanel.base.BaseTest;
import com.mock.hackathon.AdminPanel.pages.FormPage;

public class FormTest extends BaseTest {

   
    @Test(dataProvider = "formData")
    public void testFormSubmission(String user, String pass, String comment, String dropdownValue) {

        FormPage formPage = new FormPage();

        formPage.navigateToHtmlForm();
        formPage.fillForm(user, pass, comment, dropdownValue);
        formPage.submitForm();

        Assert.assertTrue(formPage.isUsernameDisplayed(user),
                "Username validation failed");
    }


    @Test(dataProvider = "formData")
    public void testDropdownSelection(String user, String pass, String comment, String dropdownValue) {

        FormPage formPage = new FormPage();

        formPage.navigateToHtmlForm();
        formPage.fillForm(user, pass, comment, dropdownValue);
        formPage.submitForm();

        Assert.assertTrue(formPage.isCorrectDropdownSelected(dropdownValue),
                "Dropdown validation failed");
    }

  
    @Test(dataProvider = "formData")
    public void testRadioAndCheckboxSelection(String user, String pass, String comment, String dropdownValue) {

        FormPage formPage = new FormPage();

        formPage.navigateToHtmlForm();
        formPage.fillForm(user, pass, comment, dropdownValue);
        formPage.submitForm();

        Assert.assertTrue(formPage.isCorrectRadioSelected("rd1"),
                "Radio validation failed");

        Assert.assertTrue(formPage.isCheckboxSelected("cb1"),
                "Checkbox validation failed");
    }

    // ✅ 4. Empty form validation
    @Test
    public void testEmptyFormSubmission() {

        FormPage formPage = new FormPage();

        formPage.navigateToHtmlForm();
        formPage.submitForm();

        Assert.assertTrue(formPage.isEmptyFormHandled(),
                "Empty form validation failed");
    }

    @DataProvider(name = "formData")
    public Object[][] getFormData() {
        return new Object[][] {
                {"mickey", "pass123", "Test comment 1", "dd1"},
                {"admin", "admin123", "Test comment 2", "dd2"}
        };
    }
}