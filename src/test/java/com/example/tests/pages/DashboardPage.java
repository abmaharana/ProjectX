package com.example.tests.pages;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class DashboardPage extends BasePage {

    // @FindBy(xpath = "//span[contains(text(), 'testuser')]")
    // private WebElement welcomeHeader;

    @FindBy(xpath = "//button[text()='Logout']")
    private WebElement logoutButton;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    // Dynamic locator for welcome header
    private By welcomeHeader(String username) {
        return By.xpath("//span[contains(text(), '" + username + "')]");
    }

    public String getWelcomeText(String username) {
         // Debug: Check if element exists in DOM
         List<WebElement> elements = driver.findElements(welcomeHeader(username));
         if (elements.isEmpty()) {
         System.out.println("Element not found in DOM for username: " + username);
         } else {
         System.out.println("Element found but may not be visible.");
         }
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(200));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeHeader(username)));
        return element.getText();
        }catch (TimeoutException e) {
           // Take screenshot
           File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
           try {
               FileUtils.copyFile(screenshot, new File("screenshot-failure-" + System.currentTimeMillis() + ".png"));
           } catch (IOException ioException) {
               ioException.printStackTrace();
           }
           throw e; // Rethrow to fail the test
       }
    }
    
}
