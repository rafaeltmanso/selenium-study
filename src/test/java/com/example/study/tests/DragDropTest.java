package com.example.study.tests;

import com.example.study.base.BaseTest;
import com.example.study.pages.DragDropPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.List;

public class DragDropTest extends BaseTest {

    @Test
    public void testPageLoads() {
        DragDropPage page = new DragDropPage(driver, wait);
        page.open();
        
        Assertions.assertTrue(page.isPageLoaded(), "Page should load");
        Assertions.assertEquals("Column A", page.getColumnAHeaderText(), "Column A header should be visible");
        Assertions.assertEquals("Column B", page.getColumnBHeaderText(), "Column B header should be visible");
    }

    @Test
    public void testInitialItemCount() {
        DragDropPage page = new DragDropPage(driver, wait);
        page.open();
        
        Assertions.assertEquals(2, page.getColumnAItemCount(), "Column A should start with 2 items");
        Assertions.assertEquals(1, page.getColumnBItemCount(), "Column B should start with 1 item");
    }

    @Test
    public void testDragAndDropItem() {
        DragDropPage page = new DragDropPage(driver, wait);
        page.open();
        
        List<org.openqa.selenium.WebElement> columnAItems = page.getColumnAItems();
        org.openqa.selenium.WebElement item = columnAItems.get(0);
        
        page.dragItemFromAToB(item);
        
        wait.until(d -> page.getColumnBItemCount() == 2);
        
        List<String> columnBTexts = page.getColumnBTexts();
        Assertions.assertTrue(columnBTexts.contains("A"), "Item A should be in Column B after drag");
    }
}