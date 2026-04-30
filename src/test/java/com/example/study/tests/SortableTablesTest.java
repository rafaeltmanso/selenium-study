package com.example.study.tests;

import com.example.study.base.BaseTest;
import com.example.study.pages.SortableTablesPage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SortableTablesTest extends BaseTest {

    @Test
    public void testPageLoads() {
        SortableTablesPage page = new SortableTablesPage(driver, wait);
        page.open();
        
        Assertions.assertTrue(page.isPageLoaded(), "Page should load");
        Assertions.assertEquals(5, page.getHeaderCount(), "Table should have 5 columns");
        Assertions.assertEquals(4, page.getRowCount(), "Table should have 4 rows");
    }

    @Test
    public void testColumnHeaders() {
        SortableTablesPage page = new SortableTablesPage(driver, wait);
        page.open();
        
        String[] expected = {"Last Name", "First Name", "Email", "Due ($)", "Web Site"};
        Assertions.assertArrayEquals(expected, page.getHeaderTexts().toArray(), "Headers should match");
    }

    @Test
    public void testDefaultSort() {
        SortableTablesPage page = new SortableTablesPage(driver, wait);
        page.open();
        
        Assertions.assertTrue(page.getFirstRowFirstCellText().contains("Smith"), "First row should be Smith");
    }
}