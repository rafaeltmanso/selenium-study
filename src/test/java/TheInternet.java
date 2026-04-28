import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TheInternet {
    private static final String BASE_URL = "https://theinternet-tau.vercel.app";
    private static final String FORM_AUTH_USERNAME = "tomsmith";
    private static final String FORM_AUTH_PASSWORD = "SuperSecretPassword!";
    private static final By CHECKBOX_INPUTS = By.cssSelector("input[type='checkbox']");
    private static final By FORM_AUTH_USERNAME_INPUT = By.id("username");
    private static final By FORM_AUTH_PASSWORD_INPUT = By.id("password");
    private static final By FORM_AUTH_LOGIN_BUTTON = By.cssSelector("button[type='submit']");
    private static final By SECURE_AREA_TITLE = By.xpath("//h1[normalize-space()='Secure Area']");
    private static final By SECURE_AREA_LOGOUT_BUTTON = By.xpath("//button[normalize-space()='Logout']");
    private static final By DRAG_AND_DROP_TITLE = By.xpath("//h1[normalize-space()='Drag and Drop']");
    private static final By DRAG_COLUMN_A_HEADER = By.cssSelector("main .grid > div:nth-of-type(1) h2");
    private static final By DRAG_COLUMN_B_HEADER = By.cssSelector("main .grid > div:nth-of-type(2) h2");
    private static final By DRAG_COLUMN_A_ITEMS = By.cssSelector("main .grid > div:nth-of-type(1) [draggable='true']");
    private static final By DRAG_COLUMN_B_ITEMS = By.cssSelector("main .grid > div:nth-of-type(2) [draggable='true']");
    private static final By DROPDOWN_TITLE = By.xpath("//h1[normalize-space()='Dropdown List']");
    private static final By DROPDOWN_SELECT = By.cssSelector("main select");
    private static final By CONTEXT_MENU_TITLE = By.xpath("//h1[normalize-space()='Context Menu']");
    private static final By CONTEXT_MENU_BOX = By.cssSelector("main .cursor-context-menu");
    private static final By CONTEXT_MENU_STATUS = By.xpath("//p[contains(normalize-space(), 'Context menu triggered:')]");
    private static final By DYNAMIC_LOADING_TITLE = By.xpath("//h1[normalize-space()='Dynamic Loading']");
    private static final By DYNAMIC_LOADING_START_BUTTON = By.cssSelector("button.px-6.py-3.bg-primary");
    private static final By DYNAMIC_LOADING_LOADED_CONTENT = By.xpath("//p[contains(text(), 'Hello World')]");
    private static final By DYNAMIC_CONTROLS_TITLE = By.xpath("//h1[normalize-space()='Dynamic Controls']");
    private static final By DYNAMIC_CONTROLS_TOGGLE_BUTTON = By.cssSelector("button.px-6.py-3.bg-primary");
    private static final By DYNAMIC_CONTROLS_TEXT_INPUT = By.id("text-input");
    private static final By DYNAMIC_CONTROLS_CHECKBOX = By.id("checkbox");
    private static final By DYNAMIC_CONTROLS_STATUS = By.cssSelector("div.p-4.rounded-lg.bg-muted\\/50 p");
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(BASE_URL);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testAddAndRemoveElements() {
        // Navigate to Add/Remove Elements page
        WebElement addRemoveLink = wait.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Add/Remove")));
        addRemoveLink.click();

        // Click Add Element button
        WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add Element']")));
        addButton.click();

        // Verify Delete button appears
        WebElement deleteButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[text()='Delete']")));
        Assertions.assertTrue(deleteButton.isDisplayed(), "Delete button should be visible after adding an element");

        // Click Delete button
        deleteButton.click();

        // Verify Delete button disappears
        boolean isDeleteButtonInvisible = wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//button[text()='Delete']")));
        Assertions.assertTrue(isDeleteButtonInvisible, "Delete button should disappear after clicking it");
    }

    @Test
    public void testCheckboxesInitialState() {
        goToCheckboxesPage();

        WebElement checkbox1 = getCheckbox(0);
        WebElement checkbox2 = getCheckbox(1);

        Assertions.assertFalse(checkbox1.isSelected(), "Checkbox 1 should be unchecked by default");
        Assertions.assertTrue(checkbox2.isSelected(), "Checkbox 2 should be checked by default");
        
    }

    @Test
    public void testCheckboxesCanBeToggled() {
        goToCheckboxesPage();

        WebElement checkbox1 = getClickableCheckbox(0);
        WebElement checkbox2 = getClickableCheckbox(1);

        checkbox1.click();
        checkbox2.click();

        Assertions.assertTrue(checkbox1.isSelected(), "Checkbox 1 should be checked after clicking");
        Assertions.assertFalse(checkbox2.isSelected(), "Checkbox 2 should be unchecked after clicking");
    }

    @Test
    public void testFormAuthentication() {
        driver.get(BASE_URL + "/examples/form-auth");

        WebElement usernameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(FORM_AUTH_USERNAME_INPUT));
        WebElement passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(FORM_AUTH_PASSWORD_INPUT));
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(FORM_AUTH_LOGIN_BUTTON));

        usernameInput.sendKeys(FORM_AUTH_USERNAME);
        passwordInput.sendKeys(FORM_AUTH_PASSWORD);
        loginButton.click();

        WebElement secureAreaTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(SECURE_AREA_TITLE));
        WebElement logoutButton = wait.until(ExpectedConditions.visibilityOfElementLocated(SECURE_AREA_LOGOUT_BUTTON));

        Assertions.assertTrue(
                secureAreaTitle.isDisplayed(),
                "Secure Area title should be displayed after successful login");
        Assertions.assertTrue(
                driver.getPageSource().contains("You logged into a secure area!"),
                "Success message should be displayed after login");
        Assertions.assertTrue(
                logoutButton.isDisplayed(),
                "Logout button should be visible after successful login");
    }

    @Test
    public void testDragAndDropPageLoads() {
        goToDragAndDropPage();

        WebElement pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(DRAG_AND_DROP_TITLE));
        WebElement columnAHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(DRAG_COLUMN_A_HEADER));
        WebElement columnBHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(DRAG_COLUMN_B_HEADER));

        Assertions.assertTrue(pageTitle.isDisplayed(), "Drag and Drop page title should be visible");
        Assertions.assertEquals("Column A", columnAHeader.getText().trim(), "Column A section should be visible");
        Assertions.assertEquals("Column B", columnBHeader.getText().trim(), "Column B section should be visible");
        Assertions.assertEquals(2, getDraggableItemsInColumn("Column A").size(), "Column A should start with 2 items");
        Assertions.assertEquals(1, getDraggableItemsInColumn("Column B").size(), "Column B should start with 1 item");
    }

    @Test
    public void testDragAndDropSwapsColumns() {
        goToDragAndDropPage();

        WebElement itemA = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DRAG_COLUMN_A_ITEMS)).get(0);
        WebElement itemB = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(DRAG_COLUMN_B_ITEMS)).get(0);

        Assertions.assertEquals(2, getDraggableItemsInColumn("Column A").size(), "Column A should start with 2 items");
        Assertions.assertEquals(1, getDraggableItemsInColumn("Column B").size(), "Column B should start with 1 item");

        dragAndDropItem(itemA, itemB);

        wait.until(d -> getDraggableItemsInColumn("Column A").size() == 1);
        wait.until(d -> getDraggableItemsInColumn("Column B").size() == 2);

        Assertions.assertTrue(
                getDraggableTextsInColumn("Column B").contains("A"),
                "Item A should be moved to Column B after drag and drop");
    }

    @Test
    public void testDropdownDefaultSelection() {
        goToDropdownPage();

        Select dropdown = getDropdownSelect();
        WebElement selectedOption = dropdown.getFirstSelectedOption();

        Assertions.assertEquals("Please select an option", selectedOption.getText().trim(),
                "Dropdown should start with the placeholder option selected");
        Assertions.assertEquals(3, dropdown.getOptions().size(), "Dropdown should contain placeholder plus two options");
    }

    @Test
    public void testDropdownCanSelectOptions() {
        goToDropdownPage();

        Select dropdown = getDropdownSelect();

        dropdown.selectByVisibleText("Option 1");
        Assertions.assertEquals("Option 1", dropdown.getFirstSelectedOption().getText().trim(),
                "Dropdown should select Option 1");

        dropdown.selectByVisibleText("Option 2");
        Assertions.assertEquals("Option 2", dropdown.getFirstSelectedOption().getText().trim(),
                "Dropdown should select Option 2");
    }

    @Test
    public void testContextMenuPageLoads() {
        goToContextMenuPage();

        WebElement pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(CONTEXT_MENU_TITLE));
        WebElement contextMenuBox = wait.until(ExpectedConditions.visibilityOfElementLocated(CONTEXT_MENU_BOX));
        WebElement statusText = wait.until(ExpectedConditions.visibilityOfElementLocated(CONTEXT_MENU_STATUS));

        Assertions.assertTrue(pageTitle.isDisplayed(), "Context Menu page title should be visible");
        Assertions.assertTrue(contextMenuBox.isDisplayed(), "Context menu interaction box should be visible");
        Assertions.assertTrue(statusText.getText().contains("None yet"),
                "Context menu status should indicate no interaction initially");
    }

    @Test
    public void testContextMenuCanBeTriggered() {
        goToContextMenuPage();

        WebElement contextMenuBox = wait.until(ExpectedConditions.visibilityOfElementLocated(CONTEXT_MENU_BOX));
        new Actions(driver).contextClick(contextMenuBox).perform();

        wait.until(ExpectedConditions.not(ExpectedConditions.textToBe(CONTEXT_MENU_STATUS, "Context menu triggered: None yet")));
        WebElement updatedStatusText = wait.until(ExpectedConditions.visibilityOfElementLocated(CONTEXT_MENU_STATUS));

        Assertions.assertTrue(updatedStatusText.getText().contains("Context menu triggered:"),
                "Status should confirm that context menu was triggered");
        Assertions.assertFalse(updatedStatusText.getText().contains("None yet"),
                "Status should change after right-click action");
    }

    @Test
    public void testDynamicLoadingContentAppearsAfterDelay() {
        goToDynamicLoadingPage();

        WebElement startButton = wait.until(ExpectedConditions.elementToBeClickable(DYNAMIC_LOADING_START_BUTTON));
        startButton.click();

        WebElement loadedContent = wait.until(ExpectedConditions.visibilityOfElementLocated(DYNAMIC_LOADING_LOADED_CONTENT));

        Assertions.assertTrue(loadedContent.isDisplayed(), "Content should be visible after 5 seconds");
        Assertions.assertFalse(loadedContent.getText().isEmpty(), "Content should not be empty");
    }

    @Test
    public void testDynamicControlsStartDisabled() {
        goToDynamicControlsPage();

        WebElement toggleButton = wait.until(ExpectedConditions.visibilityOfElementLocated(DYNAMIC_CONTROLS_TOGGLE_BUTTON));
        WebElement textInput = driver.findElement(DYNAMIC_CONTROLS_TEXT_INPUT);
        WebElement checkbox = driver.findElement(DYNAMIC_CONTROLS_CHECKBOX);

        Assertions.assertEquals("Enable Controls", toggleButton.getText().trim(),
                "Toggle button should say 'Enable Controls' when controls are disabled");
        Assertions.assertFalse(textInput.isEnabled(), "Text input should be disabled initially");
        Assertions.assertFalse(checkbox.isEnabled(), "Checkbox should be disabled initially");

        String statusText = driver.findElement(DYNAMIC_CONTROLS_STATUS).getText();
        Assertions.assertTrue(statusText.contains("Controls Disabled"),
                "Status should indicate controls are disabled");
    }

    @Test
    public void testDynamicControlsCanBeEnabled() {
        goToDynamicControlsPage();

        WebElement toggleButton = wait.until(ExpectedConditions.elementToBeClickable(DYNAMIC_CONTROLS_TOGGLE_BUTTON));
        toggleButton.click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(DYNAMIC_CONTROLS_TOGGLE_BUTTON, "Disable Controls"));

        WebElement textInput = driver.findElement(DYNAMIC_CONTROLS_TEXT_INPUT);
        WebElement checkbox = driver.findElement(DYNAMIC_CONTROLS_CHECKBOX);

        Assertions.assertTrue(textInput.isEnabled(), "Text input should be enabled after clicking Enable");
        Assertions.assertTrue(checkbox.isEnabled(), "Checkbox should be enabled after clicking Enable");

        String statusText = wait.until(ExpectedConditions.visibilityOfElementLocated(DYNAMIC_CONTROLS_STATUS)).getText();
        Assertions.assertTrue(statusText.contains("Controls Enabled"),
                "Status should indicate controls are enabled");
    }

    @Test
    public void testDynamicControlsCanBeDisabled() {
        goToDynamicControlsPage();

        WebElement toggleButton = wait.until(ExpectedConditions.elementToBeClickable(DYNAMIC_CONTROLS_TOGGLE_BUTTON));
        toggleButton.click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(DYNAMIC_CONTROLS_TOGGLE_BUTTON, "Disable Controls"));

        toggleButton = driver.findElement(DYNAMIC_CONTROLS_TOGGLE_BUTTON);
        toggleButton.click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(DYNAMIC_CONTROLS_TOGGLE_BUTTON, "Enable Controls"));

        WebElement textInput = driver.findElement(DYNAMIC_CONTROLS_TEXT_INPUT);
        WebElement checkbox = driver.findElement(DYNAMIC_CONTROLS_CHECKBOX);

        Assertions.assertFalse(textInput.isEnabled(), "Text input should be disabled after clicking Disable");
        Assertions.assertFalse(checkbox.isEnabled(), "Checkbox should be disabled after clicking Disable");

        String statusText = wait.until(ExpectedConditions.visibilityOfElementLocated(DYNAMIC_CONTROLS_STATUS)).getText();
        Assertions.assertTrue(statusText.contains("Controls Disabled"),
                "Status should indicate controls are disabled");
    }

    @Test
    public void testDynamicControlsCanTypeInEnabledInput() {
        goToDynamicControlsPage();

        WebElement toggleButton = wait.until(ExpectedConditions.elementToBeClickable(DYNAMIC_CONTROLS_TOGGLE_BUTTON));
        toggleButton.click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(DYNAMIC_CONTROLS_TOGGLE_BUTTON, "Disable Controls"));
        WebElement textInput = wait.until(ExpectedConditions.elementToBeClickable(DYNAMIC_CONTROLS_TEXT_INPUT));

        textInput.clear();
        textInput.sendKeys("Hello World");

        Assertions.assertEquals("Hello World", textInput.getAttribute("value"),
                "Should be able to type in the text input when enabled");
    }

    @Test
    public void testDynamicControlsCheckboxCanBeToggled() {
        goToDynamicControlsPage();

        WebElement toggleButton = wait.until(ExpectedConditions.elementToBeClickable(DYNAMIC_CONTROLS_TOGGLE_BUTTON));
        toggleButton.click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(DYNAMIC_CONTROLS_TOGGLE_BUTTON, "Disable Controls"));

        WebElement checkbox = driver.findElement(DYNAMIC_CONTROLS_CHECKBOX);
        Assertions.assertFalse(checkbox.isSelected(), "Checkbox should be unchecked initially when enabled");

        checkbox.click();
        Assertions.assertTrue(checkbox.isSelected(), "Checkbox should be checked after clicking");

        checkbox.click();
        Assertions.assertFalse(checkbox.isSelected(), "Checkbox should be unchecked after clicking again");
    }

    private void goToCheckboxesPage() {
        driver.get(BASE_URL + "/examples/checkboxes");
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(CHECKBOX_INPUTS, 1));
    }

    private void goToDragAndDropPage() {
        driver.get(BASE_URL + "/examples/drag-and-drop");
        wait.until(ExpectedConditions.visibilityOfElementLocated(DRAG_AND_DROP_TITLE));
    }

    private void goToDropdownPage() {
        driver.get(BASE_URL + "/examples/dropdown");
        wait.until(ExpectedConditions.visibilityOfElementLocated(DROPDOWN_TITLE));
        wait.until(ExpectedConditions.visibilityOfElementLocated(DROPDOWN_SELECT));
    }

    private void goToContextMenuPage() {
        driver.get(BASE_URL + "/examples/context-menu");
        wait.until(ExpectedConditions.visibilityOfElementLocated(CONTEXT_MENU_TITLE));
        wait.until(ExpectedConditions.visibilityOfElementLocated(CONTEXT_MENU_BOX));
    }

    private void goToDynamicLoadingPage() {
        driver.get(BASE_URL + "/examples/dynamic-loading");
        wait.until(ExpectedConditions.visibilityOfElementLocated(DYNAMIC_LOADING_TITLE));
    }

    private void goToDynamicControlsPage() {
        driver.get(BASE_URL + "/examples/dynamic-controls");
        wait.until(ExpectedConditions.visibilityOfElementLocated(DYNAMIC_CONTROLS_TITLE));
    }

    private void html5DragAndDrop(WebElement source, WebElement target) {
        String script = "const source = arguments[0];"
                + "const target = arguments[1];"
                + "const dataTransfer = new DataTransfer();"
                + "source.dispatchEvent(new DragEvent('dragstart', { dataTransfer }));"
                + "target.dispatchEvent(new DragEvent('dragover', { dataTransfer }));"
                + "target.dispatchEvent(new DragEvent('drop', { dataTransfer }));"
                + "source.dispatchEvent(new DragEvent('dragend', { dataTransfer }));";

        ((JavascriptExecutor) driver).executeScript(script, source, target);
    }

    private void dragAndDropItem(WebElement source, WebElement target) {
        new Actions(driver)
                .clickAndHold(source)
                .moveToElement(target)
                .release(target)
                .perform();

        if (getDraggableItemsInColumn("Column A").size() == 2) {
            html5DragAndDrop(source, target);
        }
    }

    private java.util.List<WebElement> getDraggableItemsInColumn(String columnName) {
        By columnItems = "Column A".equals(columnName) ? DRAG_COLUMN_A_ITEMS : DRAG_COLUMN_B_ITEMS;
        return driver.findElements(columnItems);
    }

    private java.util.List<String> getDraggableTextsInColumn(String columnName) {
        return getDraggableItemsInColumn(columnName).stream()
                .map(WebElement::getText)
                .map(String::trim)
                .toList();
    }

    private WebElement getCheckbox(int index) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CHECKBOX_INPUTS)).get(index);
    }

    private WebElement getClickableCheckbox(int index) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(CHECKBOX_INPUTS)).get(index);
    }

    private Select getDropdownSelect() {
        WebElement selectElement = wait.until(ExpectedConditions.elementToBeClickable(DROPDOWN_SELECT));
        return new Select(selectElement);
    }
}