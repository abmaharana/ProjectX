package com.example.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@name='username']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) throws InterruptedException {
        System.out.println("=== Inside Login page ===");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(200));
        wait.until(ExpectedConditions.visibilityOf(usernameInput));
        usernameInput.clear();
        Thread.sleep(5000);
        // usernameInput.sendKeys(username);
        WebElement usernameInput = driver.findElement(By.xpath("//input[@name='username']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", usernameInput, username);
        System.out.println("=== Entered username ===");
    }

    public void enterPassword(String password) {
          passwordInput.clear();
//        passwordInput.sendKeys(password);
        WebElement passwordInput = driver.findElement(By.xpath("//input[@name='password']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].value = arguments[1];", passwordInput, password);
        System.out.println("=== Entered password ===");
    }

    public void clickLogin() {
        loginButton.click();
    }
}
