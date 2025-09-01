package com.example.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "//span")
    private WebElement welcomeHeader;

    @FindBy(xpath = "//button[text()='Logout']")
    private WebElement logoutButton;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getWelcomeText() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(driver -> welcomeHeader.isDisplayed());
        return welcomeHeader.getText();
    }
}
