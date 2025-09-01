package com.example.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(name = "username")
    private WebElement usernameInput;

    @FindBy(name = "password")
    private WebElement passwordInput;

    @FindBy(xpath = "//button[text()='Login']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void enterUsername(String username) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(200));
        wait.until(ExpectedConditions.visibilityOf(usernameInput));
        usernameInput.clear();
        usernameInput.sendKeys(username);
    }

    public void enterPassword(String password) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30), Duration.ofMillis(200));
        wait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.clear();
        passwordInput.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }
}
