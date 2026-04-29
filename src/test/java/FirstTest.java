import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Objects;
import java.util.Set;

public class FirstTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--window-size=1920,1080");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://rafaelmanso.dev");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyPageSaysRafaelManso() {
        String pageTitle = driver.getTitle();
        Assertions.assertTrue(pageTitle.contains("Rafael Manso"), "Page title should contain 'Rafael Manso'");
    }

@Test
    public void clickContact() {
        WebElement contactButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='#contact']")));
        contactButton.click();
        
        WebElement contactSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("contact")));
        Assertions.assertNotNull(contactSection, "Contact section should be visible after clicking button");
    }

    @Test
    public void clickLinkedInButton() {
        WebElement linkedInButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='linkedin.com']")));
        linkedInButton.click();
        
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        
        wait.until(ExpectedConditions.urlContains("linkedin.com"));
        Assertions.assertTrue(driver.getCurrentUrl().contains("linkedin.com"), "Should navigate to LinkedIn");
        
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @Test
    public void clickProjectsButton() {
        WebElement projectsButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='github.com']")));
        projectsButton.click();
        
        String originalWindow = driver.getWindowHandle();
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        
        wait.until(ExpectedConditions.urlContains("github.com"));
        Assertions.assertTrue(Objects.requireNonNull(driver.getCurrentUrl()).contains("github.com/rafaeltmanso"), "Should navigate to projects");
        
        driver.close();
        driver.switchTo().window(originalWindow);
    }

    @Test
    public void clickDownloadCvButton() {
        WebElement downloadCvButton = wait.until(
            ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='drive.google.com']"))
        );
        downloadCvButton.click();
        
        String originalWindow = driver.getWindowHandle();
        Set<String> newWindows = driver.getWindowHandles();
        newWindows.remove(originalWindow);
        driver.switchTo().window(newWindows.iterator().next());
        
        String cvUrl = "https://drive.google.com/file/d/1UhO8CSvrFpQR1ag6-0qcExAH9KE8ggR0/view";
        wait.until(ExpectedConditions.urlContains(cvUrl));

        driver.close();
        driver.switchTo().window(originalWindow);
    }
}