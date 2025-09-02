package com.example.tests.steps;

import com.example.base.Config;
import com.example.base.DriverFactory;
import com.example.tests.pages.DashboardPage;
import com.example.tests.pages.LoginPage;
import com.example.tests.pages.SignupPage;
import io.cucumber.java.After;
import io.cucumber.java.en.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.net.MalformedURLException;

public class AuthSteps {

    private WebDriver driver;
    private SignupPage signupPage;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;

    private static final Logger logger = LogManager.getLogger(AuthSteps.class);

    @Given("I open the browser")
    public void iOpenTheBrowser() throws MalformedURLException {
        driver = DriverFactory.getDriver();
        driver.get(Config.getBaseUrl());
//        driver.get(System.getProperty("baseUrl","http://app:8080/login"));
        logger.info("Browser opened");
    }

    @When("I navigate to the signup page")
    public void iNavigateToTheSignupPage() {
        driver.get(Config.getBaseUrl() + "/signup");
        signupPage = new SignupPage(driver);
        logger.info("Signup page opened");
    }

    @And("I sign up with username {string} and password {string}")
    public void iSignUp(String username, String password) {
        signupPage.enterUsername(username);
        signupPage.enterPassword(password);
        signupPage.enterEmail("test@email.com");
        signupPage.clickSignup();
        logger.info("Signup successful");
    }

    @When("I navigate to the login page")
    public void iNavigateToTheLoginPage() {
        driver.get(Config.getBaseUrl() + "/login");
        loginPage = new LoginPage(driver);
        logger.info("Login page opened");
    }

    @And("I login with username {string} and password {string}")
    public void iLogin(String username, String password) throws InterruptedException {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLogin();
        dashboardPage = new DashboardPage(driver);
        logger.info("Login successful");
    }

    @Then("I should see the dashboard with username {string}")
    public void iShouldSeeTheDashboard(String expectedUser) {
        logger.info("Username text verified: " + dashboardPage.getWelcomeText(expectedUser));
        Assert.assertTrue(dashboardPage.getWelcomeText(expectedUser).contains(expectedUser));
        driver.quit();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            DriverFactory.quitDriver();
            driver = null;
        }
    }
}
