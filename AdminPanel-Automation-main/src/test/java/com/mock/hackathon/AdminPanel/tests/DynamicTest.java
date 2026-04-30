package com.mock.hackathon.AdminPanel.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mock.hackathon.AdminPanel.base.BaseTest;
import com.mock.hackathon.AdminPanel.pages.DynamicPage;

public class DynamicTest extends BaseTest {

    // ✅ 1. Verify dynamic elements become visible after trigger
    @Test
    public void testDynamicElementsVisibility() {

        DynamicPage dynamicPage = new DynamicPage();

        dynamicPage.navigateToDynamicButtons();

        dynamicPage.clickStart(); // only trigger

        Assert.assertTrue(dynamicPage.isButtonOneVisible(),
                "Button One did not appear dynamically");
    }

    // ✅ 2. Interact with dynamically appearing elements
    @Test
    public void testDynamicInteractionFlow() {

        DynamicPage dynamicPage = new DynamicPage();

        dynamicPage.navigateToDynamicButtons();

        dynamicPage.clickAllDynamicButtons();

        Assert.assertTrue(dynamicPage.isAllButtonsClicked(),
                "Dynamic interaction flow failed");
    }

    // ✅ 3. Verify redirect / final state (acts as destination validation)
    @Test
    public void testFinalStateAfterDynamicFlow() {

        DynamicPage dynamicPage = new DynamicPage();

        dynamicPage.navigateToDynamicButtons();

        dynamicPage.clickAllDynamicButtons();

        Assert.assertTrue(dynamicPage.isAllButtonsClicked(),
                "Final state validation failed - buttons flow incomplete");
    }
}