package com.mock.hackathon.AdminPanel.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mock.hackathon.AdminPanel.base.BaseTest;
import com.mock.hackathon.AdminPanel.pages.AlertPage;

public class AlertTest extends BaseTest {

    // ✅ 1. Alert accept
    @Test
    public void testAlertAccept() {

        AlertPage alertPage = new AlertPage();

        alertPage.navigateToAlertsPage();
        alertPage.triggerAlertAndAccept();

        Assert.assertTrue(alertPage.isAlertHandled(),
                "Alert was not handled properly");
    }

    // ✅ 2. Confirm accept
    @Test
    public void testConfirmAccept() {

        AlertPage alertPage = new AlertPage();

        alertPage.navigateToAlertsPage();
        alertPage.triggerConfirmAccept();

        Assert.assertTrue(alertPage.isConfirmAccepted(),
                "Confirm accept failed");
    }

    // ✅ 3. Confirm dismiss
    @Test
    public void testConfirmDismiss() {

        AlertPage alertPage = new AlertPage();

        alertPage.navigateToAlertsPage();
        alertPage.triggerConfirmDismiss();

        Assert.assertTrue(alertPage.isConfirmCancelled(),
                "Confirm dismiss failed");
    }

    // ✅ 4. Prompt input
    @Test
    public void testPromptInput() {

        AlertPage alertPage = new AlertPage();

        String inputText = "Mickey";

        alertPage.navigateToAlertsPage();
        alertPage.triggerPromptAndEnterText(inputText);

        Assert.assertTrue(alertPage.isPromptValueDisplayed(inputText),
                "Prompt input validation failed");
    }
}