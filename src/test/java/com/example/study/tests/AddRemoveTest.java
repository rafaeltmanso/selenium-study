package com.example.study.tests;

import com.example.study.base.BaseTest;
import com.example.study.pages.AddRemovePage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AddRemoveTest extends BaseTest {

    @Test
    public void testCanAddElement() {
        AddRemovePage page = new AddRemovePage(driver, wait);
        page.open();
        
        page.clickAddElement();
        
        Assertions.assertTrue(page.isDeleteButtonVisible(), "Delete button should appear");
    }

    @Test
    public void testCanRemoveElement() {
        AddRemovePage page = new AddRemovePage(driver, wait);
        page.open();
        
        page.clickAddElement();
        Assertions.assertTrue(page.isDeleteButtonVisible(), "Delete button should appear");
        
        page.clickDelete();
        
        Assertions.assertTrue(page.isDeleteButtonGone(), "Delete button should disappear");
    }
}