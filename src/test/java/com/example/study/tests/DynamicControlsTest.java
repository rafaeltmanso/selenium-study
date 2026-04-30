package com.example.study.tests;

import com.example.study.base.BaseTest;
import com.example.study.pages.DynamicControlsPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DynamicControlsTest extends BaseTest {

    @Test
    public void testControlsStartDisabled() {
        DynamicControlsPage page = new DynamicControlsPage(driver, wait);
        page.open();
        
        Assertions.assertEquals("Enable Controls", page.getToggleButtonText(), "Toggle button should say 'Enable Controls'");
        Assertions.assertTrue(page.isControlsDisabled(), "Controls should be disabled initially");
        Assertions.assertTrue(page.isStatusShowingDisabled(), "Status should show disabled");
    }

    @Test
    public void testCanEnableControls() {
        DynamicControlsPage page = new DynamicControlsPage(driver, wait);
        page.open();
        
        page.clickToggleButton();
        page.waitForToggleButtonText("Disable Controls");
        
        Assertions.assertTrue(page.isControlsEnabled(), "Controls should be enabled after toggle");
        Assertions.assertTrue(page.isStatusShowingEnabled(), "Status should show enabled");
    }

    @Test
    public void testCanDisableControlsAfterEnabling() {
        DynamicControlsPage page = new DynamicControlsPage(driver, wait);
        page.open();
        
        page.clickToggleButton();
        page.waitForToggleButtonText("Disable Controls");
        
        page.clickToggleButton();
        page.waitForToggleButtonText("Enable Controls");
        
        Assertions.assertTrue(page.isControlsDisabled(), "Controls should be disabled");
    }

    @Test
    public void testCanTypeInEnabledInput() {
        DynamicControlsPage page = new DynamicControlsPage(driver, wait);
        page.open();
        
        page.clickToggleButton();
        page.waitForToggleButtonText("Disable Controls");
        
        page.typeInTextInput("Hello World");
        
        Assertions.assertEquals("Hello World", page.getTextInputValue(), "Should be able to type when enabled");
    }

    @Test
    public void testCheckboxCanBeToggled() {
        DynamicControlsPage page = new DynamicControlsPage(driver, wait);
        page.open();
        
        page.clickToggleButton();
        page.waitForToggleButtonText("Disable Controls");
        
        Assertions.assertFalse(page.isCheckboxSelected(), "Checkbox should be unchecked initially");
        
        page.clickCheckbox();
        Assertions.assertTrue(page.isCheckboxSelected(), "Checkbox should be checked after click");
        
        page.clickCheckbox();
        Assertions.assertFalse(page.isCheckboxSelected(), "Checkbox should be unchecked after second click");
    }
}