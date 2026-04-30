package com.example.study.tests;

import com.example.study.base.BaseTest;
import com.example.study.pages.DropdownPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DropdownTest extends BaseTest {

    @Test
    public void testDefaultSelection() {
        DropdownPage page = new DropdownPage(driver, wait);
        page.open();
        
        Assertions.assertEquals("Please select an option", page.getSelectedOption(), 
            "Default selection should be placeholder");
        Assertions.assertEquals(3, page.getAllOptions().size(), "Should have 3 options");
    }

    @Test
    public void testCanSelectOptions() {
        DropdownPage page = new DropdownPage(driver, wait);
        page.open();
        
        page.selectByVisibleText("Option 1");
        Assertions.assertEquals("Option 1", page.getSelectedOption(), "Should select Option 1");
        
        page.selectByVisibleText("Option 2");
        Assertions.assertEquals("Option 2", page.getSelectedOption(), "Should select Option 2");
    }
}