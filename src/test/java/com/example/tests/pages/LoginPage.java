package com.example.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.By;

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

    public void enterUsername(String username) {
        System.out.println("=== Inside Login page ===");
        WebElement usernameInput = driver.findElement(By.xpath("//input[@name='username']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        usernameInput.clear();
        js.executeScript("arguments[0].value = arguments[1];", usernameInput, username);
        System.out.println("=== Entered username ===");
    }

    public void enterPassword(String password) {
        WebElement passwordInput = driver.findElement(By.xpath("//input[@name='password']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        passwordInput.clear();
        js.executeScript("arguments[0].value = arguments[1];", passwordInput, password);
        System.out.println("=== Entered password ===");
    }

    public void clickLogin() {
        loginButton.click();
    }
}
