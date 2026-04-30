package com.example.study.tests;

import com.example.study.base.BaseTest;
import com.example.study.pages.CheckboxesPage;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CheckboxesTest extends BaseTest {

    @Test
    public void testCheckboxesInitialState() {
        CheckboxesPage page = new CheckboxesPage(driver, wait);
        page.open();
        
        List<org.openqa.selenium.WebElement> checkboxes = page.getCheckboxes();
        
        org.junit.jupiter.api.Assertions.assertFalse(page.isCheckboxChecked(0), 
            "Checkbox 1 should be unchecked by default");
        org.junit.jupiter.api.Assertions.assertTrue(page.isCheckboxChecked(1), 
            "Checkbox 2 should be checked by default");
    }

    @Test
    public void testCheckboxesCanBeToggled() {
        CheckboxesPage page = new CheckboxesPage(driver, wait);
        page.open();
        
        page.checkCheckbox(0);
        page.checkCheckbox(1);
        
        org.junit.jupiter.api.Assertions.assertTrue(page.isCheckboxChecked(0), 
            "Checkbox 1 should be checked after clicking");
        org.junit.jupiter.api.Assertions.assertFalse(page.isCheckboxChecked(1), 
            "Checkbox 2 should be unchecked after clicking");
    }
}