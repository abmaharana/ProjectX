package com.example.tests.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//span[contains(text(), 'testuser')]")
    private WebElement welcomeHeader;

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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeHeader(username)));
        return welcomeHeader.getText();
    }
}
