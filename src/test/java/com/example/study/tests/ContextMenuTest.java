package com.example.study.tests;

import com.example.study.base.BaseTest;
import com.example.study.pages.ContextMenuPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ContextMenuTest extends BaseTest {

    @Test
    public void testPageLoads() {
        ContextMenuPage page = new ContextMenuPage(driver, wait);
        page.open();
        
        Assertions.assertTrue(page.isPageLoaded(), "Page should load");
        Assertions.assertTrue(page.getContextMenuBox().isDisplayed(), "Context menu box should be visible");
    }

    @Test
    public void testInitialStatus() {
        ContextMenuPage page = new ContextMenuPage(driver, wait);
        page.open();
        
        Assertions.assertTrue(page.getStatusText().contains("None yet"), 
            "Initial status should show None yet");
    }

    @Test
    public void testContextMenuTriggering() {
        ContextMenuPage page = new ContextMenuPage(driver, wait);
        page.open();
        
        page.triggerContextMenu();
        
        Assertions.assertTrue(page.isStatusUpdated(), "Status should update after triggering");
    }
}