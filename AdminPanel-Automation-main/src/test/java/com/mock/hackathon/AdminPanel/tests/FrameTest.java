package com.mock.hackathon.AdminPanel.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mock.hackathon.AdminPanel.base.BaseTest;
import com.mock.hackathon.AdminPanel.pages.FramePage;

public class FrameTest extends BaseTest {

    // ✅ 1. Switch to iframe and interact
    @Test
    public void testSwitchToIFrameAndInteract() {

        FramePage framePage = new FramePage();

        framePage.navigateToIFramePage();
        framePage.switchToIFrame();

        Assert.assertTrue(framePage.isIframeItemVisible(),
                "Failed to interact with element inside iframe");
    }

    // ✅ 2. Verify content inside iframe
    @Test
    public void testNestedIframeContent() {

        FramePage framePage = new FramePage();

        framePage.navigateToIFramePage();
        framePage.switchToIFrame();

        Assert.assertTrue(framePage.isIframeItemVisible(),
                "Iframe content validation failed");
    }

    // ✅ 3. Switch back and verify main page
    @Test
    public void testSwitchBackToMainPage() {

        FramePage framePage = new FramePage();

        framePage.navigateToIFramePage();
        framePage.switchToIFrame();

        framePage.switchToDefault();

        Assert.assertTrue(framePage.isMainPageAccessible(),
                "Main page not accessible after switching back");
    }
}